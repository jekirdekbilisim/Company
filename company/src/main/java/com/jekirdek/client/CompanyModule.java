package com.jekirdek.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.jekirdek.client.component.Label;
import com.jekirdek.client.constant.DashboardConstant;
import com.jekirdek.client.template.DashboardHeader;
import com.jekirdek.client.template.MenuManager;
import com.jekirdek.client.template.NavigationManager;
import com.jekirdek.client.template.SpinnerManager;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.widget.CompanySearchWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CompanyModule implements EntryPoint {

	public void onModuleLoad() {
		GWT.log("Module started loaded");

		SpinnerManager spinner = new SpinnerManager();
		PageUtil.spinnerManager = spinner;

		MenuManager menu = new MenuManager();
		PageUtil.menuManager = menu;

		NavigationManager navigation = new NavigationManager();
		PageUtil.navigationManager = navigation;

		DashboardHeader header = new DashboardHeader();
		PageUtil.dashboardHeader = header;

		CompanySearchWidget companySearch = new CompanySearchWidget();
		PageUtil.companySearchWidget = companySearch;

		Label pageNameLbl = new Label();
		PageUtil.pageName = pageNameLbl;

		RootPanel.get(DashboardConstant.DIV_SPINNER).add(spinner);
		RootPanel.get(DashboardConstant.DIV_CONTENT).add(navigation);
		// RootPanel.get(DashboardConstant.DIV_MENU).add(menu);
		// RootPanel.get(DashboardConstant.DIV_LEFT_WIDGET).add(companySearch);
		// RootPanel.get(DashboardConstant.DIV_HEADER).add(header);
		RootPanel.get(DashboardConstant.DIV_PAGE_NAME).add(pageNameLbl);
	}
}
