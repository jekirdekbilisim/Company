package com.jekirdek.server.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.RemoteService;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.DocumentController;
import com.jekirdek.client.util.FileDownloadItem;
import com.jekirdek.server.util.SessionUtil;

public class FileServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String channel = request.getParameter(URLConstant.CHANNEL);
		if (URLConstant.CHANNEL_FILE_UPLOAD.equals(channel)) {
			fileUploadProcess(request, response);
		} else if (URLConstant.CHANNEL_COMPANY_LOGO_VIEW.equals(channel)) {
			companyLogoView(request, response);
		} else if (URLConstant.CHANNEL_FILE_DOWNLOAD.equals(channel)) {
			fileDownloadProcess(request, response);
		}
	}

	private void fileUploadProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not a multipart request");
			return;
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileSessionKey = request.getParameter(URLConstant.FILE_SESSION_KEY);

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			FileItem fileItem;
			if (iter.hasNext()) {
				fileItem = (FileItem) iter.next();
				// String fileName = fileItem.getFieldName(); // file name, if you need it
				// byte[] fileContentByte = IOUtils.toByteArray(fileItem.getInputStream());
				request.getSession().setAttribute(fileSessionKey, fileItem);
			}
		}
		catch (Exception caught) {
			throw new RuntimeException(caught);
		}
	}

	private void companyLogoView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String companyOid = request.getParameter(URLConstant.COMPANY_OID);
		if (StringUtils.isEmpty(companyOid))
			companyOid = SessionUtil.getSessionUser().getSelectedCompanyOid();

		byte[] imageData = readImageDataFromDB(companyOid);
		response.setContentType("image/png");
		if (imageData != null)
			response.getOutputStream().write(imageData, 0, imageData.length);
		// response.getOutputStream().flush();
	}

	private void fileDownloadProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// process the data (In your case go get it)
		FileDownloadItem downloadItem = readFile(request.getParameter(URLConstant.FILE_OID));

		// do not cache
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		// content length is needed for MSIE
		response.setContentLength(downloadItem.getFileContent().length);
		// set the filename and the type
		response.setContentType(downloadItem.getFileContentType());
		response.addHeader("Content-Disposition", "attachment;filename=" + downloadItem.getFileName());
		response.getOutputStream().write(downloadItem.getFileContent());
		response.getOutputStream().flush();
	}

	private FileDownloadItem readFile(String fileOid) throws ServletException {
		DocumentController controller = (DocumentController) getBean("documentController");
		try {
			return controller.getDocumentDBStoreByOid(fileOid);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Exception occured when document was reading from db");
		}
	}

	private byte[] readImageDataFromDB(String companyOid) throws ServletException {
		CompanyController controller = (CompanyController) getBean("companyController");
		try {
			return controller.getLogoByte(companyOid);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Exception occured when logo was reading from db");
		}
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}