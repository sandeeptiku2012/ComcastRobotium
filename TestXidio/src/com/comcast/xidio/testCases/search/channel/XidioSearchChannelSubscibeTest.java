package com.comcast.xidio.testCases.search.channel;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.FilterObject;
import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetChannelSearchList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSearchChannelSubscibeTest extends ActivityInstrumentationTestCase2<FirstRun> 
{
	private Solo solo;


	public XidioSearchChannelSubscibeTest() {
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

	public void testXidioSearchChannelSubscibe() 
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
		for (JSONObject currentObject : filteredChannelAndShows) {
			try {
				if(currentObject.get("level").equals("SHOW")){
					assertTrue(solo.searchText(currentObject.getString(TestConstants.TITLE).toString()));
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					if(solo.searchText(TestConstants.SUBSCRIBE)){
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						assertTrue(solo.waitForText("order finalized (complete"));
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						assertTrue(solo.searchText(TestConstants.UNSUBSCRIBE));
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						assertTrue(solo.waitForText("Order successfully removed"));
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
					}
					else if(solo.searchText(TestConstants.UNSUBSCRIBE)){
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						assertTrue(solo.waitForText("Order successfully removed"));
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						assertTrue(solo.waitForText("order finalized (complete"));
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						assertTrue(solo.searchText(TestConstants.UNSUBSCRIBE));

					}
					else{
						assertTrue(false);
					}
				}
				else{
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					continue;
				}
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_1000);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				solo.sleep(TestConstants.SLEEP_TIME_1000);

			} catch (JSONException e) {
				Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioSearchChannelSubscibe :" , e);
			}
		}

	}
	
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}