package com.comcast.xidio.testCases.search.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.MultiStateEditTextView;

public class XidioSearchActivityHintTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;

	public XidioSearchActivityHintTest() {
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

	public void testXidioSearchActivityHint() 
	{
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
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		MultiStateEditTextView searchText = (MultiStateEditTextView) solo.getCurrentActivity().findViewById(com.xfinity.xidio.R.id.header_search_text);
		assertTrue(searchText.getHint().toString().equalsIgnoreCase(TestConstants.SEARCH));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
	}

	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
