package com.jekirdek.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.Anchor;
import com.jekirdek.client.component.Div;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.controller.LoginMenuController;
import com.jekirdek.client.controller.LoginMenuControllerAsync;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.util.UrlUtil;

public class CompanySelectWidget extends Composite {

	private static CompanySelectUiBinder	uiBinder	= GWT.create(CompanySelectUiBinder.class);

	interface CompanySelectUiBinder extends UiBinder<Widget, CompanySelectWidget> {
	}

	private final LoginMenuControllerAsync	loginMenuController	= GWT.create(LoginMenuController.class);
	private final CompanyControllerAsync	companyController	= GWT.create(CompanyController.class);

	private CompanySelectData				companySelectData	= new CompanySelectData();
	private CompanySearchDTO				searchDTO			= new CompanySearchDTO();

	@UiField
	Div										companyWidget;

	@UiField
	Anchor									companyAnc;

	InlineLabel								companyLbl;
	Image									companyLogoImg;

	public CompanySelectWidget(CompanySelectData data) {
		initWidget(uiBinder.createAndBindUi(this));
		companySelectData = data;
		initEventHandler();
		loadWidgetData();

	}

	private void initEventHandler() {
		companyAnc.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectedCompany();
			}
		});
	}

	private void loadWidgetData() {
		companyLogoImg = new Image();
		companyLogoImg.setHeight("100px");
		// companyLogoImg.setUrl("images/company_logo.png");
		companyLogoRender();

		companyLbl = new InlineLabel();
		companyLbl.setText(companySelectData.getAlias().concat("-").concat(companySelectData.getTradeName()));

		companyAnc.getElement().appendChild(companyLogoImg.getElement());
		companyAnc.getElement().appendChild(companyLbl.getElement());
	}

	private void companyLogoRender() {
		companyController.companyHasLogo(companySelectData.getCompanyOid(), new AsyncCall<Boolean>() {
			public void successCall(Boolean result) {
				if (result) {
					companyLogoImg.setUrl(UrlUtil.generateLogoViewUrl(companySelectData.getCompanyOid()));
				} else {
					companyLogoImg.setUrl("images/company_logo.png");
				}
				// logoImgDiv.add(logoImg);
			};

		});
	}

	private void selectedCompany() {
		searchDTO.setAlias(companySelectData.getAlias());
		searchDTO.setCompanyOid(companySelectData.getCompanyOid());
		loginMenuController.authCompanySelected(searchDTO, new AsyncCall<AuthDataDTO>() {
			@Override
			public void successCall(AuthDataDTO result) {
				ClientCacheUtil.instance().loginProcess(result, URLConstant.COMPANY_INFO);
				PageUtil.dashboardHeader.renderCompanyLogo();
			};

		});
	}
}