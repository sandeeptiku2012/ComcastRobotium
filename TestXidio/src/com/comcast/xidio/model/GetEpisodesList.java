package com.comcast.xidio.model;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.comcast.xidio.core.common.XidioAsynTask;
import com.xfinity.xidio.core.URLFactory;
import com.xfinity.xidio.vimond.models.BaseObject;
import com.xfinity.xidio.vimond.models.Show;

public class GetEpisodesList 
{
	private static GetEpisodesList instance;

	public static synchronized GetEpisodesList getInstance() {
		if (instance == null)
			instance = new GetEpisodesList();
		return instance;
	}

	public JSONArray getEpisodeList(String showId) throws Exception 
	{
		
		Show showObj = new Show();
		showObj.setId(Long.parseLong(showId));
		BaseObject showObj1 = showObj;
		try {
			String episodeUrl = URLFactory.getEpisodeContentURL(showObj1);
			Log.v("episodeUrl will be ", episodeUrl);
			JSONObject temp=new XidioAsynTask().execute(episodeUrl).get();
			JSONArray arrayToReturn = null;
			try{
			if(temp.has("assets"))
				if(temp.getJSONObject("assets").has("asset"))
				{
					try{
						arrayToReturn=temp.getJSONObject("assets").getJSONArray("asset");
					}
					catch(Exception e)
					{
						arrayToReturn=new JSONArray();
						arrayToReturn.put(temp.getJSONObject("assets").getJSONObject("asset"));
					}
				}
			
			return arrayToReturn;
			}
			catch(Exception e)
			{
				Log.e("Exception :", "Exceptions occured in get episode list.", e);
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		
	}
}