/**
 * 
 */
package com.jekirdek.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.PasswordTextField;
import com.jekirdek.client.component.TextField;
import com.jekirdek.client.controller.LoginMenuController;
import com.jekirdek.client.controller.LoginMenuControllerAsync;
import com.jekirdek.client.dto.AdminLoginDTO;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.PageUtil;

/**
 * @author ibesli
 * 
 */
public class AdminLogin extends AbstractPage implements IPage {

	private static AdminLoginUiBinder uiBinder = GWT.create(AdminLoginUiBinder.class);

	interface AdminLoginUiBinder extends UiBinder<Widget, AdminLogin> {
	}

	private final LoginMenuControllerAsync loginMenuController = GWT.create(LoginMenuController.class);

	private AdminLoginDTO dto = new AdminLoginDTO();

	@UiField
	TextField userNameTxt;

	@UiField
	PasswordTextField password;

	@UiField
	Button loginBtn;

	public AdminLogin() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		userNameTxt.setText("jekirdek");
		password.setText("jekirdek");
	}

	private void initEventHandler() {
		loginBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loginProcess();
			}
		});
		userNameTxt.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					loginBtn.click();
				}
			}
		});

		password.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					loginBtn.click();
				}
			}
		});
	}

	private void loginProcess() {
		dto.setUserName(userNameTxt.getFieldValue());
		dto.setPassword(password.getFieldValue());
		loginMenuController.adminLogin(dto, new AsyncCall<AuthDataDTO>() {
			@Override
			public void successCall(AuthDataDTO result) {
				if (result != null && result.getSessionUser() != null && result.getSessionUser().getLogin()) {
					ClientCacheUtil.instance().loginProcess(result, null);
					PageUtil.dashboardHeader.renderCompanyLogo();
				}
			}
		});
	}

	@Override
	public String pageName() {
		return messages.AdminLogin_pageName();
	}
}
