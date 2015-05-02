package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.dto.CompanyDocumentData;
import com.jekirdek.client.dto.DocumentDTO;
import com.jekirdek.client.dto.DocumentTypeDTO;
import com.jekirdek.client.util.FileDownloadItem;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proxyServlet/documentController")
public interface DocumentController extends RemoteService {

	@Authorization(roles = { RoleType.MEMBER_LOGIN })
	void saveDocument(DocumentDTO dto) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.NOT_LOGIN })
	List<CompanyDocumentData> loadCompanyDocumentList(String string);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.NOT_LOGIN })
	FileDownloadItem getDocumentStoreByOid(String fileDbStoreOid);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	List<ListItem> loadAllDocumentTypeCmb(String param);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	List<DocumentTypeDTO> loadAllDocumentType(String string);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	void deleteDocumentByOid(String documentStoreOid) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	void saveDocumentType(DocumentTypeDTO dto) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	void deleteDocumentTypeByOid(String objid) throws MthsException;

}
