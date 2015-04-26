package com.jekirdek.client.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.PanelCollapse;
import org.gwtbootstrap3.client.ui.PanelGroup;
import org.gwtbootstrap3.client.ui.PanelHeader;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.PaginationSize;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.jekirdek.client.component.Combo;
import com.jekirdek.client.component.FileInput;
import com.jekirdek.client.component.FileUploaded;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.DocumentController;
import com.jekirdek.client.controller.DocumentControllerAsync;
import com.jekirdek.client.dto.CompanyDocumentData;
import com.jekirdek.client.dto.DocumentDTO;
import com.jekirdek.client.dto.DocumentGrid;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.FileAsyncCall;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.util.UrlUtil;
import com.jekirdek.client.widget.MessageBox;

public class CompanyDocument extends AbstractPage implements IPage {

	private static DocumentInsertUiBinder	uiBinder	= GWT.create(DocumentInsertUiBinder.class);

	interface DocumentInsertUiBinder extends UiBinder<Widget, CompanyDocument> {
	}

	private final DocumentControllerAsync	documentController	= GWT.create(DocumentController.class);

	@UiField
	Combo									documentTypeCmb;

	@UiField
	DateTimePicker							announcementDate;

	@UiField
	FileInput								fileInput;

	@UiField
	Button									saveBtn, closeBtn;

	@UiField
	PanelGroup								accordionPanelGroup;

	@UiField
	Form									documentInsertForm;

	private boolean							fileUploaded		= false;
	private DocumentDTO						dto					= new DocumentDTO();

