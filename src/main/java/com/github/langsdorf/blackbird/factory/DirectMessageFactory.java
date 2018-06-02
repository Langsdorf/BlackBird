package com.github.langsdorf.blackbird.factory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.langsdorf.blackbird.BlackBird;
import com.github.langsdorf.blackbird.directmessage.DirectMessage;
import com.github.langsdorf.blackbird.directmessage.DirectMessageI;
import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.http.BasicRequest;
import com.github.langsdorf.blackbird.http.CustomRequest;
import com.github.langsdorf.blackbird.http.URLList;
import com.github.langsdorf.blackbird.json.JSONArray;
import com.github.langsdorf.blackbird.json.JSONException;
import com.github.langsdorf.blackbird.json.JSONObject;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class DirectMessageFactory implements DirectMessageI, URLList {

	private BlackBird session;

	@SneakyThrows(value = { NumberFormatException.class, JSONException.class, IOException.class })
	public DirectMessage showDirectMessage(long id) throws TwitterException {
		String URL = DIRECT_MESSAGE_SHOW.replace("VALUE1", Long.toString(id));
		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);

		if (response.getCode() >= 200 && response.getCode() < 300 && response.getBody().contains("\"events\"")) {
			String body = response.getBody();
			JSONObject jsonObject = new JSONObject(body);
			JSONObject message = jsonObject.getJSONObject("event");

			Long m_id = Long.parseLong(message.getString("id"));
			Long timestamp = Long.parseLong(message.getString("created_timestamp"));

			JSONObject data_list = message.getJSONObject("message_create");
			Long sender_id = Long.parseLong(data_list.getString("sender_id"));

			JSONObject target = data_list.getJSONObject("target");
			Long target_id = Long.parseLong(target.getString("recipient_id"));
			
			JSONObject message_data = data_list.getJSONObject("message_data");
			String text = message_data.getString("text");

			DirectMessage dm = new DirectMessage();
			dm.setId(m_id);
			dm.setCreatedAt(new Date(timestamp * 1000));
			dm.setMessage(text);
			dm.setRecipientId(target_id);
			dm.setSenderId(sender_id);
			return dm;
		}

		throw new TwitterException("", response);
	}

	public List<DirectMessage> getDirectMessages() throws TwitterException {
		return getDirectMessages(20);
	}

	@SneakyThrows(value = { IOException.class, JSONException.class })
	public List<DirectMessage> getDirectMessages(int max) throws TwitterException {
		List<DirectMessage> dm_list = new ArrayList<DirectMessage>();
		String URL = DIRECT_MESSAGE_LIST.replace("$?count=VALUE1$", "?count=" + max);

		Response response = new BasicRequest(getSession()).request(Verb.GET, URL);
		if (response.getCode() >= 200 && response.getCode() < 300 && response.getBody().contains("\"events\"")) {
			String body = response.getBody();
			System.out.println(body);
			JSONObject jsonObject = new JSONObject(body);

			JSONArray messages_list = jsonObject.getJSONArray("events");
			String next_cursor = "";
			if (!jsonObject.isNull("next_cursor")) {
				next_cursor = jsonObject.getString("next_cursor");
			}

			for (int i = 0; i < messages_list.length(); ++i) {
				JSONObject message = new JSONObject(messages_list.getString(i));
				Long id = Long.parseLong(message.getString("id"));
				Long timestamp = Long.parseLong(message.getString("created_timestamp"));

				JSONObject data_list = message.getJSONObject("message_create");
				Long sender_id = Long.parseLong(data_list.getString("sender_id"));

				JSONObject target = data_list.getJSONObject("target");
				Long target_id = Long.parseLong(target.getString("recipient_id"));

				JSONObject message_data = data_list.getJSONObject("message_data");
				String text = message_data.getString("text");
				DirectMessage dm = new DirectMessage();
				dm.setId(id);
				dm.setCreatedAt(new Date(timestamp * 1000));
				dm.setMessage(text);
				dm.setRecipientId(target_id);
				dm.setSenderId(sender_id);
				dm.setNextCursor(next_cursor);
				dm_list.add(dm);
			}
		}

		return dm_list;
	}

	@SneakyThrows(JSONException.class)
	public DirectMessage sendDirectMessage(long recipientId, String text) {
		String URL = DIRECT_MESSAGE_NEW;
		String pattern = MESSAGE_CREATE_PATTERN.replace("$ID$", Long.toString(recipientId)).replace("$TEXT$", text);
		String response = new CustomRequest(getSession()).request(URL, pattern.getBytes(StandardCharsets.UTF_8));
		JSONObject jsonObject = new JSONObject(response);

		JSONObject message = jsonObject.getJSONObject("event");
		Long m_id = Long.parseLong(message.getString("id"));
		Long timestamp = Long.parseLong(message.getString("created_timestamp"));

		JSONObject data_list = message.getJSONObject("message_create");
		Long sender_id = Long.parseLong(data_list.getString("sender_id"));

		JSONObject target = data_list.getJSONObject("target");
		Long target_id = Long.parseLong(target.getString("recipient_id"));

		JSONObject message_data = data_list.getJSONObject("message_data");
		String text_m = message_data.getString("text");

		DirectMessage dm = new DirectMessage();
		dm.setId(m_id);
		dm.setCreatedAt(new Date(timestamp * 1000));
		dm.setMessage(text_m);
		dm.setRecipientId(target_id);
		dm.setSenderId(sender_id);
		return dm;
	}

	@SneakyThrows(IOException.class)
	public void destroyDirectMessage(long id) throws TwitterException {
		String URL = DIRECT_MESSAGE_DESTROY.replace("VALUE1", Long.toString(id));
		Response response = new BasicRequest(getSession()).request(Verb.DELETE, URL);

		if (response.getCode() >= 200 && response.getCode() < 300) {
			return;
		}

		throw new TwitterException("", response);
	}

}