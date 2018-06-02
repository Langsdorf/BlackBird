package com.github.langsdorf.blackbird;

import com.github.langsdorf.blackbird.api.TweetAPI;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.tweet.Tweet;

public class TweetExample {
	
	public static void main(String[] args) throws TwitterException {
		BlackBird blackBird = new BlackBird(args[0], args[1], args[2], args[3]);
		long tw_id = createTweet(blackBird, "Check out my github profile: https://github.com/Langsdorf");
		deleteTweet(blackBird, tw_id);
	}
	
	public static long createTweet(BlackBird session, String text) throws TwitterException {
		TweetAPI tweet_api = session.getTweetAPI();
		Tweet tweet = tweet_api.createTweet(text);
		System.out.println("Tweet text: " + tweet.getText());
		System.out.println("Tweet created at: " + tweet.getCreatedAt());
		System.out.println("Tweet ID: " + tweet.getTweetId());
		return tweet.getTweetId();
	}
	
	public static void deleteTweet(BlackBird session, long id) throws TwitterException {
		TweetAPI tweet_api = session.getTweetAPI();
		Tweet tweet = tweet_api.deleteTweet(id);
		System.out.println("Deleted tweet text: " + tweet.getText());
		System.out.println("Deleted tweet created at: " + tweet.getCreatedAt());
		System.out.println("Deleted tweet ID: " + tweet.getTweetId());
	}

}
