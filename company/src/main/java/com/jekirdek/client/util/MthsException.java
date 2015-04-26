package com.jekirdek.client.util;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MthsException extends Exception implements IsSerializable, Serializable {

	private static final long	serialVersionUID	= 7008780400012932254L;

	private String				message;

	public MthsException() {
		super();
	}

	public MthsException(String message) {
		super(message);
		this.message = message;
	}

	public MthsException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
