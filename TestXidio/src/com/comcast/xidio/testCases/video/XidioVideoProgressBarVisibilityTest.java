package com.comcast.xidio.testCases.video;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;

import com.comcast.playerplatform.primetime.android.events.AbstractPlayerPlatformVideoEventListener;
import com.comcast.playerplatform.primetime.android.player.PlayerPlatformAPI;
import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetEpisodesList;
import com.comcast.xidio.model.GetShowContent;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.VideoPlayerView;

public class XidioVideoProgressBarVisibilityTest extends ActivityInstrumentationTestCase2<FirstRun> {
	
	
	private Solo solo;
	private long before_videoTime = 0;
	private long after_videoTime = 0;
	private PlayerPlatformAPI platformApi;
	private boolean adComplete=false;
	
	public XidioVideoProgressBarVisibilityTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {
		GetSolo.getInstance().setUpSolo(getInstrumentation(), getActivity());
		solo = GetSolo.getInstance().getSoloObject();
		GetCatagoryLists.getInstance().storeBasicLists(XidioApplication.getLastLoggedInUser(),XidioApplication.getLastSessionId());
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioVideoProgressBarVisibility() 
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
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		JSONArray popularJsonArray = GetCatagoryLists.getInstance().getPopularList();
		for (int j=0;j<popularJsonArray.length();j++) {
			
			try {
				JSONObject currentObject = popularJsonArray.getJSONObject(j);
				if(currentObject.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW")){
				String channelTitle = currentObject.getString(TestConstants.TITLE);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
				assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
				solo.sleep(TestConstants.SLEEP_TIME_500);
				assertTrue(solo.searchText(channelTitle.toString()));
				String channelContentKey = currentObject.getString("contentKey");
				JSONArray channelShowContent = GetShowContent.getInstance().getShowContent(channelContentKey);
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
						VideoPlayerView vvvv = (VideoPlayerView) solo.getCurrentActivity().findViewById(R.id.video_player_view);
						ProgressBar pBar=null;
						for(int cCount=0;cCount<vvvv.getChildCount();cCount++){
							
							if(vvvv.getChildAt(cCount) instanceof ProgressBar){
								pBar=(ProgressBar) vvvv.getChildAt(cCount);
								break;
							}
						}
						assertTrue(pBar.isShown());
						break;
					}
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					
				}
				break;
				}
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				}
				
			} catch (Exception e) {
				Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioXidioVideoProgressBarVisibility" +
						". " , e);
			}
		}

	}
	
	@Override
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
