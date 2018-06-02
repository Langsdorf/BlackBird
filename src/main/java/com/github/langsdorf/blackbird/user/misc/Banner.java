package com.github.langsdorf.blackbird.user.misc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Banner {
	
	private String URL;
	private BannerSizes size;
	private long userId;
	private String userScreenName;

}
