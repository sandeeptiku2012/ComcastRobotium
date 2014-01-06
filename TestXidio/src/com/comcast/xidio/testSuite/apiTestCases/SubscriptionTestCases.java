package com.comcast.xidio.testSuite.apiTestCases;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.comcast.xidio.testCases.subscription.XidioSubscriptionButtonLabelChangeTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionChannelHighlightTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionChannelHighlightedContentTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionEpisodeHighlightTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionEpisodeSubcriptionTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionFeaturedApiTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionFeaturedShowCountTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionFeaturedUITest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionFeaturedUnSubscriptionTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionFeaturedVideoCountTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionPageTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionPopularApiTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionPopularLabelTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionPopularShowCountTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionPopularUITest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionPopularVideoCountTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionShowHighlightTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionShowSubcriptionTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionUnsubscribeApiCheck;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionUnsubscribeUICheck;
import com.comcast.xidio.testCases.subscription.XidioSubscritptionPopularUnsubscriptionTest;
import com.comcast.xidio.testCases.subscription.channel.XidioSubscriptionChannelActivity;
import com.comcast.xidio.testCases.subscription.channel.XidioSubscriptionChannelEpisodeActivity;
import com.comcast.xidio.testCases.subscription.channel.XidioSubscriptionChannelShowCountTest;
import com.comcast.xidio.testCases.subscription.channel.XidioSubscriptionEpisodeActivity;
import com.comcast.xidio.testCases.subscription.channel.XidioSubscriptionShowActivity;
import com.comcast.xidio.testCases.subscription.channel.XidioSubscriptionUnsubscribeTest;
import com.comcast.xidio.testCases.subscription.search.XidioSubscriptionSearchChannelTest;
import com.comcast.xidio.testCases.subscription.search.XidioSubscriptionSearchChannelUnsubscribedTest;
import com.comcast.xidio.testCases.subscription.search.XidioSubscriptionSearchPopularTest;
import com.comcast.xidio.testCases.subscription.search.XidioSubscriptionSearchPopularUnsubscribedTest;

public class SubscriptionTestCases extends TestSuite 
{
	public static Test suite() 
	{
		//return new TestSuiteBuilder(SubscriptionTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases" }).build();
		TestSuite testSuite = new TestSuite(XidioSubscriptionButtonLabelChangeTest.class);
		testSuite.addTestSuite(XidioSubscriptionChannelHighlightTest.class);
		testSuite.addTestSuite(XidioSubscriptionChannelHighlightedContentTest.class);
		testSuite.addTestSuite(XidioSubscriptionEpisodeHighlightTest.class);
		testSuite.addTestSuite(XidioSubscriptionEpisodeSubcriptionTest.class);
		testSuite.addTestSuite(XidioSubscriptionFeaturedApiTest.class);
		testSuite.addTestSuite(XidioSubscriptionFeaturedShowCountTest.class);
		testSuite.addTestSuite(XidioSubscriptionFeaturedUITest.class);
		testSuite.addTestSuite(XidioSubscriptionFeaturedUnSubscriptionTest.class);
		testSuite.addTestSuite(XidioSubscriptionFeaturedVideoCountTest.class);
		testSuite.addTestSuite(XidioSubscriptionPageTest.class);
		testSuite.addTestSuite(XidioSubscriptionPopularApiTest.class);
		testSuite.addTestSuite(XidioSubscriptionPopularShowCountTest.class);
		testSuite.addTestSuite(XidioSubscriptionPopularUITest.class);
		testSuite.addTestSuite(XidioSubscriptionPopularVideoCountTest.class);
		testSuite.addTestSuite(XidioSubscriptionShowHighlightTest.class);
		testSuite.addTestSuite(XidioSubscriptionShowSubcriptionTest.class);
		testSuite.addTestSuite(XidioSubscriptionUnsubscribeApiCheck.class);
		testSuite.addTestSuite(XidioSubscriptionUnsubscribeUICheck.class);
		testSuite.addTestSuite(XidioSubscritptionPopularUnsubscriptionTest.class);
		testSuite.addTestSuite(XidioSubscriptionPopularLabelTest.class);
		
		
		//Channel
		
		testSuite.addTestSuite(XidioSubscriptionChannelActivity.class);
		testSuite.addTestSuite(XidioSubscriptionChannelEpisodeActivity.class);
		testSuite.addTestSuite(XidioSubscriptionChannelShowCountTest.class);
		testSuite.addTestSuite(XidioSubscriptionEpisodeActivity.class);
		testSuite.addTestSuite(XidioSubscriptionShowActivity.class);
		testSuite.addTestSuite(XidioSubscriptionUnsubscribeTest.class);
		
		
		//search
		
		testSuite.addTestSuite(XidioSubscriptionSearchChannelTest.class);
		testSuite.addTestSuite(XidioSubscriptionSearchChannelUnsubscribedTest.class);
		testSuite.addTestSuite(XidioSubscriptionSearchPopularTest.class);
		testSuite.addTestSuite(XidioSubscriptionSearchPopularUnsubscribedTest.class);
		 
		
		return testSuite;
		
		
	}
}
