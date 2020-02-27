package com.github.langsdorf.blackbird;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.langsdorf.blackbird.api.DirectMessageAPI;
import com.github.langsdorf.blackbird.api.TweetAPI;
import com.github.langsdorf.blackbird.api.UserAPI;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.factory.DirectMessageFactory;
import com.github.langsdorf.blackbird.factory.TweetFactory;
import com.github.langsdorf.blackbird.factory.UserFactory;
import com.github.langsdorf.blackbird.http.URLList;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Setter(AccessLevel.PACKAGE)
@Getter
public class BlackBird implements URLList {

	private String consumerKey;
	private String consumerSecret;

	private String accessToken;
	private String accessTokenSecret;

	private OAuth10aService oauthService;
	private OAuth1AccessToken oauthAccessToken;
	private OAuth1RequestToken oauthRequestToken;

	private DirectMessageFactory directMessageFactory;
	private DirectMessageAPI directMessageAPI;

	private UserFactory userFactory;
	private UserAPI userAPI;

	private TweetFactory tweetFactory;
	private TweetAPI tweetAPI;

	public BlackBird(String consumerKey, String consumerSecret) {
		setConsumerKey(consumerKey);
		setConsumerSecret(consumerSecret);
	}

	public BlackBird(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		setConsumerKey(consumerKey);
		setConsumerSecret(consumerSecret);
		setAccessToken(accessToken);
		setAccessTokenSecret(accessTokenSecret);
		auth();
	}

	@SneakyThrows(Exception.class)
	private void auth() {
		OAuth10aService service = new ServiceBuilder(getConsumerKey()).apiSecret(getConsumerSecret())
				.build(TwitterApi.instance());
		OAuth1RequestToken requestToken = service.getRequestToken();
		OAuth1AccessToken oauthAccessToken = new OAuth1AccessToken(getAccessToken(), getAccessTokenSecret());

		setOauthAccessToken(oauthAccessToken);
		setOauthService(service);
		setOauthRequestToken(requestToken);
		checkCredentials();
	}

	@SneakyThrows(Exception.class)
	public void checkCredentials() {
		String URL = PROTECTED_RESOURCE_URL;
		OAuthRequest request = new OAuthRequest(Verb.GET, URL);
		getOauthService().signRequest(getOauthAccessToken(), request);
		Response response = getOauthService().execute(request);
		if (response.getCode() >= 200 && response.getCode() < 300) {
			init();
		} else {
			throw new TwitterException("Missing or incorrect authentication credentials.");
		}
	}

	public BlackBird pinAuth(String pin) throws IOException, InterruptedException, ExecutionException {
		OAuth1AccessToken accessToken = oauthService.getAccessToken(oauthRequestToken, pin);
		setOauthAccessToken(accessToken);
        checkCredentials();
        System.out.println("???");
		return this;
	}

	void init() {
		setDirectMessageFactory(new DirectMessageFactory(this));
		setUserFactory(new UserFactory(this));
		setTweetFactory(new TweetFactory(this));

		setDirectMessageAPI(new DirectMessageAPI(this));
		setUserAPI(new UserAPI(this));
		setTweetAPI(new TweetAPI(this));
	}

}
