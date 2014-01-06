package com.comcast.xidio.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.comcast.xidio.core.common.XidioAsynTask;
import com.comcast.xidio.core.utils.JsonArrayToArrayList;
import com.xfinity.xidio.core.URLFactory;

public class GetChannelSearchList
{
	private static GetChannelSearchList instance;

	public static synchronized GetChannelSearchList getInstance() 
	{
		return (instance == null) ? new GetChannelSearchList() : instance;
	}

	public ArrayList<JSONObject> getChannelSearchList(String filterText)
	{
		
		JSONArray tempArray=new JSONArray();
		try {
			JSONObject temp=new XidioAsynTask().execute(URLFactory.SearchForChannelAndShow(filterText)).get();
			
			
			if(temp.has("categories"))
				if(temp.getJSONObject("categories").has("category"))
				{
					try{
						tempArray=temp.getJSONObject("categories").getJSONArray("category");
					}
					catch(Exception e)
					{
						tempArray=new JSONArray();
						tempArray.put(temp.getJSONObject("categories").getJSONObject("category"));
					}
					
				}
				
			
			
			return JsonArrayToArrayList.getInstance().convert(tempArray);
			}
			catch (Exception e)
			{
				Log.e("Exception occured in get episodes list from search criteria", e.getLocalizedMessage());			
			}
		return null;

	}
}
