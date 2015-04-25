package com.jekirdek.server.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceBundleUtil {

	Logger logger = Logger.getLogger(getClass().getName());
	private ResourceBundle bundle = ResourceBundle.getBundle("com.jekirdek.client.i18n.UIMessages", new Locale("tr"));
	private static ResourceBundleUtil instance = null;

	public static ResourceBundleUtil instance() {
		if (instance == null) {
			instance = new ResourceBundleUtil();
		}
		return instance;
	}

	public String getString(String key) {
		String message = null;
		try {
			message = bundle.getString(key);
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return key;
		}
		return message;
	}
}
