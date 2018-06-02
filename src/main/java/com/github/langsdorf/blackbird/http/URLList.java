package com.github.langsdorf.blackbird.http;

public interface URLList {

	String BASE_URL = "https://api.twitter.com/1.1/";

	String DIRECT_MESSAGE = "direct_messages/events/";
	String DIRECT_MESSAGE_SHOW = BASE_URL + DIRECT_MESSAGE + "show.json?id=VALUE1";
	String DIRECT_MESSAGE_LIST = BASE_URL + DIRECT_MESSAGE + "list.json$?count=VALUE1$";
	String DIRECT_MESSAGE_NEW = BASE_URL + DIRECT_MESSAGE + "new.json";
	String DIRECT_MESSAGE_DESTROY = BASE_URL + DIRECT_MESSAGE + "destroy.json?id=VALUE1";

	String ACCOUNT = "account/";
	String PROTECTED_RESOURCE_URL = BASE_URL + ACCOUNT + "verify_credentials.json";
	String ACCOUNT_REMOVE_PROFILE_BANNER = BASE_URL + ACCOUNT + "remove_profile_banner.json";
	String ACCOUNT_UPDATE_PROFILE_BANNER = BASE_URL + ACCOUNT + "update_profile_banner.json?$PARAM$";
	String ACCOUNT_UPDATE_PROFILE_IMAGE = BASE_URL + ACCOUNT + "update_profile_image.json?$PARAM$";
	String ACCOUNT_SETTINGS = BASE_URL + ACCOUNT + "settings.json?$PARAM$";
	String ACCOUNT_UPDATE_PROFILE = BASE_URL + ACCOUNT + "update_profile.json?$PARAM$";
	String MESSAGE_CREATE_PATTERN = "{\"event\":{\"type\":\"message_create\",\"message_create\":{\"target\":{\"recipient_id\":\"$ID$\"},\"message_data\":{\"text\":\"$TEXT$\"}}}}";

	String USERS = "users/";
	String USERS_SHOW = BASE_URL + USERS + "show.json?$PARAM$";
	String USERS_REPORT_SPAM = BASE_URL + USERS + "report_spam.json?$PARAM$&$PARAM2$";
	String USERS_PROFILE_BANNER = BASE_URL + USERS + "profile_banner.json?$PARAM$";
	String USERS_LOOKUP = BASE_URL + USERS + "lookup.json?$PARAM$";

	String BLOCKS = "blocks/";
	String BLOCKS_CREATE = BASE_URL + BLOCKS + "create.json?$PARAM$";
	String BLOCKS_DESTROY = BASE_URL + BLOCKS + "destroy.json?$PARAM$";
	String BLOCKS_ID_LIST = BASE_URL + BLOCKS + "ids.json";
	String BLOCKS_USERS_LIST = BASE_URL + BLOCKS + "list.json";

	String MUTES = "mutes/users/";
	String MUTES_CREATE = BASE_URL + MUTES + "create.json?$PARAM$";
	String MUTES_DESTROY = BASE_URL + MUTES + "destroy.json?$PARAM$";
	String MUTES_ID_LIST = BASE_URL + MUTES + "ids.json";
	String MUTES_USER_LIST = BASE_URL + MUTES + "list.json";

	String FRIENDSHIPS = "friendships/";
	String FRIENDSHIPS_CREATE = BASE_URL + FRIENDSHIPS + "create.json?$PARAM$";
	String FRIENDSHIPS_DESTROY = BASE_URL + FRIENDSHIPS + "destroy.json?$PARAM$";
	String FRIENDSHIPS_SHOW = BASE_URL + FRIENDSHIPS + "show.json?$PARAM$";
	String FRIENDSHIPS_INCOMING = BASE_URL + FRIENDSHIPS + "incoming.json";
	String FRIENDSHIPS_LOOKUP = BASE_URL + FRIENDSHIPS + "lookup.json?$PARAM$";
	String FRIENDSHIPS_OUTGOING = BASE_URL + FRIENDSHIPS + "outgoing.json?$PARAM$";

	String FOLLOWERS = "followers/";
	String FOLLOWERS_ID_LIST = BASE_URL + FOLLOWERS + "ids.json?";
	String FOLLOWERS_USER_LIST = BASE_URL + FOLLOWERS + "list.json?";

	String FOLLOWING = "friends/";
	String FOLLOWING_ID_LIST = BASE_URL + FOLLOWING + "ids.json?";
	String FOLLOWING_USER_LIST = BASE_URL + FOLLOWING + "list.json?";

	String STATUSES = "statuses/";
	String STATUSES_CREATE = BASE_URL + STATUSES + "update.json?$PARAM$";
	String STATUSES_DESTROY = BASE_URL + STATUSES + "destroy/$ID$.json";
	String STATUSES_SHOW = BASE_URL + STATUSES + "show.json?$PARAM$";
	String STATUSES_RETWEET = BASE_URL + STATUSES + "retweet/$ID$.json";
	String STATUSES_UNRETWEET = BASE_URL + STATUSES + "unretweet/$ID$.json";
	String STATUSES_LOOKUP = BASE_URL + STATUSES + "lookup.json?$PARAM$";
	String STATUSES_RETWEETS = BASE_URL + STATUSES + "retweets/$ID$.json";
	String STATUSES_RETWEETERS_IDS = BASE_URL + STATUSES + "retweeters/ids.json?";

	String FAVORITES = "favorites/";
	String FAVORITES_CREATE = BASE_URL + FAVORITES + "create.json?$PARAM$";
	String FAVORITES_DESTROY = BASE_URL + FAVORITES + "destroy.json?$PARAM$";
	String FAVORITES_LIST = BASE_URL + FAVORITES + "list.json?$PARAM$";

}
