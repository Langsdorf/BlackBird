package com.github.langsdorf.blackbird.directmessage;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DirectMessage {

	private long id;
	private long senderId;
	private long recipientId;
	private String message;
	private String nextCursor;
	private Date createdAt;

}
