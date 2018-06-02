package com.github.langsdorf.blackbird;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter

public class URLBasedOAuth {

	private String consumerKey;
	private String consumerSecret;
	private BlackBird session;
		
	public URLBasedOAuth(String consumerKey, String consumerSecret) {
		setConsumerKey(consumerKey);
		setConsumerSecret(consumerSecret);
		setSession(new BlackBird(consumerKey, consumerSecret));
		prepare();
	}
	
	public String getAuthorizationUrl() {
		return getSession().getOauthService().getAuthorizationUrl(getSession().getOauthRequestToken());
	}

	@SneakyThrows(Exception.class)
	public void prepare() {
		OAuth10aService service = new ServiceBuilder(getConsumerKey())
                .apiSecret(getConsumerSecret())
                .build(TwitterApi.instance());
		OAuth1RequestToken requestToken = service.getRequestToken();	
		getSession().setOauthService(service);
		getSession().setOauthRequestToken(requestToken);
	}
	
	@SneakyThrows(Exception.class)
	public BlackBird authenticate(String pin) {
		return session.pinAuth(pin);
	}
}
