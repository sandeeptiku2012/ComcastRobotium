package com.comcast.xidio.testCases.video;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.playerplatform.primetime.android.events.AbstractPlayerPlatformVideoEventListener;
import com.comcast.playerplatform.primetime.android.player.PlayerPlatformAPI;
import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetChannelSearchList;
import com.comcast.xidio.model.GetEpisodesList;
import com.comcast.xidio.model.GetShowContent;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.VideoPlayerView;

public class XidioSearchPopularVideoForwardTest extends	ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;
	private long before_videoTime = 0;
	private long after_videoTime = 0;
	private PlayerPlatformAPI platformApi;
	private Boolean adComplete=false;
	public XidioSearchPopularVideoForwardTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {
		
		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		GetCatagoryLists.getInstance().storeBasicLists(XidioApplication.getLastLoggedInUser(), XidioApplication.getLastSessionId());
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSearchPopularVideoForward() 
	{

		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo,  getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_C);
		solo.sendKey(KeyEvent.KEYCODE_N);
		solo.sendKey(KeyEvent.KEYCODE_N);
		
		ArrayList<JSONObject> channelSearchArray =GetChannelSearchList.getInstance().getChannelSearchList("CNN");
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		
		
		for (JSONObject currentObject : channelSearchArray) {
			try {
				if(currentObject.get("level").equals("SHOW")){
				String channelTitle = currentObject.getString(TestConstants.TITLE);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
				assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
				solo.sleep(TestConstants.SLEEP_TIME_500);
				assertTrue(solo.searchText(channelTitle.toString()));
				String key = currentObject.getString("@id");
				JSONArray channelShowContent = GetShowContent.getInstance().getShowContent(key);
				if(channelShowContent!=null){
				for(int i=0;i<channelShowContent.length();i++){
					JSONObject showsList = channelShowContent.getJSONObject(i);
					String showTitle = showsList.getString(TestConstants.TITLE);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					assertTrue(solo.searchText(showTitle.toString()));
					String showId = showsList.getString("@id");
					JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					if(episodeListArray!=null){
						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						
						JSONObject currentEpisode = episodeListArray.getJSONObject(0);
						String episodeTitle = currentEpisode.getString(TestConstants.TITLE);
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						assertTrue(solo.searchText(episodeTitle));
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
						
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						VideoPlayerView vView = (VideoPlayerView) solo.getCurrentActivity().findViewById(R.id.video_player_view);
						platformApi = vView.getPlatformAPI();
						platformApi.addEventListener(platformEventListener);
						while(!adComplete)
						{
							if(!platformApi.isAdPlaying() && platformApi.getCurrentAd()==null){
								break;
							}
						}
						
						solo.sleep(15000);
						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						before_videoTime=platformApi.getCurrentPosition();
						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);

						solo.sleep(TestConstants.SLEEP_TIME_1000);
						after_videoTime=platformApi.getCurrentPosition();
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						if(after_videoTime-before_videoTime>29000)
							assertTrue(true);
						else
							assertTrue(false);
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						break;
					}
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					
				}
				}
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				}
				
			} catch (Exception e) {
				Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioSearchPopularVideoForwardTest " , e);
			}
		}

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
	private final AbstractPlayerPlatformVideoEventListener platformEventListener = new AbstractPlayerPlatformVideoEventListener() {

		@Override
		public void adComplete(long id) {
			
			adComplete=true;
			super.adComplete(id);
		}

	};
}
