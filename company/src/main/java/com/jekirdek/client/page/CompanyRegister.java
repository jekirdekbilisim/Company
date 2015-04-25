package com.jekirdek.client.page;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Div;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlPosition;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.component.Combo;
import com.jekirdek.client.constant.CompanyType;
import com.jekirdek.client.constant.ManagerType;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.dto.CompanyInfoDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.InspectorDTO;
import com.jekirdek.client.dto.ManagerDTO;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientConvertUtil;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.widget.CorpManagerWidget;
import com.jekirdek.client.widget.InspectorWidget;
import com.jekirdek.client.widget.RealManagerWidget;

public class CompanyRegister extends AbstractPage implements IPage {

	private CompanyInfoDTO					companyInfoDTO		= new CompanyInfoDTO();
	private CompanySearchDTO				companySearchDTO	= new CompanySearchDTO();

	private static CompanyRegisterUiBinder	uiBinder			= GWT.create(CompanyRegisterUiBinder.class);

	interface CompanyRegisterUiBinder extends UiBinder<Widget, CompanyRegister> {
	}

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final CompanyControllerAsync	companyController	= GWT.create(CompanyController.class);

	@UiField
	TextBox									mersisNo, tradeName, companyCenter;

	@UiField
	Combo									companyType;

	@UiField
	TextBox									committedCapital, paidCapital, phoneNo, faxNo, internetAddress, alias, contactAdress;

	@UiField
	Button									saveBtn, addRealManagerBtn, addCorpManagerBtn, addInspectorBtn, closeBtn;

	@UiField
	Div										managerWidgetDiv, inspectorWidgetDiv;

	public CompanyRegister() {
		initWidget(uiBinder.createAndBindUi(this));
		// create event handler
		initEventListener();
		// load component data
		loadPageData();

	}

	private void loadPageData() {
		loadCompanyTypeCmbData();
		// true if user login
		// companySearchDTO.setWithObjId(Boolean.TRUE);
		// companySearchDTO.setAlias(ClientCacheUtil.instance().getSessionUser().getSelectedCompanyAlias());
		companyController.loadCompanyInfo(companySearchDTO, new AsyncCall<CompanyInfoDTO>() {
			@Override
			public void successCall(CompanyInfoDTO result) {
				companyInfoDTO = result;
				loadCompanyInfos();
			}
		});
	}

	private void loadCompanyTypeCmbData() {
		companyType.addItem(CompanyType.ANONIM, CompanyType.ANONIM);
		companyType.addItem(CompanyType.IRTIBAT_BUROSU, CompanyType.IRTIBAT_BUROSU);
		companyType.addItem(CompanyType.KOLEKTIF, CompanyType.KOLEKTIF);
		companyType.addItem(CompanyType.KOMANDIT, CompanyType.KOMANDIT);
		companyType.addItem(CompanyType.LIMITED, CompanyType.LIMITED);
		companyType.addItem(CompanyType.SUBE, CompanyType.SUBE);
	}

