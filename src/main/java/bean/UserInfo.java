package bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {

	@JsonProperty("Name")
	private String name;
	@JsonProperty("Screen Name")
	private String screen_name;
	@JsonProperty("ID")
	private int ID;
	@JsonProperty("Location")
	private String location;
	@JsonProperty("Created At")
	private String Created_At;	
	@JsonProperty("Description")
	private String Description;
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setScreenName(String screen_name) {
		this.screen_name=screen_name;
	}
	
	public void setID(int ID) {
		this.ID=ID;
	}
	
	public void setLocation(String location) {
		this.location=location;
	}
	
	public void setCreatedAt(String Created_At) {
		this.Created_At=Created_At;
	}
	
	public void setDescription(String Description) {
		this.Description=Description;
	}
}
