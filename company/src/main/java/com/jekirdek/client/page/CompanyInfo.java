package com.jekirdek.client.page;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormControlStatic;
import org.gwtbootstrap3.client.ui.html.Div;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.constant.CompanyType;
import com.jekirdek.client.constant.ManagerType;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.dto.CompanyDTO;
import com.jekirdek.client.dto.CompanyInfoDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.InspectorDTO;
import com.jekirdek.client.dto.ManagerDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.widget.CorpManagerWidget;
import com.jekirdek.client.widget.InspectorWidget;
import com.jekirdek.client.widget.RealManagerWidget;

public class CompanyInfo extends AbstractPage implements IPage {

	private CompanySearchDTO			companySearchDTO	= new CompanySearchDTO();

	private static CompanyInfoUiBinder	uiBinder			= GWT.create(CompanyInfoUiBinder.class);

	interface CompanyInfoUiBinder extends UiBinder<Widget, CompanyInfo> {
	}

	private final CompanyControllerAsync	companyController	= GWT.create(CompanyController.class);

	@UiField
	FormControlStatic						companyCenter, paidCapital, phoneNo, faxNo, internetAddress;

	@UiField
	FormControlStatic						companyType, mersisNo, tradeName, alias, committedCapital, contactAdress;

	@UiField
	Div										managerWidget, inspectorWidget;

	@UiField
	Button									updatePageBtn;

	public CompanyInfo() {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		// create event handler
		createEventListener();
		// load component data
		loadCompanyInfo();
		authorizationControl();
	}

	private void authorizationControl() {
		// control user role, for anonymous user remove update button
		List<String> privilegeList = ClientCacheUtil.instance().getPrivilegeItemList();
		String selectedCompanyOid = ClientCacheUtil.instance().getSessionUser().getSelectedCompanyOid();
		List<String> authCompanyList = ClientCacheUtil.instance().getSessionUser().getAuthorizedCompanyOidList();
		if (privilegeList != null && privilegeList.contains("CompanyRegister_SELECT") && authCompanyList.contains(selectedCompanyOid)) {
			updatePageBtn.setVisible(true);
		} else {
			updatePageBtn.removeFromParent();
		}

	}

	private void loadCompanyInfo() {
		// true if user login
		companySearchDTO.setWithObjId(Boolean.FALSE);
		companySearchDTO.setAlias(ClientCacheUtil.instance().getSessionUser().getSelectedCompanyAlias());
		companyController.loadCompanyInfo(companySearchDTO, new AsyncCall<CompanyInfoDTO>() {
			@Override
			public void successCall(CompanyInfoDTO result) {
				loadCompanyData(result);
			}

		});
	}

	private void loadCompanyData(CompanyInfoDTO infoDTO) {
		if (infoDTO == null || infoDTO.getCompanyDTO() == null)
			return;
		CompanyDTO companyDTO = infoDTO.getCompanyDTO();
		companyType.setText(CompanyType.getDesc(companyDTO.getCompanyType()));
		mersisNo.setText(companyDTO.getMersisNo());
		tradeName.setText(companyDTO.getTradeName());
		alias.setText(companyDTO.getAlias());
		companyCenter.setText(companyDTO.getCompanyCenter());
		committedCapital.setText(companyDTO.getCommittedCapital());
		paidCapital.setText(companyDTO.getPaidCapital());
		phoneNo.setText(companyDTO.getPhoneNo());
		faxNo.setText(companyDTO.getFaxNo());
		internetAddress.setText(companyDTO.getInternetAddress());

		// inspector list
		if (infoDTO.getInspectorDtoList() != null) {
			for (InspectorDTO inspectorDTO : infoDTO.getInspectorDtoList()) {
				inspectorWidget.add(new InspectorWidget(inspectorDTO, Boolean.TRUE));
			}
		}

		// Manager list
		if (infoDTO.getManagerDtoList() != null) {
			for (ManagerDTO managerDTO : infoDTO.getManagerDtoList()) {
				if (ManagerType.REAL.equals(managerDTO.getManagerType()))
					managerWidget.add(new RealManagerWidget(managerDTO));
				else
					managerWidget.add(new CorpManagerWidget(managerDTO));
			}
		}
	}

	private void createEventListener() {
		updatePageBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PageUtil.navigationManager.controlOpenPageByFUrl(URLConstant.COMPANY_REGISTER);
			}
		});
	}

	@Override
	public String pageName() {
		return messages.CompanyInfo_pageName();
	}
}
