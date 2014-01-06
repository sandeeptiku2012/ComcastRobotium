package com.comcast.xidio.testSuite.apiTestCases;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.comcast.xidio.testCases.details.header.XidioHeaderImageAndTextTest;
import com.comcast.xidio.testCases.details.header.XidioHeaderOnFocusTest;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInFeatureEpisode;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInHomeFeatureShowTest;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInHomeFeaturedChannel;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInHomePopularChannelEpisode;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInHomePopularEpisode;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInPopularChannel;
import com.comcast.xidio.testCases.details.header.XidioHeaderVisibilityInPopularShow;
import com.comcast.xidio.testCases.details.header.XidioHomeTextPositionTest;
import com.comcast.xidio.testCases.details.header.search.XidioSearchHeaderVisibilityChannelEpisode;
import com.comcast.xidio.testCases.details.header.search.XidioSearchHeaderVisibilityInChannel;
import com.comcast.xidio.testCases.details.header.search.XidioSearchHeaderVisibilityInEpisode;
import com.comcast.xidio.testCases.details.header.search.XidioSearchHeaderVisibilityinShow;
import com.comcast.xidio.testCases.details.header.subscription.XidioSubscriptionHeaderVisibilityInChannelEpisodeTest;
import com.comcast.xidio.testCases.details.header.subscription.XidioSubscriptionHeaderVisibilityInChannelTest;
import com.comcast.xidio.testCases.details.header.subscription.XidioSubscriptionHeaderVisibilityInEpisodeTest;
import com.comcast.xidio.testCases.details.header.subscription.XidioSubscriptionHeaderVisibilityInShowTest;
import com.comcast.xidio.testCases.navigationBar.XidioNavigationLeftToRightTest;
import com.comcast.xidio.testCases.navigationBar.XidioNavigationRightToLeftTest;

public class NavigationBarTestCases extends TestSuite {
	public static Test suite() 
	{

		//return null;//new TestSuiteBuilder(NavigationBarTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.navogationBar" }).build();
		TestSuite testSuite = new TestSuite(XidioHeaderImageAndTextTest.class);
		testSuite.addTestSuite(XidioHeaderOnFocusTest.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInFeatureEpisode.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInHomeFeaturedChannel.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInHomeFeatureShowTest.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInHomePopularChannelEpisode.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInHomePopularEpisode.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInPopularChannel.class);
		testSuite.addTestSuite(XidioHeaderVisibilityInPopularShow.class);
		testSuite.addTestSuite(XidioHomeTextPositionTest.class);
		
		
		//SEARCH
		testSuite.addTestSuite(XidioSearchHeaderVisibilityChannelEpisode.class);
		testSuite.addTestSuite(XidioSearchHeaderVisibilityInChannel.class);
		testSuite.addTestSuite(XidioSearchHeaderVisibilityInEpisode.class);
		testSuite.addTestSuite(XidioSearchHeaderVisibilityinShow.class);
		
		//SUBSCRIPTION
		testSuite.addTestSuite(XidioSubscriptionHeaderVisibilityInChannelEpisodeTest.class);
		testSuite.addTestSuite(XidioSubscriptionHeaderVisibilityInChannelTest.class);
		testSuite.addTestSuite(XidioSubscriptionHeaderVisibilityInEpisodeTest.class);
		testSuite.addTestSuite(XidioSubscriptionHeaderVisibilityInShowTest.class);
		
		//NavigationBar
		
		testSuite.addTestSuite(XidioNavigationLeftToRightTest.class);
		testSuite.addTestSuite(XidioNavigationRightToLeftTest.class);
		
		
		return testSuite;
	}
}
