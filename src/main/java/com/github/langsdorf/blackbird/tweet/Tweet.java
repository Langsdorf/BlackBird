package com.github.langsdorf.blackbird.tweet;

import com.github.langsdorf.blackbird.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
	
	private long tweetId;
	private String text;
	private String createdAt;
	private int retweetCount;
	private int favoriteCount;
	private User user;

}
