package com.jekirdek.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.Button;
import com.jekirdek.client.component.ComboBox;
import com.jekirdek.client.component.TextField;
import com.jekirdek.client.controller.UserController;
import com.jekirdek.client.controller.UserControllerAsync;
import com.jekirdek.client.dto.UserDTO;
import com.jekirdek.client.util.AsyncCall;

public class UserRegister extends AbstractPage implements IPage {

	public static final String			TOKEN		= "user-register";

	private UserDTO						userDTO		= new UserDTO();

	private static UserRegisterUiBinder	uiBinder	= GWT.create(UserRegisterUiBinder.class);

	interface UserRegisterUiBinder extends UiBinder<Widget, UserRegister> {
	}

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final UserControllerAsync	userService	= GWT.create(UserController.class);

	@UiField
	TextField							userTCKN, userFirstName, userLastName, userEmail, userCellPhone, userPhone, userPhoneInternal;

	@UiField
	TextArea							userAddress;

	@UiField
	Button								saveBtn, cancelBtn;

	@UiField
	ComboBox							userCountryCmb, userCityCmb, userDistrictCmb;

	// @UiField
	// VerticalPanel panel;

	@UiField
	TabPanel							mainTab;

	public UserRegister() {
		initWidget(uiBinder.createAndBindUi(this));

		createEventListener();
		loadComponentData();
	}

	private void loadComponentData() {
		// Fill the comboboxes
		userCountryCmb.addItem("Türkiye");
		userCountryCmb.addItem("Amerika");

		userCityCmb.addItem("İstanbul");
		userCityCmb.addItem("Ankara");

		userDistrictCmb.addItem("Üsküdar");
		userDistrictCmb.addItem("Ümraniye");

		// userDahili.set
	}

	private void createEventListener() {
		mainTab.selectTab(0, true);

		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				userDTO.setUserTCKN(userTCKN.getValue());
				userDTO.setUserFirstName(userFirstName.getValue());
				userDTO.setUserLastName(userLastName.getValue());
				userDTO.setUserEmail(userEmail.getValue());
				userDTO.setUserCellPhone(userCellPhone.getValue());
				userDTO.setUserPhone(userPhone.getValue());
				userDTO.setUserPhoneInternal(userPhoneInternal.getValue());
				userDTO.setUserCountry(userCountryCmb.getFieldValue());
				userDTO.setUserCity(userCityCmb.getFieldValue());
				userDTO.setUserDistrict(userDistrictCmb.getFieldValue());
				userDTO.setUserAddress(userAddress.getValue());

				userService.createUser(userDTO, new AsyncCall<Void>() {

					@Override
					public void successCall(Void result) {

					}
				});
			}
		});

		cancelBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				userDTO.setUserTCKN(null);
				userDTO.setUserFirstName(null);
				userDTO.setUserLastName(null);
				userDTO.setUserEmail(null);
				userDTO.setUserCellPhone(null);
				userDTO.setUserPhone(null);
				userDTO.setUserPhoneInternal(null);
				userDTO.setUserCountry(null);
				userDTO.setUserCity(null);
				userDTO.setUserDistrict(null);
				userDTO.setUserAddress(null);
			}
		});
	}

	@Override
	public String pageName() {
		return messages.UserRegister_pageName();
	}
}
