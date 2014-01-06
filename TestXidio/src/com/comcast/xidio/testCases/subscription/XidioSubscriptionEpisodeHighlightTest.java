package com.comcast.xidio.testCases.subscription;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.comcast.cil.scalerow.ContentRowGrid;
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

public class XidioSubscriptionEpisodeHighlightTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject currChannel;
	
	public XidioSubscriptionEpisodeHighlightTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionEpisodeHighlight() 
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
		
		
		
					
					
		
		if(subscribedChannels==null || subscribedChannels.length()==0)
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
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		for(int i=0;i<subscribedChannels.length();i++)
		{
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			try {
				 currChannel = subscribedChannels.getJSONObject(i);
				String subsContentKey  = currChannel.getString("@id");
				JSONArray subsShowContent = GetShowContent.getInstance().getShowContent(subsContentKey);
				if(subsShowContent!=null){
					for(int j=0; j<subsShowContent.length();j++){
						solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						JSONObject showsList = subsShowContent.getJSONObject(j);
						String showId = showsList.getString("@id");
						JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
							if(episodeListArray!=null){
								solo.sleep(TestConstants.SLEEP_TIME_1000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
								solo.sleep(TestConstants.SLEEP_TIME_500);
								JSONObject epList = episodeListArray.getJSONObject(0);
								String title =  epList.getString("title");
								ContentRowGrid grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.details_rows_container);
								View currView = grid.findFocus();
								TextView titleText= (TextView)currView.findViewById(R.id.item_title);
								String TitleString = (String) titleText.getText();
								assertEquals(title,TitleString);
								assertTrue(currView.isFocused());
								ImageView image = (ImageView)currView.findViewById(R.id.img_view);
								int imgHeight = image.getHeight();
								int imgWidth = image.getWidth();
								int EPISODE_WIDTH_SELECTED = (int) (solo.getCurrentActivity().getResources().getDimension(R.dimen.episode_tile_image_selected_width) * XidioApplication.WidthConversion);
								int EPISODE_HEIGHT_SELECTED = (int) (solo.getCurrentActivity().getResources().getDimension(R.dimen.episode_tile_image_selected_height) * XidioApplication.WidthConversion);
								assertEquals(EPISODE_HEIGHT_SELECTED,imgHeight);
								assertEquals(EPISODE_WIDTH_SELECTED,imgWidth);
							
								break;
							}
							
					
						
						
					}
				} 
			else{
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				continue;
				
			}
		}
				catch (Exception e) {
				
				Log.e("Exception:", "Exception occured in XidioSubscriptionEpisodeHighlight test", e);
			}
			
			break;
		}
		
		
		
	}

	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
	
}
