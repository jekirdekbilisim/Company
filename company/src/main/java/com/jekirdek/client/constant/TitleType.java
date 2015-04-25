package com.jekirdek.client.constant;

import com.google.gwt.core.client.GWT;
import com.jekirdek.client.i18n.UIMessages;

public class TitleType {

	public static final String	BASKAN					= "BASKAN";
	public static final String	BASKAN_VEKILI			= "BASKAN_VEKILI";
	public static final String	KURUL_UYESI				= "KURUL_UYESI";
	public static final String	BAGIMSIZ_BASKAN_VEKILI	= "BAGIMSIZ_BASKAN_VEKILI";
	public static final String	BAGIMSIZ_KURUL_UYESI	= "BAGIMSIZ_KURUL_UYESI";

	public static UIMessages	messages				= GWT.create(UIMessages.class);

	public static String getDesc(String companyType) {
		switch (companyType) {
		case BASKAN:
			return messages.Title_Baskan();
		case BASKAN_VEKILI:
			return messages.Title_BaskanVekili();
		case KURUL_UYESI:
			return messages.Title_KurulUyesi();
		case BAGIMSIZ_BASKAN_VEKILI:
			return messages.Title_BagimsizBaskanVekili();
		case BAGIMSIZ_KURUL_UYESI:
			return messages.Title_BagimsizKurulUyesi();
		default:
			return "";
		}
	}
}
