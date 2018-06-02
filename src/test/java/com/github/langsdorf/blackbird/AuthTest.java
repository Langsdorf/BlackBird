package com.github.langsdorf.blackbird;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.URLBasedOAuth;
import com.github.langsdorf.blackbird.api.TweetAPI;
import com.github.langsdorf.blackbird.exception.TwitterException;

public class AuthTest {

	public static void main(String[] args) throws TwitterException {
		String consumerKey = "";
		String consumerSecret = "";
		String accessToken = "";
		String accessTokenSecret = "";
		testAuth1(consumerKey, consumerSecret);
		testAuth2(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	public static void testAuth1(String consumerKey, String consumerSecret) throws TwitterException {
		URLBasedOAuth oauthURL = new URLBasedOAuth(consumerKey, consumerSecret);
		Scanner in = new Scanner(System.in);
		System.out.println(oauthURL.getAuthorizationUrl());

		String pin = in.nextLine();

		BlackBird blackBird = oauthURL.authenticate(pin);
		TweetAPI tweet_api = blackBird.getTweetAPI();
		tweet_api.createTweet("Working! " + ThreadLocalRandom.current().nextInt(0, 100));

		in.close();
	}

	public static void testAuth2(String consumerKey, String consumerSecret, String accessToken,
			String accessTokenSecret) throws TwitterException {
		BlackBird bb = new BlackBird(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		TweetAPI tweet_api = bb.getTweetAPI();
		tweet_api.createTweet("Working! " + ThreadLocalRandom.current().nextInt(0, 100));
	}

}
