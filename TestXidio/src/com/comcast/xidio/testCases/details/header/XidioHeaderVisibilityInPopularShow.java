package com.comcast.xidio.testCases.details.header;

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
import com.comcast.xidio.model.GetShowContent;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioHeaderVisibilityInPopularShow extends ActivityInstrumentationTestCase2<FirstRun> {

	private Solo solo;
	String elementTitle =null;

	public XidioHeaderVisibilityInPopularShow() {
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

	public void testXidioHeaderVisibilityInHomePopularShow()
	{
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo, getInstrumentation());
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
		
		View homebutton = solo.getView(R.id.header_home_button);
		View homeText = solo.getView(R.id.header_home_text);
		View searchButton = solo.getView(R.id.header_search_button);
		View searchText = solo.getView(R.id.header_search_text);
		View subscriptionButton = solo.getView(R.id.header_subscription_button);
		View subscriptionText = solo.getView(R.id.header_subscription_text);
		View settingsButton = solo.getView(R.id.header_settings_button);
		View settingsText = solo.getView(R.id.header_settings_text);
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
								String channelContentKey = currentElement.getString("contentKey");
								JSONArray showContent = GetShowContent.getInstance().getShowContent(channelContentKey);
								if(showContent!=null){
									for(int j=0; j<showContent.length();j++){
										solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
										solo.sleep(TestConstants.SLEEP_TIME_500);
										solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
										assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
										solo.sleep(TestConstants.SLEEP_TIME_2000);
										assertFalse(homebutton.isShown());
										assertFalse(homeText.isShown());
										assertFalse(searchButton.isShown());
										assertFalse(searchText.isShown());
										assertFalse(subscriptionButton.isShown());
										assertFalse(subscriptionText.isShown());
										assertFalse(settingsButton.isShown());
										assertFalse(settingsText.isShown());
										break;
										
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
