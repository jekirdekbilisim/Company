package com.jekirdek.client.template;

import org.gwtbootstrap3.extras.bootbox.client.Bootbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.AuthorizationController;
import com.jekirdek.client.controller.AuthorizationControllerAsync;
import com.jekirdek.client.page.AdminLogin;
import com.jekirdek.client.page.BlankPage;
import com.jekirdek.client.page.CompanyAuthSelect;
import com.jekirdek.client.page.CompanyDocument;
import com.jekirdek.client.page.CompanyDocument_2;
import com.jekirdek.client.page.CompanyInfo;
import com.jekirdek.client.page.CompanyLogoUpload;
import com.jekirdek.client.page.CompanyRegister;
import com.jekirdek.client.page.CorpManagerRegister;
import com.jekirdek.client.page.InspectorRegister;
import com.jekirdek.client.page.LogHistory;
import com.jekirdek.client.page.MemberLogin;
import com.jekirdek.client.page.RealManagerRegister;
import com.jekirdek.client.page.UserCompanyAssign;
import com.jekirdek.client.page.UserRegister;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.MenuItem;
import com.jekirdek.client.util.PageUtil;

public class NavigationManager extends FlowPanel {

	private final AuthorizationControllerAsync	authorizationController	= GWT.create(AuthorizationController.class);

	@Override
	protected void onLoad() {
		super.onLoad();
		initializeUrlHistory();
	}

	private void initializeUrlHistory() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("URL history new item : " + event.getValue());
				if (event.getValue() == null || event.getValue().isEmpty()) {
					GWT.log("URL history is null, do nothing ");
					return;
				}
				openPageWithToken(event.getValue());
			}

		});
		GWT.log("URL history initialized");
	}

	/**
	 * control firendly url is already opened and open page
	 */
	public void controlOpenPageByFUrl(String friendlyUrl) {
		if (friendlyUrl == null) {
			GWT.log("You are trying open page with NULL friendly url");
			return;
		}
		if (friendlyUrl.equals(PageUtil.getPageFriendlyUrl())) {
			openPageWithToken(friendlyUrl);
		} else {
			addPage2HistoryItem(friendlyUrl);
		}
	}

	public void addPage2HistoryItem(String pageToken) {
		History.newItem(pageToken);
	}

	public void openPageWithToken(String newToken) {
		String pageFriendlyUrl = newToken;
		String companyAlias = "";
		if (newToken != null && newToken.contains("/")) {
			String[] args = newToken.split("/");
			pageFriendlyUrl = args[0];
			companyAlias = args[1];
			changeSelectedCompany(companyAlias);
		}
		if (controlAuthorization(pageFriendlyUrl)) {
			clear();

			if (controlCompanySelectNeeded(pageFriendlyUrl)) {
				Widget page = createPage(pageFriendlyUrl);
				add(page);
			} else {
				Bootbox.alert("Lütfen sol taraftaki şirket arama fonsiyonundan işlem yapmak istediğiniz şirketi seçin");
			}
		} else {
			Bootbox.alert("Bu sayfaya yetkiniz yoktur");
		}
	}

	private boolean controlAuthorization(String pageFriendlyUrl) {
		MenuItem menuItem = ClientCacheUtil.instance().findMenuItemByFUrl(pageFriendlyUrl);
		if (menuItem != null) {
			String className = ClientCacheUtil.instance().getClassNameFromClass(menuItem);
			if (ClientCacheUtil.instance().getPrivilegeItemList().contains(className.concat(ClientCacheUtil.PRIV_TOKEN))) {
				return true;
			}
		}
		return false;
	}

	private boolean controlCompanySelectNeeded(String pageFriendlyUrl) {
		if (URLConstant.COMPANY_INFO.equals(pageFriendlyUrl) || URLConstant.COMPANY_REGISTER.equals(pageFriendlyUrl)
				|| URLConstant.COMPANY_LOGO_UPLOAD.equals(pageFriendlyUrl) || URLConstant.COMPANY_DOCUMENT_2.equals(pageFriendlyUrl)) {
			if (ClientCacheUtil.instance().getSessionUser().getSelectedCompanyAlias() == null
					|| ClientCacheUtil.instance().getSessionUser().getSelectedCompanyAlias().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public void changeSelectedCompany(String alias) {
		ClientCacheUtil.instance().getSessionUser().setSelectedCompanyAlias(alias);
		authorizationController.changeSelectedCompanyWithAlias(alias, new AsyncCall<Void>() {
			@Override
			public void successCall(Void result) {

			}
		});
	}

	public Widget createPage(String pageFriendlyUrl) {
		GWT.log("Request " + pageFriendlyUrl + " page initialize for open");
		if (pageFriendlyUrl.equals(URLConstant.MEMBER_LOGIN)) {
			return new MemberLogin();
		} else if (pageFriendlyUrl.equals(URLConstant.COMPANY_REGISTER)) {
			return new CompanyRegister();
		} else if (pageFriendlyUrl.equals(URLConstant.COMPANY_INFO)) {
			return new CompanyInfo();
		} else if (pageFriendlyUrl.equals(URLConstant.REAL_MANAGER_REGISTER)) {
			return new RealManagerRegister();
		} else if (pageFriendlyUrl.equals(URLConstant.CORP_MANAGER_REGISTER)) {
			return new CorpManagerRegister();
		} else if (pageFriendlyUrl.equals(URLConstant.INSPECTOR_REGISTER)) {
			return new InspectorRegister();
		} else if (pageFriendlyUrl.equals(URLConstant.USER_REGISTER)) {
			return new UserRegister();
		} else if (pageFriendlyUrl.equals(URLConstant.COMPANY_LOGO_UPLOAD)) {
			return new CompanyLogoUpload();
		} else if (pageFriendlyUrl.equals(URLConstant.USER_COMPANY_ASSIGN)) {
			return new UserCompanyAssign();
		} else if (pageFriendlyUrl.equals(URLConstant.COMPANY_AUTH_SELECT)) {
			return new CompanyAuthSelect();
		} else if (pageFriendlyUrl.equals(URLConstant.COMPANY_DOCUMENT_2)) {
			return new CompanyDocument_2();
		} else if (pageFriendlyUrl.equals(URLConstant.COMPANY_DOCUMENT)) {
			return new CompanyDocument();
		} else if (pageFriendlyUrl.equals(URLConstant.ADMIN_LOGIN)) {
			return new AdminLogin();
		} else if (pageFriendlyUrl.equals(URLConstant.LOG_HISTORY)) {
			return new LogHistory();
		} else
			// TODO bos sayfaya yonlenmeli
			return new BlankPage();
	}

}
