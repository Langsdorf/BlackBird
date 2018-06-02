package com.github.langsdorf.blackbird.api;

import java.util.List;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.factory.UserFactory;
import com.github.langsdorf.blackbird.user.User;
import com.github.langsdorf.blackbird.user.UserI;
import com.github.langsdorf.blackbird.user.list.BirdList;
import com.github.langsdorf.blackbird.user.misc.AccountSettings;
import com.github.langsdorf.blackbird.user.misc.Banner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserAPI implements UserI {

	private BlackBird session;

	public AccountSettings getAccountSettings() throws TwitterException {
		return getFactory().getAccountSettings();
	}

	public List<Banner> getProfileBanner(String screenName) throws TwitterException {
		return getFactory().getProfileBanner(screenName);
	}

	public List<Banner> getProfileBanner(long userId) throws TwitterException {
		return getFactory().getProfileBanner(userId);
	}

	public void removeProfileBanner() throws TwitterException {
		getFactory().removeProfileBanner();
	}

	public AccountSettings updateAccountSettings(boolean sleep_time_enabled, int start_sleep_time, int end_sleep_time,
			String time_zone, int trend_location_woeid, String lang) throws TwitterException {
		return getFactory().updateAccountSettings(sleep_time_enabled, start_sleep_time, end_sleep_time, time_zone,
				trend_location_woeid, lang);
	}

	public User updateProfile(String name, String url, String location, String description, String profile_link_color)
			throws TwitterException {
		return getFactory().updateProfile(name, url, location, description, profile_link_color);
	}

	public void updateProfileBanner(String image) throws TwitterException {
		getFactory().updateProfileBanner(image);
	}

	public void updateProfileBanner(String image, int width, int height, int offset_left, int offset_top)
			throws TwitterException {
		getFactory().updateProfileBanner(image, width, height, offset_left, offset_top);
	}

	public void updateProfileImage(String image) throws TwitterException {
		getFactory().updateProfileImage(image);
	}

	public BirdList<Long> getBlockIDList(String cursor) throws TwitterException {
		return getFactory().getBlockIDList(cursor);
	}

	public BirdList<Long> getBlockIDList() throws TwitterException {
		return getFactory().getBlockIDList();
	}

	public BirdList<User> getBlockUserList(String cursor) throws TwitterException {
		return getFactory().getBlockUserList(cursor);
	}

	public BirdList<User> getBlockUserList() throws TwitterException {
		return getFactory().getBlockUserList();
	}

	public BirdList<Long> getMutesIDList(String cursor) throws TwitterException {
		return getFactory().getMutesIDList(cursor);
	}

	public BirdList<User> getMutesUserList(String cursor) throws TwitterException {
		return getFactory().getMutesUserList(cursor);
	}

	public BirdList<Long> getMutesIDList() throws TwitterException {
		return getFactory().getMutesIDList();
	}

	public BirdList<User> getMutesUserList() throws TwitterException {
		return getFactory().getMutesUserList();
	}

	public User blockUser(String screenName) throws TwitterException {
		return getFactory().blockUser(screenName);
	}

	public User blockUser(long userId) throws TwitterException {
		return getFactory().blockUser(userId);
	}

	public User unblockUser(String screenName) throws TwitterException {
		return getFactory().unblockUser(screenName);
	}

	public User unblockUser(long userId) throws TwitterException {
		return getFactory().unblockUser(userId);
	}

	public User muteUser(String screenName) throws TwitterException {
		return getFactory().muteUser(screenName);
	}

	public User muteUser(long userId) throws TwitterException {
		return getFactory().muteUser(userId);
	}

	public User unmuteUser(String screenName) throws TwitterException {
		return getFactory().unmuteUser(screenName);
	}

	public User unmuteUser(long userId) throws TwitterException {
		return getFactory().unmuteUser(userId);
	}

	public User reportSpam(String screenName, boolean block) throws TwitterException {
		return getFactory().reportSpam(screenName, block);
	}

	public User reportSpam(long userId, boolean block) throws TwitterException {
		return getFactory().reportSpam(userId, block);
	}

	public BirdList<Long> getFollowersIDList(String screenName, int count, String cursor) throws TwitterException {
		return getFactory().getFollowersIDList(screenName, count, cursor);
	}

	public BirdList<Long> getFollowersIDList(long userId, int count, String cursor) throws TwitterException {
		return getFactory().getFollowersIDList(userId, count, cursor);
	}

	public BirdList<User> getFollowersList(String screenName, int count, String cursor) throws TwitterException {
		return getFactory().getFollowersList(screenName, count, cursor);
	}

	public BirdList<User> getFollowersList(long userId, int count, String cursor) throws TwitterException {
		return getFactory().getFollowersList(userId, count, cursor);
	}

	public BirdList<Long> getFollowingIDList(String screenName, int count, String cursor) throws TwitterException {
		return getFactory().getFollowingIDList(screenName, count, cursor);
	}

	public BirdList<Long> getFollowingIDList(long userId, int count, String cursor) throws TwitterException {
		return getFactory().getFollowingIDList(userId, count, cursor);
	}

	public BirdList<User> getFollowingList(String screenName, int count, String cursor) throws TwitterException {
		return getFactory().getFollowingList(screenName, count, cursor);
	}

	public BirdList<User> getFollowingList(long userId, int count, String cursor) throws TwitterException {
		return getFactory().getFollowingList(userId, count, cursor);
	}

	public BirdList<Long> getIncomingRequests(String cursor) throws TwitterException {
		return getFactory().getIncomingRequests(cursor);
	}

	public List<User> relationsLookup(String... screen_names) throws TwitterException {
		return getFactory().relationsLookup(screen_names);
	}

	public List<User> relationsLookup(long... usersId) throws TwitterException {
		return getFactory().relationsLookup(usersId);
	}

	public BirdList<Long> getOutcomingRequests(String cursor) throws TwitterException {
		return getFactory().getOutcomingRequests(cursor);
	}

	public BirdList<User> userLookup(String... screenNames) throws TwitterException {
		return getFactory().userLookup(screenNames);
	}

	public BirdList<User> userLookup(long... usersId) throws TwitterException {
		return getFactory().userLookup(usersId);
	}

	public User getUser(String screenName) throws TwitterException {
		return getFactory().getUser(screenName);
	}

	public User getUser(long userId) throws TwitterException {
		return getFactory().getUser(userId);
	}

	public User followUser(String screenName) throws TwitterException {
		return getFactory().followUser(screenName);
	}

	public User followUser(long userId) throws TwitterException {
		return getFactory().followUser(userId);
	}

	public User unfollowUser(String screenName) throws TwitterException {
		return getFactory().unfollowUser(screenName);
	}

	public User unfollowUser(long userId) throws TwitterException {
		return getFactory().unfollowUser(userId);
	}

	public UserFactory getFactory() {
		return getSession().getUserFactory();
	}

}
