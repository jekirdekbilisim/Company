package com.jekirdek.client.page;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.Div;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.widget.CompanySelectWidget;

public class CompanyAuthSelect extends AbstractPage implements IPage {

	private static CompanyAuthSelectUiBinder	uiBinder	= GWT.create(CompanyAuthSelectUiBinder.class);

	interface CompanyAuthSelectUiBinder extends UiBinder<Widget, CompanyAuthSelect> {
	}

	private final CompanyControllerAsync	companyController	= GWT.create(CompanyController.class);

	@UiField
	Div										companyWidgetList;

	public CompanyAuthSelect() {
		initWidget(uiBinder.createAndBindUi(this));
		loadAuthCompany();
	}

	private void loadAuthCompany() {
		companyController.loadAuthCompany("", new AsyncCall<List<CompanySelectData>>() {
			@Override
			public void successCall(List<CompanySelectData> result) {
				if (result != null)
					loadAuthCompanyWidgets(result);
			}
		});
	}

	private void loadAuthCompanyWidgets(List<CompanySelectData> result) {
		for (CompanySelectData companySelectData : result) {
			companyWidgetList.add(new CompanySelectWidget(companySelectData));
		}
	}

	@Override
	public String pageName() {
		return messages.CompanyAuthSelect_pageName();
	}
}