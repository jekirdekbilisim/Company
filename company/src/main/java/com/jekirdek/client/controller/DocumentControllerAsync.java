package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jekirdek.client.dto.CompanyDocumentData;
import com.jekirdek.client.dto.DocumentDTO;
import com.jekirdek.client.dto.DocumentTypeDTO;
import com.jekirdek.client.util.FileDownloadItem;
import com.jekirdek.client.util.ListItem;

public interface DocumentControllerAsync {

	void saveDocument(DocumentDTO dto, AsyncCallback<Void> asyncCallback);

	void loadCompanyDocumentList(String string, AsyncCallback<List<CompanyDocumentData>> callback);

	void getDocumentStoreByOid(String fileDbStoreOid, AsyncCallback<FileDownloadItem> callback);

	void loadAllDocumentTypeCmb(String param, AsyncCallback<List<ListItem>> callback);

	void loadAllDocumentType(String string, AsyncCallback<List<DocumentTypeDTO>> callback);

	void deleteDocumentByOid(String documentStoreOid, AsyncCallback<Void> callback);

	void saveDocumentType(DocumentTypeDTO dto, AsyncCallback<Void> callback);

	void deleteDocumentTypeByOid(String objid, AsyncCallback<Void> callback);

}
