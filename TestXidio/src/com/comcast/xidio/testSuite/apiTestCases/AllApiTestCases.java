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
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedEpisodeNetworkImageTest;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedHighlightedContentTest;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedNetworkImageTest;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedShowCount;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedShowHighlight;
import com.comcast.xidio.testCases.home.featured.XidioHomeFeaturedShowNetworkImageTest;
import com.comcast.xidio.testCases.home.featured.XidioShowsDetailBackButtonTest;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailActivityFeaturedEpisodeSubcription;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailActivityFeaturedShowSubcription;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailsActivityFeaturedEpisodeActivityChange;
import com.comcast.xidio.testCases.home.featured.details.XidioDetailsActivityFeaturedEpisodeTitleChange;
import com.comcast.xidio.testCases.home.featured.details.XidioHomeFeaturedSubscriptionTextTest;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityChange;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityChangeTitle;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityPopularEpisodeList;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityPopularListChannelsTitle;
import com.comcast.xidio.testCases.home.popular.XidioHomeActivityPopularTitle;
import com.comcast.xidio.testCases.home.popular.XidioHomeChannelShowCountTest;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularChannelHighlight;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularEpisodeHighlight;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularEpisodeNetworkImageTest;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularHighlightedContenTest;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularNetworkImageTest;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularShowHighlight;
import com.comcast.xidio.testCases.home.popular.XidioHomePopularShowNetworkImageTest;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailActivityPopularEpisodeSubscriptionTest;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailActivityPopularShowSubcription;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailsActivityPopularEpisodeActivityChange;
import com.comcast.xidio.testCases.home.popular.details.XidioDetailsActivityPopularEpisodeTitleChange;
import com.comcast.xidio.testCases.home.popular.details.XidioHomePopularSubcriptionTextTest;
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
import com.comcast.xidio.testCases.navigationBar.XidioNavigationLeftToRightTest;
import com.comcast.xidio.testCases.navigationBar.XidioNavigationRightToLeftTest;
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
import com.comcast.xidio.testCases.settings.XidioSettingsClearCacheButton;
import com.comcast.xidio.testCases.settings.XidioSettingsDisplayButtonTest;
import com.comcast.xidio.testCases.settings.XidioSettingsInformationTest;
import com.comcast.xidio.testCases.settings.XidioSettingsLabelCheckTest;
import com.comcast.xidio.testCases.settings.XidioSettingsNetworkButtonTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionButtonLabelChangeTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionChannelHighlightTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionChannelHighlightedContentTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionChannelNetworkImageTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionEpisodeHighlightTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionEpisodeSubcriptionTest;
import com.comcast.xidio.testCases.subscription.XidioSubscriptionEpisodesNetworkImageTest;
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
import com.comcast.xidio.testCases.subscription.XidioSubscriptionShowNetworkImageTest;
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

public class AllApiTestCases extends TestSuite
{
	

	
	
	public static Test suite()
	{
		//return new TestSuiteBuilder(AllApiTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases" }).build();
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
		
		
		

		//return new TestSuiteBuilder(HomeTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.home" }).build();
		testSuite.addTestSuite(XidioDefaultPageCheck.class);
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
		//testSuite.addTestSuite(XidioDetailsActivityPopularChannelVideoPlay.class);
		//testSuite.addTestSuite(XidioDetailsActivityPopularShowVideoPlay.class);
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
		
		//Popular Detail
		
		testSuite.addTestSuite(XidioDetailActivityPopularEpisodeSubscriptionTest.class);
		testSuite.addTestSuite(XidioDetailActivityPopularShowSubcription.class);
		testSuite.addTestSuite(XidioDetailsActivityPopularEpisodeActivityChange.class);
		testSuite.addTestSuite(XidioDetailsActivityPopularEpisodeTitleChange.class);
		testSuite.addTestSuite(XidioHomePopularSubcriptionTextTest.class);
		
		


		//return null;//new TestSuiteBuilder(NavigationBarTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.navogationBar" }).build();
		testSuite.addTestSuite(XidioHeaderImageAndTextTest.class);
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
		

		//return  new TestSuiteBuilder(SearchTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases.subscription" }).build();
		
		testSuite.addTestSuite(XidioSearchActivityBlankSearch.class);
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

		//return new TestSuiteBuilder(SubscriptionTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases" }).build();
		testSuite.addTestSuite(XidioSubscriptionButtonLabelChangeTest.class);
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
		 

		//return new TestSuiteBuilder(SubscriptionTestCases.class).includePackages(new String[] { "com.comcast.xidio.testCases" }).build();
		
		//Settings
		
		testSuite.addTestSuite(XidioSettingsInformationTest.class);
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
		 
		
		//NetWorkImage
		
		testSuite.addTestSuite(XidioHomeFeaturedNetworkImageTest.class);
		testSuite.addTestSuite(XidioHomeFeaturedShowNetworkImageTest.class);
		testSuite.addTestSuite(XidioHomeFeaturedEpisodeNetworkImageTest.class);
		testSuite.addTestSuite(XidioHomePopularNetworkImageTest.class);
		testSuite.addTestSuite(XidioHomePopularShowNetworkImageTest.class);
		testSuite.addTestSuite(XidioHomePopularEpisodeNetworkImageTest.class);
		testSuite.addTestSuite(XidioSubscriptionChannelNetworkImageTest.class);
		testSuite.addTestSuite(XidioSubscriptionShowNetworkImageTest.class);
		testSuite.addTestSuite(XidioSubscriptionEpisodesNetworkImageTest.class);
		
		return testSuite;
		
		
	
		
	}
}
