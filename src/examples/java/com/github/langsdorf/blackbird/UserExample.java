package com.github.langsdorf.blackbird;

import com.github.langsdorf.blackbird.api.UserAPI;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.user.User;

public class UserExample {

	public static void main(String[] args) throws TwitterException {
		BlackBird blackBird = new BlackBird(args[0], args[1], args[2], args[3]);
		
		blockUser(blackBird, "finkd"); //blocking the user called "finkd"
		unblockUser(blackBird, "finkd"); //unblocking the user called "finkd"
		muteUser(blackBird, "finkd"); //muting the user called "finkd"
		unmuteUser(blackBird, "finkd");  //unmuting the user called "finkd"
		followUser(blackBird, "finkd");  //follow the user called "finkd"
		unfollowUser(blackBird, "finkd"); //unfollow the user called "finkd"
		getFollowersName(blackBird, "thlangsdorf"); //get thlangsdorf's followers name
	}
	
	public static void blockUser(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		User user = user_api.blockUser(target_name);
		System.out.println("Blocked user name: " + user.getName());
	}
	
	public static void unblockUser(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		User user = user_api.unblockUser(target_name);
		System.out.println("Unblocked user name: " + user.getName());
	}
	
	public static void muteUser(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		User user = user_api.muteUser(target_name);
		System.out.println("Muting user: " + user.getName());
	}
	
	public static void unmuteUser(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		User user = user_api.unmuteUser(target_name);
		System.out.println("Unmuting user: " + user.getName());
	}
	
	
	public static void followUser(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		User user = user_api.followUser(target_name);
		System.out.println("Following " + user.getName());
	}
	
	public static void unfollowUser(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		User user = user_api.unfollowUser(target_name);
		System.out.println("Unfollowing " + user.getName());
	}
	
	public static void getFollowersName(BlackBird session, String target_name) throws TwitterException {
		UserAPI user_api =  session.getUserAPI();
		System.out.println(target_name + "'s followers:");
		for (User user : user_api.getFollowersList(target_name, -1, "")) {
			String name = user.getName();
			System.out.println(name);
		}
	}

}
