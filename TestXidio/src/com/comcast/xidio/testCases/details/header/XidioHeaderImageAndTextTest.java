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

public class XidioHeaderImageAndTextTest extends
		ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;

	public XidioHeaderImageAndTextTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {
		GetSolo.getInstance().setUpSolo(getInstrumentation(), getActivity());
		solo = GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();

	}

	public void testHeaderImageAndText() {
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
		solo.sleep(TestConstants.SLEEP_TIME_5000);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.searchText(TestConstants.HOME));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.searchText(TestConstants.SEARCH));
		View homebutton = solo.getView(R.id.header_home_button);
		View homeText = solo.getView(R.id.header_home_text);
		View searchButton = solo.getView(R.id.header_search_button);
		View searchText = solo.getView(R.id.header_search_text);
		View subscriptionButton = solo.getView(R.id.header_subscription_button);
		View subscriptionText = solo.getView(R.id.header_subscription_text);
		View settingsButton = solo.getView(R.id.header_settings_button);
		View settingsText = solo.getView(R.id.header_settings_text);

		for (int i = 0; i < 4; i++) {
			if (homebutton.isFocused()) {
				assertTrue(homeText.isShown());
			} else if (searchButton.isFocused()) {
				assertTrue(searchText.isShown());
			} else if (subscriptionButton.isFocused()) {
				assertTrue(subscriptionText.isShown());
			} else if (settingsButton.isFocused()) {
				assertTrue(settingsText.isShown());
			}
			solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
		}
	}

	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
