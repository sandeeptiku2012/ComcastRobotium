package com.comcast.xidio.testCases.subscription.search;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;

import com.comcast.xidio.core.common.FilterObject;
import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetFeaturedList;
import com.comcast.xidio.model.GetLoginResponse;
import com.comcast.xidio.model.GetPopularList;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionSearchPopularUnsubscribedTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private String userId;	
	
	public XidioSubscriptionSearchPopularUnsubscribedTest() {
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

	public void testXidioSubscriptionSearchPopularUnsubscribed() 
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
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_C);
		solo.sendKey(KeyEvent.KEYCODE_N);
		solo.sendKey(KeyEvent.KEYCODE_N);
		ArrayList<JSONObject> filteredPopularArray = FilterObject.getInstance().getFilteredObjectList(GetCatagoryLists.getInstance().getPopularList(), "CNN");
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		JSONObject channelToCheck=null;
		try
		{
			
			
			for(JSONObject currChoice:filteredPopularArray)
			{
				
					
					if(currChoice.has("category"))
						if(currChoice.getJSONObject("category").has("level"))
							if(currChoice.getJSONObject("category").getString("level").trim().contentEquals("SHOW"))
							{
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								solo.waitForActivity(TestConstants.DETAILS_ACTIVITY);
								solo.sleep(TestConstants.SLEEP_TIME_1000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
								if(solo.searchText(TestConstants.SUBSCRIBE))
								{
									solo.sleep(TestConstants.SLEEP_TIME_1000);
									solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
									solo.sleep(TestConstants.SLEEP_TIME_5000);
								}
								solo.sleep(TestConstants.SLEEP_TIME_1000);
								solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
								solo.sleep(TestConstants.SLEEP_TIME_5000);
								
															
								channelToCheck=currChoice;
								break;								
							}
					if(!currChoice.has("productGroup"))
							solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
					
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					
				}	
		
		}
		catch (JSONException e) 
		{
			Log.e("Exception:", "Exception occured in XidioSubscriptionFeaturedApiTest test cases.", e);
		}
		solo.sendKey(KeyEvent.KEYCODE_BACK);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		JSONArray channels=GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());	
		
		boolean foundChannel = false;
		
		if(null==channels)
			assertTrue(true);
		else{
		try {
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_1000);
			for(int i=0;i<channels.length();i++)
			{
				solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				foundChannel=solo.searchText(channelToCheck.getString("title"));
				if(foundChannel)
					break;
				else
				
				{	
					solo.sendKey(KeyEvent.KEYCODE_BACK);
					solo.sleep(TestConstants.SLEEP_TIME_2000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
				}
				
			}
		} catch (JSONException e) {
			
			Log.e("Exception occured in XidioSubscriptionPopularUITest test case get channel title ", e.getLocalizedMessage());
		}
		
		assertFalse(foundChannel);
				
	}
	}
	
	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