	public CompanyDocument() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		loadCmbData();
		loadCompanyDocuments();
		autorizationControl();
	}

	private void autorizationControl() {
		if (!PageUtil.controlUserAuthForCompanyUpdate()) {
			documentInsertForm.removeFromParent();
		}
	}

	private void initEventHandler() {

		fileInput.addCompletedCallback(new FileAsyncCall<SubmitCompleteEvent>() {
			@Override
			public void successCall(SubmitCompleteEvent result) {
				fileInput.getUploadBtn().setEnabled(true);
				fileUploaded = true;
				saveBtn.setEnabled(true);
			}

		});

		fileInput.addValueChangeHandler(new ValueChangeHandler<JsArray<FileUploaded>>() {

			@Override
			public void onValueChange(ValueChangeEvent<JsArray<FileUploaded>> event) {
				Growl.growl("File input change event");
			}
		});

		fileInput.getUploadBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Growl.growl("File input upload button click handler");
			}

		});

		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveDocument();
			}

		});

		closeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PageUtil.navigationManager.controlOpenPageByFUrl(URLConstant.COMPANY_DOCUMENT_2);
			}
		});
	}

	private void saveDocument() {
		if (!fileUploaded) {
			new MessageBox("Lütfen Dosya Yükleyiniz");
			return;
		}

		dto.setDocType(documentTypeCmb.getSelectedItemKey());
		dto.setFileSessionKey(fileInput.getFileSessionKey());
		dto.setAnnouncementDate(announcementDate.getValue());

		documentController.saveDocument(dto, new AsyncCall<Void>() {
			@Override
			public void successCall(Void result) {
				Bootbox.alert("İşleminiz başarılı bir şekilde sonuçlanmıştır");
				saveBtn.setEnabled(false);
				fileUploaded = false;
				saveBtn.setEnabled(false);
				loadCompanyDocuments();
			}
		});
	}

	private void loadCmbData() {
		if (!PageUtil.controlUserAuthForCompanyUpdate()) {
			documentController.loadAllDocumentTypeCmb("", new AsyncCall<List<ListItem>>() {
				@Override
				public void successCall(List<ListItem> result) {
					documentTypeCmb.addItemList(result);
				}
			});
		}

	}

	public void loadCompanyDocuments() {
		documentController.loadCompanyDocumentList("", new AsyncCall<List<CompanyDocumentData>>() {
			@Override
			public void successCall(List<CompanyDocumentData> result) {
				accordionPanelGroup.clear();
				if (result != null) {
					loadCompanyDocumentWidget(result);
				}
			}

		});
	}

	private void loadCompanyDocumentWidget(List<CompanyDocumentData> result) {

		// aynı dokuman tipini ayni kategoride toplama icin
		HashMap<String, List<CompanyDocumentData>> documentMap = groupDocumentByType(result);
		for (String docTypeOid : documentMap.keySet()) {
			List<CompanyDocumentData> companyDocumentDatas = documentMap.get(docTypeOid);
			// header
			Heading heading = new Heading(HeadingSize.H4, companyDocumentDatas.get(0).getDocType());
			PanelHeader panelHeader = new PanelHeader();
			panelHeader.setDataParent("#" + accordionPanelGroup.getId());
			panelHeader.setDataToggle(Toggle.COLLAPSE);
			panelHeader.setDataTarget("#" + docTypeOid);
			panelHeader.add(heading);
			// content body
			PanelBody panelBody = new PanelBody();
			// SimpleLayoutPanel layoutPanel = new SimpleLayoutPanel();
			initializeAndAddDataGrid(panelBody, panelHeader, companyDocumentDatas);
			// panelBody.add(layoutPanel);
			PanelCollapse panelCollapse = new PanelCollapse();
			panelCollapse.setId(docTypeOid);
			panelCollapse.add(panelBody);
			// panel
			Panel panel = new Panel();
			panel.add(panelHeader);
			panel.add(panelCollapse);
			// add panelgroup
			accordionPanelGroup.add(panel);

		}
	}

	private void initializeAndAddDataGrid(PanelBody panelBody, PanelHeader panelHeader, List<CompanyDocumentData> list) {
		final CellTable<DocumentGrid> grid = new CellTable<>();
		final SimplePager pager = new SimplePager();
		final ListDataProvider<DocumentGrid> dataProvider = new ListDataProvider<>();
		final Pagination pagination = new Pagination(PaginationSize.SMALL);

		final TextColumn<DocumentGrid> col1 = new TextColumn<DocumentGrid>() {

			@Override
			public String getValue(final DocumentGrid object) {
				return String.valueOf(object.getDocumentName());
			}
		};
		grid.addColumn(col1, "Dosya Adı");

		final TextColumn<DocumentGrid> col2 = new TextColumn<DocumentGrid>() {

			@Override
			public String getValue(final DocumentGrid object) {
				return String.valueOf(object.getAnnouncementDate());
			}
		};
		grid.addColumn(col2, "İlan Tarihi");

		final Column<DocumentGrid, String> col4 = new Column<DocumentGrid, String>(new ButtonCell(ButtonType.PRIMARY, IconType.DOWNLOAD)) {
			@Override
			public String getValue(DocumentGrid object) {
				return "Dosya İndir";
			}
		};
		col4.setFieldUpdater(new FieldUpdater<DocumentGrid, String>() {
			@Override
			public void update(int index, DocumentGrid object, String value) {
				Window.open(UrlUtil.generateFileDownloadUrl(object.getDocumentStoreOid()), "_blank", "");
			}
		});
		grid.addColumn(col4, "İndir");

		if (PageUtil.controlUserAuthForCompanyUpdate()) {
			final Column<DocumentGrid, String> col5 = new Column<DocumentGrid, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
				@Override
				public String getValue(DocumentGrid object) {
					return "Dosyayı Sil";
				}
			};
			col5.setFieldUpdater(new FieldUpdater<DocumentGrid, String>() {
				@Override
				public void update(int index, DocumentGrid object, String value) {
					deleteDocumentConfirm(object);
				}
			});
			grid.addColumn(col5, "Sil");
		}

		grid.addRangeChangeHandler(new RangeChangeEvent.Handler() {

			@Override
			public void onRangeChange(final RangeChangeEvent event) {
				pagination.rebuild(pager);
			}
		});

		grid.setBordered(true);
		grid.setCondensed(true);
		grid.setStriped(true);
		grid.setHover(true);
		// grid.setHeight("260px");
		grid.setWidth("100%");
		// grid.setPageSize(5);
		pager.setDisplay(grid);
		pager.setPageSize(5);
		pagination.clear();
		dataProvider.addDataDisplay(grid);

		panelBody.add(grid);
		panelBody.add(pagination);

		// load data to provider
		for (CompanyDocumentData companyDocumentData : list) {
			DocumentGrid documentGrid = new DocumentGrid();
			documentGrid.setAnnouncementDate(companyDocumentData.getAnnouncementDate());
			documentGrid.setDocumentName(companyDocumentData.getFileName());
			documentGrid.setDocumentStoreOid(companyDocumentData.getDocDbStoreOid());
			dataProvider.getList().add(documentGrid);
		}
		dataProvider.flush();
		pagination.rebuild(pager);
	}

	protected void deleteDocumentConfirm(final DocumentGrid deletedDocument) {
		Bootbox.confirm("Seçili dosya silinecektir, Emin misiniz?", new ConfirmCallback() {

			@Override
			public void callback(boolean result) {
				deleteDocument(deletedDocument);
			}
		});
	}

	protected void deleteDocument(final DocumentGrid deletedDocument) {
		documentController.deleteDocumentByOid(deletedDocument.getDocumentStoreOid(), new AsyncCall<Void>() {
			@Override
			public void successCall(Void result) {
				deleteSuccessMessage();
				loadCompanyDocuments();
			}
		});
	}

	protected void deleteSuccessMessage() {
		// GrowlOptions go = new GrowlOptions();
		// go.setPosition(GrowlPosition.TOP_CENTER);
		// go.setType(GrowlType.WARNING);
		// go.setIconType(IconType.WARNING.getCssName());
		Growl.growl("Silme işlemi başarılı bir şekilde sonuçlanmıştır", GrowlType.SUCCESS);
	}

	/**
	 * aynı dokuman tipini ayni kategoride toplama icin
	 * 
	 * @param result
	 * @return
	 */
	private HashMap<String, List<CompanyDocumentData>> groupDocumentByType(List<CompanyDocumentData> result) {
		HashMap<String, List<CompanyDocumentData>> documentMap = new HashMap<>();
		for (CompanyDocumentData companyDocumentData : result) {
			// current proccessing doc type
			String docTypeOid = companyDocumentData.getDocTypeOid();
			// get document list by type
			List<CompanyDocumentData> documentList = documentMap.get(docTypeOid);
			// add document to list by type
			if (documentList == null)
				documentList = new ArrayList<>();
			documentList.add(companyDocumentData);
			documentMap.put(docTypeOid, documentList);
		}
		return documentMap;
	}

	@Override
	public String pageName() {
		return messages.CompanyDocumentInsert_pageName();
	}
}