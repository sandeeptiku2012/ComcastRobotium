package com.comcast.xidio.testCases.subscription.channel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.comcast.cil.scalerow.ContentRowGrid;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetShowContent;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionUnsubscribeTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject response;
	private JSONObject currChannel;
	String userId;
	
	public XidioSubscriptionUnsubscribeTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionUnsubscribe() 
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
		
		
		JSONArray subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser()); 
		
		
		
					
					
		
		if(subscribedChannels==null || subscribedChannels.length()==0)
		{
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_BACK);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
			subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());
			
		}
		
			
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		assertTrue(solo.searchText(TestConstants.SUBSCRIPTIONS));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		String title=null;
		for(int i=0;i<subscribedChannels.length();i++)
		{
			try {
				JSONObject currentElement = subscribedChannels.getJSONObject(i);
				
				
				
					if(currentElement.getString("level").contentEquals("SHOW"))
					{	 
						title = currentElement.getString("title");
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						assertTrue(solo.searchText(TestConstants.UNSUBSCRIBE));
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						
						break;
						
					
				
			}
			
		 } catch (JSONException e) {
				
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeApiCheck test", e);
			}
		}
		JSONArray currentSubscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());
		for(int i=0;i<currentSubscribedChannels.length();i++)
		{
			try {
				JSONObject currentElement = currentSubscribedChannels.getJSONObject(i);
			
					if(currentElement.getString("level").contentEquals("SHOW"))
					{	 
						solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						assertFalse(solo.searchText(title));
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
			}
			
		 } catch (JSONException e) {
				
				Log.e("Exception:", "Exception occured in XidioSubscriptionUnsubscribeApiCheck test", e);
			}
		}
		solo.sendKey(KeyEvent.KEYCODE_BACK);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
		
		
	}

	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
	
}
