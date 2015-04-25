package com.jekirdek.client.util;

import com.jekirdek.client.component.Label;
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

}
