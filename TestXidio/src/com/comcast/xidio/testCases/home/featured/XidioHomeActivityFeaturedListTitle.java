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

public class XidioHomeActivityFeaturedListTitle extends ActivityInstrumentationTestCase2<FirstRun> {

	private Solo solo;
	//String elementTitle =null;

	public XidioHomeActivityFeaturedListTitle() {
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

	public void testXidioHomeActivityFeaturedListTitle()
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_BACK );
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		
		solo.sleep(TestConstants.SLEEP_TIME_500);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		try {
			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{
			
				for (int count = 0; count < featuredJsonArray.length(); count++) 
				{
					JSONObject currentElement = featuredJsonArray.getJSONObject(count);
					String elementTitle =null;
					if ((!currentElement.has("category")) && !currentElement.has("asset")){
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						continue;
					}
					
					
					
					if(currentElement.has("productGroup"))
					{
						if(currentElement.getJSONObject("productGroup").has("title"))
							elementTitle = currentElement.getJSONObject("category").getString("title").trim();
						else 
							elementTitle = currentElement.getString("title").trim();
					}
					
					else if(currentElement.has("category"))
					{	if(currentElement.getJSONObject("category").has("title"))
									elementTitle = currentElement.getJSONObject("category").getString("title").trim();
						else 
									elementTitle = currentElement.getString("title").trim();
					
					}
					else if(currentElement.has("asset"))
					{
						if(currentElement.getJSONObject("asset").has("title"))
							elementTitle = currentElement.getJSONObject("asset").getString("title").trim();
						else 
							elementTitle = currentElement.getString("title").trim();
							
					}
					if(elementTitle!=null && elementTitle.length()>0)
					{
						solo.sleep(TestConstants.SLEEP_TIME_500);
						assertTrue(solo.waitForText(elementTitle));
					}
					
					solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
				}
			
			}
		} catch (Exception e) 
		{
			Log.e(this.getClass().getCanonicalName(), "Failed to complete the test XidioHomeActivityFeaturedListTitle " , e);
			assertTrue(false);
		}
		
		solo.sleep(2000);
	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
