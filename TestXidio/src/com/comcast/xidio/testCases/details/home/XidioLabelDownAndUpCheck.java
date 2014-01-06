package com.comcast.xidio.testCases.details.home;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioLabelDownAndUpCheck extends ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;

	public XidioLabelDownAndUpCheck() {
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

	public void testLabelDownAndUpCheck()
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		//solo.sleep(TestConstants.SLEEP_TIME_2000);
		//assertTrue(solo.searchText(TestConstants.UP_NEXT));
		//solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		assertTrue(solo.searchText(TestConstants.FEATURE));
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		assertTrue(solo.searchText(TestConstants.POPULAR));
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		//solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		assertTrue(solo.searchText(TestConstants.FEATURE));
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
	//	assertTrue(solo.searchText(TestConstants.UP_NEXT));
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		assertTrue(solo.searchText(TestConstants.HOME));
	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}

