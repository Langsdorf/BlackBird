package com.github.langsdorf.blackbird.user.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountSettings {
	
	private String language;
	private boolean protectedUser;
	private String screenName;
	private boolean sleepTimeEnabled;
	private int sleepTimeEnd;
	private int sleepTimeStart;
	private String timezone;
	private String tzinfoName;
	private String country;
	private String countryCode;
	private String locationName;
	
	

}
