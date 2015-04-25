package com.jekirdek.client.util;

import com.google.gwt.core.client.GWT;
import com.jekirdek.client.constant.URLConstant;

public class UrlUtil {

	public static String generateLogoViewUrl(String companyOid) {
		String url = GWT.getModuleBaseURL() + URLConstant.FILE_SERVLET + "?" + URLConstant.CHANNEL + "="
				+ URLConstant.CHANNEL_COMPANY_LOGO_VIEW;
		if (companyOid != null && !companyOid.equals(""))
			url += "&" + URLConstant.COMPANY_OID + "=" + companyOid;
		return url;
	}

	public static String generateFileUploadUrl(String fileSessionKey) {
		return GWT.getModuleBaseURL() + URLConstant.FILE_SERVLET + "?" + URLConstant.CHANNEL + "=" + URLConstant.CHANNEL_FILE_UPLOAD + "&"
				+ URLConstant.FILE_SESSION_KEY + "=" + fileSessionKey;
	}

	public static String generateFileDownloadUrl(String fileDBStoreOid) {
		return GWT.getModuleBaseURL() + URLConstant.FILE_SERVLET + "?" + URLConstant.CHANNEL + "=" + URLConstant.CHANNEL_FILE_DOWNLOAD
				+ "&" + URLConstant.FILE_OID + "=" + fileDBStoreOid;
	}

}
