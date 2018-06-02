package com.github.langsdorf.blackbird.factory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.http.BasicRequest;
import com.github.langsdorf.blackbird.http.URLList;
import com.github.langsdorf.blackbird.json.JSONArray;
import com.github.langsdorf.blackbird.json.JSONException;
import com.github.langsdorf.blackbird.json.JSONObject;
import com.github.langsdorf.blackbird.tweet.Tweet;
import com.github.langsdorf.blackbird.tweet.TweetI;
import com.github.langsdorf.blackbird.user.User;
import com.github.langsdorf.blackbird.user.list.BirdList;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class TweetFactory implements TweetI, URLList {

	private BlackBird session;

	@SuppressWarnings("deprecation")
	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet createTweet(String status) throws TwitterException {
		String URL = STATUSES_CREATE.replace("$PARAM$", "status=" + URLEncoder.encode(status));
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet deleteTweet(long tweetId) throws TwitterException {
		String URL = STATUSES_DESTROY.replace("$ID$", Long.toString(tweetId));
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet showTweet(long tweetId) throws TwitterException {
		String URL = STATUSES_SHOW.replace("$PARAM$", "id=" + tweetId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<Tweet> tweetLookup(long... tweetsId) throws TwitterException {
		String URL = STATUSES_LOOKUP;

		if (tweetsId.length > 100)
			throw new TwitterException("Max 100 users per request");
		if (tweetsId.length == 1) {
			URL = URL.replace("$PARAM$", "id=" + tweetsId[0]);
		} else {
			StringJoiner sj = new StringJoiner(",");
			for (long names : tweetsId) {
				sj.add(Long.toString(names));
			}
			URL = URL.replace("$PARAM$", "id=" + sj.toString());
		}

		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return tweetListCompact(jsonArray);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet retweet(long tweetId) throws TwitterException {
		String URL = STATUSES_RETWEET.replace("$ID$", Long.toString(tweetId));

		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet unretweet(long tweetId) throws TwitterException {
		String URL = STATUSES_UNRETWEET.replace("$ID$", Long.toString(tweetId));

		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<Tweet> getRetweets(long tweetId, int count) throws TwitterException {
		String URL = STATUSES_RETWEETS.replace("$ID$", Long.toString(tweetId));
		if (count != -1)
			URL = URL + "?count=" + count;

		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return tweetListCompact(jsonArray);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getRetweetersId(long tweetId, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("id=" + tweetId);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = STATUSES_RETWEETERS_IDS + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getSession().getUserFactory().getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}
	
	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet like(long tweetId) throws TwitterException {
		String URL = FAVORITES_CREATE.replace("$PARAM$", "id=" + tweetId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}
		
		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public Tweet unlike(long tweetId) throws TwitterException {
		String URL = FAVORITES_DESTROY.replace("$PARAM$", "id=" + tweetId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getTweetCompact(jsonObject);
		}
		
		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<Tweet> getLikedTweets(String screenName, int count) throws TwitterException {
		String URL = FAVORITES_LIST.replace("$PARAM$", "screen_name=" + screenName);
		if (count != -1)
			URL = URL + "&count=" + count;
		
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return tweetListCompact(jsonArray);
		}
		
		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<Tweet> getLikedTweets(long userId, int count) throws TwitterException {
		String URL = FAVORITES_LIST.replace("$PARAM$", "user_id=" + userId);
		if (count != -1)
			URL = URL + "&count=" + count;
		
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return tweetListCompact(jsonArray);
		}
		
		throw new TwitterException("", response);
	}

	@SneakyThrows(JSONException.class)
	public Tweet getTweetCompact(JSONObject jsonObject) {
		String createdAt = jsonObject.isNull("created_at") ? "" : jsonObject.getString("created_at");
		long tweetId = jsonObject.isNull("id") ? 0 : jsonObject.getLong("id");
		String text = jsonObject.isNull("text") ? "" : jsonObject.getString("text");
		int retweetCount = jsonObject.isNull("retweet_count") ? -1 : jsonObject.getInt("retweet_count");
		int favoriteCount = jsonObject.isNull("favorite_count") ? -1 : jsonObject.getInt("favorite_count");

		JSONObject jsonUser = jsonObject.getJSONObject("user");
		User user = getSession().getUserFactory().getUserCompact(jsonUser);

		return new Tweet(tweetId, text, createdAt, retweetCount, favoriteCount, user);
	}

	@SneakyThrows(JSONException.class)
	public List<Tweet> tweetListCompact(JSONArray jsonArray) {
		List<Tweet> tweet_list = new ArrayList<Tweet>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonTweet = jsonArray.getJSONObject(i);
			tweet_list.add(getTweetCompact(jsonTweet));
		}

		return tweet_list;
	}

}
