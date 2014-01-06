package com.comcast.xidio.testCases.home.featured.details;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioHomeFeaturedSubscriptionTextTest extends ActivityInstrumentationTestCase2<FirstRun> {

	private Solo solo;
	String elementTitle =null;

	public XidioHomeFeaturedSubscriptionTextTest() {
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

	public void testXidioHomeFeaturedSubscriptionText()
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
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		//solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		
		
		try {
			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{
			
				for (int count = 0; count < featuredJsonArray.length(); count++) 
				{
					JSONObject currentElement = featuredJsonArray.getJSONObject(count);
	
						
								if(currentElement.getJSONObject("category").getString("level").contentEquals("SHOW")){
								assertTrue(solo.searchText(currentElement.getString(TestConstants.TITLE).toString()));
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
								solo.sleep(TestConstants.SLEEP_TIME_2000);
								if(solo.searchText(TestConstants.SUBSCRIBE)){
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									solo.sleep(TestConstants.SLEEP_TIME_5000);
									assertTrue(solo.searchText(TestConstants.UNSUBSCRIBE));
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									solo.sleep(TestConstants.SLEEP_TIME_5000);
									assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
								}
								else if(solo.searchText(TestConstants.UNSUBSCRIBE)){
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									solo.sleep(TestConstants.SLEEP_TIME_5000);
									assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									solo.sleep(TestConstants.SLEEP_TIME_5000);
									assertTrue(solo.searchText(TestConstants.UNSUBSCRIBE));

								}
								else{
									assertTrue(false);
								}
								break;
							}
						
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_1000);
					 }
			}
					
					
					
			
			
			
		
		}catch (Exception e) 
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
