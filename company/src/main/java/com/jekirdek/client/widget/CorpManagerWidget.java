package com.jekirdek.client.widget;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormControlStatic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.constant.TitleType;
import com.jekirdek.client.dto.ManagerDTO;
import com.jekirdek.client.page.CompanyRegister;
import com.jekirdek.client.page.CorpManagerRegister;
import com.jekirdek.client.util.PageUtil;

public class CorpManagerWidget extends Composite {

	private static ManagerWidgetUiBinder	uiBinder	= GWT.create(ManagerWidgetUiBinder.class);

	interface ManagerWidgetUiBinder extends UiBinder<Widget, CorpManagerWidget> {
	}

	@UiField
	FormControlStatic		mersisNo, tradeName, companyCenter, name, surname, title;

	@UiField
	Button					updateBtn, deleteBtn;

	private ManagerDTO		managerDTO	= new ManagerDTO();
	private CompanyRegister	companyRegister;
	private boolean			updateMode	= false;

	public CorpManagerWidget(ManagerDTO dto, boolean openMode) {
		initWidget(uiBinder.createAndBindUi(this));
		managerDTO = dto;
		updateMode = openMode;
		initEventHandler();
		componentValueSet();
		authorizationControl();
	}

	private void authorizationControl() {
		// control user role, for anonymous user remove update button
		if (!(updateMode && PageUtil.controlUserAuthForCompanyUpdate())) {
			updateBtn.removeFromParent();
			deleteBtn.removeFromParent();
		}
	}

	private void componentValueSet() {
		title.setText(TitleType.getDesc(managerDTO.getTitle()));
		mersisNo.setText(managerDTO.getMersisNo());
		tradeName.setText(managerDTO.getTradeName());
		companyCenter.setText(managerDTO.getCompanyCenter());
		name.setText(managerDTO.getName());
		surname.setText(managerDTO.getSurname());
	}

	private void initEventHandler() {
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateBtnClicked();
			}
		});

		deleteBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				companyRegister.deleteManager(managerDTO);
				removeFromParent();
			}
		});
	}

	protected void updateBtnClicked() {
		CorpManagerRegister corpManagerRegister = companyRegister.openCorpManagerPage();
		corpManagerRegister.prepareForUpdate(managerDTO);
	}

	public CompanyRegister getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyRegister companyRegister) {
		this.companyRegister = companyRegister;
	}

}
