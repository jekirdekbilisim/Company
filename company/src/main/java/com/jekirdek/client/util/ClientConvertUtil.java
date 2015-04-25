package com.jekirdek.client.util;

public class ClientConvertUtil {

	private static final String	LOWER_i		= "\u0069"; // i
	private static final String	LOWER_I		= "\u0131"; // ı
	private static final String	LOWER_ss	= "\u015F"; // ş
	private static final String	LOWER_gg	= "\u011F"; // ğ
	private static final String	LOWER_cc	= "\u00E7"; // ç
	private static final String	LOWER_oo	= "\u00F6"; // ö
	private static final String	LOWER_uu	= "\u00FC"; // ü

	private static final String	UPPER_i		= "\u0130"; // i
	private static final String	UPPER_I		= "\u0049"; // ı
	private static final String	UPPER_ss	= "\u015E"; // ş
	private static final String	UPPER_gg	= "\u011E"; // ğ
	private static final String	UPPER_cc	= "\u00C7"; // ç
	private static final String	UPPER_oo	= "\u00D6"; // ö
	private static final String	UPPER_uu	= "\u00DC"; // ü

	public static String convertUpperCaseTurkish(String query) {
		if (query != null) {
			query = query.replaceAll(LOWER_i, UPPER_i);// i
			query = query.replaceAll(LOWER_I, UPPER_I);// ı
			query = query.replaceAll(LOWER_ss, UPPER_ss);// ş
			query = query.replaceAll(LOWER_gg, UPPER_gg);// ğ
			query = query.replaceAll(LOWER_cc, UPPER_cc);// ç
			query = query.replaceAll(LOWER_oo, UPPER_oo);// ö
			query = query.replaceAll(LOWER_uu, UPPER_uu);// ü
		}
		return query.toUpperCase();
	}

	public static String convertUpperCaseUrl(String fieldValue) {
		fieldValue = fieldValue.toUpperCase();
		if (fieldValue.contains(UPPER_i))
			fieldValue = fieldValue.replaceAll(UPPER_i, UPPER_I);
		if (fieldValue.contains(UPPER_ss))
			fieldValue = fieldValue.replaceAll(UPPER_ss, "S");
		if (fieldValue.contains(UPPER_gg))
			fieldValue = fieldValue.replaceAll(UPPER_gg, "G");
		if (fieldValue.contains(UPPER_cc))
			fieldValue = fieldValue.replaceAll(UPPER_cc, "C");
		if (fieldValue.contains(UPPER_oo))
			fieldValue = fieldValue.replaceAll(UPPER_oo, "O");
		if (fieldValue.contains(UPPER_uu))
			fieldValue = fieldValue.replaceAll(UPPER_uu, "U");
		return fieldValue;
	}
}
