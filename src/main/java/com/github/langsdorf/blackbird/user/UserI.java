package com.github.langsdorf.blackbird.user;

import java.util.List;

import com.github.langsdorf.blackbird.exception.TwitterException;
import com.github.langsdorf.blackbird.user.list.BirdList;
import com.github.langsdorf.blackbird.user.misc.AccountSettings;
import com.github.langsdorf.blackbird.user.misc.Banner;

public interface UserI {

	/**
	 * Returns settings (including current trend, geo and sleep time information)
	 * for the authenticating user. <br>
	 * <br>
	 * Retorna as configurações (incluindo trend, geo e sleep time) do usuário
	 * autenticado. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-settings
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/settings.json
	 * 
	 * @return AccountSettings
	 * @throws TwitterException
	 */
	AccountSettings getAccountSettings() throws TwitterException;

	/**
	 * Returns a map of the available size variations of the specified user’s
	 * profile banner. If the user has not uploaded a profile banner, a HTTP 404
	 * will be served instead. <br>
	 * <br>
	 * Retorna um mapa das variações de tamanho disponíveis do banner de perfil do
	 * usuário especificado. Se o usuário não tiver um banner de perfil, um código
	 * HTTP 404 será exibido. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-verify_credentials
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/verify_credentials.json
	 * 
	 * @return User
	 * @throws TwitterException
	 */
	List<Banner> getProfileBanner(String screenName) throws TwitterException;

	/**
	 * Returns a map of the available size variations of the specified user’s
	 * profile banner. If the user has not uploaded a profile banner, a HTTP 404
	 * will be served instead. <br>
	 * <br>
	 * Retorna um mapa das variações de tamanho disponíveis do banner de perfil do
	 * usuário especificado. Se o usuário não tiver um banner de perfil, um código
	 * HTTP 404 será exibido. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-verify_credentials
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/verify_credentials.json
	 * 
	 * @return User
	 * @throws TwitterException
	 */
	List<Banner> getProfileBanner(long userId) throws TwitterException;

	/**
	 * Removes the uploaded profile banner for the authenticating user. Returns HTTP
	 * 200 upon success. <br>
	 * <br>
	 * Remove o banner de perfil do usuário autenticado. Retorna um código HTTP 200
	 * se tiver sucesso. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-verify_credentials
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/verify_credentials.json
	 * 
	 * @throws TwitterException
	 */
	void removeProfileBanner() throws TwitterException;

	/**
	 * Updates the authenticating user’s settings. Only the parameters specified
	 * will be updated. <br>
	 * <br>
	 * Atualiza as configurações do usuário autenticado. Somente parâmetros
	 * especificados serão atualizados. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-settings
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/settings.json
	 * 
	 * @return AccountSettings
	 * @param time_zone http://api.rubyonrails.org/classes/ActiveSupport/TimeZone.html
	 * @param lang https://developer.twitter.com/en/docs/developer-utilities/supported-languages/api-reference/get-help-languages
	 * @throws TwitterException
	 */
	AccountSettings updateAccountSettings(boolean sleep_time_enabled, int start_sleep_time, int end_sleep_time,
			String time_zone, int trend_location_woeid, String lang) throws TwitterException;

	/**
	 * Updates the authenticating user’s profile. Only the parameters specified will
	 * be updated. <br>
	 * <br>
	 * Atualiza o perfil do usuário autenticado. Somente parâmetros especificados
	 * serão atualizados. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-update_profile
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/update_profile.json
	 * 
	 * @return AccountSettings
	 * @throws TwitterException
	 */
	User updateProfile(String name, String url, String location, String description, String profile_link_color) throws TwitterException;

	/**
	 * Uploads a profile banner on behalf of the authenticating user. <br>
	 * <br>
	 * Atualiza o banner de perfil do usuário autenticado. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-update_profile_banner
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/update_profile_banner.json
	 * 
	 * @throws TwitterException
	 */
	void updateProfileBanner(String image) throws TwitterException;

	/**
	 * Uploads a profile banner on behalf of the authenticating user. <br>
	 * <br>
	 * Atualiza o banner de perfil do usuário autenticado. <br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-update_profile_banner
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/update_profile_banner.json
	 * 
	 * @throws TwitterException
	 */
	void updateProfileBanner(String image, int width, int height, int offset_left, int offset_top) throws TwitterException;
	
	/**
	 * Updates the authenticating user’s profile image. Note that this method expects raw multipart data, not a URL to an image.<br>
	 * <br>
	 * Atualiza a imagem de perfil do usuário autenticado. Note que esse método espera raw multipart data, não uma URL de alguma imagem.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-update_profile_image
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/account/update_profile_image.json
	 * 
	 * @throws TwitterException
	 */
	void updateProfileImage(String image) throws TwitterException;
	
