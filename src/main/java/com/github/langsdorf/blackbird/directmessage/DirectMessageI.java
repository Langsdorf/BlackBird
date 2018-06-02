package com.github.langsdorf.blackbird.directmessage;

import java.util.List;

import com.github.langsdorf.blackbird.exception.TwitterException;

public interface DirectMessageI {
	
    /**
     * Returns a single Direct Message event by the given id.
     * <br>
     * <br>Retorna uma mensagem direta especificada pelo id.
     * <br>
     * <br>https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/get-event
     * <br>
     * <br>https://api.twitter.com/1.1/direct_messages/events/show.json
     * 
     * @param id DirectMessage id.
     * @return DirectMessage
     * @throws TwitterException
     * */
	DirectMessage showDirectMessage(long id) throws TwitterException;
	
	/**
     * Returns all Direct Message events (both sent and received) within the last 30 days. Sorted in reverse-chronological order.
     * <br>
     * <br>Retorna todas as mensagens diretas (enviadas e recebidas) dentro dos últimos 30 dias. Organizadas em ordem cronológica reversa.
     * <br>
     * <br>https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/list-events
     * <br>
     * <br>https://api.twitter.com/1.1/direct_messages/events/list.json
     * .
     * @return List of DirectMessages
     * @throws TwitterException
     * */
	List<DirectMessage> getDirectMessages() throws TwitterException;
	
	/**
     * Returns all Direct Message events (both sent and received) within the last 30 days. Sorted in reverse-chronological order.
     * <br>
     * <br>Retorna todas as mensagens diretas (enviadas e recebidas) dentro dos últimos 30 dias. Organizadas em ordem cronológica reversa.
     * <br>
     * <br>https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/list-events
     * <br>
     * <br>https://api.twitter.com/1.1/direct_messages/events/list.json
     * .
     * @return List of DirectMessages
     * @param max Max number of events to be returned. 20 default. 50 max.
     * @param max Quantidade máxima de mensagens que devem retornar. 20 padrão, 50 máximo.
     * @throws TwitterException
     * */
	List<DirectMessage> getDirectMessages(int max) throws TwitterException;
	
	/**
     * Sends a new Direct Message to the specified user from the authenticating user
     * <br>
     * <br>Envia uma nova mensagem direta para um determinado usuário utilizando o usuário autenticado.
     * <br>
     * <br>https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/new-message
     * <br>
     * <br>https://api.twitter.com/1.1/direct_messages/events/new.json
     * .
     * @return DirectMessage
     * @param recipientId The ID of the user who should receive the direct message.
     * @param text The text of your Direct Message.
     * @param recipientId O id do usuário que irá receber a mensagem.
     * @param text A mensagem a ser enviada.
     * */
	DirectMessage sendDirectMessage(long recipientId, String text);
	
    /**
     * Deletes the direct message specified in the required ID parameter.
     * <br>
     * <br>Deleta a mensagem direta especificada pelo Id.
     * <br>
     * <br>https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/delete-message-event
     * <br>
     * <br>https://api.twitter.com/1.1/direct_messages/events/destroy.json
     * 
     * @param id DirectMessage id.
     * @throws TwitterException
     * */
	void destroyDirectMessage(long id) throws TwitterException;

	
}