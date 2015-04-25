/**
 * 
 */
package com.jekirdek.client.page;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.constant.ErrorCode;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.LoginMenuController;
import com.jekirdek.client.controller.LoginMenuControllerAsync;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.dto.MemberLoginDTO;
import com.jekirdek.client.pojo.CertInfo;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.PageUtil;
import com.jekirdek.client.util.SmartCardUtil;

/**
 * @author ibesli
 * 
 */
public class MemberLogin extends AbstractPage implements IPage {

	private static MemberLoginUiBinder	uiBinder	= GWT.create(MemberLoginUiBinder.class);

	interface MemberLoginUiBinder extends UiBinder<Widget, MemberLogin> {
	}

	private final LoginMenuControllerAsync	loginMenuController	= GWT.create(LoginMenuController.class);

	private MemberLoginDTO					dto					= new MemberLoginDTO();

	@UiField
	Input									pinTxt;
	@UiField
	Button									loginBtn;

	public MemberLogin() {
		initWidget(uiBinder.createAndBindUi(this));
		initEventHandler();
		pinTxt.setText("45178302594");
		pinTxt.setFocus(true);
	}

	private void initEventHandler() {
		loginBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// certificateNumberControl();
				loginWithTckn();
			}
		});
		pinTxt.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					loginBtn.click();
				}
			}
		});
	}

	private void certificateNumberControl() {
		String pin = pinTxt.getText();
		if (pin == null || pin.trim().equals(""))
			return;

		String certNumberStr = SmartCardUtil.countCertificate();
		if (certNumberStr != null && String.valueOf("1").equals(certNumberStr)) {
			loginSmartCard(pin);
		} else {
			selectLoginCert(pin);
		}

	}

	private void selectLoginCert(String pin) {

	}

	private void loginSmartCard(String pin) {
		String resultLoginCard = SmartCardUtil.loginSmartCardWithApplet(pin);
		if (ErrorCode.CODE_1.equals(resultLoginCard)) {
			getCertInfosFromCard();
			loginWithTckn();
		}
	}

	private void loginWithTckn() {
		CertInfo certInfo = new CertInfo();
		certInfo.setSubjectSerialNumber(pinTxt.getText());
		dto.setCertInfo(certInfo);
		loginMenuController.controlAndLoginWithTckn(dto, new AsyncCall<AuthDataDTO>() {

			@Override
			public void successCall(AuthDataDTO result) {
				if (result != null && result.getSessionUser() != null && result.getSessionUser().getLogin()) {
					if (result.getSessionUser().getAuthorizedCompanyOidList() != null
							&& result.getSessionUser().getAuthorizedCompanyOidList().size() == 1) {
						ClientCacheUtil.instance().loginProcess(result, URLConstant.COMPANY_INFO);
						PageUtil.dashboardHeader.renderCompanyLogo();
					} else {
						ClientCacheUtil.instance().loginProcess(result, URLConstant.COMPANY_AUTH_SELECT);
					}
				}
			}

		});
	}

	private void getCertInfosFromCard() {
		String[] certInfoArr = SmartCardUtil.smartCardInfoFromApplet();
		if (certInfoArr == null) {
			logger.warning("Certinfo array is null");
			return;
		}
		if (certInfoArr.length != 11) {
			logger.warning("Certinfo array size is not correct");
		}
		CertInfo certInfo = new CertInfo();
		certInfo.setCardSerialNumber(certInfoArr[0]);
		certInfo.setCardSerialNumberHex(certInfoArr[1]);
		certInfo.setCardEmail(certInfoArr[2]);
		certInfo.setSubjectName(certInfoArr[3]);
		certInfo.setSubjectCountry(certInfoArr[4]);
		certInfo.setSubjectLocalityName(certInfoArr[5]);
		certInfo.setSubjectSerialNumber(certInfoArr[6]);
		certInfo.setIssuerName(certInfoArr[7]);
		certInfo.setIssuerOrganizationName(certInfoArr[8]);
		certInfo.setIssuerOrganizationUnitName(certInfoArr[9]);
		certInfo.setIssuerCountry(certInfoArr[10]);
		dto.setCertInfo(certInfo);
	}

	@Override
	public String pageName() {
		return messages.MemberLogin_pageName();
	}
}
