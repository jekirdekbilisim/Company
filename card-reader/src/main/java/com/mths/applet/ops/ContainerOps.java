package com.mths.applet.ops;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
import tr.gov.tubitak.uekae.esya.api.common.util.Base64;
import tr.gov.tubitak.uekae.esya.api.signature.Signable;
import tr.gov.tubitak.uekae.esya.api.signature.Signature;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureContainer;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureFactory;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureFormat;
import tr.gov.tubitak.uekae.esya.api.signature.config.ConfigurationException;
import tr.gov.tubitak.uekae.esya.api.signature.impl.SignableBytes;

import com.mths.applet.resource.ResourceUtil;

/**
 * Provides operations for new interface
 *
 * @author suleyman.uslu
 */
public class ContainerOps extends Ops {

	private static Logger logger = LoggerFactory.getLogger(ContainerOps.class);

	/**
	 * Determines format of signature to be created Can be CAdES or XAdES
	 */
	SignatureFormat signatureFormat;

	public ContainerOps(byte[] aContentBytes, ECertificate aCertificate, BaseSigner aBaseSigner, SignatureFormat aSignatureFormat) {

		super(aContentBytes, aCertificate, aBaseSigner);

		signatureFormat = aSignatureFormat;
	}

	/**
	 * Creates signature with new signing interface, called from WebLoginApplet
	 * 
	 * @return signature as byte array
	 */
	public String sign() {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			logger.info("Starting signing process with new interface");

			SignatureContainer container = SignatureFactory.createContainer(signatureFormat, createContext(contentBytes));

			Signature signature = container.createSignature(certificate);

			signature.addContent(getContent(contentBytes), true);

			signature.sign(baseSigner);

			container.write(baos);

			logger.info("Signature : " + new String(baos.toByteArray()));
			logger.info("End of signing with new interface");
		}
		catch (Exception e) {
			logger.error("error in sign with new interface", e);
		}

		return Base64.encode(baos.toByteArray());
	}

	public static Signable getContent(byte[] contentBytes) {

		return new SignableBytes(contentBytes, "data.txt", "text/plain");
	}

	/**
	 * Creates context and sets data
	 * 
	 * @return created context
	 */
	public static tr.gov.tubitak.uekae.esya.api.signature.Context createContext(byte[] contentBytes) throws ConfigurationException {

		logger.info("Context is being created");
		tr.gov.tubitak.uekae.esya.api.signature.Context c = new tr.gov.tubitak.uekae.esya.api.signature.Context();
		logger.info("Context has created");

		try {
			c.setConfig(ResourceUtil.getSignatureConfig());
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		if (c.getConfig() != null)
			logger.info("Config is not null");
		else
			logger.error("Config is null!");
		return c;
	}
}
