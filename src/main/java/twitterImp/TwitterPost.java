package twitterImp;

import java.io.IOException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import dbConnection.TwitterDB;
import demo.AppController;

public class TwitterPost extends TwitterDB {
	String encodedUrl = null;
	String returnStatus;
	ReplaceString replaceStrObj = new ReplaceString();
	int statusCode;
	
	public String postTweet(String tweetStr) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException
	{
		System.out.println("String in "+tweetStr);
		String outString = replaceStrObj.replaceSpa(tweetStr);
		System.out.println("String out"+outString);
		
		try 
		{
			HttpPost httpPost = new HttpPost(
			"https://api.twitter.com/1.1/statuses/update.json?status="+outString+".");
	
			oAuthConsumer.sign(httpPost);
	
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpPost);
	
			statusCode = httpResponse.getStatusLine().getStatusCode();
	
			System.out.println("Status is"+statusCode + ':'+ httpResponse.getStatusLine().getReasonPhrase());
			System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
			returnStatus="Successfully Posted Twitter Status!!";
		}
		finally
		{
			if(statusCode!=200 & statusCode!=403) 
			{
				int temp = (int)hitMiss.get("failedHit")+1;
				hitMiss.replace("failedHit", hitMiss.get("failedHit"),temp);
				AppController.hitcount.setFailedResponseCount((int)hitMiss.get("failedHit"));
				returnStatus="Status Failed";
			}
			if(statusCode==403) 
			{
				returnStatus="Duplicate Status! Please post different one!";
			}
			
		}
		
		return returnStatus;
	}

}
