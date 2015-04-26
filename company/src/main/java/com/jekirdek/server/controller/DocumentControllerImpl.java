package com.jekirdek.server.controller;

import java.sql.SQLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.client.controller.DocumentController;
import com.jekirdek.client.dto.CompanyDocumentData;
import com.jekirdek.client.dto.DocumentDTO;
import com.jekirdek.client.dto.DocumentTypeDTO;
import com.jekirdek.client.util.FileDownloadItem;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.dao.ActionLogDAO;
import com.jekirdek.server.dao.CompanyDAO;
import com.jekirdek.server.dao.DocumentDAO;
import com.jekirdek.server.dao.UserDAO;
import com.jekirdek.server.entity.CompanyDocument;
import com.jekirdek.server.entity.DocumentDBStore;
import com.jekirdek.server.entity.DocumentType;
import com.jekirdek.server.util.ServerConvertUtil;
import com.jekirdek.server.util.SessionUtil;

/**
 * The server side implementation of the RPC service.
 */
@Service("documentController")
@Transactional
public class DocumentControllerImpl extends AbstractController implements DocumentController {

	@Autowired
	private transient DocumentDAO	documentDAO;

	@Autowired
	private transient CompanyDAO	companyDAO;

	@Autowired
	private transient UserDAO		userDAO;

	@Autowired
	private transient ActionLogDAO	actionLogDAO;

	@Override
	public void saveDocument(DocumentDTO dto) throws Exception {
		// yetkisiz islem kontrolu
		SessionUtil.userAuthCompanyControl();

		CompanyDocument companyDocument = new CompanyDocument();
		DocumentDBStore dbStore = createDBStore(dto.getFileSessionKey());
		companyDocument.setDocument(dbStore);
		companyDocument.setAnnouncementDate(dto.getAnnouncementDate());
		companyDocument.setCompany(companyDAO.findByOid(SessionUtil.getSessionUser().getSelectedCompanyOid()));
		companyDocument.setDocumentType(documentDAO.findDocumentTypeByOid(dto.getDocType()));
		companyDocument.setUploadUser(SessionUtil.getSessionUser().getObjid());
		companyDocument.setUploadDate(new Date());
		companyDocument = documentDAO.persistOrUpdate(companyDocument);

		// log for this process
		actionLogDAO.documentInsert(companyDocument.getDocumentType().getObjid(), companyDocument.getDocument().getObjid());

		// dosya sessiondan siliniyor
		SessionUtil.session.removeAttribute(dto.getFileSessionKey());
	}

	private DocumentDBStore createDBStore(String fileSessionKey) throws Exception {
		FileItem fileItem = (FileItem) SessionUtil.session.getAttribute(fileSessionKey);
		if (fileItem == null) {
			throw new Exception("Dosya yüklenemedi, tekrar deneyiniz");
		}
		DocumentDBStore dbStore = new DocumentDBStore();
		dbStore.setDocument(ServerConvertUtil.fileItem2Blob(fileItem));
		dbStore.setContentType(fileItem.getContentType());
		dbStore.setFileName(fileItem.getName());
		dbStore.setFormattedSize(ServerConvertUtil.formattedByte(fileItem.get().length));
		return dbStore;
	}

	@Override
	public List<CompanyDocumentData> loadCompanyDocumentList(String string) {
		List<CompanyDocument> companyDocumentList = documentDAO.loadCompanyDocument(SessionUtil.getSessionUser().getSelectedCompanyOid());
		if (companyDocumentList == null || companyDocumentList.isEmpty())
			return null;

		// sort list by turkish
		sortList(companyDocumentList);

		List<CompanyDocumentData> documentDataList = new ArrayList<CompanyDocumentData>();
		for (CompanyDocument companyDocument : companyDocumentList) {
			CompanyDocumentData documentData = new CompanyDocumentData();
			documentData.setDocTypeOid(companyDocument.getDocumentType().getObjid());
			documentData.setDocDbStoreOid(companyDocument.getDocument().getObjid());
			documentData.setDocSize(companyDocument.getDocument().getFormattedSize());
			documentData.setDocType(companyDocument.getDocumentType().getName());
			documentData.setFileName(companyDocument.getDocument().getFileName());
			documentData.setAnnouncementDate(companyDocument.getAnnouncementDate());

			documentDataList.add(documentData);
		}
		return documentDataList;
	}

	private void sortList(List<CompanyDocument> companyDocumentList) {
		Collections.sort(companyDocumentList, new Comparator<CompanyDocument>() {
			@Override
			public int compare(CompanyDocument o1, CompanyDocument o2) {
				Collator trCollator = Collator.getInstance(new Locale("tr", "TR"));
				return trCollator.compare(o1.getDocumentType().getName(), o2.getDocumentType().getName());
			}
		});
	}

	@Override
	public FileDownloadItem getDocumentDBStoreByOid(String fileDbStoreOid) {
		DocumentDBStore documentDBStore = documentDAO.findDocumentDBStoreByOid(fileDbStoreOid);
		FileDownloadItem downloadItem = new FileDownloadItem();

		Long length;
		byte[] data = "".getBytes();
		try {
			length = documentDBStore.getDocument().length();
			data = documentDBStore.getDocument().getBytes(1, length.intValue());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		downloadItem.setFileContent(data);
		downloadItem.setFileContentType(documentDBStore.getContentType());
		downloadItem.setFileName(documentDBStore.getFileName());
		return downloadItem;
	}

	@Override
	public List<ListItem> loadAllDocumentTypeCmb(String param) {
		return documentDAO.loadAllDocumentTypeCmb();
	}

	@Override
	public List<DocumentTypeDTO> loadAllDocumentType(String param) {
		List<DocumentTypeDTO> typeDtoList = new ArrayList<>();
		List<DocumentType> typeList = documentDAO.loadAllDocumentType();

		if (typeList != null) {
			for (DocumentType type : typeList) {
				typeDtoList.add(new DocumentTypeDTO(type.getObjid(), type.getGroup(), type.getName()));
			}
		}
		return typeDtoList;
	}
}