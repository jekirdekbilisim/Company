package com.mths.applet.smartcard;

import java.io.InputStream;
import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import javax.swing.JOptionPane;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;
import tr.gov.tubitak.uekae.esya.api.common.util.StringUtil;
import tr.gov.tubitak.uekae.esya.api.smartcard.config.CardTypeConfig;
import tr.gov.tubitak.uekae.esya.api.smartcard.config.SmartCardConfigParser;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

import com.mths.applet.resource.ResourceUtil;

public class AkisTest {

	private static Logger logger = LoggerFactory.getLogger(AkisTest.class);

	public static void main(String[] args) throws SmartCardException, CardException {
		AkisTest test = new AkisTest();
		test.atrValue();

	}

	private void atrValue() throws SmartCardException, CardException {
		configInit();
		String[] terminals = SmartOp.getCardTerminals();
		String terminal = terminals[0];
		TerminalFactory tf = TerminalFactory.getDefault();
		CardTerminal ct = tf.terminals().getTerminal(terminal);
		Card card = ct.connect("*");
		String ATR = StringUtil.toString(card.getATR().getBytes());
		JOptionPane.showMessageDialog(null, ATR);
		System.out.println(ATR);

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
}
