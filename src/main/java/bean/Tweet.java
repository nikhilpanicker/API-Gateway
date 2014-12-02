package bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
	
	@JsonProperty("newTweet")
	private String newTweet;
	
	public String getTweetString() {
		return newTweet;
	}

}
