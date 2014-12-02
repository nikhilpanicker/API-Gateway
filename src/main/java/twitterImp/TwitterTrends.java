package twitterImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import bean.Analytics;
import dbConnection.TwitterDB;
import demo.AppController;

public class TwitterTrends extends TwitterDB{

	int statusCode;
	List<String> result;
	@SuppressWarnings("finally")
	@Cacheable("trends")
	public List<String> getTrendsInfo(int id) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException, JSONException
	{

		try
		{
			HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/trends/place.json?id="+id);
	
			oAuthConsumer.sign(httpget);
	
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpget);
			
			String trendsResults = IOUtils.toString(httpResponse.getEntity().getContent()); 

			String subStr=trendsResults.substring(1, trendsResults.length()-1);
			JSONObject jsnobject = new JSONObject(subStr);
			JSONArray jsonArray = jsnobject.getJSONArray("trends");
			result = new ArrayList<String>();
			String name;
			
			for (int index = 0, total = jsonArray.length(); index < total; index++) 
			{
			   final JSONObject jsonObject = jsonArray.getJSONObject(index);			                            
			   name = jsonObject.getString("name");			    
			   result.add(name);
			}
			
		    System.out.println("result-->"+result);	    
	 		statusCode = httpResponse.getStatusLine().getStatusCode();	
	 		System.out.println(statusCode + ':'+ httpResponse.getStatusLine().getReasonPhrase());
	 		
		}
		
		finally
		{
			if(statusCode!=200) 
			{
				System.out.println("hitmiss finally");
				hitMiss.replace("failedHit", hitMiss.get("failedHit"),(int)hitMiss.get("failedHit")+1);
				AppController.hitcount.setFailedResponseCount((int)hitMiss.get("failedHit"));
				System.out.println("Inside failed imp"+(int)hitMiss.get("failedHit"));
			}
			return result;
		}
		
	}
}
