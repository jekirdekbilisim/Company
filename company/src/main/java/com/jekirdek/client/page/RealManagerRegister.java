package com.jekirdek.client.page;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.Combo;
import com.jekirdek.client.constant.ManagerType;
import com.jekirdek.client.constant.TitleType;
import com.jekirdek.client.dto.ManagerDTO;

public class RealManagerRegister extends AbstractPage implements IPage {

	private static ManagerRegisterUiBinder	uiBinder	= GWT.create(ManagerRegisterUiBinder.class);

	interface ManagerRegisterUiBinder extends UiBinder<Widget, RealManagerRegister> {
	}

	@UiField
	TextBox					name, surname;

	@UiField
	TextArea				profile;

	@UiField
	Combo					titleType;

	@UiField
	Button					addManagerBtn;

	private ManagerDTO		managerDTO	= new ManagerDTO();

	private CompanyRegister	companyRegister;

	public RealManagerRegister() {
		initWidget(uiBinder.createAndBindUi(this));
		createEventHandler();
		loadPageData();
	}

	private void createEventHandler() {
		addManagerBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				managerDTO.setTitle(titleType.getSelectedItemKey());
				managerDTO.setProfile(profile.getText());
				managerDTO.setName(name.getText());
				managerDTO.setSurname(surname.getText());
				managerDTO.setManagerType(ManagerType.REAL);

				companyRegister.saveOrUpdateManager(managerDTO);
			}
		});
	}

	private void loadPageData() {
		titleType.addItem(TitleType.getDesc(TitleType.BASKAN), TitleType.BASKAN);
		titleType.addItem(TitleType.getDesc(TitleType.BASKAN_VEKILI), TitleType.BASKAN_VEKILI);
		titleType.addItem(TitleType.getDesc(TitleType.KURUL_UYESI), TitleType.KURUL_UYESI);
		titleType.addItem(TitleType.getDesc(TitleType.BAGIMSIZ_BASKAN_VEKILI), TitleType.BAGIMSIZ_BASKAN_VEKILI);
		titleType.addItem(TitleType.getDesc(TitleType.BAGIMSIZ_KURUL_UYESI), TitleType.BAGIMSIZ_KURUL_UYESI);
	}

	public void prepareForUpdate(ManagerDTO paramDTO) {
		this.managerDTO = paramDTO;
		name.setText(paramDTO.getName());
		surname.setText(paramDTO.getSurname());
		profile.setText(paramDTO.getProfile());
		titleType.setSelectedItem(paramDTO.getTitle());
	}

	public CompanyRegister getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyRegister companyRegister) {
		this.companyRegister = companyRegister;
	}

	@Override
	public String pageName() {
		return messages.RealManagerRegister_pageName();
	}
}
