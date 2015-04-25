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
import com.jekirdek.client.page.RealManagerRegister;
import com.jekirdek.client.util.ClientCacheUtil;

public class RealManagerWidget extends Composite {

	private static ManagerWidgetUiBinder	uiBinder	= GWT.create(ManagerWidgetUiBinder.class);

	interface ManagerWidgetUiBinder extends UiBinder<Widget, RealManagerWidget> {
	}

	@UiField
	FormControlStatic		name, surname, profile, title;

	@UiField
	Button					updateBtn, deleteBtn;

	private ManagerDTO		managerDTO	= new ManagerDTO();
	private CompanyRegister	companyRegister;

	public RealManagerWidget(ManagerDTO dto) {
		initWidget(uiBinder.createAndBindUi(this));
		managerDTO = dto;
		initEventHandler();
		componentValueSet();
		authorizationControl();
	}

	private void authorizationControl() {
		// control user role, for anonymous user remove update button
		if (ClientCacheUtil.instance().getPrivilegeItemList() == null
				|| !ClientCacheUtil.instance().getPrivilegeItemList().contains("CompanyRegister_SELECT")) {
			updateBtn.removeFromParent();
			deleteBtn.removeFromParent();
		}
	}

	private void componentValueSet() {
		name.setText(managerDTO.getName());
		surname.setText(managerDTO.getSurname());
		profile.setText(managerDTO.getProfile());
		title.setText(TitleType.getDesc(managerDTO.getTitle()));
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
		RealManagerRegister realManagerRegister = companyRegister.openRealManagerPage();
		realManagerRegister.prepareForUpdate(managerDTO);
	}

	public CompanyRegister getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyRegister companyRegister) {
		this.companyRegister = companyRegister;
	}

}
