package com.comcast.xidio.testCases.home.featured;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.comcast.xidio.core.common.GetCatagoryLists;
import com.comcast.xidio.core.common.GetSolo;
import com.comcast.xidio.core.constant.TestConstants;
import com.comcast.xidio.loginUtil.LoginUtil;
import com.comcast.xidio.model.GetEpisodesList;
import com.comcast.xidio.model.GetShowContent;
import com.jayway.android.robotium.solo.Solo;
import com.xfinity.xidio.BaseXidioActivity;
import com.xfinity.xidio.FirstRun;
import com.xfinity.xidio.R;
import com.xfinity.xidio.core.XidioApplication;
import com.xfinity.xidio.views.VideoPlayerView;
import com.xfinity.xidio.views.XidioButton;
import com.xfinity.xidio.views.XidioNetworkImageView;

public class XidioDetailsFeaturedEpisodeIcon extends ActivityInstrumentationTestCase2<FirstRun> 
{
	private Solo solo;
	Activity activity;
	View vView ;
	XidioButton curItem;
	BitmapDrawable playIcon;
	BitmapDrawable curIcon;
	public XidioDetailsFeaturedEpisodeIcon() 
	{
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

	public void testXidioDetailsFeaturedEpisodeIcon()
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
		solo.sleep(TestConstants.SLEEP_TIME_500);




		try{
			JSONArray featuredJsonArray = GetCatagoryLists.getInstance().getFeaturedList();
			if(featuredJsonArray!=null && featuredJsonArray.length()>0)
			{

				for (int count = 0; count < featuredJsonArray.length(); count++) 
				{
					JSONObject currentElement = featuredJsonArray.getJSONObject(count);

					if(!currentElement.getString("contentType").contentEquals("bundle")){

						if(currentElement.has("asset"))
						{		 

							activity=solo.getCurrentActivity();
							vView = activity.getCurrentFocus();
							curItem = (XidioButton) vView.findViewById(R.id.item_identifier);
							playIcon = (BitmapDrawable) vView.getResources().getDrawable(R.drawable.ic_play_row_normal);
							curIcon = (BitmapDrawable) curItem.getBackground();
							assertTrue(playIcon.getBitmap()==curIcon.getBitmap());


						}
						else{
							activity=solo.getCurrentActivity();
							vView = activity.getCurrentFocus();
							curItem = (XidioButton) vView.findViewById(R.id.item_identifier);
							playIcon = (BitmapDrawable) vView.getResources().getDrawable(R.drawable.ic_play_row_normal);
							curIcon = (BitmapDrawable) curItem.getBackground();
							assertTrue(playIcon.getBitmap()!=curIcon.getBitmap());
						}
						solo.sendKey(KeyEvent.KEYCODE_DPAD_RIGHT);
						solo.sleep(TestConstants.SLEEP_TIME_1000);
					}
					
				}
			}

		}

		catch(Exception e)
		{
			Log.d("Exception from test XidioDetailsFeaturedEpisodeIcon = ", e.getLocalizedMessage());
		}
	}

	protected void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}



}
