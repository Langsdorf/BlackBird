package com.github.langsdorf.blackbird.http;

/**
 * @author Twitter4j. All credits to them for this.
 */
public interface HTTPStatusCode {

	int OK = 200;
	int MULTIPLE_CHOICES = 300;
	int FOUND = 302;
	int NOT_MODIFIED = 304;
	int BAD_REQUEST = 400;
	int UNAUTHORIZED = 401;
	int FORBIDDEN = 403;
	int NOT_FOUND = 404;
	int NOT_ACCEPTABLE = 406;
	int ENHANCE_YOUR_CLAIM = 420;
	int UNPROCESSABLE_ENTITY = 422;
	int TOO_MANY_REQUESTS = 429;
	int INTERNAL_SERVER_ERROR = 500;
	int BAD_GATEWAY = 502;
	int SERVICE_UNAVAILABLE = 503;
	int GATEWAY_TIMEOUT = 504;
	
}