	private void initEventListener() {

		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveCompany();
			}
		});

		addRealManagerBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				openRealManagerPage();
			}
		});

		addCorpManagerBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				openCorpManagerPage();
			}
		});

		addInspectorBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addIncspectorClicked();
			}
		});

		closeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PageUtil.navigationManager.controlOpenPageByFUrl(URLConstant.COMPANY_INFO);
			}
		});

		alias.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				alias.setText(ClientConvertUtil.convertUpperCaseUrl(alias.getText()));
			}
		});

		tradeName.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				tradeName.setText(ClientConvertUtil.convertUpperCaseTurkish(tradeName.getText()));
			}
		});
	}

	private void loadCompanyInfos() {
		loadCompanyDetails();
		loadManagersInfoList();
		loadInspectorsInfoList();
	}

	private void loadCompanyDetails() {
		if (companyInfoDTO == null || companyInfoDTO.getCompanyDTO() == null)
			return;
		companyType.setSelectedItem(companyInfoDTO.getCompanyDTO().getCompanyType());
		mersisNo.setText(companyInfoDTO.getCompanyDTO().getMersisNo());
		tradeName.setText(companyInfoDTO.getCompanyDTO().getTradeName());
		alias.setText(companyInfoDTO.getCompanyDTO().getAlias());
		companyCenter.setText(companyInfoDTO.getCompanyDTO().getCompanyCenter());
		committedCapital.setText(companyInfoDTO.getCompanyDTO().getCommittedCapital());
		paidCapital.setText(companyInfoDTO.getCompanyDTO().getPaidCapital());
		phoneNo.setText(companyInfoDTO.getCompanyDTO().getPhoneNo());
		faxNo.setText(companyInfoDTO.getCompanyDTO().getFaxNo());
		internetAddress.setText(companyInfoDTO.getCompanyDTO().getInternetAddress());
	}

	private void loadManagersInfoList() {
		// Manager list
		managerWidgetDiv.clear();
		if (companyInfoDTO.getManagerDtoList() != null) {
			for (ManagerDTO managerDTO : companyInfoDTO.getManagerDtoList()) {
				if (ManagerType.REAL.equals(managerDTO.getManagerType())) {
					RealManagerWidget managerWidget = new RealManagerWidget(managerDTO);
					managerWidget.setCompanyRegister(this);
					managerWidgetDiv.add(managerWidget);
				} else {
					CorpManagerWidget managerWidget = new CorpManagerWidget(managerDTO);
					managerWidget.setCompanyRegister(this);
					managerWidgetDiv.add(managerWidget);
				}
			}
		}
	}

	private void loadInspectorsInfoList() {
		// inspector list
		inspectorWidgetDiv.clear();
		if (companyInfoDTO.getInspectorDtoList() != null) {
			for (InspectorDTO inspectorDTO : companyInfoDTO.getInspectorDtoList()) {
				InspectorWidget inspectorWidget = new InspectorWidget(inspectorDTO, Boolean.FALSE);
				inspectorWidget.setCompanyRegister(this);
				inspectorWidgetDiv.add(inspectorWidget);
			}
		}
	}

	public RealManagerRegister openRealManagerPage() {
		RealManagerRegister realManager = (RealManagerRegister) popupPage(URLConstant.REAL_MANAGER_REGISTER);
		realManager.setCompanyRegister(this);
		return realManager;
	}

	public CorpManagerRegister openCorpManagerPage() {
		CorpManagerRegister corpManager = (CorpManagerRegister) popupPage(URLConstant.CORP_MANAGER_REGISTER);
		corpManager.setCompanyRegister(this);
		return corpManager;
	}

	private void addIncspectorClicked() {
		openInspectorCreatePopup();
	}

	public InspectorRegister openInspectorCreatePopup() {
		InspectorRegister inspectorRegister = (InspectorRegister) popupPage(URLConstant.INSPECTOR_REGISTER);
		inspectorRegister.setCompanyRegister(this);
		return inspectorRegister;
	}

	private void saveCompany() {
		companyInfoDTO.getCompanyDTO().setCommittedCapital(committedCapital.getText());
		companyInfoDTO.getCompanyDTO().setCompanyCenter(companyCenter.getText());
		companyInfoDTO.getCompanyDTO().setCompanyType(companyType.getSelectedItemKey());
		companyInfoDTO.getCompanyDTO().setContactAddress(contactAdress.getText());
		companyInfoDTO.getCompanyDTO().setFaxNo(faxNo.getText());
		companyInfoDTO.getCompanyDTO().setInternetAddress(internetAddress.getText());
		companyInfoDTO.getCompanyDTO().setMersisNo(mersisNo.getText());
		companyInfoDTO.getCompanyDTO().setPaidCapital(paidCapital.getText());
		companyInfoDTO.getCompanyDTO().setPhoneNo(phoneNo.getText());
		companyInfoDTO.getCompanyDTO().setTradeName(tradeName.getText());
		companyInfoDTO.getCompanyDTO().setAlias(alias.getText());
		// sign process
		companyInfoDTO.setSignableInput(companyInfoDTO.toString());
		// companyInfoDTO.setSign(SmartCardUtil.signDocument(companyInfoDTO.getSignableInput()));

		companyController.saveUpdateCompany(companyInfoDTO, new AsyncCall<Void>() {

			@Override
			public void successCall(Void result) {
				Bootbox.alert("işleminiz başarılı olarak gerçekleştirilmiştir");
			}
		});
	}

	// call from manager created update popup
	public void saveOrUpdateManager(ManagerDTO dirDTO) {
		if (dirDTO == null)
			growlMessage("UYARI", "Yönetici bilgisi boş olamaz, Hata alındı");

		if (dirDTO.getObjid() == null) {
			companyInfoDTO.getManagerDtoList().add(dirDTO);
		} else {
			for (ManagerDTO managerDto : companyInfoDTO.getManagerDtoList()) {
				if (managerDto.getObjid() != null && managerDto.getObjid().equals(dirDTO.getObjid())) {
					managerDto.setCompanyCenter(dirDTO.getCompanyCenter());
					managerDto.setManagerType(dirDTO.getManagerType());
					managerDto.setMersisNo(dirDTO.getMersisNo());
					managerDto.setTitle(dirDTO.getTitle());
					managerDto.setName(dirDTO.getName());
					managerDto.setSurname(dirDTO.getSurname());
					managerDto.setProfile(dirDTO.getProfile());
					managerDto.setTradeName(dirDTO.getTradeName());
				}
			}
		}
		loadManagersInfoList();
		closeActivePopup();
	}

	private void growlMessage(String title, String message) {
		GrowlOptions go = new GrowlOptions();
		go.setPosition(GrowlPosition.TOP_CENTER);
		go.setType(GrowlType.WARNING);
		go.setIconType(IconType.WARNING.getCssName());
		Growl.growl(title, message, go);
	}

	// call from manager widget delete button
	public void deleteManager(ManagerDTO dirDTO) {
		if (companyInfoDTO.getManagerDtoList() != null) {
			int deletedIndex = -1;
			for (ManagerDTO managerDTO : companyInfoDTO.getManagerDtoList()) {
				if (dirDTO.getObjid() != null && dirDTO.getObjid().equals(managerDTO.getObjid()))
					deletedIndex = companyInfoDTO.getManagerDtoList().indexOf(managerDTO);
				else if (dirDTO.getTempKeyForClientDelete().equals(managerDTO.getTempKeyForClientDelete()))
					deletedIndex = companyInfoDTO.getManagerDtoList().indexOf(managerDTO);
			}
			if (deletedIndex != -1)
				companyInfoDTO.getManagerDtoList().remove(deletedIndex);
		}
	}

	// call from inspector created update popup
	public void saveOrUpdateInspector(InspectorDTO insDTO) {
		if (insDTO == null)
			growlMessage("HATA", "Denetçi bilgisi boş olamaz, Hata alındı");
		if (insDTO.getObjid() == null) {
			companyInfoDTO.getInspectorDtoList().add(insDTO);
		} else {
			for (InspectorDTO inspectorDTO : companyInfoDTO.getInspectorDtoList()) {
				if (inspectorDTO.getObjid() != null && inspectorDTO.getObjid().equals(insDTO.getObjid())) {
					inspectorDTO.setAddress(insDTO.getAddress());
					inspectorDTO.setName(insDTO.getName());
					inspectorDTO.setRegisteredBranch(insDTO.getRegisteredBranch());
					inspectorDTO.setSurname(insDTO.getSurname());
					inspectorDTO.setTitle(insDTO.getTitle());
				}
			}
		}
		loadInspectorsInfoList();
		closeActivePopup();
	}

	// call from managerwidget delete button
	public void deleteInspector(InspectorDTO insDTO) {
		if (companyInfoDTO.getInspectorDtoList() != null) {
			int deletedIndex = -1;
			for (InspectorDTO inspectorDTO : companyInfoDTO.getInspectorDtoList()) {
				if (insDTO.getObjid() != null && insDTO.getObjid().equals(inspectorDTO.getObjid()))
					deletedIndex = companyInfoDTO.getInspectorDtoList().indexOf(inspectorDTO);
				else if (insDTO.getTempKeyForClientDelete().equals(inspectorDTO.getTempKeyForClientDelete()))
					deletedIndex = companyInfoDTO.getInspectorDtoList().indexOf(inspectorDTO);
			}
			if (deletedIndex != -1)
				companyInfoDTO.getInspectorDtoList().remove(deletedIndex);
		}
	}

	@Override
	public String pageName() {
		return messages.CompanyRegister_pageName();
	}
}
