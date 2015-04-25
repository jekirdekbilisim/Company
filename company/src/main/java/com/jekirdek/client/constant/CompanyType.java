package com.jekirdek.client.constant;

import com.google.gwt.core.client.GWT;
import com.jekirdek.client.i18n.UIMessages;

public class CompanyType {

	public static final String	LIMITED			= "LIMITED";
	public static final String	ANONIM			= "ANONIM";
	public static final String	KOMANDIT		= "KOMANDIT";
	public static final String	KOLEKTIF		= "KOLEKTIF";
	public static final String	IRTIBAT_BUROSU	= "IRTIBAT_BUROSU";
	public static final String	SUBE			= "SUBE";

	public static UIMessages	messages		= GWT.create(UIMessages.class);

	public static String getDesc(String companyType) {
		switch (companyType) {
		case LIMITED:
			return messages.Company_Limited();
		case ANONIM:
			return messages.Company_Anonim();
		case KOMANDIT:
			return messages.Company_Komandit();
		case KOLEKTIF:
			return messages.Company_Kolektif();
		case IRTIBAT_BUROSU:
			return messages.Company_IrtibatBurosu();
		case SUBE:
			return messages.Company_Sube();
		default:
			return "";
		}
	}

}
