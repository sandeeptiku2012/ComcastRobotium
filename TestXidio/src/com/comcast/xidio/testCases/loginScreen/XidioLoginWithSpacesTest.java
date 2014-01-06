package com.comcast.xidio.testCases.loginScreen;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;

public class XidioLoginWithSpacesTest extends ActivityInstrumentationTestCase2<FirstRun> {
	
	private Solo solo;
	
	public XidioLoginWithSpacesTest() {
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

	public void testXidioUsernameWithSpaces()
	{
		String[] username={"   ","tes t_153","test_153  "};
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		for(int i=0;i<3;i++){
			solo.clearEditText(0);
			solo.clearEditText(1);
		solo.enterText(0, username[i]);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.enterText(1, "Demo1111");
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(solo.ENTER);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(solo.ENTER);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		assertFalse(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		assertFalse(solo.searchText("HOME"));
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		}

	}
	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

}
