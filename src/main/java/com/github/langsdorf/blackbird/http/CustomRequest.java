package com.github.langsdorf.blackbird.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomRequest {
	
	private BlackBird session;
	
	public String request(String u, byte[] out) {
		try {
			URL url = new URL(u);
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("POST");
			http.setDoOutput(true);

			int length = out.length;
			http.setFixedLengthStreamingMode(length);

			OAuth10aService service = getSession().getOauthService();
			OAuth1AccessToken accessToken = getSession().getOauthAccessToken();
			OAuthRequest request = new OAuthRequest(Verb.POST, u);
			service.signRequest(accessToken, request);

			for (String key : request.getHeaders().keySet()) {
				http.setRequestProperty(key, request.getHeaders().get(key));
			}
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			http.connect();

			OutputStream os = http.getOutputStream();
			os.write(out);
			BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			if (http.getResponseCode() >= 200 && http.getResponseCode() < 300) {
				return reader.readLine();
			}

			throw new TwitterException(reader.readLine());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
