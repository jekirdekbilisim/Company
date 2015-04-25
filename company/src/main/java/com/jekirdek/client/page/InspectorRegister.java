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
import com.jekirdek.client.dto.InspectorDTO;

public class InspectorRegister extends AbstractPage implements IPage {

	private static InspectorRegisterUiBinder	uiBinder	= GWT.create(InspectorRegisterUiBinder.class);

	interface InspectorRegisterUiBinder extends UiBinder<Widget, InspectorRegister> {
	}

	@UiField
	TextBox					name, surname, title, registeredBranch;

	@UiField
	TextArea				address;

	@UiField
	Button					addInspectorBtn;

	private InspectorDTO	dto	= new InspectorDTO();
	private CompanyRegister	companyRegister;

	public InspectorRegister() {
		initWidget(uiBinder.createAndBindUi(this));
		createEventHandler();
	}

	private void createEventHandler() {
		addInspectorBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addIspectorClicked();
			}

		});
	}

	private void addIspectorClicked() {
		dto.setName(name.getText());
		dto.setSurname(surname.getText());
		dto.setTitle(title.getText());
		dto.setRegisteredBranch(registeredBranch.getText());
		dto.setAddress(address.getText());

		companyRegister.saveOrUpdateInspector(dto);
	}

	public void openForUpdate(InspectorDTO inspectorDTO) {
		this.dto = inspectorDTO;
		name.setText(dto.getName());
		surname.setText(dto.getSurname());
		title.setText(dto.getTitle());
		registeredBranch.setText(dto.getRegisteredBranch());
		address.setText(dto.getAddress());
	}

	public CompanyRegister getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyRegister companyRegister) {
		this.companyRegister = companyRegister;
	}

	@Override
	public String pageName() {
		return messages.InspectorRegister_pageName();
	}
}
