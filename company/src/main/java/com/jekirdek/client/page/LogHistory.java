package com.jekirdek.client.page;

import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.jekirdek.client.controller.LogHistoryController;
import com.jekirdek.client.controller.LogHistoryControllerAsync;
import com.jekirdek.client.dto.LogHistoryDTO;
import com.jekirdek.client.dto.LogHistoryData;
import com.jekirdek.client.util.AsyncCall;

public class LogHistory extends AbstractPage implements IPage {

	private static LogHistoryUiBinder		uiBinder	= GWT.create(LogHistoryUiBinder.class);

	private final LogHistoryControllerAsync	controller	= GWT.create(LogHistoryController.class);

	interface LogHistoryUiBinder extends UiBinder<Widget, LogHistory> {
	}

	private LogHistoryDTO					dto				= new LogHistoryDTO();

	@UiField
	DateTimePicker							startDate, finishDate;

	@UiField
	Button									searchBtn;

	@UiField
	CellTable<LogHistoryData>				logGrid;

	@UiField
	Pagination								logGridPagination;

	final SimplePager						pager			= new SimplePager();
	final ListDataProvider<LogHistoryData>	dataProvider	= new ListDataProvider<>();

	public LogHistory() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		initTable();
		initDateField();
	}

	private void initDateField() {
		Date startDateInit = new Date();
		CalendarUtil.addMonthsToDate(startDateInit, -1);
		startDate.setStartDate(startDateInit);
		finishDate.setStartDate(new Date());
	}

	private void initEventHandler() {
		searchBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				searchLogHistory();

			}

		});
	}

	private void searchLogHistory() {
		dto.setStartDate(startDate.getValue());
		dto.setFinishDate(finishDate.getValue());
		controller.searchLogHistoryByDate(dto, new AsyncCall<List<LogHistoryData>>() {
			@Override
			public void successCall(List<LogHistoryData> result) {
				// load data to provider
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				dataProvider.flush();
				logGridPagination.rebuild(pager);
			}
		});
	}

	private void initTable() {
		TextColumn<LogHistoryData> actionLogType = new TextColumn<LogHistoryData>() {

			@Override
			public String getValue(LogHistoryData object) {
				return object.getActionLogType();
			}
		};
		TextColumn<LogHistoryData> userName = new TextColumn<LogHistoryData>() {

			@Override
			public String getValue(LogHistoryData object) {
				return object.getUser();
			}
		};
		// TextColumn<LogHistoryData> effectedUserName = new TextColumn<LogHistoryData>() {
		//
		// @Override
		// public String getValue(LogHistoryData object) {
		// return object.getEffectedUser();
		// }
		// };
		TextColumn<LogHistoryData> companyName = new TextColumn<LogHistoryData>() {

			@Override
			public String getValue(LogHistoryData object) {
				return object.getCompany();
			}
		};
		TextColumn<LogHistoryData> documentTypeName = new TextColumn<LogHistoryData>() {

			@Override
			public String getValue(LogHistoryData object) {
				return object.getDocumentType();
			}
		};
		TextColumn<LogHistoryData> managerName = new TextColumn<LogHistoryData>() {

			@Override
			public String getValue(LogHistoryData object) {
				return object.getManager();
			}
		};
		TextColumn<LogHistoryData> inspectorName = new TextColumn<LogHistoryData>() {

			@Override
			public String getValue(LogHistoryData object) {
				return object.getInspector();
			}
		};

		logGrid.addColumn(actionLogType, messages.LogHistory_actionLogType());
		logGrid.addColumn(userName, messages.LogHistory_userName());
		// logGrid.addColumn(effectedUserName, messages.LogHistory_effectedUserName());
		logGrid.addColumn(companyName, messages.LogHistory_companyName());
		logGrid.addColumn(documentTypeName, messages.LogHistory_documentTypeName());
		logGrid.addColumn(managerName, messages.LogHistory_managerName());
		logGrid.addColumn(inspectorName, messages.LogHistory_inspectorName());

		pager.setDisplay(logGrid);
		pager.setPageSize(5);
		logGridPagination.clear();
		dataProvider.addDataDisplay(logGrid);

		logGrid.addRangeChangeHandler(new RangeChangeEvent.Handler() {

			@Override
			public void onRangeChange(final RangeChangeEvent event) {
				logGridPagination.rebuild(pager);
			}
		});

	}

	@Override
	public String pageName() {
		return messages.LogHistory_pageName();
	}
}