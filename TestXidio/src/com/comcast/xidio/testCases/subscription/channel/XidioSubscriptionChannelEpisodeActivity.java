package com.comcast.xidio.testCases.subscription.channel;

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
import com.comcast.xidio.model.GetShowContent;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionChannelEpisodeActivity extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject currChannel;
	
	public XidioSubscriptionChannelEpisodeActivity() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionChannelEpisodeActivity() 
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
		solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		for(int i=0;i<subscribedChannels.length();i++)
		{
			
			
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			try {
				 currChannel = subscribedChannels.getJSONObject(i);
				String subsContentKey  = currChannel.getString("@id");
				JSONArray subsShowContent = GetEpisodesList.getInstance().getEpisodeList(subsContentKey);
				if(subsShowContent!=null){
				for(int j =0;j<subsShowContent.length();j++){

					solo.sleep(TestConstants.SLEEP_TIME_2000);
					JSONObject showsList = subsShowContent.getJSONObject(j);
					String showTitle= showsList.getString("title");
					
					
					if (j == 0)
						solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					assertTrue(solo.searchText(showTitle));
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_5000);
					assertTrue(solo.searchText(showTitle));
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					//solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				
				} 
				}
			else{
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				continue;
				
			}
				break;
		}
				catch (Exception e) {
				
				Log.e("Exception:", "Exception occured in XidioSubscriptionChannelEpisodeActivity test", e);
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
