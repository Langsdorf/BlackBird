package com.github.langsdorf.blackbird.api;

import java.util.List;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.directmessage.DirectMessage;
import com.github.langsdorf.blackbird.directmessage.DirectMessageI;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.factory.DirectMessageFactory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class DirectMessageAPI implements DirectMessageI {

	private BlackBird session;

	public DirectMessage showDirectMessage(long id) throws TwitterException {
		return getFactory().showDirectMessage(id);
	}

	public List<DirectMessage> getDirectMessages() throws TwitterException {
		return getFactory().getDirectMessages();
	}

	public List<DirectMessage> getDirectMessages(int max) throws TwitterException {
		return getFactory().getDirectMessages(max);
	}

	public DirectMessage sendDirectMessage(long recipientId, String text) {
		return getFactory().sendDirectMessage(recipientId, text);
	}

	public void destroyDirectMessage(long id) throws TwitterException {
		getFactory().destroyDirectMessage(id);
	}
	
	public DirectMessageFactory getFactory() {
		return getSession().getDirectMessageFactory();
	}

}
