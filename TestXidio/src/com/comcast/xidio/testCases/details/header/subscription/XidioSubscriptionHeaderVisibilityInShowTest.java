package com.comcast.xidio.testCases.details.header.subscription;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetShowContent;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionHeaderVisibilityInShowTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject currChannel;
	
	public XidioSubscriptionHeaderVisibilityInShowTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionHeaderVisibilityInShow() 
	{

		//passing through the first Run Activity
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		
		//starting the test main Activity
		
		
		JSONArray subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser()); 
		
		View homebutton = solo.getView(R.id.header_home_button);
		View homeText = solo.getView(R.id.header_home_text);
		View searchButton = solo.getView(R.id.header_search_button);
		View searchText = solo.getView(R.id.header_search_text);
		View subscriptionButton = solo.getView(R.id.header_subscription_button);
		View subscriptionText = solo.getView(R.id.header_subscription_text);
		View settingsButton = solo.getView(R.id.header_settings_button);
		View settingsText = solo.getView(R.id.header_settings_text);
		
					
					
		
		if(subscribedChannels.length()==0 || subscribedChannels==null)
		{
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			assertTrue(solo.searchText(TestConstants.SUBSCRIPTIONS));
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
		
		
		for(int i=0;i<subscribedChannels.length();i++)
		{
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			try {
				 currChannel = subscribedChannels.getJSONObject(i);
				String subsContentKey  = currChannel.getString("@id");
				JSONArray subsShowContent = GetShowContent.getInstance().getShowContent(subsContentKey);
				if(subsShowContent!=null){
					JSONObject showsList = subsShowContent.getJSONObject(0);
					String showTitle = showsList.getString(TestConstants.TITLE);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					assertTrue(solo.searchText(showTitle.toString()));
					solo.sleep(TestConstants.SLEEP_TIME_5000);
					assertFalse(homebutton.isShown());
					assertFalse(homeText.isShown());
					assertFalse(searchButton.isShown());
					assertFalse(searchText.isShown());
					assertFalse(subscriptionButton.isShown());
					assertFalse(subscriptionText.isShown());
					assertFalse(settingsButton.isShown());
					assertFalse(settingsText.isShown());
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					break;
				} 
			else{
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				continue;
				
			}
		}
				catch (JSONException e) {
				
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
