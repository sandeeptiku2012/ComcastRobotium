package com.comcast.xidio.testCases.details.header.search;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetChannelSearchList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;

public class XidioSearchHeaderVisibilityInChannel extends ActivityInstrumentationTestCase2<FirstRun> 
{
	private Solo solo;


	public XidioSearchHeaderVisibilityInChannel() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testSearchHeaderVisibilityInChannel() 
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_C);
		solo.sendKey(KeyEvent.KEYCODE_N);
		solo.sendKey(KeyEvent.KEYCODE_N);
		ArrayList<JSONObject> filteredChannelAndShows = GetChannelSearchList.getInstance().getChannelSearchList("cnn");
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		
		
		View homebutton = solo.getView(R.id.header_home_button);
		View homeText = solo.getView(R.id.header_home_text);
		View searchButton = solo.getView(R.id.header_search_button);
		View searchText = solo.getView(R.id.header_search_text);
		View subscriptionButton = solo.getView(R.id.header_subscription_button);
		View subscriptionText = solo.getView(R.id.header_subscription_text);
		View settingsButton = solo.getView(R.id.header_settings_button);
		View settingsText = solo.getView(R.id.header_settings_text);
		
		
		for (JSONObject currentObject : filteredChannelAndShows) {
			try {
				if(currentObject.get("level").equals("SHOW")){
					assertTrue(solo.searchText(currentObject.getString(TestConstants.TITLE).toString()));
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					assertFalse(homebutton.isShown());
					assertFalse(homeText.isShown());
					assertFalse(searchButton.isShown());
					assertFalse(searchText.isShown());
					assertFalse(subscriptionButton.isShown());
					assertFalse(subscriptionText.isShown());
					assertFalse(settingsButton.isShown());
					assertFalse(settingsText.isShown());
					break;

					}
					
				else
				{
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					continue;
				}
				
			} catch (JSONException e) {
				Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioSearchPopularResult :" , e);
			}
		}

	}
	
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
