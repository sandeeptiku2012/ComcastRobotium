package com.comcast.xidio.testSuite.loginSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.comcast.xidio.testCases.loginScreen.XidioLoginActivityChangeNeg;
import com.comcast.xidio.testCases.loginScreen.XidioLoginActivityChangePos;
import com.comcast.xidio.testCases.loginScreen.XidioLoginDigitUsername;
import com.comcast.xidio.testCases.loginScreen.XidioLoginEmptyPassword;
import com.comcast.xidio.testCases.loginScreen.XidioLoginEmptyUserName;
import com.comcast.xidio.testCases.loginScreen.XidioLoginEmptyUserNameAndPassword;
import com.comcast.xidio.testCases.loginScreen.XidioLoginErrorMessageNeg;
import com.comcast.xidio.testCases.loginScreen.XidioLoginErrorMessagePos;
import com.comcast.xidio.testCases.loginScreen.XidioLoginInvalidPassword;
import com.comcast.xidio.testCases.loginScreen.XidioLoginInvalidUsername;
import com.comcast.xidio.testCases.loginScreen.XidioLoginPageTest;
import com.comcast.xidio.testCases.loginScreen.XidioLoginSpecialCharecterInBoth;
import com.comcast.xidio.testCases.loginScreen.XidioLoginSpecialCharecterInPassword;
import com.comcast.xidio.testCases.loginScreen.XidioLoginSpecialCharecterInUsername;
import com.comcast.xidio.testCases.loginScreen.XidioLoginWithSpacesTest;
import com.comcast.xidio.testCases.loginScreen.XidioSilentLoginTest;

public class LoginTestCases extends TestSuite {
	public static Test suite()
	{
		//return null;
		//return new TestSuiteBuilder(LoginTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.loginScreen", "com.comcast.xideo.testCases.Authentication"}).build();
		TestSuite testSuite = new TestSuite(XidioLoginActivityChangeNeg.class);
		testSuite.addTestSuite(XidioLoginActivityChangePos.class);
		testSuite.addTestSuite(XidioLoginDigitUsername.class);
		testSuite.addTestSuite(XidioLoginEmptyPassword.class);
		testSuite.addTestSuite(XidioLoginEmptyUserName.class);
		testSuite.addTestSuite(XidioLoginEmptyUserNameAndPassword.class);
		testSuite.addTestSuite(XidioLoginErrorMessageNeg.class);
		testSuite.addTestSuite(XidioLoginErrorMessagePos.class);
		testSuite.addTestSuite(XidioLoginInvalidPassword.class);
		testSuite.addTestSuite(XidioLoginInvalidUsername.class);
		testSuite.addTestSuite(XidioLoginPageTest.class);
		testSuite.addTestSuite(XidioLoginSpecialCharecterInBoth.class);
		testSuite.addTestSuite(XidioLoginSpecialCharecterInUsername.class);
		testSuite.addTestSuite(XidioLoginSpecialCharecterInPassword.class);
		testSuite.addTestSuite(XidioLoginWithSpacesTest.class);
		testSuite.addTestSuite(XidioSilentLoginTest.class);
		
		return testSuite;
	}
}
