package com.github.langsdorf.blackbird.exception;

import java.io.IOException;
import java.util.StringJoiner;

import com.github.langsdorf.blackbird.http.HTTPStatusCode;
import com.github.langsdorf.blackbird.json.JSONException;
import com.github.langsdorf.blackbird.json.JSONObject;
import com.github.scribejava.core.model.Response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter(AccessLevel.PROTECTED)
public class TwitterException extends Exception implements HTTPStatusCode {

	private static final long serialVersionUID = -8049135171139755980L;
	private Response response;
	private int statusCode;
	private int errorCode;
	private String message;
	private String errorMessage;

	public TwitterException(String message, Response res) throws IOException {
		this(res.getBody());
		setResponse(res);
		setStatusCode(res.getCode());
	}

	public TwitterException(String message, Throwable throwable) {
		super(message, throwable);
		setStatusCode(-1);
		setErrorCode(-1);
		setMessage(message);
		read(message);
	}

	public TwitterException(String message) {
		this(message, (Throwable) null);
	}

	@SneakyThrows(JSONException.class)
	private void read(String message) {
		if (message != null && message.startsWith("{") && message.endsWith("}")) {
			JSONObject jsonObject = new JSONObject(message);
			if (jsonObject != null && !jsonObject.isNull("errors")) {
				JSONObject error = jsonObject.getJSONArray("errors").getJSONObject(0);
				setErrorMessage(error.getString("message"));
				setErrorCode(error.getInt("code"));
			}
		}
	}

	public String getLocalizedMessage() {
		StringJoiner stringJoiner = new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
		if (getStatusCode() != -1) {
			stringJoiner.add(getMessageByStatusCode());
		}
		if (getErrorCode() != -1 && getErrorMessage() != null) {
			stringJoiner.add("Message: " + getErrorMessage());
			stringJoiner.add("Error code: " + getErrorCode());
		}
		if (!getMessage().equals("")) {
			stringJoiner.add(getMessage());
		}
		return stringJoiner.toString();
	}

	/**
	 * @author Twitter4j. All credits to them for this.
	 */
	private String getMessageByStatusCode() {
		String cause = null;
		switch (getStatusCode()) {
		case 304:
			cause = "There was no new data to return.";
			break;
		case 400:
			cause = "The request was invalid. An accompanying error message will explain why. This is the status code will be returned during version 1.0 rate limiting (https://developer.twitter.com/en/docs/basics/rate-limiting). In API v1.1, a request without authentication is considered invalid and you will get this response.";
			break;
		case 401:
			cause = "Authentication credentials (https://developer.twitter.com/en/docs/basics/authentication/overview/oauth) were missing or incorrect. Ensure that you have set valid consumer key/secret, access token/secret, and the system clock is in sync.";
			break;
		case 403:
			cause = "The request is understood, but it has been refused. An accompanying error message will explain why. This code is used when requests are being denied due to update limits (https://support.twitter.com/articles/15364-about-twitter-limits-update-api-dm-and-following).";
			break;
		case 404:
			cause = "The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method.";
			break;
		case 406:
			cause = "Returned by the Search API when an invalid format is specified in the request.\nReturned by the Streaming API when one or more of the parameters are not suitable for the resource. The track parameter, for example, would throw this error if:\n The track keyword is too long or too short.\n The bounding box specified is invalid.\n No predicates defined for filtered resource, for example, neither track nor follow parameter defined.\n Follow userid cannot be read.";
			break;
		case 420:
			cause = "Returned by the Search and Trends API when you are being rate limited (https://developer.twitter.com/en/docs/basics/rate-limiting).\nReturned by the Streaming API:\n Too many login attempts in a short period of time.\n Running too many copies of the same application authenticating with the same account name.";
			break;
		case 422:
			cause = "Returned when an image uploaded to POST account/update_profile_banner(https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners) is unable to be processed.";
			break;
		case 429:
			cause = "Returned in API v1.1 when a request cannot be served due to the application's rate limit having been exhausted for the resource. See Rate Limiting in API v1.1.(https://developer.twitter.com/en/docs/basics/rate-limiting)";
			break;
		case 500:
			cause = "Something is broken. Please post to the forum (https://twittercommunity.com/) so the Twitter team can investigate.";
			break;
		case 502:
			cause = "Twitter is down or being upgraded.";
			break;
		case 503:
			cause = "The Twitter servers are up, but overloaded with requests. Try again later.";
			break;
		case 504:
			cause = "The Twitter servers are up, but the request couldn't be serviced due to some failure within our stack. Try again later.";
			break;
		default:
			cause = "";
			break;
		}
		return String.valueOf(getStatusCode()) + ":" + cause;
	}

}