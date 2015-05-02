package com.jekirdek.client.page;

import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormControlStatic;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;

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
import com.jekirdek.client.component.Combo;
import com.jekirdek.client.controller.AuthorizationController;
import com.jekirdek.client.controller.AuthorizationControllerAsync;
import com.jekirdek.client.dto.UserCompanyAuthDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.ListItem;

public class UserCompanyAssign extends AbstractPage implements IPage {

	private static UserCompanyAssignUiBinder	uiBinder	= GWT.create(UserCompanyAssignUiBinder.class);

	interface UserCompanyAssignUiBinder extends UiBinder<Widget, UserCompanyAssign> {
	}

	private final AuthorizationControllerAsync	authorization	= GWT.create(AuthorizationController.class);

	@UiField
	Combo										notAuthorizedCompanyCmb;

	@UiField
	Button										searchBtn, saveBtn;

	@UiField
	TextBox										userTcknTxt;

	@UiField
	FormGroup									nameFG, surnameFG;

	@UiField
	FormControlStatic							name, surname;

	@UiField
	CellTable<ListItem>							authorizedCompanyTable;

	@UiField
	Pagination									authorizedCompanyPagination;

	private UserCompanyAuthDTO					dto				= new UserCompanyAuthDTO();
	final SimplePager							pager			= new SimplePager();
	final ListDataProvider<ListItem>			dataProvider	= new ListDataProvider<>();

	public UserCompanyAssign() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		initCellTable();
	}

	private void initEventHandler() {
		searchBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadCompanyData();
			}
		});

		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// dto.setSourceItemList(companyCmb.getAllItem());
				// dto.setTargetItemList(targetCmb.getAllItem());
				authorization.saveUserAuthCompany(dto, new AsyncCall<List<String>>() {

					@Override
					public void successCall(List<String> result) {
						ClientCacheUtil.instance().getSessionUser().setAuthorizedCompanyOidList(result);
						Bootbox.alert("Kaydetme işleminiz başarıyla sonuçlanmıştır");
					}
				});
			}
		});
	}

	private void initCellTable() {

		TextColumn<ListItem> documentTypeClm = new TextColumn<ListItem>() {

			@Override
			public String getValue(ListItem object) {
				return object.getValue();
			}
		};
		authorizedCompanyTable.addColumn(documentTypeClm, "Döküman Tipi");

		final Column<ListItem, String> deleteClm = new Column<ListItem, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(ListItem object) {
				return "Sil";
			}
		};
		deleteClm.setFieldUpdater(new FieldUpdater<ListItem, String>() {
			@Override
			public void update(int index, ListItem row, String value) {
				deleteCompanyAuth(row);
			}
		});
		authorizedCompanyTable.addColumn(deleteClm, "Sil");

		pager.setDisplay(authorizedCompanyTable);
		pager.setPageSize(5);
		dataProvider.addDataDisplay(authorizedCompanyTable);
		authorizedCompanyPagination.rebuild(pager);

	}

	protected void deleteCompanyAuth(ListItem row) {

	}

	private void loadCompanyData() {
		dto.setTckn(userTcknTxt.getText());
		authorization.loadUserCompany(dto, new AsyncCall<UserCompanyAuthDTO>() {

			@Override
			public void successCall(UserCompanyAuthDTO result) {
				nameFG.setVisible(true);
				surnameFG.setVisible(true);
				name.setText(result.getName());
				surname.setText(result.getSurname());
				notAuthorizedCompanyCmb.clear();
				dataProvider.getList().clear();
				if (result.getNotAuthorizedCompanyList() != null)
					notAuthorizedCompanyCmb.addItemList(result.getNotAuthorizedCompanyList());
				if (result.getAuthorizedCompanyList() != null) {
					loadAuthorizedCompanyTable(result.getAuthorizedCompanyList());
				}
			}
		});
	}

	protected void loadAuthorizedCompanyTable(List<ListItem> authorizedCompanyList) {
		dataProvider.getList().addAll(authorizedCompanyList);
		dataProvider.flush();
		authorizedCompanyPagination.rebuild(pager);
	}

	@Override
	public String pageName() {
		return messages.UserCompanyAssign_pageName();
	}
}