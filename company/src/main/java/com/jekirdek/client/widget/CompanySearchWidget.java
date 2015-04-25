package com.jekirdek.client.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.SuggestBox;
import com.jekirdek.client.component.SuggestBoxComponent;
import com.jekirdek.client.component.SuggestBoxData;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.AuthorizationController;
import com.jekirdek.client.controller.AuthorizationControllerAsync;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.dto.CompanySearchSuggestDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.PageUtil;

public class CompanySearchWidget extends Composite {

	private static CompanySearchUiBinder	uiBinder	= GWT.create(CompanySearchUiBinder.class);

	interface CompanySearchUiBinder extends UiBinder<Widget, CompanySearchWidget> {
	}

	private final CompanyControllerAsync		companyController		= GWT.create(CompanyController.class);
	private final AuthorizationControllerAsync	authorizationController	= GWT.create(AuthorizationController.class);

	private CompanySearchSuggestDTO				dto						= new CompanySearchSuggestDTO();

	// @UiField
	SuggestBox									companySuggestBox;

	public CompanySearchWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		// initEventHandler();
		// loadData();
	}

	private void loadData() {
		if (ClientCacheUtil.instance().getCompanySuggestDataList() != null
				&& !ClientCacheUtil.instance().getCompanySuggestDataList().isEmpty()) {
			loadData(ClientCacheUtil.instance().getCompanySuggestDataList());
		} else {
			companyController.searchCompanySuggest(dto, new AsyncCall<List<ListItem>>() {
				public void successCall(List<ListItem> result) {
					loadData(result);
					ClientCacheUtil.instance().setCompanySuggestDataList(result);
				};

			});
		}
	}

	private void loadData(List<ListItem> result) {
		SuggestBoxData companySuggestionList = (SuggestBoxData) companySuggestBox.getSuggestOracle();
		companySuggestionList.clear();
		if (result != null && result != null) {
			for (ListItem item : result) {
				companySuggestionList.add(item);
			}
		}
	}

	private void initEventHandler() {
		companySuggestBox.addSelectionHandler(new SelectionHandler<Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				String companyOid = null;
				if (event.getSelectedItem() instanceof SuggestBoxComponent) {
					companyOid = ((SuggestBoxComponent) event.getSelectedItem()).getSelectedInputId();
				}
				String selected = event.getSelectedItem().getReplacementString();
				int index = selected.lastIndexOf("-");
				String alias = selected.substring(index + 1, selected.length());
				changeSelectedCompany(alias, companyOid);
				PageUtil.navigationManager.controlOpenPageByFUrl(URLConstant.COMPANY_INFO);
				companySuggestBox.setText("");
			}

		});

	}

	private void changeSelectedCompany(String alias, String companyOid) {
		ClientCacheUtil.instance().getSessionUser().setSelectedCompanyAlias(alias);
		ClientCacheUtil.instance().getSessionUser().setSelectedCompanyOid(companyOid);
		authorizationController.changeSelectedCompanyWithOid(companyOid, new AsyncCall<Void>() {
			@Override
			public void successCall(Void result) {
				PageUtil.dashboardHeader.renderCompanyLogo();
			}

		});
	}
}