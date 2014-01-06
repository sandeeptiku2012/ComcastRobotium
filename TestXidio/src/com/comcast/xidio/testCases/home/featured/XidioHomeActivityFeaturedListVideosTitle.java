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
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioHomeActivityFeaturedListVideosTitle extends ActivityInstrumentationTestCase2<FirstRun> {

	private Solo solo;
	String elementTitle =null;

	public XidioHomeActivityFeaturedListVideosTitle() {
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

	public void testXidioHomeActivityFeaturedListVideosTitle()
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
		
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		try {
			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{
			
				for (int count = 0; count < featuredJsonArray.length(); count++) 
				{
					JSONObject currentElement = featuredJsonArray.getJSONObject(count);
	
					//if(currentElement.has("content_category"))
					//{
						if(currentElement.getString("contentType").contentEquals("asset")){
							 if(currentElement.has("category"))
							{		 if(currentElement.getJSONObject("category").has("title"))
														elementTitle = currentElement.getJSONObject("category").getString("title").trim();
											else if(currentElement.has("title"))
														elementTitle = currentElement.getString("title").trim();
											else
												currentElement=null;
								
								
							}
						//}
					 }
										
					
					if(elementTitle!=null)
					{
						solo.sleep(TestConstants.SLEEP_TIME_500);
						assertTrue(solo.waitForText(elementTitle));
					}
					//else 
					//	assertTrue(false);
					
					currentElement=null;
					
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
				}
			
			}
		} catch (Exception e) 
		{
			Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioHomeActivityFeaturedListVideosTitle " , e);
			assertTrue(false);
		}
		
		solo.sleep(2000);
	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
