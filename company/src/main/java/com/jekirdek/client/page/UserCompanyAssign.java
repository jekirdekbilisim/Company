package com.jekirdek.client.page;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.ComboBox;
import com.jekirdek.client.component.ImageButton;
import com.jekirdek.client.controller.AuthorizationController;
import com.jekirdek.client.controller.AuthorizationControllerAsync;
import com.jekirdek.client.dto.UserCompanyAuthDTO;
import com.jekirdek.client.dto.UserCompanyAuthData;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.ListItem;

public class UserCompanyAssign extends AbstractPage implements IPage {

	private static UserCompanyAssignUiBinder	uiBinder	= GWT.create(UserCompanyAssignUiBinder.class);

	interface UserCompanyAssignUiBinder extends UiBinder<Widget, UserCompanyAssign> {
	}

	private final AuthorizationControllerAsync	authorization	= GWT.create(AuthorizationController.class);
	private UserCompanyAuthDTO					dto				= new UserCompanyAuthDTO();

	@UiField
	ComboBox									sourceCmb, targetCmb;

	@UiField
	ImageButton									rightBtn, leftBtn;

	@UiField
	Button										searchBtn, saveBtn;

	@UiField
	TextBox										userTcknTxt;

	public UserCompanyAssign() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
	}

	private void initEventHandler() {
		searchBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadCompanyData();
			}
		});

		rightBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				List<ListItem> selectedItemList = sourceCmb.getSelectedItemList();
				if (selectedItemList != null && !selectedItemList.isEmpty()) {
					targetCmb.addItemList(selectedItemList);
					sourceCmb.removeItemList(selectedItemList);
				}
			}
		});

		leftBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				List<ListItem> selectedItemList = targetCmb.getSelectedItemList();
				if (selectedItemList != null && !selectedItemList.isEmpty()) {
					sourceCmb.addItemList(selectedItemList);
					targetCmb.removeItemList(selectedItemList);
				}
			}
		});

		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dto.setSourceItemList(sourceCmb.getAllItem());
				dto.setTargetItemList(targetCmb.getAllItem());
				authorization.saveUserAuthCompany(dto, new AsyncCall<List<String>>() {

					@Override
					public void successCall(List<String> result) {
						ClientCacheUtil.instance().getSessionUser().setAuthorizedCompanyOidList(result);
						Bootbox.alert("Kaydetme işleminiz başarıyla sonuçlanmıştır");
					}
				});
			}
		});
	}

	private void loadCompanyData() {
		dto.setUserTckn(userTcknTxt.getText());
		authorization.loadUserCompany(dto, new AsyncCall<UserCompanyAuthData>() {

			@Override
			public void successCall(UserCompanyAuthData result) {
				sourceCmb.clear();
				sourceCmb.addItemList(result.getSourceItemList());
				targetCmb.clear();
				targetCmb.addItemList(result.getTargetItemList());
			}

		});
	}

	@Override
	public String pageName() {
		return messages.UserCompanyAssign_pageName();
	}
}