package com.mths.applet.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.PolicyReader;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.ValidationPolicy;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.signature.config.ConfigurationException;

public class ResourceUtil {
	private static Logger	logger				= LoggerFactory.getLogger(ResourceUtil.class.getName());

	public static String	licensePath			= "/lisans/Full_lisans.xml";
	public static String	policyPath			= "./config/certval-policy.xml";
	public static String	log4jPath			= "/config/log4j.properties";
	public static String	smartCardConfig		= "/config/smartcard-config.xml";
	public static String	esyaSignatureConfig	= "/config/esya-signature-config.xml";

	public static InputStream getFile(String aFileName) throws IOException {
		logger.info("getting file name : " + aFileName);
		try {
			URL url = ResourceUtil.class.getResource(aFileName);
			logger.info("url path " + url.getPath());

			logger.info("opening stream");
			InputStream is = url.openStream();
			logger.info("stream opened");
			return is;
		}
		catch (Exception e) {
			logger.error("Error in getting file " + aFileName);
			e.printStackTrace();
		}
		return null;
	}

	public static ValidationPolicy getPolicy() throws IOException, ESYAException {

		System.out.println("getting policy");
		InputStream policyStream = getFile(policyPath);

		System.out.println("validation policy is being created");
		ValidationPolicy policy = PolicyReader.readValidationPolicy(policyStream);
		System.out.println("validation policy is created, being returned");
		return policy;
	}

	// public static Config getConfig() throws IOException {
	//
	// mslogger.info("getting config");
	// InputStream configStream = getFile("xmlsignature-config.xml");
	//
	// mslogger.info("config is being created");
	//
	// Config config = new Config(configStream);
	//
	// URL configUrl = ResourceUtil.class.getResource("xmlsignature-config.xml");
	// // Config config = new Config(configUrl.openStream());
	//
	// mslogger.info("config path " + configUrl.getPath());
	// mslogger.info("config is created");
	// return config;
	// }

	public static tr.gov.tubitak.uekae.esya.api.signature.config.Config getSignatureConfig() throws IOException, ConfigurationException {

		logger.info("getting config");
		InputStream configStream = getFile(esyaSignatureConfig);

		logger.info("config is being created");

		tr.gov.tubitak.uekae.esya.api.signature.config.Config config = new tr.gov.tubitak.uekae.esya.api.signature.config.Config(
				configStream);

		URL configUrl = ResourceUtil.class.getResource(esyaSignatureConfig);
		// Config config = new Config(configUrl.openStream());

		logger.info("config path " + configUrl.getPath());
		logger.info("config is created");
		return config;
	}

	public static InputStream getLicense() throws Exception {
		return getFile(/* licensePath */"lisans.xml");
	}
}
