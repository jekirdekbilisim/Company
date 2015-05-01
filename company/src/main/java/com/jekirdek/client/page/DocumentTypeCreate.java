/**
 * 
 */
package com.jekirdek.client.page;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
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

	private final DocumentControllerAsync	documentController	= GWT.create(DocumentController.class);

	@UiField
	TextBox									groupTxt, nameTxt;

	@UiField
	Button									saveBtn, clearBtn;

	@UiField
	CellTable<DocumentTypeDTO>				docTypeGrid;

	@UiField
	Pagination								docTypePagination;

	private DocumentTypeDTO					dto					= new DocumentTypeDTO();
	final SimplePager						pager				= new SimplePager();
	final ListDataProvider<DocumentTypeDTO>	dataProvider		= new ListDataProvider<>();

	public DocumentTypeCreate() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		initGridData();
		loadPageData();
	}

	private void initGridData() {

		TextColumn<DocumentTypeDTO> documentGroupClm = new TextColumn<DocumentTypeDTO>() {

			@Override
			public String getValue(DocumentTypeDTO object) {
				return object.getGroup();
			}
		};
		documentGroupClm.setSortable(true);
		docTypeGrid.addColumn(documentGroupClm, "Döküman Grubu");

		TextColumn<DocumentTypeDTO> documentTypeClm = new TextColumn<DocumentTypeDTO>() {

			@Override
			public String getValue(DocumentTypeDTO object) {
				return object.getName();
			}
		};
		documentTypeClm.setSortable(true);
		docTypeGrid.addColumn(documentTypeClm, "Döküman Tipi");

		final Column<DocumentTypeDTO, String> updateClm = new Column<DocumentTypeDTO, String>(new ButtonCell(ButtonType.PRIMARY,
				IconType.PENCIL)) {
			@Override
			public String getValue(DocumentTypeDTO object) {
				return "Güncelle";
			}
		};
		updateClm.setFieldUpdater(new FieldUpdater<DocumentTypeDTO, String>() {
			@Override
			public void update(int index, DocumentTypeDTO row, String value) {
				preparePageUpdate(row);
			}
		});
		docTypeGrid.addColumn(updateClm, "Güncelle");

		pager.setDisplay(docTypeGrid);
		pager.setPageSize(5);
		dataProvider.addDataDisplay(docTypeGrid);
		docTypePagination.rebuild(pager);

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
		documentController.loadAllDocumentType("", new AsyncCall<List<DocumentTypeDTO>>() {

			@Override
			public void successCall(List<DocumentTypeDTO> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				dataProvider.flush();
				docTypePagination.rebuild(pager);
			}
		});
	}

	private void preparePageUpdate(DocumentTypeDTO row) {
		groupTxt.setText(row.getGroup());
		nameTxt.setText(row.getName());
		dto.setObjid(row.getObjid());
	}

	private void loadPageData() {
		loadDocTypeTable();
	}

	private void saveOrUpdateDocType() {
		dto.setName(nameTxt.getText());
		dto.setGroup(nameTxt.getText());

		documentController.saveDocumentType(dto, new AsyncCall<Void>() {

			@Override
			public void successCall(Void result) {
				refreshPage();
				loadDocTypeTable();
			}

		});

	}

	private void refreshPage() {
		groupTxt.setText("");
		nameTxt.setText("");
		dto = new DocumentTypeDTO();
	}

	@Override
	public String pageName() {
		return messages.DocumentTypeCreate_pageName();
	}
}
