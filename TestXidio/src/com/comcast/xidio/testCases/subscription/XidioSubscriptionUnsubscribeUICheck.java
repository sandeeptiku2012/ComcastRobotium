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

public class XidioSubscriptionUnsubscribeUICheck extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject response;
	private JSONObject currChannel;
	private String userId;	
	
	public XidioSubscriptionUnsubscribeUICheck() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionUnsubscribeUICheck() 
	{

		//passing through the first Run Activity
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo,  getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		
		//starting the test main Activity
		
		JSONObject loginResponse=GetLoginResponse.getInstance().getLoginResponse(TestConstants.USERNAME,TestConstants.PASSWORD);
		JSONArray subscribedChannels = null; 
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		assertTrue(solo.searchText(TestConstants.SUBSCRIPTIONS));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		if(loginResponse.has("response"))
			try {
					response=loginResponse.getJSONObject("response");
				
				if(response.has("code"))
				if(response.getString("code").equalsIgnoreCase("AUTHENTICATION_OK"))
					{
						userId =response.getString("userId");
						subscribedChannels=GetSubscriptionList.getInstance().getSubscriptionList(userId);
					}
				} 
				catch (JSONException e) 
				{
					Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test", e);
				}
		
		JSONObject channelToCheck=null;
		
		if(subscribedChannels.length()==0 || subscribedChannels==null)
			assertTrue(false);
		
			try {
				channelToCheck=subscribedChannels.getJSONObject(0);
			} catch (JSONException e) {
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test", e);
			}
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			
			if(solo.searchText(TestConstants.UNSUBSCRIBE)){
				solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
				assertTrue(solo.waitForText("Order successfully removed"));
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
				solo.sleep(TestConstants.SLEEP_TIME_5000);
			}
			else
			{
				assertTrue(false);
				}
			subscribedChannels=GetSubscriptionList.getInstance().getSubscriptionList(userId);
			solo.sendKey(KeyEvent.KEYCODE_BACK);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			if(subscribedChannels.length()==0 || subscribedChannels==null)
				assertTrue(true);
			
			for(int i=0;i<subscribedChannels.length();i++)
			{	try {
				currChannel=subscribedChannels.getJSONObject(i);
				assertTrue(solo.searchText(currChannel.getString("title")));
			} catch (JSONException e) {
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeUICheck test.", e);
			}
				
				solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				
			}
			
		
		
		
	
	}

	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
	
}
