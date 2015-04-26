package com.jekirdek.server.dao;

import java.sql.Blob;
import java.util.List;

import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.entity.Company;

public interface CompanyDAO extends AbstractDAO<String, Company> {

	Boolean companyHasLogo(String selectedCompanyOid);

	Company findByAlias(String companyAlias);

	void deleteManagerByOid(String objid);

	void deleteInspectorByOid(String objid);

	List<ListItem> searchAllCompanyForSuggest();

	String findCompanyOidByAlias(String companyAlias) throws MthsException;

	List<String> findUserAuthorizedCompanyOidListByRole(String userOid, String roleName);

	String findCompanyAliasByOid(String companyOid) throws MthsException;

	List<CompanySelectData> findUserAuthorizedCompanyListByRole(String userOid, String roleName);

	Blob findLogoByCompanyOid(String selectedCompanyOid) throws MthsException;

	String findCompanyNameByOid(String companyOid) throws MthsException;

	String findManagerNameByOid(String managerOid) throws MthsException;

	String findInspectorNameByOid(String inspectorOid) throws MthsException;

}
