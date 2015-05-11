package com.mths.applet.ops;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.ParameterList;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.find.certificate.trusted.TrustedCertificateFinderFromECertStore;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.PolicyClassInfo;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.ValidationPolicy;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;

import com.mths.applet.resource.ResourceUtil;

/**
 * Main class for signature operations in applet
 *
 * @author suleyman.uslu
 */
public abstract class Ops {

	private static Logger logger = LoggerFactory.getLogger(Ops.class);

	protected byte[] contentBytes;
	protected ECertificate certificate;
	protected BaseSigner baseSigner;

	public Ops(byte[] aContentBytes, ECertificate aCertificate, BaseSigner aBaseSigner) {

		contentBytes = aContentBytes;
		certificate = aCertificate;
		baseSigner = aBaseSigner;
	}

	public abstract String sign();

	protected void copyCertStore() throws Exception {
		final String DEPO_DIZIN_ADI = ".sertifikadeposu";
		String DEFAULT_DEPO_DOSYA_ADI = "SertifikaDeposu.svt";

		String storePath = null;

		logger.info("License path : " + ResourceUtil.licensePath);
		ValidationPolicy policy = ResourceUtil.getPolicy();
		logger.info("policy is read for cert store copying");
		List<PolicyClassInfo> trustedCertFinders = policy.bulmaPolitikasiAl().getTrustedCertificateFinders();
		logger.info("trusted cert finders are fetched");

		for (PolicyClassInfo policyClassInfo : trustedCertFinders) {
			logger.info("this is a policy class info");
			if (policyClassInfo.getClassName().equals(TrustedCertificateFinderFromECertStore.class.getName())) {
				logger.info("equals");
				ParameterList list = policyClassInfo.getParameters();
				storePath = (String) list.getParameter(/* StoreFinder.PARAM_STOREPATH */"storepath");
				logger.info("storepath 1 " + storePath);
			}
		}

		if (storePath == null) {
			storePath = policy.getDefaultStorePath();
			logger.info("storepath 2 " + storePath);
		}

		if (storePath == null) {
			String folder = (String) AccessController.doPrivileged(new PrivilegedAction<Object>() {
				@Override
				public Object run() {
					return System.getProperty("user.home") + System.getProperty("file.separator") + DEPO_DIZIN_ADI
							+ System.getProperty("file.separator");
				}
			});

			// todo to be removed!!!!!
			// folder = "C:\\Users\\suleyman.uslu\\.sertifikadeposu\\";

			storePath = folder + DEFAULT_DEPO_DOSYA_ADI;
			logger.info("storepath 3 " + storePath);
		}

		final String mStorePath = storePath;

		final File myFile = (File) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				return new File(mStorePath);
			}
		});

		Boolean isCreateNewFile = (Boolean) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					return myFile.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		});

		if (isCreateNewFile == true) {
			URL url = ResourceUtil.class.getResource("SertifikaDeposu.svt");
			InputStream depoStream = url.openStream();
			FileOutputStream fos = new FileOutputStream(myFile);
			transferData(depoStream, fos);
		}

	}

	private static void transferData(InputStream is, OutputStream os) throws ESYAException {
		try {
			byte[] block = new byte[4096];
			while (true) {
				int lenght = is.read(block);
				if (lenght == -1)
					break;
				os.write(block, 0, lenght);
			}
		}
		catch (IOException ex) {
			throw new ESYAException("InputStream can not be read", ex);
		}

	}
}
