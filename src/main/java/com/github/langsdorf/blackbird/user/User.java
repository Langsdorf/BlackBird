package com.github.langsdorf.blackbird.user;

import java.util.List;
import java.util.Map;

import com.github.langsdorf.blackbird.user.misc.Relationships;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private long userId;
	private String name;
	private String screenName;
	private String location;
	private String description;
	private String profileWebsite;
	private boolean protectedProfile;
	private int followers;
	private int following;
	private String createdAt;
	private int favouritesCount;
	private String timeZone;
	private boolean verified;
	private int tweetsCount;
	private String language;
	private String profileColor;
	private String profileBannerUrl;
	private String profileImageUrl;
	private List<User> followersList;
	private List<User> followingList;
	private List<User> blockList;
	private List<User> muteList;
	private Map<User, List<Relationships>> relationships;

	public User(String screenName) {
		setScreenName(screenName);
	}
	
	public User(long userId) {
		setUserId(userId);
	}
	
}