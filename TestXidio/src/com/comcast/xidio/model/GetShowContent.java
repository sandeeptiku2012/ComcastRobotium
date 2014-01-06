package com.comcast.xidio.model;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.comcast.xidio.core.common.XidioAsynTask;
import com.xfinity.xidio.core.URLFactory;
import com.xfinity.xidio.vimond.models.BaseObject;
import com.xfinity.xidio.vimond.models.Show;

public class GetShowContent 
{
	private static GetShowContent instance;

	public static synchronized GetShowContent getInstance()
	{
		if (instance == null)
			instance = new GetShowContent();
		return instance;
	}

	public JSONArray getShowContent(String showId) 
	{
		
		Show showObj = new Show();
		showObj.setId(Long.parseLong(showId));
		BaseObject showObj1 = showObj;
			
			try{
				JSONObject temp=new XidioAsynTask().execute(URLFactory.getShowContentURL(showObj1)).get();		
		
		JSONArray arrayToReturn = null;
			if(temp.has("categories"))
				if(temp.getJSONObject("categories").has("category"))
				{
					try{
						arrayToReturn=temp.getJSONObject("categories").getJSONArray("category");
					}
					catch(Exception e)
					{
						arrayToReturn=new JSONArray();
						arrayToReturn.put(temp.getJSONObject("categories").getJSONObject("category"));
					}
				}
			
			return arrayToReturn;
			
			
		} catch (Exception e) {
			Log.e("Exception occured in get show content", e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;

		
				
	}

}

