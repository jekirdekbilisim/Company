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
import com.jekirdek.client.dto.InspectorDTO;
import com.jekirdek.client.page.CompanyRegister;
import com.jekirdek.client.page.InspectorRegister;
import com.jekirdek.client.util.ClientCacheUtil;

public class InspectorWidget extends Composite {

	private static InspectorWidgetUiBinder uiBinder = GWT.create(InspectorWidgetUiBinder.class);

	interface InspectorWidgetUiBinder extends UiBinder<Widget, InspectorWidget> {
	}

	@UiField
	FormControlStatic name, surname, title, registeredBranch, address;

	@UiField
	Button updateBtn, deleteBtn;

	private InspectorDTO inspectorDTO = new InspectorDTO();
	private CompanyRegister companyRegister;
	private Boolean readOnly = Boolean.TRUE;

	public InspectorWidget(InspectorDTO inspectorDTO, Boolean readOnly) {
		initWidget(uiBinder.createAndBindUi(this));
		this.inspectorDTO = inspectorDTO;
		setReadOnly(readOnly);
		componentValueSet();
		authorizationControl();
		createEventHandler();
	}

	private void componentValueSet() {
		name.setText(inspectorDTO.getName());
		surname.setText(inspectorDTO.getSurname());
		title.setText(inspectorDTO.getTitle());
		registeredBranch.setText(inspectorDTO.getRegisteredBranch());
		address.setText(inspectorDTO.getAddress());
	}

	private void authorizationControl() {
		// control user role, for anonymous user remove update button
		if (!readOnly && ClientCacheUtil.instance().getPrivilegeItemList() != null
				&& ClientCacheUtil.instance().getPrivilegeItemList().contains("CompanyRegister_SELECT")) {
			updateBtn.setVisible(true);
			deleteBtn.setVisible(true);
		} else {
			updateBtn.removeFromParent();
			deleteBtn.removeFromParent();
		}
	}

	private void createEventHandler() {
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				openInspectorPopup();
			}
		});

		deleteBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				companyRegister.deleteInspector(inspectorDTO);
				removeFromParent();
			}
		});
	}

	private void openInspectorPopup() {
		InspectorRegister inspectorRegister = companyRegister.openInspectorCreatePopup();
		inspectorRegister.openForUpdate(inspectorDTO);
	}

	public CompanyRegister getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyRegister companyRegister) {
		this.companyRegister = companyRegister;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		authorizationControl();
	}

}
