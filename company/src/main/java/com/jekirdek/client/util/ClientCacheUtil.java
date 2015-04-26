package com.jekirdek.client.util;

import java.util.List;

import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.pojo.SessionUser;

public class ClientCacheUtil {

	private SessionUser				sessionUser;
	private List<MenuItem>			clientMenuList;
	private List<String>			privilegeItemList;
	private List<ListItem>			companySuggestDataList;

	private static ClientCacheUtil	instance	= null;

	public static final String		PRIV_TOKEN	= "_SELECT";

	public static ClientCacheUtil instance() {
		if (instance == null) {
			instance = new ClientCacheUtil();
		}
		return instance;
	}

	public SessionUser getSessionUser() {
		if (sessionUser == null)
			sessionUser = new SessionUser();
		return sessionUser;
	}

	public void setSessionUser(SessionUser sessionUser) {
		this.sessionUser = sessionUser;
	}

	public List<MenuItem> getClientMenuList() {
		return clientMenuList;
	}

	public void setClientMenuList(List<MenuItem> clientMenuList) {
		this.clientMenuList = clientMenuList;
	}

	public List<String> getPrivilegeItemList() {
		return privilegeItemList;
	}

	public void setPrivilegeItemList(List<String> privilegeItemList) {
		this.privilegeItemList = privilegeItemList;
	}

	public boolean getUserLogin() {
		if (sessionUser != null && sessionUser.getLogin())
			return true;
		return false;
	}

	public String getLoginUserName() {
		if (sessionUser != null && sessionUser.getLogin())
			return sessionUser.getFullName();
		return "";
	}

	public void loginProcess(AuthDataDTO authData, String openedPage) {
		// PageUtil.menuManager.loginMenuPrivilegeSessionUser(result, openedPage);

		ClientCacheUtil.instance().setClientMenuList(authData.getMenuItemList());
		ClientCacheUtil.instance().setPrivilegeItemList(authData.getPrivilegeItemList());
		ClientCacheUtil.instance().setSessionUser(authData.getSessionUser());

		if (PageUtil.dashboardHeader != null) {
			PageUtil.dashboardHeader.userLoginprocess();
		}

		if (PageUtil.menuManager != null) {
			PageUtil.menuManager.loadMenu(authData.getMenuItemList(), openedPage);
		}

	}

	public String firstVisibleMenuFUrl() {
		for (MenuItem menuItem : clientMenuList) {
			if (menuItem.getVisible())
				return menuItem.getFriendlyUrl();
		}
		return "";
	}

	public MenuItem findMenuItemByFUrl(String pageFriendlyUrl) {
		for (MenuItem menuItem : clientMenuList) {
			if (menuItem.getFriendlyUrl().equals(pageFriendlyUrl))
				return menuItem;
		}
		return null;
	}

	public String getClassNameFromClass(MenuItem menuItem) {
		return menuItem.getClassName().substring(menuItem.getClassName().lastIndexOf(".") + 1, menuItem.getClassName().length());
	}

	public List<ListItem> getCompanySuggestDataList() {
		return companySuggestDataList;
	}

	public void setCompanySuggestDataList(List<ListItem> companySuggestDataList) {
		this.companySuggestDataList = companySuggestDataList;
	}
}
