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

public class XidioHomeFeaturedHighlightedContentTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;

	public XidioHomeFeaturedHighlightedContentTest() {
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

	public void testXidioHomeFeaturedHighlightedContent() 
	{
		LoginUtil.authenticateUser(solo, getInstrumentation());
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);

		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		try{
			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{


				for(int j=0;j<featuredJsonArray.length();j++)
				{
					JSONObject currElement=featuredJsonArray.getJSONObject(j);
					String level = currElement.getJSONObject("category").getString("level").trim();
					if(level.equalsIgnoreCase("SHOW")||level.equalsIgnoreCase("SUB_SHOW")||currElement.has("asset"))
					{  
						assertTrue(solo.searchText(currElement.getString(TestConstants.TITLE).trim()));
					}

				}	
			}
		}


		catch(Exception e)
		{
			Log.d("Exception from test XidioHomeFeaturedHighlightedContent : ", e.getLocalizedMessage());
		}



	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
