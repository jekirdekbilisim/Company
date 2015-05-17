package com.mths.applet.smartcard;

import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.crypto.Algorithms;
import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
import tr.gov.tubitak.uekae.esya.api.common.util.bag.Pair;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureFormat;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.BaseSmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.P11SmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

import com.mths.applet.ops.ContainerOps;
import com.mths.applet.ops.Ops;
import com.mths.applet.resource.ErrorCode;

public class SmartCardManager {

	private static Logger				logger			= LoggerFactory.getLogger(SmartCardManager.class);

	private static Object				lockObject		= new Object();
	private static SmartCardManager		scManager;
	private List<ECertificate>			eCertList;
	private Map<Integer, BaseSmartCard>	smartCardsTerminalMap;
	private Map<String, Integer>		certsTerminalMap;
	private ECertificate				activeCertificate;
	private BaseSmartCard				activeSmartCard;
	private BaseSigner					baseSigner;
	private SignatureFormat				signatureFormat	= SignatureFormat.CAdES;

	private int							terminalIndex;
	private String[]					terminalArr;

	/**
	 * Singleton is used for this class. If many card placed, it wants to user to select one of cards. If there is a influential change in
	 * the smart card environment, it repeat the selection process. The influential change can be: If there is a new smart card connected to
	 * system. The cached card is removed from system. These situations are checked in getInstance() function. So for your non-squential
	 * SmartCard Operation, call getInstance() function to check any change in the system.
	 * 
	 * In order to reset thse selections, call reset function.
	 * 
	 * @return SmartCardManager instance
	 * @throws SmartCardException
	 */
	public static SmartCardManager getInstance() throws SmartCardException {
		synchronized (lockObject) {
			if (scManager == null) {
				scManager = new SmartCardManager();
			}
			return scManager;
		}
	}

	/**
	 * 
	 * @throws SmartCardException
	 */
	private SmartCardManager() throws SmartCardException {
		listOfTerminals();
		listCertificatesOfTerminals();
	}

