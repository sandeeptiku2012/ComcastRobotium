package com.comcast.xidio.testCases.loginScreen;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.EditText;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;

public class XidioLoginDigitUsername extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;

	public XidioLoginDigitUsername() {
		super(FirstRun.class);

	}

	@Override
	protected void setUp() throws Exception {
		GetSolo.getInstance().setUpSolo(getInstrumentation(), getActivity());
		solo = GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	@SuppressLint("DefaultLocale")
	public void testXidioLoginDigitUsername()
	{
		
		assertTrue(solo.waitForActivity(TestConstants.FIRST_RUN));
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		
			
			solo.enterText((EditText) solo.getCurrentActivity().findViewById(com.xfinity.xidio.R.id.first_run_username),TestConstants.DIGIT_USERNAME);
			solo.enterText((EditText) solo.getCurrentActivity().findViewById(com.xfinity.xidio.R.id.first_run_password),TestConstants.PASSWORD);
			solo.sleep(TestConstants.SLEEP_TIME_500);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			assertTrue(solo.searchText("Error, check username/password and try again"));
			assertTrue(solo.getView(R.id.first_run_error_text).isShown());
			solo.sleep(TestConstants.SLEEP_TIME_1000);

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
