package com.jekirdek.client.page;

import org.gwtbootstrap3.client.ui.Button;
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

public class CorpManagerRegister extends AbstractPage implements IPage {

	private static ManagerRegisterUiBinder	uiBinder	= GWT.create(ManagerRegisterUiBinder.class);

	interface ManagerRegisterUiBinder extends UiBinder<Widget, CorpManagerRegister> {
	}

	@UiField
	TextBox					mersisNo, tradeName, companyCenter, name, surname;

	@UiField
	Combo					titleType;

	@UiField
	Button					addManagerBtn;

	private ManagerDTO		managerDTO	= new ManagerDTO();

	private CompanyRegister	companyRegister;

	public CorpManagerRegister() {
		initWidget(uiBinder.createAndBindUi(this));
		createEventHandler();
		loadPageData();
	}

	private void createEventHandler() {
		addManagerBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				managerDTO.setTitle(titleType.getSelectedItemKey());
				managerDTO.setCompanyCenter(companyCenter.getText());
				managerDTO.setMersisNo(mersisNo.getText());
				managerDTO.setTradeName(tradeName.getText());
				managerDTO.setName(name.getText());
				managerDTO.setSurname(surname.getText());
				managerDTO.setManagerType(ManagerType.CORPORATE);

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
		titleType.setSelectedItem(paramDTO.getTitle());
		companyCenter.setText(paramDTO.getCompanyCenter());
		mersisNo.setText(paramDTO.getMersisNo());
		tradeName.setText(paramDTO.getTradeName());
		name.setText(paramDTO.getName());
		surname.setText(paramDTO.getSurname());
	}

	public CompanyRegister getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyRegister companyRegister) {
		this.companyRegister = companyRegister;
	}

	@Override
	public String pageName() {
		return messages.CorpManagerRegister_pageName();
	}
}
