package com.comcast.xidio.testCases.details.header;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.View;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;

public class XidioHeaderOnFocusTest extends ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;

	public XidioHeaderOnFocusTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception 
	{
		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
		
	}

	public void testHomeButtonFocus()
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		assertTrue(solo.searchText(TestConstants.HOME));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		View home = solo.getView(R.id.header_home_button);
		assertTrue(home.isFocused());
		
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.searchText(TestConstants.SEARCH));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		//View search = solo.getView(R.id.header_search_button);
		View search = solo.getView(R.id.header_search_text);
		assertTrue(search.isFocused());
		
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.searchText(TestConstants.SUBSCRIPTIONS));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		View subscription = solo.getView(R.id.header_subscription_button);
		assertTrue(subscription.isFocused());
		
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.searchText(TestConstants.SETTINGS));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		View settings = solo.getView(R.id.header_settings_button);
		assertTrue(settings.isFocused());
		
		
	
	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
