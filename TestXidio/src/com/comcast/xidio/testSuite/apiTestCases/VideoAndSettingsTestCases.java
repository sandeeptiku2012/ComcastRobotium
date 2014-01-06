package com.comcast.xidio.testSuite.apiTestCases;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.comcast.xidio.testCases.settings.XidioSettingsClearCacheButton;
import com.comcast.xidio.testCases.settings.XidioSettingsDisplayButtonTest;
import com.comcast.xidio.testCases.settings.XidioSettingsInformationTest;
import com.comcast.xidio.testCases.settings.XidioSettingsLabelCheckTest;
import com.comcast.xidio.testCases.settings.XidioSettingsNetworkButtonTest;
import com.comcast.xidio.testCases.video.XidioAdDurationCheck;
import com.comcast.xidio.testCases.video.XidioHomeActivityVideoPauseCheck;
import com.comcast.xidio.testCases.video.XidioHomeActivityVideoPlayCheck;
import com.comcast.xidio.testCases.video.XidioSearchPopularVideoForwardTest;
import com.comcast.xidio.testCases.video.XidioSearchPopularVideoPlayPauseTest;
import com.comcast.xidio.testCases.video.XidioSearchPopularVideoRewindTest;
import com.comcast.xidio.testCases.video.XidioSubscriptionVideoForwardTest;
import com.comcast.xidio.testCases.video.XidioSubscriptionVideoPlayPauseTest;
import com.comcast.xidio.testCases.video.XidioSubscriptionVideoRewindTest;
import com.comcast.xidio.testCases.video.XidioVideoDurationCheck;
import com.comcast.xidio.testCases.video.XidioVideoFastForward;
import com.comcast.xidio.testCases.video.XidioVideoMultiFastForward;
import com.comcast.xidio.testCases.video.XidioVideoMultiRewind;
import com.comcast.xidio.testCases.video.XidioVideoPlayAgainTest;
import com.comcast.xidio.testCases.video.XidioVideoRewind;

public class VideoAndSettingsTestCases extends TestSuite 
{
	public static Test suite() 
	{
		//return new TestSuiteBuilder(SubscriptionTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases" }).build();
		
		//Settings
		
		TestSuite testSuite = new TestSuite(XidioSettingsInformationTest.class);
		testSuite.addTestSuite(XidioSettingsClearCacheButton.class);
		testSuite.addTestSuite(XidioSettingsDisplayButtonTest.class);
		testSuite.addTestSuite(XidioSettingsLabelCheckTest.class);
		testSuite.addTestSuite(XidioSettingsNetworkButtonTest.class);
		 
		 //Video
		 
		testSuite.addTestSuite(XidioAdDurationCheck.class);
		testSuite.addTestSuite(XidioHomeActivityVideoPauseCheck.class);
		testSuite.addTestSuite(XidioHomeActivityVideoPlayCheck.class);
		testSuite.addTestSuite(XidioSearchPopularVideoForwardTest.class);
		testSuite.addTestSuite(XidioSearchPopularVideoPlayPauseTest.class);
		testSuite.addTestSuite(XidioSearchPopularVideoRewindTest.class);
		testSuite.addTestSuite(XidioSubscriptionVideoForwardTest.class);
		testSuite.addTestSuite(XidioSubscriptionVideoPlayPauseTest.class);
		testSuite.addTestSuite(XidioSubscriptionVideoRewindTest.class);
		testSuite.addTestSuite(XidioVideoDurationCheck.class);
		testSuite.addTestSuite(XidioVideoFastForward.class);
		testSuite.addTestSuite(XidioVideoMultiFastForward.class);
		testSuite.addTestSuite(XidioVideoMultiRewind.class);
		testSuite.addTestSuite(XidioVideoPlayAgainTest.class);
		testSuite.addTestSuite(XidioVideoRewind.class);
		 
		
		return testSuite;
		
		
	}
}
