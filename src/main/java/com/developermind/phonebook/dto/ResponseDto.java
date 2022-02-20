package com.developermind.phonebook.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ResponseDto<T> {

	private final T data;

	private final HttpStatus httpStatus;

	private final String message;

	private final boolean success;

	private final long timestamp = System.currentTimeMillis();

	public ResponseDto(String message, boolean success, T data, HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.success = success;
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public boolean isSuccess() {
		return success;
	}

}