	/**
	 * Returns an array of numeric user ids the authenticating user is blocking.<br>
	 * <br>
	 * Retorna uma lista de ids das pessoas bloqueadas pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-blocks-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getBlockIDList(String cursor) throws TwitterException;
	
	/**
	 * Returns an array of numeric user ids the authenticating user is blocking.<br>
	 * <br>
	 * Retorna uma lista de ids das pessoas bloqueadas pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-blocks-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getBlockIDList() throws TwitterException;
	
	/**
	 * Returns a collection of user objects that the authenticating user is blocking.<br>
	 * <br>
	 * Retorna uma lista de usuários bloqueados pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-blocks-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getBlockUserList(String cursor) throws TwitterException;
	
	/**
	 * Returns a collection of user objects that the authenticating user is blocking.<br>
	 * <br>
	 * Retorna uma lista de usuários bloqueados pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-blocks-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getBlockUserList() throws TwitterException;

	/**
	 * Returns an array of numeric user ids the authenticating user has muted.<br>
	 * <br>
	 * Retorna uma lista de ids das pessoas mutadas pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-mutes-users-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getMutesIDList(String cursor) throws TwitterException;
	
	/**
	 * Returns an array of user objects the authenticating user has muted.<br>
	 * <br>
	 * Retorna uma lista de usuários mutados pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-mutes-users-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getMutesUserList(String cursor) throws TwitterException;

	/**
	 * Returns an array of numeric user ids the authenticating user has muted.<br>
	 * <br>
	 * Retorna uma lista de ids das pessoas mutadas pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-mutes-users-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getMutesIDList() throws TwitterException;
	
	/**
	 * Returns an array of user objects the authenticating user has muted.<br>
	 * <br>
	 * Retorna uma lista de usuários mutados pelo usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/get-mutes-users-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getMutesUserList() throws TwitterException;
	
	/**
	 * Blocks the specified user from following the authenticating user.<br>
	 * <br>
	 * Bloqueia um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-blocks-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/create.json
	 * 
	 * @throws TwitterException
	 */
	User blockUser(String screenName) throws TwitterException;
	
	/**
	 * Blocks the specified user from following the authenticating user.<br>
	 * <br>
	 * Bloqueia um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-blocks-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/create.json
	 * 
	 * @throws TwitterException
	 */
	User blockUser(long userId) throws TwitterException;
	
	/**
	 * Un-blocks the user specified in the ID parameter for the authenticating user.<br>
	 * <br>
	 * Desbloqueia um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-blocks-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/destroy.json
	 * 
	 * @throws TwitterException
	 */
	User unblockUser(String screenName) throws TwitterException;
	
	/**
	 * Un-blocks the user specified in the ID parameter for the authenticating user.<br>
	 * <br>
	 * Desbloqueia um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-blocks-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/blocks/destroy.json
	 * 
	 * @throws TwitterException
	 */
	User unblockUser(long userId) throws TwitterException;
	
	//
	
	/**
	 * Mutes the user specified in the ID parameter for the authenticating user.<br>
	 * <br>
	 * Muta um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-mutes-users-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/create.json
	 * 
	 * @throws TwitterException
	 */
	User muteUser(String screenName) throws TwitterException;
	
	/**
	 * Mutes the user specified in the ID parameter for the authenticating user.<br>
	 * <br>
	 * Muta um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-mutes-users-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/create.json
	 * 
	 * @throws TwitterException
	 */
	User muteUser(long userId) throws TwitterException;
	
	/**
	 * Un-mutes the user specified in the ID parameter for the authenticating user.<br>
	 * <br>
	 * Desmuta um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-mutes-users-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/destroy.json
	 * 
	 * @throws TwitterException
	 */
	User unmuteUser(String screenName) throws TwitterException;
	
	/**
	 * Un-mutes the user specified in the ID parameter for the authenticating user.<br>
	 * <br>
	 * Desmuta um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-mutes-users-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/mutes/users/destroy.json
	 * 
	 * @throws TwitterException
	 */
	User unmuteUser(long userId) throws TwitterException;
	
	/**
	 * Report the specified user as a spam account to Twitter.<br>
	 * <br>
	 * Reporta um usuário como spam.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-users-report_spam
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/users/report_spam.json
	 * 
	 * @throws TwitterException
	 */
	User reportSpam(String screenName, boolean block) throws TwitterException;

