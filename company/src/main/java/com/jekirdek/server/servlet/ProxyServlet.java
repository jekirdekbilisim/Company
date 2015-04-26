package com.jekirdek.server.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.util.SessionUtil;

@SuppressWarnings("serial")
public class ProxyServlet extends RemoteServiceServlet {

	@Override
	public void init() {
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		try {

			HttpServletRequest request = getThreadLocalRequest();
			SessionUtil.session = request.getSession(true);
			// session time out 20 minutes
			SessionUtil.session.setMaxInactiveInterval(20 * 60);
			// session locale
			// SessionUtil.session.setAttribute("locale", request.getLocale());
			createOrUpdateSessionUser(request);
			Object handler = getBean(request);
			RPCRequest rpcRequest = RPC.decodeRequest(payload, handler.getClass(), this);
			if (!controlAuthorization(rpcRequest)) {
				throw new MthsException("Yetkisiz Erişim, Erişmeye çalıştığınız sayfaya yetkiniz yoktur." + handler.toString()
						+ rpcRequest.getMethod());
			}
			onAfterRequestDeserialized(rpcRequest);
			return RPC.invokeAndEncodeResponse(handler, rpcRequest.getMethod(), rpcRequest.getParameters(),
					rpcRequest.getSerializationPolicy());
		}
		catch (IncompatibleRemoteServiceException ex) {
			log("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
			return RPC.encodeResponseForFailure(null, ex);
		}
		catch (Exception ex) {
			log("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
			return RPC.encodeResponseForFailure(null, ex);
		}
	}

	private Boolean controlAuthorization(RPCRequest rpcRequest) throws MthsException {
		if (rpcRequest.getMethod().isAnnotationPresent(Authorization.class)) {
			Authorization authorization = rpcRequest.getMethod().getAnnotation(Authorization.class);
			// AuthorizationController controller = getAuthorizationBean();
			Boolean hasAuthorize = Boolean.FALSE;
			// authorize control by role
			if (!StringUtils.isEmpty(authorization.roles())) {
				for (String role : authorization.roles()) {
					if (role.equals(SessionUtil.getSessionUser().getAuthorizarionRole()))
						hasAuthorize = Boolean.TRUE;
				}
			}
			return hasAuthorize;
		} else {
			throw new MthsException(rpcRequest.getMethod().getName()
					+ " The method does not have authorization annotation, control method annotation");
		}
	}

	private void createOrUpdateSessionUser(HttpServletRequest request) {
		SessionUser sessionUser = SessionUtil.getSessionUser();
		if (sessionUser == null) {
			sessionUser = new SessionUser();
			sessionUser.setMenuRole(RoleType.NOT_LOGIN);
			sessionUser.setAuthorizarionRole(RoleType.NOT_LOGIN);
		}
		sessionUser.setClientIp(getClientIp(request));
		SessionUtil.putSessionUser(sessionUser);
	}

	private String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	/**
	 * Determine Spring bean to handle request based on request URL, e.g. a request ending in /myService will be handled by bean with name
	 * "myService".
	 * 
	 * @param request
	 * @return handler bean
	 */
	protected Object getBean(HttpServletRequest request) {
		String service = getService(request);
		Object bean = getBean(service);
		if (!(bean instanceof RemoteService)) {
			throw new IllegalArgumentException("Spring bean is not a GWT RemoteService: " + service + " (" + bean + ")");
		}
		return bean;
	}

	/**
	 * Parse the service name from the request URL.
	 * 
	 * @param request
	 * @return bean name
	 */
	protected String getService(HttpServletRequest request) {
		String url = request.getRequestURI();
		String service = url.substring(url.lastIndexOf("/") + 1);
		return service;
	}

	/**
	 * Look up a spring bean with the specified name in the current web application context.
	 * 
	 * @param name
	 *            bean name
	 * @return the bean
	 */
	protected Object getBean(String name) {

		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if (applicationContext == null) {
			throw new IllegalStateException("No Spring web application context found");
		}
		if (!applicationContext.containsBean(name)) {
			{
				throw new IllegalArgumentException("Spring bean not found: " + name);
			}
		}
		return applicationContext.getBean(name);
	}
}