package com.jekirdek.server.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.constant.ServerConstant;

public class SessionUtil {

	public static HttpSession							session;

	public static AnnotationConfigWebApplicationContext	context;

	public static SessionUser getSessionUser() {
		return (SessionUser) session.getAttribute(ServerConstant.SESSION_USER);
	}

	public static void putSessionUser(SessionUser sessionUser) {
		session.setAttribute(ServerConstant.SESSION_USER, sessionUser);
	}

	public static void removeSessionUserFromSession() {
		session.removeAttribute(ServerConstant.SESSION_USER);
	}

	public static void userAuthCompanyControl() throws MthsException {
		if (!SessionUtil.getSessionUser().getAuthorizedCompanyOidList().contains(SessionUtil.getSessionUser().getSelectedCompanyOid())) {
			throw new MthsException("İşlem yapmaya çalıştığınız şirket yetkili olduğunuz şirketler arasında değildir.");
		}
	}
}
