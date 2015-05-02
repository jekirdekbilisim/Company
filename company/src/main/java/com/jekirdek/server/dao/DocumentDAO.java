package com.jekirdek.server.dao;

import java.util.List;

import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.entity.CompanyDocument;
import com.jekirdek.server.entity.DocumentStore;
import com.jekirdek.server.entity.DocumentType;

public interface DocumentDAO extends AbstractDAO<String, CompanyDocument> {

	List<CompanyDocument> loadCompanyDocument(String selectedCompanyOid);

	DocumentStore findDocumentStoreByOid(String fileDbStoreOid);

	List<ListItem> loadAllDocumentTypeCmb();

	String findDocumentTypeNameByOid(String documentTypeOid);

	DocumentType findDocumentTypeByOid(String documentTypeOid);

	List<DocumentType> loadAllDocumentType();

	CompanyDocument findCompanyDocumentByStoreOid(String documentStoreOid);

	void persistOrUpdateDocumentType(DocumentType documentType) throws MthsException;

	void deleteDocumentTypeByOid(String documentTypeOid) throws MthsException;

}
