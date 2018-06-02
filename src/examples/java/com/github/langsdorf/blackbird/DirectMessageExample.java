package com.github.langsdorf.blackbird;

import com.github.langsdorf.blackbird.api.DirectMessageAPI;
import com.github.langsdorf.blackbird.api.UserAPI;
import com.github.langsdorf.blackbird.directmessage.DirectMessage;
import com.github.langsdorf.blackbird.exception.TwitterException;

public class DirectMessageExample {

	public static void main(String[] args) throws TwitterException, InterruptedException {
		BlackBird blackBird = new BlackBird(args[0], args[1], args[2], args[3]);
		sendDM(blackBird, "thlangsdorf", "Using BlackBird! :D"); // sending a dm to "thlangsdorf"
	}

	public static void sendDM(BlackBird session, String username, String text) throws TwitterException {
		DirectMessageAPI directmessage_api = session.getDirectMessageAPI();
		UserAPI user_api = session.getUserAPI();

		long userID = user_api.getUser(username).getUserId(); // Get user id by username
		DirectMessage dm_sent = directmessage_api.sendDirectMessage(userID, text);

		System.out.println("DM ID: " + dm_sent.getId());
		System.out.println("DM Sender: " + dm_sent.getSenderId());
		System.out.println("DM Target: " + dm_sent.getRecipientId());
		System.out.println("DM Date: " + dm_sent.getCreatedAt().toString());
		System.out.println("DM Text: " + dm_sent.getMessage());
	}

}
