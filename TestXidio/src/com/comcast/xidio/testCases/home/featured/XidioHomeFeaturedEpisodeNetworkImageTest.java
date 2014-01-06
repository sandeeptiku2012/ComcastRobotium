package com.comcast.xidio.testCases.home.featured;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

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
import com.xfinity.xidio.views.XidioNetworkImageView;

public class XidioHomeFeaturedEpisodeNetworkImageTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;

	public XidioHomeFeaturedEpisodeNetworkImageTest() {
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

	public void testXidioHomeFeaturedEpisodeNetworkImage()
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
		

		try{

			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{

				XidioItemTile currItem=null;
				for(int j=0;j<featuredJsonArray.length();j++)
				{
					JSONObject currElement=featuredJsonArray.getJSONObject(j);
					if(currElement.has("category"))
					{	if(currElement.getJSONObject("category").has("level"))
					{
						if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SUB_SHOW"))
						{   

							solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
							solo.sleep(TestConstants.SLEEP_TIME_2000);
							continue;

						}
						else if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW")){

							solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
							solo.waitForActivity(TestConstants.DETAILS_ACTIVITY);
							solo.sleep(TestConstants.SLEEP_TIME_5000);
							String channelContentKey = currElement.getString("contentKey");
							JSONArray episodeListArray1 = GetEpisodesList.getInstance().getEpisodeList(channelContentKey);
							if(episodeListArray1==null){
								JSONArray showContent = GetShowContent.getInstance().getShowContent(channelContentKey);
								solo.sleep(TestConstants.SLEEP_TIME_5000);

								if (showContent == null) 
								{
									solo.sendKey(KeyEvent.KEYCODE_BACK);
									solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
									solo.sleep(TestConstants.SLEEP_TIME_5000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									continue;

								} else {

									
									solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
									solo.sleep(TestConstants.SLEEP_TIME_2000);


									for (int p = 0; p < showContent.length(); p++) {

										JSONObject showsList = showContent.getJSONObject(p);
										solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
										solo.waitForActivity(TestConstants.DETAILS_ACTIVITY);
										solo.sleep(TestConstants.SLEEP_TIME_5000);
										String epList = showsList.getString("@id");
										JSONArray episodeListArray = GetEpisodesList.getInstance().getEpisodeList(epList);
										if(episodeListArray!=null){
										solo.sleep(TestConstants.SLEEP_TIME_5000);
										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										for(int k=0;k<episodeListArray.length();k++){
											
											solo.sleep(TestConstants.SLEEP_TIME_5000);
											JSONObject episode = episodeListArray.getJSONObject(k);
											currItem = (XidioItemTile) solo.getCurrentActivity().getCurrentFocus();

											XidioNetworkImageView image = (XidioNetworkImageView)currItem.findViewById(R.id.img_view);
											if((episode.getJSONObject("metadata").getJSONObject("image-pack").getString("$").trim())!=null){
												assertTrue(image.isShown());
											}
											else{
												assertNull(image);
											}
											solo.sleep(TestConstants.SLEEP_TIME_2000);
											solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
											solo.sleep(TestConstants.SLEEP_TIME_2000);
										}
										}
										solo.sendKey(KeyEvent.KEYCODE_BACK);
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										}
									

								}

							}


							
							
						}
					}

					}			
					else if(currElement.has("asset"))
					{	

						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						continue;
					}
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
				}

			}	

		}

		catch(Exception e)
		{
			Log.d("Exception from test XidioHomeFeaturedEpisodeNetworkImage = ", e.getLocalizedMessage());
		}

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

}
