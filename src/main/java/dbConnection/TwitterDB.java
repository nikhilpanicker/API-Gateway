package dbConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class TwitterDB {
	
	 static String consumerKeyStr = "DgyA56uNzjBu9Ntq3LLaQZa4v"; 
	 static String consumerSecretStr = "BudeZE4PHdxip59ZwNsd3NoeQPmVclLWljmvT16cFDuG8JH2vh";
	 static String accessTokenStr ="710861642-rOpv6z8E24ESNmAlPwUAmPMNyu2VZZlfMWfxzeXk"; 
	 static String accessTokenSecretStr = "alC4iDnAWDCQvlh1AoOacIEdP04AUruxvEy5obbzY6P9x";
	 public Map<String, Integer> hitMiss = new HashMap<String, Integer>();

	protected OAuthConsumer oAuthConsumer;

	protected TwitterDB() {

		oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,
				consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
		hitMiss.put("failedHit", 0);
	}
}
