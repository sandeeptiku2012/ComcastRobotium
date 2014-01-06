package com.comcast.xidio.testCases.subscription;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.comcast.cil.scalerow.ContentRowGrid;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetShowContent;
import com.comcast.xidio.model.GetSubscriptionList;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;

public class XidioSubscriptionShowHighlightTest extends ActivityInstrumentationTestCase2<FirstRun>
{
	private Solo solo;
	private JSONObject currChannel;
	
	public XidioSubscriptionShowHighlightTest() {
		super(FirstRun.class);
	}

	@Override
	protected void setUp() throws Exception {

		GetSolo.getInstance().setUpSolo(getInstrumentation(),getActivity());
		solo=GetSolo.getInstance().getSoloObject();
		solo.sleep(TestConstants.SLEEP_TIME_SETUP);
		super.setUp();
	}

	public void testXidioSubscriptionShowHighlight() 
	{

		//passing through the first Run Activity
		solo.waitForActivity(TestConstants.FIRST_RUN);
		solo.sleep(TestConstants.SLEEP_TIME_1000);
		LoginUtil.authenticateUser(solo,  getInstrumentation());
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
//		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.waitForActivity(TestConstants.MAIN_ACTIVITY);
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		
		//starting the test main Activity
		
		
		JSONArray subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser()); 
		
		
		
					
					
		
		if(subscribedChannels.length()==0 || subscribedChannels==null)
		{
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			assertTrue(solo.searchText(TestConstants.SUBSCRIBE));
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			solo.sendKey(KeyEvent.KEYCODE_BACK);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
			subscribedChannels = GetSubscriptionList.getInstance().getSubscriptionList(XidioApplication.getLastLoggedInUser());
			
		}
		
			
		solo.sleep(TestConstants.SLEEP_TIME_5000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_UP);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
		assertTrue(solo.searchText(TestConstants.SUBSCRIPTIONS));
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		
		for(int i=0;i<subscribedChannels.length();i++)
		{
			solo.sleep(TestConstants.SLEEP_TIME_2000);
			solo.sendKey(KeyEvent.KEYCODE_DPAD_CENTER);
			assertTrue(solo.waitForActivity(TestConstants.DETAILS_ACTIVITY));
			solo.sleep(TestConstants.SLEEP_TIME_5000);
			try {
				 currChannel = subscribedChannels.getJSONObject(i);
				String subsContentKey  = currChannel.getString("@id");
				JSONArray subsShowContent = GetShowContent.getInstance().getShowContent(subsContentKey);
				if(subsShowContent!=null){
					solo.sleep(TestConstants.SLEEP_TIME_1000);
					solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
					solo.sleep(TestConstants.SLEEP_TIME_500);
					JSONObject showsList = subsShowContent.getJSONObject(0);
					String title =  showsList.getString("title");
					ContentRowGrid grid = (ContentRowGrid) solo.getCurrentActivity().findViewById(R.id.details_rows_container);
					View currView = grid.findFocus();
					TextView titleText= (TextView)currView.findViewById(R.id.item_title);
					String TitleString = (String) titleText.getText();
					assertEquals(title,TitleString);
					assertTrue(currView.isFocused());
					ImageView image = (ImageView)currView.findViewById(R.id.img_view);
					int imgHeight = image.getHeight();
					int imgWidth = image.getWidth();
					int SHOW_WIDTH_SELECTED = (int) (solo.getCurrentActivity().getResources().getDimension(R.dimen.show_tile_image_selected_width) * XidioApplication.WidthConversion);
					int SHOW_HEIGHT_SELECTED = (int) (solo.getCurrentActivity().getResources().getDimension(R.dimen.show_tile_image_selected_height) * XidioApplication.WidthConversion);
					assertEquals(SHOW_HEIGHT_SELECTED,imgHeight);
					assertEquals(SHOW_WIDTH_SELECTED,imgWidth);
					break;


				} 
			else{
				solo.sendKey(KeyEvent.KEYCODE_BACK);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				solo.sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
				solo.sleep(TestConstants.SLEEP_TIME_2000);
				continue;
				
			}
		}
				catch (JSONException e) {
				
				Log.e("Exception:", "Exception occured in XidioSubscriptionShowHighlight test", e);
			}
		}
		solo.sendKey(KeyEvent.KEYCODE_BACK);
		solo.sleep(TestConstants.SLEEP_TIME_2000);
		assertTrue(solo.waitForActivity(TestConstants.MAIN_ACTIVITY));
		
		
	}

	@Override
	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}
	
}
