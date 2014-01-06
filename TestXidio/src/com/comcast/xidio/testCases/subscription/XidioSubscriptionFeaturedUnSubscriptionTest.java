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
import com.comcast.xidio.model.GetFeaturedList;
import com.comcast.xidio.model.GetLoginResponse;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;

public class XidioSubscriptionFeaturedUnSubscriptionTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private String userId;
	
	
	public XidioSubscriptionFeaturedUnSubscriptionTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionFeaturedUnSubscription() 
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
								if(solo.searchText(TestConstants.SUBSCRIBE))
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
			Log.e("Exception:", "Exception occured in XidioSubscriptionFeaturedUnSubscription test cases.", e);
		}
		
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_BACK);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		channels=GetSubscriptionList.getInstance().getSubscriptionList(userId);	
		JSONObject currChannel;		
		
		if(channels==null)
			assertTrue(true);
		else{
		for(int i=0;i<channels.length();i++)
		{	
			try {
				currChannel=channels.getJSONObject(i);
				assertFalse((channelToCheck.getString("title").trim()).equalsIgnoreCase(currChannel.getString("title").trim()));
			} catch (JSONException e) {
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test.", e);
			}
				
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			
		}
	}	
		
	}
	
	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
