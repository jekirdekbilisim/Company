package com.jekirdek.client.page;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.html.Div;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.ButtonImageCell;
import com.jekirdek.client.component.GridData;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.DocumentController;
import com.jekirdek.client.controller.DocumentControllerAsync;
import com.jekirdek.client.dto.CompanyDocumentData;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.util.UrlUtil;

public class CompanyDocument_2 extends AbstractPage implements IPage {

	private static CompanyDocument2UiBinder	uiBinder	= GWT.create(CompanyDocument2UiBinder.class);

	interface CompanyDocument2UiBinder extends UiBinder<Widget, CompanyDocument_2> {
	}

	private final DocumentControllerAsync	documentController	= GWT.create(DocumentController.class);

	@UiField
	Div										accordionPanelDiv;

	@UiField
	Button									addDocBtn;

	public CompanyDocument_2() {
		initWidget(uiBinder.createAndBindUi(this));
		authorizationControl();
		initEventHandler();
		loadCompanyDocuments();
	}

	private void authorizationControl() {
		// control user role, for anonymous user remove update button
		List<String> privilegeList = ClientCacheUtil.instance().getPrivilegeItemList();
		String selectedCompanyOid = ClientCacheUtil.instance().getSessionUser().getSelectedCompanyOid();
		List<String> authCompanyList = ClientCacheUtil.instance().getSessionUser().getAuthorizedCompanyOidList();
		if (privilegeList != null && privilegeList.contains("DocumentInsert_SELECT") && authCompanyList.contains(selectedCompanyOid)) {
			addDocBtn.setVisible(true);
		} else {
			addDocBtn.removeFromParent();
		}

	}

	private void initEventHandler() {
		addDocBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PageUtil.navigationManager.controlOpenPageByFUrl(URLConstant.COMPANY_DOCUMENT);
			}
		});
	}

	public void loadCompanyDocuments() {
		documentController.loadCompanyDocumentList("", new AsyncCall<List<CompanyDocumentData>>() {
			@Override
			public void successCall(List<CompanyDocumentData> result) {
				if (result != null) {
					loadCompanyDocumentWidget(result);
				} else {
					// documentPanel.add(new Label("Şirketin yüklenmiş dökümanı bulunmamaktadır"));
				}
			}

		});
	}

	private void loadCompanyDocumentWidget(List<CompanyDocumentData> result) {
		// aynı dokuman tipini ayni kategoride toplama icin, kontrol amacli
		String docTypeeOid = null;
		Div contentDiv = null;
		GridData<CompanyDocumentData> gridData = null;
		for (CompanyDocumentData companyDocumentData : result) {
			// new doctype initialize
			if (docTypeeOid == null || !docTypeeOid.equals(companyDocumentData.getDocTypeOid())) {
				docTypeeOid = companyDocumentData.getDocTypeOid();
				contentDiv = new Div();
				gridData = new GridData<CompanyDocumentData>();
				initializeGridColumn(gridData, companyDocumentData);
				contentDiv.add(gridData);

				HTMLPanel h3 = new HTMLPanel("h3", companyDocumentData.getDocType());
				accordionPanelDiv.add(h3);
				accordionPanelDiv.add(contentDiv);
			}
			gridData.putData(companyDocumentData);
		}
		// PageUtil.jQueryAccordionPanel();
	}

	private void initializeGridColumn(GridData<CompanyDocumentData> gridData, CompanyDocumentData companyDocumentData) {
		TextColumn<CompanyDocumentData> fileNameClm = new TextColumn<CompanyDocumentData>() {

			@Override
			public String getValue(CompanyDocumentData object) {
				return object.getFileName();
			}
		};
		TextColumn<CompanyDocumentData> fileSizeClm = new TextColumn<CompanyDocumentData>() {

			@Override
			public String getValue(CompanyDocumentData object) {
				return object.getDocSize();
			}
		};
		gridData.addColumn(fileNameClm, "Dosya Adı");
		gridData.addColumn(fileSizeClm, "Dosya Boyutu");

		// if (companyDocumentData.getMetaDataValueList() != null && !companyDocumentData.getMetaDataValueList().isEmpty()) {
		//
		// for (final ListItem item : companyDocumentData.getMetaDataValueList()) {
		// TextColumn<CompanyDocumentData> metaClm = new TextColumn<CompanyDocumentData>() {
		//
		// @Override
		// public String getValue(CompanyDocumentData object) {
		// return item.getValue();
		// }
		// };
		// gridData.addColumn(metaClm, item.getKey());
		// }
		// }

		ButtonImageCell downloadBtn = new ButtonImageCell();
		Column<CompanyDocumentData, String> downloadClm = new Column<CompanyDocumentData, String>(downloadBtn) {
			@Override
			public String getValue(CompanyDocumentData object) {
				return "image";
			}
		};
		downloadClm.setFieldUpdater(new FieldUpdater<CompanyDocumentData, String>() {
			public void update(int index, CompanyDocumentData metaData, String value) {
				Window.open(UrlUtil.generateFileDownloadUrl(metaData.getDocDbStoreOid()), "_blank", "");
			}
		});

		gridData.addColumn(downloadClm, "Dosya İndir");
		gridData.addStyleName("cellTable");

	}

	@Override
	public String pageName() {
		return messages.CompanyDocument_pageName();
	}
}