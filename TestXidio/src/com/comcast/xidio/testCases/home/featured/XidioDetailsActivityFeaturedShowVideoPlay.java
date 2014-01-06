package com.comcast.xidio.testCases.home.featured;

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
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.VideoPlayerView;

public class XidioDetailsActivityFeaturedShowVideoPlay extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private boolean adCompleted=false;
	private PlayerStatus status;

	public XidioDetailsActivityFeaturedShowVideoPlay() {
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
	
	private final AbstractPlayerPlatformVideoEventListener platformEventListener = new AbstractPlayerPlatformVideoEventListener() {

		
		@Override
		public void adComplete(long id) {
			adCompleted=true;
			super.adComplete(id);
		}

	};
	private PlayerPlatformAPI platformApi;
	private long before_videoTime;
	private long after_videoTime;
	private boolean testComplete;
	
	
	
	public void testXidioDetailsActivityFeaturedShowVideoPlay() 
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
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
			
//			VideoPlayerView vView = (VideoPlayerView) solo.getCurrentActivity().findViewById(R.id.video_player_view);
//			platformApi = vView.getPlatformAPI();
//			platformApi.addEventListener(platformEventListener);
			
			
			
		JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
		if(featuredJsonArray!=null && featuredJsonArray.length()>0)
		{
			
			for(int j=0;j<featuredJsonArray.length();j++)
			{
				if(testComplete)
					break;
				
				JSONObject currElement=featuredJsonArray.getJSONObject(j);
				if(currElement.has("category"))
				{	if(currElement.getJSONObject("category").has("level"))
						{
							if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SUB_SHOW"))
								{   solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(currElement.getString("contentKey"));
									if(episodeListArray!=null)
									{
										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										
										solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
										solo.sleep(TestConstants.SLEEP_TIME_5000);
										solo.sleep(TestConstants.SLEEP_TIME_5000);
										
										VideoPlayerView vView = (VideoPlayerView) solo.getCurrentActivity().findViewById(R.id.video_player_view);
										platformApi = vView.getPlatformAPI();
										platformApi.addEventListener(platformEventListener);
										
										while(!adCompleted)
										{if(!platformApi.isAdPlaying() && platformApi.getCurrentAd()==null){
											break;
										}}
										status =  platformApi.getPlayerStatus();
										assertTrue(status.name().equalsIgnoreCase("PLAYING"));

										solo.sleep(TestConstants.SLEEP_TIME_5000);
										before_videoTime=platformApi.getCurrentPosition();
										solo.sleep(TestConstants.SLEEP_TIME_5000);
										after_videoTime=platformApi.getCurrentPosition();
										if(before_videoTime!=after_videoTime)
											assertTrue(true);
										else
											assertTrue(false);
										
										testComplete=true;
										
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										solo.sendKey(KeyEvent.KEYCODE_BACK);
										solo.sleep(TestConstants.SLEEP_TIME_500);
								
								}
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									solo.sendKey(KeyEvent.KEYCODE_BACK);
									solo.sleep(TestConstants.SLEEP_TIME_500);
									assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									continue;
								}
							else if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW")){
								
								solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								continue;
								
							}
						}
				
				}			
				else if(currElement.has("asset"))
				{	
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					continue;
				}
				solo.sleep(TestConstants.SLEEP_TIME_500);
			}
			
			
			
		}	

	}
		
	catch(Exception e)
	{
		Log.d("Exception from test XidioDetailsActivityFeaturedShowVideoPlay = ", e.getLocalizedMessage());
	}
	
	}
	
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}


