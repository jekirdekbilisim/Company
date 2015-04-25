/**
 * 
 */
package com.jekirdek.client.page;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jekirdek.client.component.GridData;
import com.jekirdek.client.component.TextField;
import com.jekirdek.client.controller.DocumentController;
import com.jekirdek.client.controller.DocumentControllerAsync;
import com.jekirdek.client.dto.DocumentTypeDTO;
import com.jekirdek.client.util.AsyncCall;

/**
 * @author ibesli
 * 
 */
public class DocumentTypeCreate extends AbstractPage implements IPage {

	private static DocumentTypeUiBinder	uiBinder	= GWT.create(DocumentTypeUiBinder.class);

	interface DocumentTypeUiBinder extends UiBinder<Widget, DocumentTypeCreate> {
	}

	private final DocumentControllerAsync	documentAsync	= GWT.create(DocumentController.class);

	@UiField
	TextField								groupTxt, nameTxt;

	@UiField
	Button									saveBtn, clearBtn;

	@UiField
	GridData<DocumentTypeDTO>				docTypeTbl;

	private DocumentTypeDTO					dto				= new DocumentTypeDTO();

	public DocumentTypeCreate() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		initGridData();
		loadPageData();
	}

	private void initGridData() {

		// doc typecelltable
		docTypeTbl.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<DocumentTypeDTO> docTypeNameClm = new TextColumn<DocumentTypeDTO>() {

			@Override
			public String getValue(DocumentTypeDTO object) {
				return object.getName();
			}
		};
		docTypeNameClm.setSortable(true);
		docTypeTbl.addColumn(docTypeNameClm, "Döküman Class Adı");

		final SingleSelectionModel<DocumentTypeDTO> selectionModelDocType = new SingleSelectionModel<DocumentTypeDTO>();
		docTypeTbl.setSelectionModel(selectionModelDocType);
		docTypeTbl.addDomHandler(new DoubleClickHandler() {

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				dto = selectionModelDocType.getSelectedObject();
				preparePageUpdate();
			}

		}, DoubleClickEvent.getType());

	}

	private void initEventHandler() {

		clearBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				refreshPage();
			}
		});

		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveOrUpdateDocType();
			}
		});

	}

	private void loadDocTypeTable() {
		documentAsync.loadAllDocumentType("", new AsyncCall<List<DocumentTypeDTO>>() {

			@Override
			public void successCall(List<DocumentTypeDTO> result) {
				docTypeTbl.putDataList(result);
			}
		});
	}

	private void preparePageUpdate() {
		nameTxt.setFieldValue(dto.getName());
	}

	private void loadPageData() {
		loadDocTypeTable();
	}

	private void saveOrUpdateDocType() {
		dto.setName(nameTxt.getFieldValue());
		dto.setGroup(nameTxt.getFieldValue());
		/*
		 * documentAsync.saveDocType(dto, new AsyncCall<Void>() {
		 * 
		 * @Override public void successCall(Void result) { refreshPage(); loadDocTypeTable(); }
		 * 
		 * });
		 */
	}

	private void refreshPage() {
		nameTxt.setFieldValue("");
		docTypeTbl.clear();
		dto = new DocumentTypeDTO();
	}

	@Override
	public String pageName() {
		return messages.DocumentTypeCreate_pageName();
	}
}
