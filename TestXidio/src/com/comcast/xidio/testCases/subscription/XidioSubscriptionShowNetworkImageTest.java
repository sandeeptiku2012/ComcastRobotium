package com.comcast.xidio.testCases.subscription;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetEpisodesList;
import com.comcast.xidio.model.GetShowContent;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.XidioItemTile;
import com.xfinity.xidio.views.XidioNetworkImageView;

public class XidioSubscriptionShowNetworkImageTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject currChannel;
	
	public XidioSubscriptionShowNetworkImageTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioShowNetworkImage() 
	{

		//passing through the first Run Activity
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo,  getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		
		//starting the test main Activity
		
		
		JSONArray subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser()); 
		
		
		
					
					
		
		if(subscribedChannels.length()==0 || subscribedChannels==null)
		{
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_BACK);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
			subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());
			
		}
		
			
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		assertTrue(solo.searchText(TestConstants.SUBSCRIPTIONS));
		assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		XidioItemTile currItem=null;
		for(int i=0;i<subscribedChannels.length();i++)
		{
			try {
				
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
				solo.sleep(TestConstants.SLEEP_TIME_5000);
					 currChannel = subscribedChannels.getJSONObject(i);
					String subsContentKey  = currChannel.getString("@id");
					JSONArray subsShowContent = GetShowContent.getInstance().getShowContent(subsContentKey);
					if(subsShowContent!=null){
					solo.sleep(TestConstants.SLEEP_TIME_5000);
					for(int j=0;j<subsShowContent.length();j++){
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
						JSONArray epContent = GetEpisodesList.getInstance().getEpisodeList(subsContentKey);
						if(epContent!=null){
							solo.sleep(TestConstants.SLEEP_TIME_1000);
							solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
						}
						JSONObject showsList = subsShowContent.getJSONObject(j);
						String showId = showsList.getString("@id");
						JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
						if(episodeListArray!=null){
							solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
							solo.sleep(TestConstants.SLEEP_TIME_2000);
							solo.sleep(TestConstants.SLEEP_TIME_1000);
							solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
						for(int k=0;k<episodeListArray.length();k++){
							
							solo.sleep(TestConstants.SLEEP_TIME_500);
							JSONObject epList = episodeListArray.getJSONObject(k);
							
							currItem = (XidioItemTile) solo.getCurrentActivity().getCurrentFocus();
							XidioNetworkImageView image = (XidioNetworkImageView)currItem.findViewById(R.id.img_view);
							if((epList.getJSONObject("metadata").getJSONObject("image-pack").getString("$").trim())!=null){
								assertTrue(image.isShown());
							}
							else{
								assertNull(image);
							}
							solo.sleep(TestConstants.SLEEP_TIME_1000);
							solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
							}
						
						}
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						
					}
					
					}
					else{
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
					}

				
			} catch (Exception e) {
				
				Log.e("Exception:", "Exception occured in XidioSubscriptionShowNetworkImage test", e);
			}
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
		}
		
		solo.sendKey(KeyEvent.KEYCODE_BACK);
		
		
		
		
	}

	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
	
}
