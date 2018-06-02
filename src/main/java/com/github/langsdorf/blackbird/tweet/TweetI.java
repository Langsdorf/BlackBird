package com.github.langsdorf.blackbird.tweet;

import java.util.List;

import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.user.list.BirdList;

public interface TweetI {
	
	/**
	 * Updates the authenticating user’s current status, also known as Tweeting.<br>
	 * <br>
	 * Publica um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/update.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet createTweet(String status) throws TwitterException;
	
	/**
	 * Destroys the status specified by the required ID parameter. The authenticating user must be the author of the specified status.<br>
	 * <br>
	 * Deleta um tweet. O autor do tweet deve ser o usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-destroy-id
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/destroy/:id.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet deleteTweet(long tweetId) throws TwitterException;
	
	/**
	 * Returns a single Tweet, specified by the id parameter.<br>
	 * <br>
	 * Retorna um tweet específico.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-show-id
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/show.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet showTweet(long tweetId) throws TwitterException;
	
	/**
	 * Returns fully-hydrated Tweet objects for up to 100 Tweets per request, as specified by comma-separated values passed to the id parameter.<br>
	 * <br>
	 * Retorna todos os tweets específicados (Máximo 100 tweets por request).<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-lookup
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/lookup.json
	 * 
	 * @return List<Tweet>
	 * @throws TwitterException
	 */
	List<Tweet> tweetLookup(long... tweetsId) throws TwitterException;
	
	/**
	 * Retweets a tweet. Returns the original Tweet with Retweet details embedded.<br>
	 * <br>
	 * Retweeta um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-retweet-id
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/retweet/:id.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet retweet(long tweetId) throws TwitterException;
	
	/**
	 * Untweets a retweeted status. Returns the original Tweet with Retweet details embedded.<br>
	 * <br>
	 * Remove o retweet de um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-unretweet-id
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/unretweet/:id.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet unretweet(long tweetId) throws TwitterException;
	
	/**
	 * Returns a collection of the 100 most recent retweets of the Tweet specified by the id parameter.<br>
	 * <br>
	 * Retorna os 100 retweets mais recentes de um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-retweets-id
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/retweets/:id.json
	 * 
	 * @return List<Tweet>
	 * @throws TwitterException
	 */
	List<Tweet> getRetweets(long tweetId, int count) throws TwitterException;
	
	/**
	 * Returns a collection of up to 100 user IDs belonging to users who have retweeted the Tweet specified by the id parameter.<br>
	 * <br>
	 * Retorna 100 ids de usuários que tenham dado retweet em um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-retweeters-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/statuses/retweeters/ids.json
	 * 
	 * @return BirdList<Tweet>
	 * @throws TwitterException
	 */
	BirdList<Long> getRetweetersId(long tweetId, int count, String cursor) throws TwitterException;
	
	/**
	 * Favorites (likes) the Tweet specified in the ID parameter as the authenticating user. Returns the favorite Tweet when successful.<br>
	 * <br>
	 * Favorita (like) um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-favorites-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/favorites/create.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet like(long tweetId) throws TwitterException;
	
	/**
	 * Unfavorites (un-likes) the Tweet specified in the ID parameter as the authenticating user. Returns the un-liked Tweet when successful.<br>
	 * <br>
	 * Desfavorita (un-like) um tweet.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-favorites-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/favorites/destroy.json
	 * 
	 * @return Tweet
	 * @throws TwitterException
	 */
	Tweet unlike(long tweetId) throws TwitterException;
	
	/**
	 * Returns the 20 most recent Tweets liked by the authenticating or specified user.<br>
	 * <br>
	 * Retorna os 20 mais recentes tweets que um usuário curtiu.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-favorites-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/favorites/list.json
	 * 
	 * @return List<Tweet>
	 * @throws TwitterException
	 */
	List<Tweet> getLikedTweets(String screenName, int count) throws TwitterException;
	
	/**
	 * Returns the 20 most recent Tweets liked by the authenticating or specified user.<br>
	 * <br>
	 * Retorna os 20 mais recentes tweets que um usuário curtiu.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-favorites-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/favorites/list.json
	 * 
	 * @return List<Tweet>
	 * @throws TwitterException
	 */
	List<Tweet> getLikedTweets(long userId, int count) throws TwitterException;
	
}