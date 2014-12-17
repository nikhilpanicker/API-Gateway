APIGateway
===============



## The gateway makes a call to 4 of twitter’s api. 

1)Posting a Tweet to timeline(of @NameSake5).

2)Tweet Search.

3)Trending list.

4)User Info of a twitter handle


Gateway also has a feature to get analytics of which API is called most and fail count on hitting an API.

**Features Implemented:**

oAuth authentication is used for connecting to Twitter.

The gateway is enabled with caching. Caching is done with in-built cacheable method of springboot framework. Caching is also done using a map in case of caching User Info of a twitter handle.
I have also used Etag filter for caching and saving bandwidth.

Mocking up a service to backlist/whitelist IP.

Analytics of which API is called most and total failures in executing an API request.

**Running the Application:**

  Navigate inside the root folder and run following command in cmd:
  
  i)	gradle build
  
  gradle bootRun
  
  OR
  
  ii)	java -jar build/libs/muleSoftGateway-0.0.1-SNAPSHOT

##Testing

Using Google Crome’s **Postman** app test can be performed

**•	Posting a tweet**

POST http://localhost:8081/tweet

Pass following json raw document in postman:
  {
 "newTweet": "Done with complete MuleSoft intern project"
}

Check on following timeline to see the tweet you posted. 
https://twitter.com/Namesake5

**Note:** If you use hashtag then the tweet fails to get posted.


**•	Tweet Search**

This API call will display 10 popular tweets of the search query inserted.

GET http://localhost:8081/tweetsearch/{seacrh_query}

Example: GET http://localhost:8081/tweetsearch/cfc

**•	Trends**

This API call will show top 10 trending topics.

GET http://localhost:8081/trends/{location_id}

Example:

To get top 10 worldwide trends

GET http://localhost:8081/trends/1


To get top 10 San Francisco trends

GET http://localhost:8081/trends/2487956


**•	User Info**

This API will return user information like name, screen name, ID, created at and description of the twitter handle being passed

GET http://localhost:8081/userinfo/{twitter_handle}

Example: GET http://localhost:8081/userinfo/afc

I have used map to cache User Information of a twitter handle since the user information are not expected to change as compared to other API calls.

Note that the time required in getting the result on first and second user information request of same user. The second request will be retrieved much quicker because it will fetch data from cache(map).


**•	Blacklist/whitelist of IP.**

I have made a mock up api just to blacklist and whitelist IP while accessing User Info.

Example: 

GET http://localhost:8081//blacklistme

After calling this API your IP will be blacklisted. Now if try to access User Info AP. It will return null values

GET http://localhost:8081/userinfo/cfc


To whitelist the IP, I have created following api. On Get request to following API your IP will be whitelisted.

GET http://localhost:8081//whitelistme


Now if you access User Info API you will get the data.

GET http://localhost:8081/userinfo/cfc





**•	Analytics**

This API will provide information regarding total number of times different API’s have been accessed and no. of times it failed to retrieve result.
GET http://localhost:8081/getanalytics


**I have deployed the application on Amazon Web Service EC 2 instance.**

Replace localhost in above url with 54.183.58.249.

Example:

Getting Trends: http://54.183.58.249:8081/trends/1

Posting tweet: http://54.183.58.249:8081/tweet

User Info: http://54.183.58.249:8081/userinfo/cfc

Tweet Search: http://54.183.58.249:8081/tweetsearch/Arsenal

Analytics: http://54.183.58.249:8081/getanalytics

Blacklisting IP: http://54.183.58.249:8081//blacklistme

Whitelist IP: http://54.183.58.249:8081//whitelistme


**Note: Testing on AWS will be slow because of limited space on my server instance.**

