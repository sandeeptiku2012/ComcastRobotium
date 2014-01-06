package com.comcast.xidio.testCases.search.channel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetChannelSearchList;
import com.comcast.xidio.model.GetEpisodesList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSearchEpisodeInChannelSubcriptionTest extends	ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;

	public XidioSearchEpisodeInChannelSubcriptionTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {
		
		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		GetCatagoryLists.getInstance().storeBasicLists(XidioApplication.getLastLoggedInUser(), XidioApplication.getLastSessionId());
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSearchEpisodeInChannelSubcription() 
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
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_C);
		solo.sendKey(KeyEvent.KEYCODE_O);
		solo.sendKey(KeyEvent.KEYCODE_O);
		//solo.sendKey(KeyEvent.KEYCODE_D);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		
		ArrayList<JSONObject> channelSearchArray =GetChannelSearchList.getInstance().getChannelSearchList("coo");
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		
		for (JSONObject currentObject : channelSearchArray) {
			try {
				if(currentObject.get("level").equals("SHOW")){
				String channelTitle = currentObject.getString(TestConstants.TITLE);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
				assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
				solo.sleep(TestConstants.SLEEP_TIME_500);
				assertTrue(solo.searchText(channelTitle.toString()));
				String key = currentObject.getString("@id");
				JSONArray channelEpisodeContent = GetEpisodesList.getInstance().getEpisodeList(key);
				if(channelEpisodeContent!=null){
					JSONObject showsList = channelEpisodeContent.getJSONObject(0);
					String showTitle = showsList.getString(TestConstants.TITLE);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					assertTrue(solo.searchText(showTitle.toString()));
					solo.sleep(TestConstants.SLEEP_TIME_5000);
					assertFalse(solo.searchText(TestConstants.SUBSCRIBE));
					assertFalse(solo.searchText(TestConstants.UNSUBSCRIBE));
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_500);
				}
				
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				}
				
			} catch (Exception e) {
				Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioSearchEpisodeInChannelSubcription " , e);
			}
		}

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