	/**
	 * Report the specified user as a spam account to Twitter.<br>
	 * <br>
	 * Reporta um usuário como spam.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-users-report_spam
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/users/report_spam.json
	 * 
	 * @throws TwitterException
	 */
	User reportSpam(long userId, boolean block) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user IDs for every user following the specified user.<br>
	 * <br>
	 * Retorna uma lista de id de todos os seguidores de um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-followers-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/followers/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getFollowersIDList(String screenName, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user IDs for every user following the specified user.<br>
	 * <br>
	 * Retorna uma lista de id de todos os seguidores de um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-followers-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/followers/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getFollowersIDList(long userId, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user objects for users following the specified user.<br>
	 * <br>
	 * Retorna uma lista de usuários de todos os seguidores de um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-followers-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/followers/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getFollowersList(String screenName, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user objects for users following the specified user.<br>
	 * <br>
	 * Retorna uma lista de usuários de todos os seguidores de um usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-followers-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/followers/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getFollowersList(long userId, int count, String cursor) throws TwitterException;
	
	//
	
	/**
	 * Returns a cursored collection of user IDs for every user the specified user is following (otherwise known as their “friends”).<br>
	 * <br>
	 * Retorna uma lista de id de todas as pessoas que o usuário segue.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friends/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getFollowingIDList(String screenName, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user IDs for every user the specified user is following (otherwise known as their “friends”).<br>
	 * <br>
	 * Retorna uma lista de id de todas as pessoas que o usuário segue.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-ids
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friends/ids.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getFollowingIDList(long userId, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user objects for every user the specified user is following (otherwise known as their “friends”).<br>
	 * <br>
	 * Retorna uma lista de usuários de todos as pessoas que o usuário segue.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friends/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getFollowingList(String screenName, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a cursored collection of user objects for every user the specified user is following (otherwise known as their “friends”).<br>
	 * <br>
	 * Retorna uma lista de usuários de todos as pessoas que o usuário segue.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-list
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friends/list.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> getFollowingList(long userId, int count, String cursor) throws TwitterException;
	
	/**
	 * Returns a collection of numeric IDs for every user who has a pending request to follow the authenticating user.<br>
	 * <br>
	 * Retorna uma lista de id de todos os usuários que possuem um pedido de follow para o usuário autenticado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-incoming
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/incoming.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getIncomingRequests(String cursor) throws TwitterException;
	
	/**
	 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided<br>
	 * <br>
	 * Retorna as relações entre o usuário autenticado e o(s) usuário(s) (Máximo 100 usuários por request).<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/lookup.json
	 * 
	 * @throws TwitterException
	 */
	List<User> relationsLookup(String... screen_names) throws TwitterException;
	
	/**
	 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided<br>
	 * <br>
	 * Retorna as relações entre o usuário autenticado e o(s) usuário(s) (Máximo 100 usuários por request).<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/lookup.json
	 * 
	 * @throws TwitterException
	 */
	List<User> relationsLookup(long... usersId) throws TwitterException;
	
	/**
	 * Returns a collection of numeric IDs for every protected user for whom the authenticating user has a pending follow request.<br>
	 * <br>
	 * Retorna uma lista de id de todos os usuários que o usuário autenticado pediu para seguir.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-outgoing
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/outgoing.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<Long> getOutcomingRequests(String cursor) throws TwitterException;
	
	/**
	 * Returns fully-hydrated user objects for up to 100 users per request, as specified by comma-separated values passed to the user_id and/or screen_name parameters.<br>
	 * <br>
	 * Retorna uma lista de usuários (Máximo 100 usuários por request).<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-lookup
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/users/lookup.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> userLookup(String... screenNames) throws TwitterException;
	
	/**
	 * Returns fully-hydrated user objects for up to 100 users per request, as specified by comma-separated values passed to the user_id and/or screen_name parameters.<br>
	 * <br>
	 * Retorna uma lista de usuários (Máximo 100 usuários por request).<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-lookup
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/users/lookup.json
	 * 
	 * @throws TwitterException
	 */
	BirdList<User> userLookup(long... usersId) throws TwitterException;
	
	/**
	 * Returns a variety of information about the user specified by the required user_id or screen_name parameter.<br>
	 * <br>
	 * Retorna um usuário especificado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-show
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/users/show.json
	 * 
	 * @throws TwitterException
	 */
	User getUser(String screenName) throws TwitterException;
	
	/**
	 * Returns a variety of information about the user specified by the required user_id or screen_name parameter.<br>
	 * <br>
	 * Retorna um usuário especificado.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-show
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/users/show.json
	 * 
	 * @throws TwitterException
	 */
	User getUser(long userId) throws TwitterException;
	
	/**
	 * Allows the authenticating user to follow (friend) the user specified in the ID parameter.<br>
	 * <br>
	 * Faz o usuário autenticado seguir outro usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/create.json
	 * 
	 * @throws TwitterException
	 */
	User followUser(String screenName) throws TwitterException;

	/**
	 * Allows the authenticating user to follow (friend) the user specified in the ID parameter.<br>
	 * <br>
	 * Faz o usuário autenticado seguir outro usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-create
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/create.json
	 * 
	 * @throws TwitterException
	 */
	User followUser(long userId) throws TwitterException;

	/**
	 * Allows the authenticating user to unfollow the user specified in the ID parameter.<br>
	 * <br>
	 * Faz o usuário autenticado parar de seguir outro usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/destroy.json
	 * 
	 * @throws TwitterException
	 */
	User unfollowUser(String screenName) throws TwitterException;

	/**
	 * Allows the authenticating user to unfollow the user specified in the ID parameter.<br>
	 * <br>
	 * Faz o usuário autenticado parar de seguir outro usuário.<br>
	 * <br>
	 * https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-destroy
	 * <br>
	 * <br>
	 * https://api.twitter.com/1.1/friendships/destroy.json
	 * 
	 * @throws TwitterException
	 */
	User unfollowUser(long userId) throws TwitterException;
	
	//wip
}