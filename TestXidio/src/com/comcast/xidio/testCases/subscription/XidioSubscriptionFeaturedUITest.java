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
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionFeaturedUITest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private String userId;
	
	
	public XidioSubscriptionFeaturedUITest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionFeaturedUITest() 
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
		JSONArray channels = null; 
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		JSONObject channelToCheck=null;
		
	
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
			Log.e("Exception:", "Exception occured in XidioSubscriptionFeaturedApiTest test cases.", e);
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
		channels=GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());	
		JSONObject currChannel;		
		
		if(channels.length()==0 ||channels==null)
			assertTrue(true);
		
		for(int i=0;i<channels.length();i++)
		{	
			try {
				currChannel=channels.getJSONObject(i);
				assertTrue(solo.searchText(currChannel.getString("title").trim()));
			} catch (JSONException e) {
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test.", e);
			}
				
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			
		}
		
		
		boolean foundChannel = false;
		try
		{
			for(int i=0;i<channels.length();i++)
			{
				currChannel = channels.getJSONObject(i);
				
				if(currChannel.getString("title").trim().contentEquals(channelToCheck.getString("title").trim()))
				{
					foundChannel=true;
					break;
				}
				
			}
		} 
		catch (JSONException e)
		{
			
			Log.e("Exception:", "Exception occured in XidioSubscriptionFeaturedApiTest in get channel title.", e);
		}
		
		assertTrue(foundChannel);
	}
	
	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
