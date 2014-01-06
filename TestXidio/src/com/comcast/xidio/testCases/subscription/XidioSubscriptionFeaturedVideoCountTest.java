package com.comcast.xidio.testCases.subscription;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetEpisodesList;
import com.comcast.xidio.model.GetFeaturedList;
import com.comcast.xidio.model.GetLoginResponse;
import com.comcast.xidio.model.GetShowContent;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionFeaturedVideoCountTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private String userId;	
	
	public XidioSubscriptionFeaturedVideoCountTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionFeaturedVideoCount() 
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo,  getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		JSONObject loginResponse=GetLoginResponse.getInstance().getLoginResponse(TestConstants.USERNAME,TestConstants.PASSWORD);
		JSONArray channels = null; 
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		JSONObject channelToCheck=null;
		
		JSONObject response;
		if(loginResponse.has("response"))
		try {
				response=loginResponse.getJSONObject("response");
			
			if(response.has("code"))
			if(response.getString("code").equalsIgnoreCase("AUTHENTICATION_OK"))
				{
					 userId =response.getString("userId");
					channels=GetSubscriptionList.getInstance().getSubscriptionList(userId);
				}
			} 
			catch (JSONException e) 
			{
				Log.e("test_Subscrption_Title", e.getLocalizedMessage());
			}
		
		try
		{
			JSONArray featuredList=GetFeaturedList.getInstance().getFeaturedList();
			
			for(int i=0;i<featuredList.length();i++)
			{
				
				JSONObject currChoice=featuredList.getJSONObject(i);
					if(currChoice.has("category"))
						if(currChoice.getJSONObject("category").has("level"))
							if(currChoice.getJSONObject("category").getString("level").trim().contentEquals("SHOW"))
							{
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								solo.waitForActivity(TestConstants.DETAILS_ACTIVITY);
								solo.sleep(TestConstants.SLEEP_TIME_1000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
								if(solo.searchText(TestConstants.UNSUBSCRIBE))
								{
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									solo.sendKey(KeyEvent.KEYCODE_BACK);
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									continue;
								}
								solo.sleep(TestConstants.SLEEP_TIME_1000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								solo.sleep(TestConstants.SLEEP_TIME_5000);
								
								
								
																						
								channelToCheck=currChoice;
								break;								
							}
					if(!currChoice.has("productGroup"))
							solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					
				}	
		
		}
		catch (JSONException e) 
		{
			Log.e("Exception:", "Exception occured in XidioSubscriptionFeaturedVideoCount test cases.", e);
		}
		
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_BACK);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		channels=GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());	
		JSONObject currChannel;	
		if(channels!=null){
		for(int i=0;i<channels.length();i++)
		{	
			try {
				currChannel=channels.getJSONObject(i);
				if(channelToCheck.getString("title").equals(currChannel.getString("title").trim())){
				String subsContentKey  = currChannel.getString("@id");
				String channelContentKey = channelToCheck.getString("contentKey");
				JSONArray channelShowContent = GetShowContent.getInstance().getShowContent(channelContentKey);
				JSONArray subsShowContent = GetShowContent.getInstance().getShowContent(subsContentKey);
				assertTrue(channelShowContent.length()==subsShowContent.length());
				
				for(int k=0;k<channelShowContent.length();k++){
					String showId = channelShowContent.getJSONObject(k).getString("@id");
					String subscribedShowId = subsShowContent.getJSONObject(k).getString("@id");
					JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
					JSONArray subscribedepisodeArray = GetEpisodesList.getInstance().getEpisodeList(subscribedShowId);
					if(episodeListArray.length()==subscribedepisodeArray.length())
						assertTrue(true);
					else
						assertTrue(false);
				}
				break;
				}
				else if(i==(channels.length()-1)){
					assertFalse(true);
				}
				else
					continue;
			} catch (JSONException e) {
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test.", e);
			} catch (Exception e) {
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test.", e);
			}
		}
		}
		else{
			assertTrue(false);
		}

				
	}
	
	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
