package com.jekirdek.client.util;

import java.util.List;

import com.jekirdek.client.component.Label;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.template.DashboardHeader;
import com.jekirdek.client.template.MenuManager;
import com.jekirdek.client.template.NavigationManager;
import com.jekirdek.client.template.SpinnerManager;
import com.jekirdek.client.widget.CompanySearchWidget;

public class PageUtil {

	public static NavigationManager		navigationManager;
	public static MenuManager			menuManager;
	public static DashboardHeader		dashboardHeader;
	public static CompanySearchWidget	companySearchWidget;
	public static SpinnerManager		spinnerManager;
	public static Label					pageName;

	public static String getPageFriendlyUrl() {
		String fullToken = getUrlPageToken();
		if (fullToken == null)
			return "";
		if (fullToken.contains("/"))
			return fullToken.substring(0, fullToken.indexOf("/"));
		else
			return fullToken;
	}

	public static String getUrlPageToken() {
		String fullUrl = getFullUrl();
		if (fullUrl.contains("#"))
			return fullUrl.substring(fullUrl.indexOf("#") + 1, fullUrl.length());
		return "";
	}

	public static native String getFullUrl() /*-{
		var s = $doc.location.href;
		return s;
	}-*/;

	public static boolean controlUserAuthForCompanyUpdate() {
		// kullanici login olmus ve secili sirkete yetkisi varsa veya admin kullanici ise true
		SessionUser sessionUser = ClientCacheUtil.instance().getSessionUser();
		String selectedCompanyOid = ClientCacheUtil.instance().getSessionUser().getSelectedCompanyOid();
		List<String> authCompanyList = ClientCacheUtil.instance().getSessionUser().getAuthorizedCompanyOidList();
		if ((RoleType.MEMBER_LOGIN.equals(sessionUser.getAuthorizarionRole()) && authCompanyList.contains(selectedCompanyOid))
				|| (RoleType.ADMIN.equals(sessionUser.getAuthorizarionRole())))
			return true;
		else
			return false;
	}

}
