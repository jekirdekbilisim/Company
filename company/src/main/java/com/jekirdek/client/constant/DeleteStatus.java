package com.jekirdek.client.constant;

public enum DeleteStatus {
	ACTIVE("A"), PASSIVE("P");

	private String code;

	private DeleteStatus(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
