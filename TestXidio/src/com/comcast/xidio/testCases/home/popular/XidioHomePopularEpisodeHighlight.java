package com.comcast.xidio.testCases.home.popular;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.comcast.cil.scalerow.ContentRowGrid;
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

public class XidioHomePopularEpisodeHighlight extends ActivityInstrumentationTestCase2<FirstRun> {

	private Solo solo;
	String elementTitle =null;

	public XidioHomePopularEpisodeHighlight() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception 
	{
		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		GetCatagoryLists.getInstance().storeBasicLists(XidioApplication.getLastLoggedInUser(), XidioApplication.getLastSessionId());
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
		
	}

	public void testXidioHomePopularEpisodeHighlight()
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo,  getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
	
		try {
			JSONArray popularJsonArray = GetCatagoryLists.getInstance().getPopularList();
			if(popularJsonArray!=null && popularJsonArray.length()>0)
			{
			
				for (int count = 0; count < popularJsonArray.length(); count++) 
				{
					JSONObject currentElement = popularJsonArray.getJSONObject(count);
	
					
						if(currentElement.getString("contentType").contentEquals("content_category")){
							 if(currentElement.has("category"))
							{	if(currentElement.getJSONObject("category").getString("level").contentEquals("SHOW"))
								{	 
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								solo.sleep(TestConstants.SLEEP_TIME_500);
								String channelContentKey = currentElement.getString("contentKey");
								JSONArray showContent = GetShowContent.getInstance().getShowContent(channelContentKey);
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								if(showContent!=null){
									for(int j=0; j<showContent.length();j++){
										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
										solo.sleep(TestConstants.SLEEP_TIME_500);
										solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										JSONObject showsList = showContent.getJSONObject(j);
										String showId = showsList.getString("@id");
										JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
											if(episodeListArray!=null){
												solo.sleep(TestConstants.SLEEP_TIME_1000);
												solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
												solo.sleep(TestConstants.SLEEP_TIME_500);
												JSONObject epList = episodeListArray.getJSONObject(0);
												String title =  epList.getString("title").trim();
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
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									continue;
								 }
								}
								solo.sleep(TestConstants.SLEEP_TIME_1000);
								break;
								
								
								}
							}
						}
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_1000);
					 }
					
					
					
					
				
			
			
		} catch (Exception e) 
		{
			Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioHomeActivityFeaturedListTitle " , e);
			assertTrue(false);
		}
		
		
		}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
