package com.comcast.xidio.testCases.search.activity;

import java.util.ArrayList;

import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.FilterObject;
import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetEpisodeSearchList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSearchActivityLabelsTest extends ActivityInstrumentationTestCase2<FirstRun> 
{
	private Solo solo;

	public XidioSearchActivityLabelsTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception
	{
		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		GetCatagoryLists.getInstance().storeBasicLists(XidioApplication.getLastLoggedInUser(), XidioApplication.getLastSessionId());
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSearchActivityLabels() {

		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_BACK);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		ArrayList<JSONObject> episodeSearchItems, featuredSearchItems, upnextSearchItems, popularSearchItems;
		String filterText = "abr";
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_A);
		solo.sendKey(KeyEvent.KEYCODE_B);
		solo.sendKey(KeyEvent.KEYCODE_R);
		
		episodeSearchItems = FilterObject.getInstance().getFilteredObjectList(GetEpisodeSearchList.getInstance().getEpisodeSearchList(filterText), filterText);
		featuredSearchItems = FilterObject.getInstance().getFilteredObjectList(GetCatagoryLists.getInstance().getFeaturedList(), filterText);
		popularSearchItems = FilterObject.getInstance().getFilteredObjectList(GetCatagoryLists.getInstance().getPopularList(), filterText);
		upnextSearchItems = FilterObject.getInstance().getFilteredObjectList(GetCatagoryLists.getInstance().getUpNextList(), filterText);
		
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		
		if (upnextSearchItems!=null && upnextSearchItems.size() != 0)
		{
			assertTrue(solo.waitForText(TestConstants.UP_NEXT));
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		}
		solo.sleep(TestConstants.SLEEP_TIME_500);
		if (featuredSearchItems.size() != 0) {
			assertTrue(solo.waitForText(TestConstants.FEATURE));
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);

		}
		solo.sleep(TestConstants.SLEEP_TIME_500);
		if (popularSearchItems.size() != 0) {
			assertTrue(solo.waitForText(TestConstants.POPULAR));
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		}
		if (episodeSearchItems.size() != 0) {
			assertTrue(solo.waitForText(TestConstants.MATCHING_EPISODES));
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		}
		solo.sleep(TestConstants.SLEEP_TIME_2000);

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
