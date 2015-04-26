package com.jekirdek.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.entity.CompanyDocument;
import com.jekirdek.server.entity.DocumentDBStore;
import com.jekirdek.server.entity.DocumentType;

@Repository("documentDAO")
@Transactional
public class DocumentDAOImpl extends AbstractDAOImpl<String, CompanyDocument> implements DocumentDAO {

	@Override
	public List<CompanyDocument> loadCompanyDocument(String selectedCompanyOid) {
		String sqlStr = "select c from CompanyDocument c where c.company.objid = :companyOid ";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("companyOid", selectedCompanyOid);
		return (List<CompanyDocument>) query.getResultList();
	}

	@Override
	public DocumentDBStore findDocumentDBStoreByOid(String fileDbStoreOid) {
		String sqlStr = "select d from DocumentDBStore d where d.objid = :documentOid ";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("documentOid", fileDbStoreOid);
		List<DocumentDBStore> result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<ListItem> loadAllDocumentTypeCmb() {
		String sqlStr = "select d.objid,concat(d.group,'-',d.name) from DocumentType d ";

		Query query = getEntityManager().createQuery(sqlStr);

		List<Object[]> result = query.getResultList();
		List<ListItem> documentList = new ArrayList<>();
		if (result != null && !result.isEmpty()) {
			for (Object[] objects : result) {
				documentList.add(new ListItem((String) objects[0], (String) objects[1]));
			}
		}
		return documentList;
	}

	@Override
	public List<DocumentType> loadAllDocumentType() {
		String sqlStr = "select d from DocumentType d ";

		Query query = getEntityManager().createQuery(sqlStr);

		return query.getResultList();
	}

	@Override
	public String findDocumentTypeNameByOid(String documentTypeOid) {
		if (StringUtils.isEmpty(documentTypeOid))
			return null;
		String sqlStr = "select concat(d.group,'-',d.name) from DocumentType d where d.objid = :documentTypeOid ";

		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("documentTypeOid", documentTypeOid);

		List<String> result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return documentTypeOid;
	}

	@Override
	public DocumentType findDocumentTypeByOid(String documentTypeOid) {
		String sqlStr = "select d from DocumentType d where d.objid = :documentTypeOid ";

		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("documentTypeOid", documentTypeOid);

		List<DocumentType> result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;

	}
}
