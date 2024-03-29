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
import com.xfinity.xidio.core.XidioApplication;

public class XidioHomeActivityChangeTitle extends ActivityInstrumentationTestCase2<FirstRun> 
{
	private Solo solo;
			
	public XidioHomeActivityChangeTitle() {
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
	

	public void testXidioHomeActivityChangeTitle()
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
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		String currElementTitle;
		try{
		JSONArray popularJsonArray = GetCatagoryLists.getInstance().getPopularList();
		if(popularJsonArray!=null && popularJsonArray.length()>0)
		{
			for(int j=0;j<popularJsonArray.length();j++)
			{
				JSONObject currElement=popularJsonArray.getJSONObject(j);
				if(currElement.has("category"))
				{	if(currElement.getJSONObject("category").has("level"))
						{
							if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SUB_SHOW"))
								{   solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									currElementTitle=currElement.getJSONObject("category").getString("title").trim();
									assertTrue(solo.searchText(currElementTitle.trim()));
									solo.sleep(TestConstants.SLEEP_TIME_500);
									solo.sendKey(KeyEvent.KEYCODE_BACK);
									assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
									solo.sleep(TestConstants.SLEEP_TIME_2000);
									continue;
								}
							else if(currElement.getJSONObject("category").getString("level").trim().equalsIgnoreCase("SHOW")){
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								currElementTitle=currElement.getJSONObject("category").getString("title").trim();
								assertTrue(solo.searchText(currElementTitle));
								solo.sleep(TestConstants.SLEEP_TIME_500);
								solo.sendKey(KeyEvent.KEYCODE_BACK);
								assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								continue;
								
							}
						}
				
				}			
				else if(currElement.has("asset"))
				{	
					solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
					assertTrue(solo.waitForActivity(TestConstants.VIDEOPLAYER_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_500);
					assertTrue(solo.searchText(currElement.getString("title").trim()));
					solo.sleep(TestConstants.SLEEP_TIME_500);
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
					solo.sleep(TestConstants.SLEEP_TIME_2000);
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
		Log.d("Exception from test XidioHomeActivityChangeTitle = ", e.getLocalizedMessage());
	}
		
	}
	
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

}
