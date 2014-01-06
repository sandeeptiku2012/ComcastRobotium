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

public class XidioHomeTextPositionTest extends ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;

	public XidioHomeTextPositionTest() {
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

	public void testHomeTextPosition()
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.searchText(TestConstants.HOME));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		View button = solo.getView(R.id.header_home_button);
		View homeText = solo.getView(R.id.header_home_text);
		int buttonLeftPos = button.getLeft();
		int textLeftPos = homeText.getLeft();
		assertTrue(buttonLeftPos < textLeftPos);
	
	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
