package com.comcast.xidio.testSuite.apiTestCases;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.comcast.xidio.testCases.search.activity.XidioSearchActivityBlankSearch;
import com.comcast.xidio.testCases.search.activity.XidioSearchActivityHintTest;
import com.comcast.xidio.testCases.search.activity.XidioSearchActivityLabelsTest;
import com.comcast.xidio.testCases.search.activity.XidioSearchActivityNegetiveSearch;
import com.comcast.xidio.testCases.search.activity.XidioSearchActivityText;
import com.comcast.xidio.testCases.search.activity.XidioSearchLeadingSpecialCharacterTest;
import com.comcast.xidio.testCases.search.activity.XidioSearchSpecialCharachterTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelEpisodeHighlightTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelEpisodeSubcriptionTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelHighlightTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelHighlightedContentTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelResult;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelResultTitleTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelShowCountTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelShowHighlightTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelShowSubscriptionTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelShowsTitleTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelSubscibeTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchChannelVideoTitleTest;
import com.comcast.xidio.testCases.search.channel.XidioSearchEpisodeInChannelSubcriptionTest;
import com.comcast.xidio.testCases.search.episodes.XidioSearchEpisodeHighlightContentTest;
import com.comcast.xidio.testCases.search.episodes.XidioSearchEpisodeResultTitleTest;
import com.comcast.xidio.testCases.search.episodes.XidioSearchEpisodesResultTest;
import com.comcast.xidio.testCases.search.featured.XidioSearchFeaturedResultTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularChannelTitleTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularEpisodeSubscriptionTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularHighlightContentTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularResultTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularShowSubcriptionTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularShowsTitleTest;
import com.comcast.xidio.testCases.search.popular.XidioSearchPopularSubscribeTest;

public class SearchTestCases extends TestSuite {
	public static Test suite() 
	{
		//return  new TestSuiteBuilder(SearchTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.subscription" }).build();

		TestSuite testSuite = new TestSuite(XidioSearchActivityBlankSearch.class);
		testSuite.addTestSuite(XidioSearchActivityHintTest.class);
		testSuite.addTestSuite(XidioSearchActivityLabelsTest.class);
		testSuite.addTestSuite(XidioSearchActivityNegetiveSearch.class);
		testSuite.addTestSuite(XidioSearchActivityText.class);
		testSuite.addTestSuite(XidioSearchLeadingSpecialCharacterTest.class);
		testSuite.addTestSuite(XidioSearchSpecialCharachterTest.class);


		//Channel

		testSuite.addTestSuite(XidioSearchChannelEpisodeHighlightTest.class);
		testSuite.addTestSuite(XidioSearchChannelEpisodeSubcriptionTest.class);
		testSuite.addTestSuite(XidioSearchChannelHighlightedContentTest.class);
		testSuite.addTestSuite(XidioSearchChannelHighlightTest.class);
		testSuite.addTestSuite(XidioSearchChannelResult.class);
		testSuite.addTestSuite(XidioSearchChannelResultTitleTest.class);
		testSuite.addTestSuite(XidioSearchChannelShowCountTest.class);
		testSuite.addTestSuite(XidioSearchChannelShowHighlightTest.class);
		testSuite.addTestSuite(XidioSearchChannelShowsTitleTest.class);
		testSuite.addTestSuite(XidioSearchChannelShowSubscriptionTest.class);
		testSuite.addTestSuite(XidioSearchChannelSubscibeTest.class);
		testSuite.addTestSuite(XidioSearchChannelVideoTitleTest.class);
		testSuite.addTestSuite(XidioSearchEpisodeInChannelSubcriptionTest.class);


		//Episodes

		testSuite.addTestSuite(XidioSearchEpisodeHighlightContentTest.class);
		testSuite.addTestSuite(XidioSearchEpisodeResultTitleTest.class);
		testSuite.addTestSuite(XidioSearchEpisodesResultTest.class);



		//Featured
		testSuite.addTestSuite(XidioSearchFeaturedResultTest.class);

		//Popular
		testSuite.addTestSuite(XidioSearchPopularChannelTitleTest.class);
		testSuite.addTestSuite(XidioSearchPopularEpisodeSubscriptionTest.class);
		testSuite.addTestSuite(XidioSearchPopularHighlightContentTest.class);
		testSuite.addTestSuite(XidioSearchPopularResultTest.class);
		testSuite.addTestSuite(XidioSearchPopularShowsTitleTest.class);
		testSuite.addTestSuite(XidioSearchPopularShowSubcriptionTest.class);
		testSuite.addTestSuite(XidioSearchPopularSubscribeTest.class);

		//UPNext
		//testSuite.addTestSuite(XidioSearchUpNextResult.class);








		return testSuite;
	}
}
