package com.comcast.xidio.testCases.home.popular;

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
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioHomePopularHighlightedContenTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;

	public XidioHomePopularHighlightedContenTest() {
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

	public void testXidioHomePopularHighlightedConten() 
	{
		LoginUtil.authenticateUser(solo,  getInstrumentation());
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);

		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		try{
			JSONArray popularJsonArray = GetCatagoryLists.getInstance().getPopularList();
			if(popularJsonArray!=null && popularJsonArray.length()>0)
			{
				for(int j=0;j<popularJsonArray.length();j++)
				{
					JSONObject currElement=popularJsonArray.getJSONObject(j);
					
						assertTrue(solo.searchText(currElement.getString(TestConstants.TITLE).trim()));
					
				}	
			}
		}


		catch(Exception e)
		{
			Log.d("Exception from test XidioDetailsActivityChange : ", e.getLocalizedMessage());
		}



	}
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
}
