package com.comcast.xidio.testCases.search.channel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
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
import com.comcast.xidio.model.GetChannelSearchList;
import com.comcast.xidio.model.GetEpisodesList;
import com.comcast.xidio.model.GetShowContent;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSearchChannelEpisodeHighlightTest extends	ActivityInstrumentationTestCase2<FirstRun> {
	private Solo solo;

	public XidioSearchChannelEpisodeHighlightTest() {
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

	public void testXidioSearchChannelEpisodeHighlight() 
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
		
		ArrayList<JSONObject> channelSearchArray =GetChannelSearchList.getInstance().getChannelSearchList("cnn");
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		
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
						ContentRowGrid grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.details_rows_container);
						View currView = grid.findFocus();
						TextView titleText= (TextView)currView.findViewById(R.id.item_title);
						String TitleString = (String) titleText.getText();
						assertEquals(episodeTitle,TitleString);
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
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					
				}
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				solo.sleep(TestConstants.SLEEP_TIME_500);
				}
				
			} catch (Exception e) {
				Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioSearchChannelEpisodeHighlight " , e);
			}
		}

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
