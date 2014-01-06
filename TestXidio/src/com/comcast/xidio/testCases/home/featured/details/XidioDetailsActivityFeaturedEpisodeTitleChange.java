package com.comcast.xidio.testCases.home.featured.details;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.xfinity.xidio.views.XidioItemTile;

public class XidioDetailsActivityFeaturedEpisodeTitleChange extends ActivityInstrumentationTestCase2<FirstRun> 
{
	private Solo solo;
	public XidioDetailsActivityFeaturedEpisodeTitleChange() 
	{
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
	
	public void testXidioDetailsActivityFeaturedEpisodeTitleChange()
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
		solo.sleep(TestConstants.SLEEP_TIME_500);
						
		try {

			JSONArray featuredArray =GetCatagoryLists.getInstance().getFeaturedList();
			
//			for(int j=0;j<featuredArray.length();j++)
//			{
//				JSONObject currElement=featuredArray.getJSONObject(j);
//				if(currElement.has("category"))
//				{	if(currElement.getJSONObject("category").has("level"))
//							{
//							if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SUB_SHOW"))
//								{
//									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
//									solo.sleep(TestConstants.SLEEP_TIME_1000);
//									continue;
//								}
//							else if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW"))
//							{
//								
//								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
//								String channelContentKey = currElement.getString("contentKey");
//								JSONArray showContent = GetShowContent.getInstance().getShowContent(channelContentKey);
//								solo.sleep(TestConstants.SLEEP_TIME_500);
//
//								if (showContent == null) 
//								{
//									solo.sendKey(KeyEvent.KEYCODE_BACK);
//									solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
//									assertTrue(true);
//
//								} else {
//									solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//									assertTrue(solo.searchText(currElement.getString("title")));
//									solo.sleep(1000);
//									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
//									for (int p = 0; p < showContent.length(); p++) {
//										solo.sleep(TestConstants.SLEEP_TIME_2000);
//										JSONObject showsList = showContent.getJSONObject(p);
//										String showId = showsList.getString("@id");
//										JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
//										if (p == 0)
//											solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//										for (int k = 0; k < episodeListArray.length(); k++) {
//											solo.sleep(TestConstants.SLEEP_TIME_500);
//											JSONObject currentEpisode = episodeListArray.getJSONObject(k);
//											String episodeTitle = currentEpisode.getString(TestConstants.TITLE);
//											assertTrue(solo.searchText(episodeTitle));
//											solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
//											assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
//											solo.sleep(TestConstants.SLEEP_TIME_1000);
//											solo.sendKey(KeyEvent.KEYCODE_BACK);
//											solo.sleep(TestConstants.SLEEP_TIME_500);
//											solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
//										}
//										solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
//										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//									}
//								}
//								
//								break;
//							}
//						}
//				}			
//				else
//				{
//					continue;
//				}
//				
//			}	
			for(int j=0;j<featuredArray.length();j++)
			{
				
				ContentRowGrid grid = null;
				View currView = null;
				TextView itemView = null;
				String itemTitle = null;
				JSONObject currElement=featuredArray.getJSONObject(j);
				if(currElement.has("category"))
				{	if(currElement.getJSONObject("category").has("level"))
							{
							if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SUB_SHOW"))
								{
									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									continue;
								}
							else if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW"))
							{
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								String channelContentKey = currElement.getString("contentKey");
								JSONArray episodeListArray1 = GetEpisodesList.getInstance().getEpisodeList(channelContentKey);
								if(episodeListArray1==null){
								JSONArray showContent = GetShowContent.getInstance().getShowContent(channelContentKey);
								solo.sleep(TestConstants.SLEEP_TIME_500);

								if (showContent == null) 
								{
									solo.sendKey(KeyEvent.KEYCODE_BACK);
									solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
									assertTrue(true);

								} else {
									
									assertTrue(solo.searchText(currElement.getString("title")));
									
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									
									//solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									for (int p = 0; p < showContent.length(); p++) {
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										JSONObject showsList = showContent.getJSONObject(p);
										if(showsList!=null){
											String showId = showsList.getString("@id");
											JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(showId);
											if (p == 0 )
												solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
											grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.details_rows_container);
											currView =  grid.findFocus();
											itemView = (TextView) currView.findViewById(R.id.item_title);
											itemTitle = (String) itemView.getText();
											assertEquals(showsList.getString("title").trim(), itemTitle.trim());
											solo.sleep(TestConstants.SLEEP_TIME_2000);
											if(episodeListArray==null){
												solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
												solo.sleep(TestConstants.SLEEP_TIME_2000);
												continue;
											}
											solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
											solo.sleep(TestConstants.SLEEP_TIME_2000);
											for (int k = 0; k < episodeListArray.length(); k++) {
												JSONObject currentEpisode = episodeListArray.getJSONObject(k);
												String episodeTitle = currentEpisode.getString(TestConstants.TITLE).trim();
												
												if(k==0)
													solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
												grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.details_rows_container);
												currView = grid.findFocus();
												itemView = (TextView) currView.findViewById(R.id.item_title);
												itemTitle = (String) itemView.getText();
												assertEquals(episodeTitle, itemTitle);
												solo.sleep(TestConstants.SLEEP_TIME_2000);
												solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
												assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
												solo.sleep(TestConstants.SLEEP_TIME_1000);
												solo.sendKey(KeyEvent.KEYCODE_BACK);
												solo.sleep(TestConstants.SLEEP_TIME_2000);
												solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
												solo.sleep(TestConstants.SLEEP_TIME_2000);
											}
											solo.sendKey(KeyEvent.KEYCODE_BACK);
											solo.sleep(TestConstants.SLEEP_TIME_2000);
											//solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										}
										else{
											solo.sendKey(KeyEvent.KEYCODE_BACK);
										}
									}
								}
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								solo.sendKey(KeyEvent.KEYCODE_BACK);

								assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
								solo.sleep(TestConstants.SLEEP_TIME_500);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
								continue;
							}
							
							
				else{
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					for(int i=0;i<episodeListArray1.length();i++){
					
						solo.sleep(TestConstants.SLEEP_TIME_500);
						JSONObject currentEpisode = episodeListArray1.getJSONObject(i);
						String episodeTitle = currentEpisode.getString(TestConstants.TITLE);
						grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.details_rows_container);
						currView = grid.findFocus();
						itemView = (TextView) currView.findViewById(R.id.item_title);
						itemTitle = (String) itemView.getText();
						assertEquals(episodeTitle, itemTitle);
						assertTrue(solo.searchText(episodeTitle));
						solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
						assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
						solo.sleep(TestConstants.SLEEP_TIME_1000);
						solo.sendKey(KeyEvent.KEYCODE_BACK);
						solo.sleep(TestConstants.SLEEP_TIME_500);
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					
					}
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
							
						}
							}
					
				}
			}
				else
				{
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					continue;
				}
				}
		} catch (Exception e) 
		{
			Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioDetailsActivityFeaturedEpisodeTitleChange " , e);
		}
		
	}
		
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
		
	
	
}
