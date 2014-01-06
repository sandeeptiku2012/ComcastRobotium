package com.comcast.xidio.testCases.video;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.playerplatform.primetime.android.enums.PlayerStatus;
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

public class XidioVideoPlayAgainTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private PlayerPlatformAPI platformApi;
	private boolean adComplete=false;
	private PlayerStatus status;
	
	public XidioVideoPlayAgainTest() {
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

	public void testXidioVideoPlayAgain() 
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
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		try{
			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{
			
				for (int count = 0; count < featuredJsonArray.length(); count++) 
				{
					JSONObject currentElement = featuredJsonArray.getJSONObject(count);
	
					
						
					if(currentElement.has("asset"))
							{		 

						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						
						String episodeTitle = currentElement.getString(TestConstants.TITLE);
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
						status =  platformApi.getPlayerStatus();
						assertTrue(status.name().equalsIgnoreCase("PLAYING"));
						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						status =  platformApi.getPlayerStatus();
						assertTrue(status.name().equalsIgnoreCase("PAUSED"));
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);

						solo.sleep(TestConstants.SLEEP_TIME_5000);
						status =  platformApi.getPlayerStatus();
						assertTrue(status.name().equalsIgnoreCase("PLAYING"));
						solo.sleep(TestConstants.SLEEP_TIME_5000);
						
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						break;
					
								
								
							}
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
						}
						
					 }
					
		}
		
	catch(Exception e)
	{
		Log.d("Exception from test XidioVideoPlayAgain = ", e.getLocalizedMessage());
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


