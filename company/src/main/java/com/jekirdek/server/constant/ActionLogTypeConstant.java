package com.jekirdek.server.constant;

public enum ActionLogTypeConstant {
	COMPANY_INSERT("COMPANY_INSERT"), COMPANY_INFO_UPDATE("COMPANY_INFO_UPDATE"), MANAGER_INSERT("MANAGER_INSERT"), MANAGER_DELETE(
			"MANAGER_DELETE"), INSPECTOR_INSERT("INSPECTOR_INSERT"), INSPECTOR_DELETE("INSPECTOR_DELETE"), USER_INSERT("USER_INSERT"), USER_INFO_UPDATE(
			"USER_INFO_UPDATE"), DOCUMENT_INSERT("DOCUMENT_INSERT"), DOCUMENT_DELETE("DOCUMENT_DELETE"), LOGO_UPLOAD("LOGO_UPLOAD");

	String	name;

	private ActionLogTypeConstant(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
