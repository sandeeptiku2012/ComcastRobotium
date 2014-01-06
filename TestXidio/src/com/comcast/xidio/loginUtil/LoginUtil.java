package com.comcast.xidio.loginUtil;

import android.app.Instrumentation;
import android.view.KeyEvent;

import com.comcast.xidio.core.constant.TestConstants;
import com.jayway.android.robotium.solo.Solo;

public class LoginUtil {
	public static void authenticateUser(Solo solo, Instrumentation instr){

//		solo.enterText(0, "test_153");
//		solo.sendKey(solo.ENTER);
//		solo.sleep(TestConstants.SLEEP_TIME_1000);
//		solo.enterText(1, "Demo1111");
//		solo.sleep(TestConstants.SLEEP_TIME_1000);
//
//		solo.sendKey(solo.ENTER);
//		solo.sleep(TestConstants.SLEEP_TIME_1000);
//		solo.sendKey(solo.ENTER);
//		solo.sleep(TestConstants.SLEEP_TIME_1000);
		
		
		
		
//		solo.enterText(0, "test_153");
		solo.sendKey(KeyEvent.KEYCODE_T);
		solo.sendKey(KeyEvent.KEYCODE_E);
		solo.sendKey(KeyEvent.KEYCODE_S);
		solo.sendKey(KeyEvent.KEYCODE_T);
		instr.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
		solo.sendKey(KeyEvent.KEYCODE_MINUS);
		instr.sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
		solo.sendKey(KeyEvent.KEYCODE_1);
		solo.sendKey(KeyEvent.KEYCODE_5);
		solo.sendKey(KeyEvent.KEYCODE_3);
		solo.sendKey(solo.ENTER);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
	

		instr.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
		solo.sendKey(KeyEvent.KEYCODE_D);
		instr.sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
		solo.sendKey(KeyEvent.KEYCODE_E);
		solo.sendKey(KeyEvent.KEYCODE_M);
		solo.sendKey(KeyEvent.KEYCODE_O);
		solo.sendKey(KeyEvent.KEYCODE_1);
		solo.sendKey(KeyEvent.KEYCODE_1);
		solo.sendKey(KeyEvent.KEYCODE_1);
		solo.sendKey(KeyEvent.KEYCODE_1);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		
		solo.sendKey(solo.ENTER);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(solo.ENTER);
		
		
	}

}
