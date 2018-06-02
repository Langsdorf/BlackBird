package com.github.langsdorf.blackbird.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.http.BasicRequest;
import com.github.langsdorf.blackbird.http.URLList;
import com.github.langsdorf.blackbird.json.JSONArray;
import com.github.langsdorf.blackbird.json.JSONException;
import com.github.langsdorf.blackbird.json.JSONObject;
import com.github.langsdorf.blackbird.user.User;
import com.github.langsdorf.blackbird.user.UserI;
import com.github.langsdorf.blackbird.user.list.BirdList;
import com.github.langsdorf.blackbird.user.list.BirdListImp;
import com.github.langsdorf.blackbird.user.misc.AccountSettings;
import com.github.langsdorf.blackbird.user.misc.Banner;
import com.github.langsdorf.blackbird.user.misc.BannerSizes;
import com.github.langsdorf.blackbird.user.misc.Relationships;
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
public class UserFactory implements UserI, URLList {

	private BlackBird session;

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public AccountSettings getAccountSettings() throws TwitterException {
		String URL = ACCOUNT_SETTINGS;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getAccountSettingsCompact(jsonObject);
		}
		throw new TwitterException("", response);
	}

	public List<Banner> getProfileBanner(String screenName) throws TwitterException {
		return getProfileBannerCompact(screenName, 0);
	}

	public List<Banner> getProfileBanner(long userId) throws TwitterException {
		return getProfileBannerCompact("", userId);
	}

	@SneakyThrows(value = { IOException.class })
	public void removeProfileBanner() throws TwitterException {
		String URL = ACCOUNT_REMOVE_PROFILE_BANNER;
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		if (!(response.getCode() >= 200 && response.getCode() < 300)) {
			throw new TwitterException("", response);
		}
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public AccountSettings updateAccountSettings(boolean sleep_time_enabled, int start_sleep_time, int end_sleep_time,
			String time_zone, int trend_location_woeid, String lang) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("sleep_time_enabled=" + sleep_time_enabled);
		if (start_sleep_time != -1)
			sj.add("start_sleep_time=" + start_sleep_time);
		if (end_sleep_time != -1)
			sj.add("end_sleep_time=" + end_sleep_time);
		if (end_sleep_time != -1)
			sj.add("end_sleep_time=" + end_sleep_time);
		if (!time_zone.equals(""))
			sj.add("time_zone=" + time_zone);
		if (trend_location_woeid != -1)
			sj.add("trend_location_woeid=" + trend_location_woeid);
		if (!lang.equals(""))
			sj.add("lang=" + lang);

		String URL = ACCOUNT_SETTINGS.replace("$PARAM$", sj.toString());
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getAccountSettingsCompact(jsonObject);
		}
		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User updateProfile(String name, String url, String location, String description, String profile_link_color)
			throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		if (!name.equals(""))
			sj.add("name=" + name);
		if (!url.equals(""))
			sj.add("url=" + name);
		if (!location.equals(""))
			sj.add("location=" + location);
		if (!description.equals(""))
			sj.add("description=" + description);
		if (!profile_link_color.equals(""))
			sj.add("profile_link_color=" + profile_link_color);

		String URL = ACCOUNT_UPDATE_PROFILE.replace("$PARAM$", sj.toString());
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}
		throw new TwitterException("", response);
	}

	public void updateProfileBanner(String image) throws TwitterException {
		updateProfileBanner(image, -1, -1, -1, -1);
	}

	@SneakyThrows(value = { IOException.class })
	public void updateProfileBanner(String image, int width, int height, int offset_left, int offset_top)
			throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("banner=" + image);
		if (width != -1)
			sj.add("width=" + width);
		if (height != -1)
			sj.add("height=" + height);
		if (offset_left != -1)
			sj.add("offset_left=" + offset_left);
		if (offset_top != -1)
			sj.add("offset_top=" + offset_top);

		String URL = ACCOUNT_UPDATE_PROFILE_BANNER.replace("$PARAM$", sj.toString());
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		if (!(response.getCode() >= 200 && response.getCode() < 300)) {
			throw new TwitterException("", response);
		}
	}

	@SneakyThrows(value = { IOException.class })
	public void updateProfileImage(String image) throws TwitterException {
		String URL = ACCOUNT_UPDATE_PROFILE_IMAGE.replace("$PARAM$", "image=" + image);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);
		if (!(response.getCode() >= 200 && response.getCode() < 300)) {
			throw new TwitterException("", response);
		}
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getBlockIDList(String cursor) throws TwitterException {
		String URL = BLOCKS_ID_LIST + "?cursor=" + cursor;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getBlockIDList() throws TwitterException {
		String URL = BLOCKS_ID_LIST;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getBlockUserList() throws TwitterException {
		String URL = BLOCKS_USERS_LIST;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getBlockUserList(String cursor) throws TwitterException {
		String URL = BLOCKS_USERS_LIST + "?cursor=" + cursor;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getMutesIDList() throws TwitterException {
		String URL = MUTES_ID_LIST;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getMutesUserList() throws TwitterException {
		String URL = MUTES_USER_LIST;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getMutesIDList(String cursor) throws TwitterException {
		String URL = MUTES_ID_LIST + "?cursor=" + cursor;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getMutesUserList(String cursor) throws TwitterException {
		String URL = MUTES_USER_LIST + "?cursor=" + cursor;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User blockUser(String screenName) throws TwitterException {
		String URL = BLOCKS_CREATE.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User blockUser(long userId) throws TwitterException {
		String URL = BLOCKS_CREATE.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User unblockUser(String screenName) throws TwitterException {
		String URL = BLOCKS_DESTROY.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User unblockUser(long userId) throws TwitterException {
		String URL = BLOCKS_DESTROY.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User muteUser(String screenName) throws TwitterException {
		String URL = MUTES_CREATE.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User muteUser(long userId) throws TwitterException {
		String URL = MUTES_CREATE.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User unmuteUser(String screenName) throws TwitterException {
		String URL = MUTES_DESTROY.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User unmuteUser(long userId) throws TwitterException {
		String URL = MUTES_DESTROY.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User reportSpam(String screenName, boolean block) throws TwitterException {
		String URL = USERS_REPORT_SPAM.replace("$PARAM$", "screen_name=" + screenName).replace("$PARAM2$",
				"perform_block=" + block);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User reportSpam(long userId, boolean block) throws TwitterException {
		String URL = USERS_REPORT_SPAM.replace("$PARAM$", "user_id=" + userId).replace("$PARAM2$",
				"perform_block=" + block);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getFollowersIDList(String screenName, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("screen_name=" + screenName);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_ID_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getFollowersIDList(long userId, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("user_id=" + userId);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_ID_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getFollowersList(String screenName, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("screen_name=" + screenName);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_USER_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getFollowersList(long userId, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("user_id=" + userId);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_USER_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getFollowingIDList(String screenName, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("screen_name=" + screenName);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_ID_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getFollowingIDList(long userId, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("user_id=" + userId);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_ID_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getFollowingList(String screenName, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("screen_name=" + screenName);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_USER_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> getFollowingList(long userId, int count, String cursor) throws TwitterException {
		StringJoiner sj = new StringJoiner("&");
		sj.add("user_id=" + userId);
		if (count != -1)
			sj.add("count=" + count);
		if (!cursor.equals(""))
			sj.add("cursor=" + cursor);

		String URL = FOLLOWERS_USER_LIST + sj.toString();
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getIncomingRequests(String cursor) throws TwitterException {
		String URL = FRIENDSHIPS_INCOMING;
		if (!cursor.equals(""))
			URL = URL + "?cursor=" + cursor;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<User> relationsLookup(String... screen_names) throws TwitterException {
		String URL = FRIENDSHIPS_LOOKUP;

		if (screen_names.length > 100)
			throw new TwitterException("Max 100 users per request");
		if (screen_names.length == 1) {
			URL = URL.replace("$PARAM$", "screen_name=" + screen_names[0]);
		} else {
			StringJoiner sj = new StringJoiner(",");
			for (String names : screen_names) {
				sj.add(names);
			}
			URL = URL.replace("$PARAM$", "screen_name=" + sj.toString());
		}

		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return lookupCompact(jsonArray);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<User> relationsLookup(long... usersId) throws TwitterException {
		String URL = FRIENDSHIPS_LOOKUP;

		if (usersId.length > 100)
			throw new TwitterException("Max 100 users per request");
		if (usersId.length == 1) {
			URL = URL.replace("$PARAM$", "user_id=" + usersId[0]);
		} else {
			StringJoiner sj = new StringJoiner(",");
			for (long ids : usersId) {
				sj.add(Long.toString(ids));
			}
			URL = URL.replace("$PARAM$", "user_id=" + sj.toString());
		}

		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return lookupCompact(jsonArray);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<Long> getOutcomingRequests(String cursor) throws TwitterException {
		String URL = FRIENDSHIPS_OUTGOING;
		if (!cursor.equals(""))
			URL = URL + "?cursor=" + cursor;
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getIDListCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> userLookup(String... screenNames) throws TwitterException {
		String URL = USERS_LOOKUP;

		if (screenNames.length > 100)
			throw new TwitterException("Max 100 users per request");
		if (screenNames.length == 1) {
			URL = URL.replace("$PARAM$", "screen_name=" + screenNames[0]);
		} else {
			StringJoiner sj = new StringJoiner(",");
			for (String names : screenNames) {
				sj.add(names);
			}
			URL = URL.replace("$PARAM$", "screen_name=" + sj.toString());
		}

		Verb verb;
		if (screenNames.length >= 50) {
			verb = Verb.POST;
		} else {
			verb = Verb.GET;
		}

		Response response = new BasicRequest(getSession()).request(verb, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return lookupUsersCompact(jsonArray);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public BirdList<User> userLookup(long... usersId) throws TwitterException {
		String URL = USERS_LOOKUP;

		if (usersId.length > 100)
			throw new TwitterException("Max 100 users per request");
		if (usersId.length == 1) {
			URL = URL.replace("$PARAM$", "user_id=" + usersId[0]);
		} else {
			StringJoiner sj = new StringJoiner(",");
			for (Long ids : usersId) {
				sj.add(Long.toString(ids));
			}
			URL = URL.replace("$PARAM$", "user_id=" + sj.toString());
		}

		Verb verb;
		if (usersId.length >= 50) {
			verb = Verb.POST;
		} else {
			verb = Verb.GET;
		}

		Response response = new BasicRequest(getSession()).request(verb, URL);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONArray jsonArray = new JSONArray(body);
			return lookupUsersCompact(jsonArray);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User getUser(String screenName) throws TwitterException {
		String URL = USERS_SHOW.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User getUser(long userId) throws TwitterException {
		String URL = USERS_SHOW.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User followUser(String screenName) throws TwitterException {
		String URL = FRIENDSHIPS_CREATE.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User followUser(long userId) throws TwitterException {
		String URL = FRIENDSHIPS_CREATE.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User unfollowUser(String screenName) throws TwitterException {
		String URL = FRIENDSHIPS_DESTROY.replace("$PARAM$", "screen_name=" + screenName);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public User unfollowUser(long userId) throws TwitterException {
		String URL = FRIENDSHIPS_DESTROY.replace("$PARAM$", "user_id=" + userId);
		Response response = new BasicRequest(getSession()).request(Verb.POST, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			return getUserCompact(jsonObject);
		}

		throw new TwitterException("", response);
	}

	public boolean checkNull(JSONObject json, String s) {
		return json.isNull(s);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<Banner> getProfileBannerCompact(String screenName, long userId) throws TwitterException {
		String URL = USERS_PROFILE_BANNER;

		if (!screenName.equals("")) {
			URL = URL.replace("$PARAM$", "screen_name=" + screenName);
		} else {
			URL = URL.replace("$PARAM$", "user_id=" + userId);
		}

		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		List<Banner> banner_list = new ArrayList<Banner>();
		if (response.getCode() >= 200 && response.getCode() < 300) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			JSONObject sizes = jsonObject.getJSONObject("sizes");

			for (BannerSizes bs : BannerSizes.values()) {
				String sizeName = bs.name().toLowerCase();
				if (sizeName.startsWith("s")) {
					sizeName = sizeName.replace("s_", "");
				}
				JSONObject bannerJson = sizes.getJSONObject(sizeName);
				String url = bannerJson.getString("url");

				Banner banner = new Banner();

				banner.setSize(bs);
				banner.setURL(url);
				banner.setUserScreenName(screenName);

				banner_list.add(banner);
			}
		}
		return banner_list;
	}

	@SneakyThrows(value = { JSONException.class })
	public AccountSettings getAccountSettingsCompact(JSONObject jsonObject) {
		JSONObject sleeptimeJson = jsonObject.getJSONObject("sleep_time");

		JSONObject timezoneJson = jsonObject.getJSONObject("time_zone");

		JSONArray trendJson = jsonObject.getJSONArray("trend_location");
		JSONObject trend = trendJson.getJSONObject(0);

		String language = jsonObject.isNull("language") ? "" : jsonObject.getString("language");

		boolean protectedUser = jsonObject.isNull("protected") ? false : jsonObject.getBoolean("protected");

		String screen_name = jsonObject.isNull("screen_name") ? "" : jsonObject.getString("screen_name");

		boolean sleep_time_enabled = sleeptimeJson.isNull("enabled") ? false : sleeptimeJson.getBoolean("enabled");
		int sleep_time_start = sleeptimeJson.isNull("start_time") ? -1 : sleeptimeJson.getInt("start_time");
		int sleep_time_end = sleeptimeJson.isNull("end_time") ? -1 : sleeptimeJson.getInt("end_time");

		String timezone = timezoneJson.isNull("name") ? "" : timezoneJson.getString("name");
		String timezoneinfo = timezoneJson.isNull("tzinfo_name") ? "" : timezoneJson.getString("tzinfo_name");

		String country = trend.isNull("country") ? "" : trend.getString("country");
		String countryCode = trend.isNull("countryCode") ? "" : trend.getString("countryCode");
		String location_name = trend.isNull("name") ? "" : trend.getString("name");

		return new AccountSettings(language, protectedUser, screen_name, sleep_time_enabled, sleep_time_end,
				sleep_time_start, timezone, timezoneinfo, country, countryCode, location_name);
	}

	@SneakyThrows(value = { JSONException.class })
	public User getUserCompact(JSONObject jsonObject) {
		long userId = jsonObject.isNull("id") ? 0 : jsonObject.getLong("id");
		String name = jsonObject.isNull("name") ? "" : jsonObject.getString("name");
		String screenName = jsonObject.isNull("screen_name") ? "" : jsonObject.getString("screen_name");
		String location = jsonObject.isNull("location") ? "" : jsonObject.getString("location");
		String description = jsonObject.isNull("description") ? "" : jsonObject.getString("description");
		String profileWebsite = jsonObject.isNull("url") ? "" : jsonObject.getString("url");
		boolean protectedProfile = jsonObject.isNull("protected") ? false : jsonObject.getBoolean("protected");
		int followers = jsonObject.isNull("followers_count") ? -1 : jsonObject.getInt("followers_count");
		int following = jsonObject.isNull("friends_count") ? -1 : jsonObject.getInt("friends_count");
		String createdAt = jsonObject.isNull("created_at") ? "" : jsonObject.getString("created_at");
		int favouritesCount = jsonObject.isNull("favourites_count") ? -1 : jsonObject.getInt("favourites_count");
		String timeZone = jsonObject.isNull("time_zone") ? "" : jsonObject.getString("time_zone");
		boolean verified = jsonObject.isNull("verified") ? false : jsonObject.getBoolean("verified");
		int tweetsCount = jsonObject.isNull("statuses_count") ? -1 : jsonObject.getInt("statuses_count");
		String language = jsonObject.isNull("lang") ? "" : jsonObject.getString("lang");
		String profileColor = jsonObject.isNull("profile_background_color") ? ""
				: jsonObject.getString("profile_background_color");
		String profileBannerUrl = jsonObject.isNull("profile_banner_url") ? ""
				: jsonObject.getString("profile_banner_url");
		String profileImageUrl = jsonObject.isNull("profile_image_url") ? ""
				: jsonObject.getString("profile_image_url");

		return new User(userId, name, screenName, location, description, profileWebsite, protectedProfile, followers,
				following, createdAt, favouritesCount, timeZone, verified, tweetsCount, language, profileColor,
				profileBannerUrl, profileImageUrl, null, null, null, null, null);
	}

	@SneakyThrows(value = { JSONException.class })
	public BirdList<Long> getIDListCompact(JSONObject jsonObject) {
		BirdList<Long> id_list = new BirdListImp<Long>();
		JSONArray jsonArray = jsonObject.getJSONArray("ids");
		for (int i = 0; i < jsonArray.length(); i++) {
			Long id = jsonArray.getLong(i);
			id_list.add(id);
		}

		String next_cursor = jsonObject.isNull("next_cursor_str") ? "" : jsonObject.getString("next_cursor_str");
		String previous_cursor = jsonObject.isNull("previous_cursor_str") ? ""
				: jsonObject.getString("previous_cursor_str");
		id_list.setNextCursor(next_cursor);
		id_list.setPreviousCursor(previous_cursor);

		return id_list;
	}

	@SneakyThrows(value = { JSONException.class })
	public BirdList<User> getUserListCompact(JSONObject jsonObject) {
		BirdList<User> user_list = new BirdListImp<User>();
		JSONArray jsonArray = jsonObject.getJSONArray("users");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonUser = jsonArray.getJSONObject(i);
			User user = getUserCompact(jsonUser);
			user_list.add(user);
		}

		String next_cursor = jsonObject.isNull("next_cursor_str") ? "" : jsonObject.getString("next_cursor_str");
		String previous_cursor = jsonObject.isNull("previous_cursor_str") ? ""
				: jsonObject.getString("previous_cursor_str");
		user_list.setNextCursor(next_cursor);
		user_list.setPreviousCursor(previous_cursor);

		return user_list;
	}

	@SneakyThrows(value = { JSONException.class })
	public List<User> lookupCompact(JSONArray jsonArray) {
		List<User> list_user = new ArrayList<User>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Map<User, List<Relationships>> relMap = new HashMap<User, List<Relationships>>();

			User user = getUserCompact(jsonObject);

			JSONArray connections = jsonObject.getJSONArray("connections");
			List<Relationships> relList = new ArrayList<Relationships>();
			for (int i2 = 0; i2 < connections.length(); i2++) {
				Relationships rel = Relationships.valueOf(connections.getString(i2).toUpperCase());
				relList.add(rel);
			}

			relMap.put(user, relList);
			user.setRelationships(relMap);
			list_user.add(user);
		}
		return list_user;
	}

	@SneakyThrows(value = { JSONException.class })
	public BirdList<User> lookupUsersCompact(JSONArray jsonArray) {
		BirdList<User> user_list = new BirdListImp<User>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonUser = jsonArray.getJSONObject(i);
			User user = getUserCompact(jsonUser);
			user_list.add(user);
		}
		return user_list;
	}

}
