package com.comcast.xidio.testCases.home.popular;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.XidioItemTile;
import com.xfinity.xidio.views.XidioNetworkImageView;

public class XidioHomePopularNetworkImageTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;

	public XidioHomePopularNetworkImageTest() {
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

	public void testXidioHomePopularNetworkImage()
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


		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);

		solo.sleep(TestConstants.SLEEP_TIME_2000);

		try{

			JSONArray popularJsonArray = GetCatagoryLists.getInstance().getPopularList();
			if(popularJsonArray!=null && popularJsonArray.length()>0)
			{

				XidioItemTile currItem=null;
				for(int j=0;j<popularJsonArray.length();j++)
				{
					JSONObject currElement=popularJsonArray.getJSONObject(j);
					if(currElement.has("category"))
					{	if(currElement.getJSONObject("category").has("level"))
					{
						if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SUB_SHOW"))
						{   
							solo.sleep(TestConstants.SLEEP_TIME_2000);
							currItem = (XidioItemTile) solo.getCurrentActivity().getCurrentFocus();
						XidioNetworkImageView image = (XidioNetworkImageView)currItem.findViewById(R.id.img_view);
							
							if((currElement.getString("imageUrl").trim())!=null){
								assertTrue(image.isShown());
							}
							else{
								assertNull(image);
							}
						 solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						 solo.sleep(TestConstants.SLEEP_TIME_2000);
								
						}
						else if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW")){
							solo.sleep(TestConstants.SLEEP_TIME_2000);
							currItem = (XidioItemTile) solo.getCurrentActivity().getCurrentFocus();
							XidioNetworkImageView image = (XidioNetworkImageView)currItem.findViewById(R.id.img_view);
							if((currElement.getString("imageUrl").trim())!=null){
								assertTrue(image.isShown());
							}
							else{
								assertNull(image);
							}
							 solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
							 solo.sleep(TestConstants.SLEEP_TIME_2000);
							

						}
					}

					}			
					else if(currElement.has("asset"))
					{	
						solo.sleep(TestConstants.SLEEP_TIME_2000);
						currItem = (XidioItemTile) solo.getCurrentActivity().getCurrentFocus();
						XidioNetworkImageView image = (XidioNetworkImageView)currItem.findViewById(R.id.img_view);
						if((currElement.getString("imageUrl").trim())!=null){
							assertTrue(image.isShown());
						}
						else{
							assertNull(image);
						}
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
			Log.d("Exception from test XidioHomePopularNetworkImage = ", e.getLocalizedMessage());
		}

	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

}
