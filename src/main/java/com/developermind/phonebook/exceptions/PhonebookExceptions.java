package com.developermind.phonebook.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class PhonebookExceptions extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3728777756197686939L;

	private Object data;

	private final String message;

	private final String status;

	public PhonebookExceptions(Object data, String message, String status) {
		this(message, status);
		this.data = data;
	}

	public PhonebookExceptions(String message, String status) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

}
