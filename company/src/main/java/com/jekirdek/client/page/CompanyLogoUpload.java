package com.jekirdek.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.FileInput;
import com.jekirdek.client.component.FileUploaded;
import com.jekirdek.client.component.Image;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.dto.CompanyLogoDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.FileAsyncCall;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.util.UrlUtil;
import com.jekirdek.client.widget.MessageBox;

public class CompanyLogoUpload extends AbstractPage implements IPage {

	private static CompanyLogoUiBinder	uiBinder	= GWT.create(CompanyLogoUiBinder.class);

	interface CompanyLogoUiBinder extends UiBinder<Widget, CompanyLogoUpload> {
	}

	private final CompanyControllerAsync	companyService	= GWT.create(CompanyController.class);
	CompanyLogoDTO							dto				= new CompanyLogoDTO();

	@UiField
	FileInput								fileInput;

	@UiField
	Image									logoImg;

	public CompanyLogoUpload() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		loadComponent();

	}

	private void loadComponent() {
		String companyOid = ClientCacheUtil.instance().getSessionUser().getSelectedCompanyOid();
		logoImg.addStyleName("maxH175");
		logoImg.setUrl(UrlUtil.generateLogoViewUrl(companyOid));

	}

	private void initEventHandler() {
		fileInput.addCompletedCallback(new FileAsyncCall<SubmitCompleteEvent>() {
			@Override
			public void successCall(SubmitCompleteEvent result) {
				showUploadedImage();
				fileInput.getUploadBtn().setEnabled(true);
			}
		});

		fileInput.addValueChangeHandler(new ValueChangeHandler<JsArray<FileUploaded>>() {

			@Override
			public void onValueChange(ValueChangeEvent<JsArray<FileUploaded>> event) {

			}
		});

		fileInput.getUploadBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveCompanyLogo();
			}

		});
	}

	private void showUploadedImage() {

	}

	private void saveCompanyLogo() {
		dto.setLogoFileSessionKey(fileInput.getFileSessionKey());
		companyService.logoUpload(dto, new AsyncCall<Void>() {
			@Override
			public void successCall(Void result) {
				PageUtil.dashboardHeader.renderCompanyLogo();
				new MessageBox("işleminiz başarılı olarak gerçekleştirilmiştir");
			}
		});
	}

	@Override
	public String pageName() {
		return messages.CompanyLogoUpload_pageName();
	}
}