package com.app.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 762860103665529026L;
	private HttpStatus status;
	private String message;

	public BlogApiException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public BlogApiException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
