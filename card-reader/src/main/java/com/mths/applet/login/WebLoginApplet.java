package com.mths.applet.login;

import java.io.InputStream;
import java.util.List;

import javax.swing.JApplet;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.asn.x509.EName;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;
import tr.gov.tubitak.uekae.esya.api.smartcard.config.CardTypeConfig;
import tr.gov.tubitak.uekae.esya.api.smartcard.config.SmartCardConfigParser;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;

import com.mths.applet.resource.ErrorCode;
import com.mths.applet.resource.ResourceUtil;
import com.mths.applet.smartcard.SmartCardManager;

public class WebLoginApplet extends JApplet {

	private static final long	serialVersionUID	= -3693190670548303841L;

	private static Logger		logger				= LoggerFactory.getLogger(WebLoginApplet.class);

	private SmartCardManager	scManager;

	private String				signableInput		= "CompanyInfoDTO [companyDTO=CompanyDTO [objid=fc7ef5fd-af0e-11e4-8f93-5c514f0e0550, companyType=R, mersisNo=345678, tradeName=Garanti, alias=garan, committedCapital=10000, paidCapital=345678, companyCenter=Center, phoneNo=2345678, faxNo=0101, internetAddress=, contactAddress=], directorDtoList=[DirectorDTO [directorType=R, objid=undefined, mersisNo=undefined, tradeName=undefined, companyCenter=undefined, name=ilyas, surname=beşli, title=başkan, tempKeyForClientDelete=1423394280009]], inspectorDtoList=[InspectorDTO [objid=undefined, name=ilyas, surname=beşli, title=garanti, address=test, registeredBranch=beşiktaş, tempKeyForClientDelete=1423394292739]]]";

	@Override
	public void init() {
		super.init();

		configInit();
		smartCardInit();

		// only local test, comment when production
		localTest();
	}

	private void configInit() {
		loadLogConfig();
		loadLicenseConfig();

		loadSmartCardConfig();

	}

	private void loadLogConfig() {
		try {
			PropertyConfigurator.configure(ResourceUtil.getFile(ResourceUtil.log4jPath));
			logger.info("Log4j is used. Configuration path is " + ResourceUtil.log4jPath);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void loadLicenseConfig() {
		try {
			LicenseUtil.setLicenseXml(ResourceUtil.getFile(ResourceUtil.licensePath));
			logger.info("License is being loaded from " + ResourceUtil.licensePath);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void loadSmartCardConfig() {
		InputStream is = null;
		try {
			is = ResourceUtil.getFile(ResourceUtil.smartCardConfig);
			List<CardTypeConfig> cards = new SmartCardConfigParser().readConfig(is);
			CardType.applyCardTypeConfig(cards);
			logger.info("Smart card config load successfull");
		}
		catch (SmartCardException e) {
			logger.error("Smart Card config file reading error", e);
		}
		catch (Exception e) {
			logger.error("Smart Card config file reading error", e);
		}

	}

	private void smartCardInit() {
		try {
			scManager = SmartCardManager.getInstance();
			// scManager.listOfTerminals();
			// scManager.listCertificatesOfTerminals();
		}
		catch (SmartCardException e) {
			logger.error("Smart Card init error", e);
		}
	}

	public String countCertificate() {
		logger.info("Smart Card total certs size = " + scManager.getCertsTerminalMap().size());
		return String.valueOf(scManager.getCertsTerminalMap().size());
	}

	public String signDocument(final String doc, final String certHex) {
		scManager.determineActivateCardAndCertificate(certHex);
		return scManager.signDocument(doc);
	}

	public String signDocument(final String doc) {
		return scManager.signDocument(doc);
	}

	public String[] selectedCertInfo() {

		String[] cardInfoArr = new String[11];
		ECertificate eCertificate = scManager.getActiveCertificate();
		EName subject = eCertificate.getSubject();
		EName issuer = eCertificate.getIssuer();

		cardInfoArr[0] = eCertificate.getSerialNumber() != null ? eCertificate.getSerialNumber().toString() : "";
		cardInfoArr[1] = eCertificate.getSerialNumberHex();
		cardInfoArr[2] = eCertificate.getEmail();

		cardInfoArr[3] = subject.getCommonNameAttribute();
		cardInfoArr[4] = subject.getCountryNameAttribute();
		cardInfoArr[5] = subject.getLocalityNameAttribute();
		cardInfoArr[6] = subject.getSerialNumberAttribute();

		cardInfoArr[7] = issuer.getCommonNameAttribute();
		cardInfoArr[8] = issuer.getOrganizationNameAttribute();
		cardInfoArr[9] = issuer.getOrganizationalUnitNameAttribute();
		cardInfoArr[10] = issuer.getCountryNameAttribute();

		return cardInfoArr;
	}

	private void localTest() {
		loginSmartCard("19863532");
		selectedCertInfo();
		signDocument(signableInput);
		logoutSmartCard();
	}

	public String logoutSmartCard() {
		String logoutResult = ErrorCode.CODE_21;
		try {
			logoutResult = scManager.logout();
		}
		catch (ESYAException e) {
			logger.error("Logout process failed ", e);
		}
		return logoutResult;
	}

	public String loginSmartCard(String pin) {
		if (scManager.getCertsTerminalMap() == null || scManager.getCertsTerminalMap().size() != 1) {
			logger.warn("scmanager cert size = " + scManager.getCertsTerminalMap().size());
			return ErrorCode.CODE_2;
		}
		String loginResult = ErrorCode.CODE_20;
		try {
			scManager.determineActivateCardAndCertificate();
			loginResult = scManager.loginWithPin(pin);
		}
		catch (ESYAException e) {
			logger.error("Login process error occured ", e);
		}

		return loginResult;
	}

}
