/**
 * 
 */
package com.apress.isf.spring.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

/**
 * @author Felipe Gutierrez
 *
 */
@Component("documentTweet")
public class DocumentTweet {

	@Autowired
	Twitter tweet;
	
	public void tweet(String text){
		tweet.timelineOperations().updateStatus(text);
	}
}
