package com.comcast.xidio.testSuite.apiTestCases;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.comcast.xidio.testCases.details.home.XidioDefaultPageCheck;
import com.comcast.xidio.testCases.details.home.XidioForwardBackwardLabelCheck;
import com.comcast.xidio.testCases.details.home.XidioForwardPageLabelCheck;
import com.comcast.xidio.testCases.details.home.XidioLabelDownAndUpCheck;
import com.comcast.xidio.testCases.details.home.XidioLabelDownCheck;
import com.comcast.xidio.testCases.home.featured.XidioDetailsActivityChange;
import com.comcast.xidio.testCases.home.featured.XidioDetailsActivityChangeTitle;
import com.comcast.xidio.testCases.home.featured.XidioDetailsActivityFeaturedChannelVideoPlay;
import com.comcast.xidio.testCases.home.featured.XidioDetailsActivityFeaturedEpisodeList;
import com.comcast.xidio.testCases.home.featured.XidioDetailsActivityFeaturedShowVideoPlay;
import com.comcast.xidio.testCases.home.featured.XidioDetailsFeaturedEpisodeIcon;
import com.comcast.xidio.testCases.home.featured.XidioHomeActivityFeaturedListBundlesTitle;
import com.comcast.xidio.testCases.home.featured.XidioHomeActivityFeaturedListChannelsTitle;
import com.comcast.xidio.testCases.home.featured.XidioHomeActivityFeaturedListShowsTitle;
import com.comcast.xidio.testCases.home.featured.XidioHomeActivityFeaturedListTitle;
import com.comcast.xidio.testCases.home.featured.XidioHomeActivityFeaturedListVideosTitle;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedChaneelWithoutShowDescriptionTest;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedChannelTitleHighlight;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedEpisodeHighlight;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedHighlightedContentTest;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedShowCount;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedShowHighlight;
import com.comcast.xidio.testCases.home.featured.XidioShowsDetailBackButtonTest;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailActivityFeaturedEpisodeSubcription;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailActivityFeaturedShowSubcription;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailsActivityFeaturedEpisodeActivityChange;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailsActivityFeaturedEpisodeTitleChange;
import com.comcast.xidio.testCases.home.featured.details.XidioHomeFeaturedSubscriptionTextTest;
import com.comcast.xidio.testCases.home.popular.XidioDetailsActivityPopularChannelVideoPlay;
import com.comcast.xidio.testCases.home.popular.XidioDetailsActivityPopularShowVideoPlay;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityChange;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityChangeTitle;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityPopularEpisodeList;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityPopularListChannelsTitle;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityPopularTitle;
import com.comcast.xidio.testCases.home.popular.XidioHomeChannelShowCountTest;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularChannelHighlight;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularEpisodeHighlight;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularHighlightedContenTest;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularShowHighlight;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailActivityPopularEpisodeSubscriptionTest;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailActivityPopularShowSubcription;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailsActivityPopularEpisodeActivityChange;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailsActivityPopularEpisodeTitleChange;
import com.comcast.xidio.testCases.home.popular.details.XidioHomePopularSubcriptionTextTest;

public class HomeTestCases extends TestSuite {
	public static Test suite()
	{
		//return new TestSuiteBuilder(HomeTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.home" }).build();
		TestSuite testSuite = new TestSuite(XidioDefaultPageCheck.class);
		testSuite.addTestSuite(XidioForwardBackwardLabelCheck.class);
		testSuite.addTestSuite(XidioForwardPageLabelCheck.class);
		testSuite.addTestSuite(XidioLabelDownAndUpCheck.class);
		testSuite.addTestSuite(XidioLabelDownCheck.class);
		
		//Featured
		testSuite.addTestSuite(XidioDetailsActivityChange.class);
		testSuite.addTestSuite(XidioDetailsActivityChangeTitle.class);
		testSuite.addTestSuite(XidioDetailsActivityFeaturedChannelVideoPlay.class);
		testSuite.addTestSuite(XidioDetailsActivityFeaturedEpisodeList.class);
		testSuite.addTestSuite(XidioDetailsActivityFeaturedShowVideoPlay.class);
		testSuite.addTestSuite(XidioDetailsFeaturedEpisodeIcon.class);
		testSuite.addTestSuite(XidioHomeActivityFeaturedListBundlesTitle.class);
		testSuite.addTestSuite(XidioHomeActivityFeaturedListChannelsTitle.class);
		testSuite.addTestSuite(XidioHomeActivityFeaturedListShowsTitle.class);
		testSuite.addTestSuite(XidioHomeActivityFeaturedListTitle.class);
		testSuite.addTestSuite(XidioHomeActivityFeaturedListVideosTitle.class);
		testSuite.addTestSuite(XidioHomeFeaturedChaneelWithoutShowDescriptionTest.class);
		testSuite.addTestSuite(XidioHomeFeaturedChannelTitleHighlight.class);
		testSuite.addTestSuite(XidioHomeFeaturedEpisodeHighlight.class);
		testSuite.addTestSuite(XidioHomeFeaturedHighlightedContentTest.class);
		testSuite.addTestSuite(XidioHomeFeaturedShowCount.class);
		testSuite.addTestSuite(XidioHomeFeaturedShowHighlight.class);
		testSuite.addTestSuite(XidioShowsDetailBackButtonTest.class);
		
		
		//Featured details
		testSuite.addTestSuite(XidioDetailActivityFeaturedEpisodeSubcription.class);
		testSuite.addTestSuite(XidioDetailsActivityFeaturedEpisodeTitleChange.class);
		testSuite.addTestSuite(XidioDetailsActivityFeaturedEpisodeActivityChange.class);
		testSuite.addTestSuite(XidioDetailActivityFeaturedShowSubcription.class);
		testSuite.addTestSuite(XidioHomeFeaturedSubscriptionTextTest.class);
		
		//Popular
		testSuite.addTestSuite(XidioDetailsActivityPopularChannelVideoPlay.class);
		testSuite.addTestSuite(XidioDetailsActivityPopularShowVideoPlay.class);
		testSuite.addTestSuite(XidioHomeActivityChange.class);
		testSuite.addTestSuite(XidioHomeActivityChangeTitle.class);
		testSuite.addTestSuite(XidioHomeActivityPopularEpisodeList.class);
		testSuite.addTestSuite(XidioHomeActivityPopularListChannelsTitle.class);
		testSuite.addTestSuite(XidioHomeActivityPopularTitle.class);
		testSuite.addTestSuite(XidioHomeChannelShowCountTest.class);
		testSuite.addTestSuite(XidioHomePopularChannelHighlight.class);
		testSuite.addTestSuite(XidioHomePopularEpisodeHighlight.class);
		testSuite.addTestSuite(XidioHomePopularHighlightedContenTest.class);
		testSuite.addTestSuite(XidioHomePopularShowHighlight.class);
		
//		//Popular Detail
		
		testSuite.addTestSuite(XidioDetailActivityPopularEpisodeSubscriptionTest.class);
		testSuite.addTestSuite(XidioDetailActivityPopularShowSubcription.class);
		testSuite.addTestSuite(XidioDetailsActivityPopularEpisodeActivityChange.class);
		testSuite.addTestSuite(XidioDetailsActivityPopularEpisodeTitleChange.class);
		testSuite.addTestSuite(XidioHomePopularSubcriptionTextTest.class);
		
		
		return testSuite;
	}
}