	public void listOfTerminals() throws SmartCardException {
		try {
			logger.info("New SmartCardManager will be created");
			terminalArr = SmartOp.getCardTerminals();

			if (terminalArr == null || terminalArr.length == 0)
				throw new SmartCardException("No terminal with smartcard");

			logger.info("Number of terminals: " + terminalArr.length);
		}
		catch (SmartCardException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void atrValue() {
		// String[] terminals = null;
		// try {
		// terminals = SmartOp.getCardTerminals();
		// }
		// catch (SmartCardException e) {
		// e.printStackTrace();
		// }
		// String terminal = terminals[0];
		// TerminalFactory tf = TerminalFactory.getDefault();
		// CardTerminal ct = tf.terminals().getTerminal(terminal);
		// Card card = null;
		// try {
		// card = ct.connect("*");
		// }
		// catch (CardException e) {
		// e.printStackTrace();
		// }
		// String atrValue = StringUtil.toString(card.getATR().getBytes());
		// logger.info("Smartcard atr value = " + atrValue);
		// // JOptionPane.showMessageDialog(null, ATR);
		// // System.out.println(ATR);
	}

	public void listCertificatesOfTerminals() throws SmartCardException {

		AccessController.doPrivileged(new PrivilegedAction<Object>() {

			public Object run() {

				// smartCards = new ArrayList<>();
				eCertList = new ArrayList<>();
				smartCardsTerminalMap = new HashMap<>();
				certsTerminalMap = new HashMap<>();
				terminalIndex = -1;
				// atrValue();
				for (String terminal : terminalArr) {

					terminalIndex++;
					try {

						logger.info("Smartcard index:" + terminalIndex);

						logger.info("PKCS11 Smartcard will be created");
						Pair<Long, CardType> slotAndCardType = SmartOp.getSlotAndCardType(terminal);
						P11SmartCard p11SmartCard = new P11SmartCard(slotAndCardType.getObject2());
						smartCardsTerminalMap.put(terminalIndex, p11SmartCard);
						p11SmartCard.openSession(slotAndCardType.getObject1());

						// take the certs of a terminal
						eCertList.addAll(getCertificates(terminalIndex));

						// TODO burdaki session kapatma islemini kaldirdik, sebebi bir daha session acmak icin beklemeyelim
						// TODO yapilmasi gereken: bi yerlerde diger kart okuyuculara ait sessionlari kapatmak
						// smartCards.get(cardIndex).closeSession();
					}
					catch (Exception e) {
						logger.error("Error in listing certificates of terminals", e);
					}
				}
				return null;
			}

		});
	}

	/**
	 * Gathers certificates of smartcard with specified index
	 * 
	 * @param index
	 *            order of smartcard
	 * @param terminal
	 * @return found certificates
	 * @throws Exception
	 */
	public synchronized List<ECertificate> getCertificates(Integer terminal) throws Exception {
		// List<byte[]> allCerts = smartCards.get(index).getSignatureCertificates();
		List<byte[]> allCerts = smartCardsTerminalMap.get(terminal).getSignatureCertificates();
		if (allCerts != null && allCerts.size() == 0) {
			logger.error("No certificate in smartcard ");
			throw new ESYAException("No certificate in smartcard");
		}

		List<ECertificate> certs = new ArrayList<ECertificate>();

		for (byte[] bs : allCerts) {
			ECertificate cert = new ECertificate(bs);
			certsTerminalMap.put(cert.getSerialNumberHex(), terminal);
			certs.add(cert);
		}
		return certs;
	}

	public String loginWithPin(String pin) throws ESYAException {
		logger.info("trying to Logging in to smart card");
		try {
			activeSmartCard.login(pin);
		}
		catch (Exception e) {
			logger.error("Error in login to smart card", e);
		}
		logger.info("Successfull Logging in to smart card");

		return ErrorCode.CODE_1;
	}

	public String logout() throws ESYAException {
		logger.info("trying to Logout from smart card");
		try {
			activeSmartCard.logout();
		}
		catch (Exception e) {
			logger.error("Error in Logout from smart card", e);
		}
		logger.info("Successfull Logout from smart card");

		return ErrorCode.CODE_1;
	}

	public String signDocument(final String doc) {
		AccessController.doPrivileged(new PrivilegedAction<Object>() {

			public Object run() {
				String signature64 = null;
				try {
					byte[] docBytes = doc.getBytes("UTF-8");
					baseSigner = activeSmartCard.getSigner(activeCertificate.asX509Certificate(), Algorithms.SIGNATURE_RSA_SHA256);

					Ops ops = new ContainerOps(docBytes, activeCertificate, baseSigner, signatureFormat);

					signature64 = ops.sign();// Base64.encode(signature);
					logger.info("Document sign : " + signature64);
				}
				catch (UnsupportedEncodingException | SmartCardException e) {
					logger.error(e.getMessage(), e);
				}
				return signature64;
			}
		});
		return null;
	}

	public void determineActivateCardAndCertificate(String certHex) {
		for (ECertificate eCertificate : eCertList) {
			if (certHex.equals(eCertificate.getSerialNumberHex()))
				activeCertificate = eCertificate;
		}
		Integer terminalNumber = certsTerminalMap.get(certHex);
		activeSmartCard = smartCardsTerminalMap.get(terminalNumber);
	}

	public void determineActivateCardAndCertificate() throws ESYAException {
		activeSmartCard = getFirstSmartCard();
		activeCertificate = getFirstCertificate();
	}

	private ECertificate getFirstCertificate() throws ESYAException {

		if (certsTerminalMap == null || certsTerminalMap.values().size() == 0)
			throw new ESYAException("No certificate found at any terminal");

		for (ECertificate certificate : eCertList) {
			return certificate;
		}

		return null;
	}

	public BaseSmartCard getFirstSmartCard() throws ESYAException {

		if (smartCardsTerminalMap == null || smartCardsTerminalMap.values().size() == 0)
			throw new ESYAException("No smart card found at any terminal");

		for (BaseSmartCard smartCard : smartCardsTerminalMap.values()) {
			return smartCard;
		}

		return null;
	}

	public List<ECertificate> getSignatureCerts() {
		return eCertList;
	}

	public void setSignatureCerts(List<ECertificate> signatureCerts) {
		this.eCertList = signatureCerts;
	}

	public Map<Integer, BaseSmartCard> getSmartCardsTerminalMap() {
		return smartCardsTerminalMap;
	}

	public void setSmartCardsTerminalMap(Map<Integer, BaseSmartCard> smartCardsTerminalMap) {
		this.smartCardsTerminalMap = smartCardsTerminalMap;
	}

	public Map<String, Integer> getCertsTerminalMap() {
		return certsTerminalMap;
	}

	public void setCertsTerminalMap(Map<String, Integer> certsTerminalMap) {
		this.certsTerminalMap = certsTerminalMap;
	}

	public ECertificate getActiveCertificate() {
		return activeCertificate;
	}

	public BaseSmartCard getActiveSmartCard() {
		return activeSmartCard;
	}

}
