package com.github.langsdorf.blackbird.http;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class BasicRequest {

	private BlackBird session;

	@SneakyThrows(value = { InterruptedException.class, ExecutionException.class, IOException.class })
	public Response request(Verb verb, String url) throws TwitterException {
		OAuth10aService service = getSession().getOauthService();
		OAuth1AccessToken access_token = getSession().getOauthAccessToken();
		OAuthRequest request = new OAuthRequest(verb, url);
		service.signRequest(access_token, request);
		return service.execute(request);
	}

}
