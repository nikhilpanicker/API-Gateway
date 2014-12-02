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
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import dbConnection.TwitterDB;
import demo.AppController;

public class TweetSearch extends TwitterDB{

	int statusCode;
	List<String> searchresult;
	
	@Cacheable("tweetsearch")
	public List<String> getTweets(String searchStr) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException
	{

		try{
		//HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q="+searchStr+"&result_type=popular&count=10&geocode=37.7771187,-122.4196396,2mi");
			HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q="+searchStr+"&result_type=popular&count=10");
		
		oAuthConsumer.sign(httpget);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpget);
		
		String searchResults = IOUtils.toString(httpResponse.getEntity().getContent()); 

		JSONObject jsnobject = new JSONObject(searchResults);
		JSONArray jsonArray = jsnobject.getJSONArray("statuses");

		searchresult = new ArrayList<String>();
		String status;
		
		for (int index = 0, total = jsonArray.length(); index < total; index++) 
		{
		   final JSONObject jsonObject = jsonArray.getJSONObject(index);			                            
		   status = jsonObject.getString("text"); 			    
		   searchresult.add(status);
		}
	     
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
			return searchresult;
		}

	}
}
