package bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Analytics {
	
	@JsonProperty("No of Times TweetPost API used ")
	private int postedCount;
	
	@JsonProperty("No of Times User Info API used")
	private int userInfoCount;
	
	@JsonProperty("No of Times Trends API used")
	private int trendsCount;
	
	@JsonProperty("No of Times Tweet Search API used")
	private int tweetSearchCount;
	
	@JsonProperty("Failed response for API call")
	private int failedResponseCount;
	
	public int getPostedCount() {
		return postedCount;
	}
	
	public int getUserInfoCount() {
		return userInfoCount;
	}
	
	public int getFailedResponseCount() {
		return failedResponseCount;
	}
	
	public int getTrendsCount() {
		return trendsCount;
	}
	
	public int getTweetSearchCount() {
		return tweetSearchCount;
	}
	
	public void setPostedCount(int postedCount) {
		this.postedCount=postedCount;
	}
	
	public void setUserInfoCount(int userInfoCount) {
		this.userInfoCount=userInfoCount;
	}
	
	public void setTrendsCount(int trendsCount) {
		this.trendsCount=trendsCount;
	}
	
	public void setTweetSearchCount(int tweetSearchCount) {
		this.tweetSearchCount=tweetSearchCount;
	}
	
	public void setFailedResponseCount(int failedResponseCount) {
		this.failedResponseCount=failedResponseCount;
	}

}
