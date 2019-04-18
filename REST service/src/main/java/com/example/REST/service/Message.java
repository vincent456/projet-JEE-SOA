package com.example.REST.service;

import java.util.Date;

public class Message {
	private int id;
	private Date timestamp;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
