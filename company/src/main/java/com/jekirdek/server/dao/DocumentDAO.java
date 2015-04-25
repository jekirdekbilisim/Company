package com.jekirdek.server.dao;

import java.util.List;

import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.entity.CompanyDocument;
import com.jekirdek.server.entity.DocumentDBStore;
import com.jekirdek.server.entity.DocumentType;

public interface DocumentDAO extends AbstractDAO<String, CompanyDocument> {

	List<CompanyDocument> loadCompanyDocument(String selectedCompanyOid);

	DocumentDBStore findDocumentDBStoreByOid(String fileDbStoreOid);

	List<ListItem> loadAllDocumentTypeCmb();

	String findDocumentTypeNameByOid(String documentTypeOid);

	DocumentType findDocumentTypeByOid(String documentTypeOid);

	List<DocumentType> loadAllDocumentType();

}
