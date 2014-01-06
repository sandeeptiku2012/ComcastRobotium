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
import com.comcast.xidio.model.GetShowContent;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioHomeChannelShowCountTest extends
		ActivityInstrumentationTestCase2<FirstRun> {

	private Solo solo;
	String elementTitle = null;

	public XidioHomeChannelShowCountTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {
		GetSolo.getInstance().setUpSolo(getInstrumentation(), getActivity());
		solo = GetSolo.getInstance().getSoloObject();
		GetCatagoryLists.getInstance().storeBasicLists(
			XidioApplication.getLastLoggedInUser(),
				XidioApplication.getLastSessionId());
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();

	}

	public void testXidioHomePopularChannelShowCount() {
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
		solo.sleep(TestConstants.SLEEP_TIME_500);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		 solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_500);
		// solo.sendKey(KeyEvent.KEYCODE_DPAD_LEFT);

		try {
			JSONArray popularJsonArray = GetCatagoryLists.getInstance().getPopularList();
			if(popularJsonArray!=null && popularJsonArray.length()>0)
			{
			
				for (int count = 0; count < popularJsonArray.length(); count++) 
				{
					JSONObject currentElement = popularJsonArray.getJSONObject(count);
	
					
						
							 if(currentElement.has("category"))
							{	if(currentElement.getJSONObject("category").getString("level").contentEquals("SHOW"))
								{	 
								
								ContentRowGrid grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.rows_container);
								View currView = grid.findFocus();
								TextView descText= (TextView)currView.findViewById(R.id.item_desc);
								String descString = (String) descText.getText();
								if(descString.equalsIgnoreCase("0 shows")){
								
									assertNull(GetShowContent.getInstance().getShowContent(currentElement.getString("contentKey")));
								}
								else{
									assertNotNull(GetShowContent.getInstance().getShowContent(currentElement.getString("contentKey")));
								}
								
							}
						}
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_1000);
					 }
					
					
					
					
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
