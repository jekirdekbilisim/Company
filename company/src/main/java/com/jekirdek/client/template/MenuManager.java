package com.jekirdek.client.template;

import java.util.List;

import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.ui.RootPanel;
import com.jekirdek.client.component.Div;
import com.jekirdek.client.constant.DashboardConstant;
import com.jekirdek.client.controller.LoginMenuController;
import com.jekirdek.client.controller.LoginMenuControllerAsync;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.MenuItem;
import com.jekirdek.client.util.PageUtil;

public class MenuManager {

	private final LoginMenuControllerAsync	menuService		= GWT.create(LoginMenuController.class);

	Div										div				= new Div();
	UListElement							ulistElement	= Document.get().createULElement();

	// @Override
	// protected void onLoad() {
	// super.onLoad();
	// String openPageFriendlyUrl = PageUtil.getUrlPageToken();
	// refreshMenuAndPrivilege(openPageFriendlyUrl);
	// }

	public MenuManager() {
		String openPageFriendlyUrl = PageUtil.getUrlPageToken();
		refreshMenuAndPrivilege(openPageFriendlyUrl);
	}

	public void refreshMenuAndPrivilege(final String openPageFriendlyUrl) {
		menuService.loadMenuItemsAndPrivileges("", new AsyncCall<AuthDataDTO>() {
			@Override
			public void successCall(AuthDataDTO authData) {
				ClientCacheUtil.instance().loginProcess(authData, openPageFriendlyUrl);
				// loadMenu(authData.getMenuItemList(), openPageFriendlyUrl);
			}

		});
	}

	public void loadMenu(List<MenuItem> result, String openPageFriendlyUrl) {

		RootPanel.get(DashboardConstant.DIV_MENU).clear(true);
		for (MenuItem menuItem : result) {
			if (menuItem.getVisible()) {
				LIElement liElement = Document.get().createLIElement();
				// liElement.addClassName("active");
				org.gwtbootstrap3.client.ui.Anchor anchor = new org.gwtbootstrap3.client.ui.Anchor();
				anchor.setHref("#".concat(menuItem.getFriendlyUrl()));
				Icon icon = new Icon(IconType.FILE_O);
				Span span = new Span(menuItem.getName());
				anchor.add(icon);
				anchor.add(span);
				liElement.appendChild(anchor.getElement());
				RootPanel.get(DashboardConstant.DIV_MENU).getElement().appendChild(liElement);
			}
		}

		if (openPageFriendlyUrl == null || openPageFriendlyUrl.isEmpty()) {
			goFisrtMenu();
		} else {
			PageUtil.navigationManager.controlOpenPageByFUrl(openPageFriendlyUrl);
		}
	}

	private void goFisrtMenu() {
		if (ClientCacheUtil.instance().getClientMenuList() != null && !ClientCacheUtil.instance().getClientMenuList().isEmpty()) {
			PageUtil.navigationManager.controlOpenPageByFUrl(ClientCacheUtil.instance().firstVisibleMenuFUrl());
		}
	}

	public Div getDiv() {
		return div;
	}

	public void setDiv(Div div) {
		this.div = div;
	}

	public UListElement getUlistElement() {
		return ulistElement;
	}

	public void setUlistElement(UListElement ulistElement) {
		this.ulistElement = ulistElement;
	}

}
