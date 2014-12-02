package twitterImp;

import java.io.IOException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import bean.Analytics;
import bean.UserInfo;
import dbConnection.TwitterDB;
import demo.AppController;

@SuppressWarnings("deprecation")
public class TwitterUserInfo extends TwitterDB {
	UserInfo userInfoObj;
	HttpClient httpClient;
	Analytics missCount;
	int statusCode;
	
	public  TwitterUserInfo() {
		// TODO Auto-generated constructor stub
		 userInfoObj = new UserInfo();
		 httpClient = new DefaultHttpClient();
	}

	@Cacheable("userinfo")
	public UserInfo getUserInfo(String screenName) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException
	{

		try
		{
			HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/users/show.json?screen_name="+screenName);
			oAuthConsumer.sign(httpget);
	
			HttpResponse httpResponse = httpClient.execute(httpget);
			
			statusCode = httpResponse.getStatusLine().getStatusCode();
			String returnStr = IOUtils.toString(httpResponse.getEntity().getContent());
			JSONObject obj = new JSONObject(returnStr);
	 
			userInfoObj.setName(obj.getString("name"));
			userInfoObj.setScreenName(obj.getString("screen_name")); 
			userInfoObj.setID(obj.getInt("id")); 
			userInfoObj.setLocation(obj.getString("location")); 
			userInfoObj.setDescription(obj.getString("description"));
			userInfoObj.setCreatedAt(obj.getString("created_at"));
	
			System.out.println("This"+statusCode + ':'+ httpResponse.getStatusLine().getReasonPhrase());
			httpget.releaseConnection();
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
		}
		
		
		return userInfoObj;
	}
}
