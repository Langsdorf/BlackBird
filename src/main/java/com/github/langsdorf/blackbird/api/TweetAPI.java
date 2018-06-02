package com.github.langsdorf.blackbird.api;

import java.util.List;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.factory.TweetFactory;
import com.github.langsdorf.blackbird.tweet.Tweet;
import com.github.langsdorf.blackbird.tweet.TweetI;
import com.github.langsdorf.blackbird.user.list.BirdList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class TweetAPI implements TweetI {

	private BlackBird session;

	public Tweet createTweet(String status) throws TwitterException {
		return getFactory().createTweet(status);
	}

	public Tweet deleteTweet(long tweetId) throws TwitterException {
		return getFactory().deleteTweet(tweetId);
	}

	public Tweet showTweet(long tweetId) throws TwitterException {
		return getFactory().showTweet(tweetId);
	}

	public List<Tweet> tweetLookup(long... tweetsId) throws TwitterException {
		return getFactory().tweetLookup(tweetsId);
	}

	public Tweet retweet(long tweetId) throws TwitterException {
		return getFactory().retweet(tweetId);
	}

	public Tweet unretweet(long tweetId) throws TwitterException {
		return getFactory().unretweet(tweetId);
	}

	public List<Tweet> getRetweets(long tweetId, int count) throws TwitterException {
		return getFactory().getRetweets(tweetId, count);
	}

	public BirdList<Long> getRetweetersId(long tweetId, int count, String cursor) throws TwitterException {
		return getFactory().getRetweetersId(tweetId, count, cursor);
	}

	public Tweet like(long tweetId) throws TwitterException {
		return getFactory().like(tweetId);
	}

	public Tweet unlike(long tweetId) throws TwitterException {
		return getFactory().unlike(tweetId);
	}

	public List<Tweet> getLikedTweets(String screenName, int count) throws TwitterException {
		return getFactory().getLikedTweets(screenName, count);
	}

	public List<Tweet> getLikedTweets(long userId, int count) throws TwitterException {
		return getFactory().getLikedTweets(userId, count);
	}
	
	public TweetFactory getFactory() {
		return getSession().getTweetFactory();
	}
	
}
