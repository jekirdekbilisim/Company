package com.jekirdek.server.dao;

import java.sql.Blob;
import java.util.List;

import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.entity.Company;

public interface CompanyDAO extends AbstractDAO<String, Company> {

	Boolean companyHasLogo(String selectedCompanyOid);

	Company findByAlias(String companyAlias);

	void deleteManagerByOid(String objid);

	void deleteInspectorByOid(String objid);

	List<ListItem> searchAllCompanyForSuggest();

	String findCompanyOidByAlias(String companyAlias) throws Exception;

	List<String> findUserAuthorizedCompanyOidListByRole(String userOid, String roleName);

	String findCompanyAliasByOid(String companyOid) throws Exception;

	List<CompanySelectData> findUserAuthorizedCompanyListByRole(String userOid, String roleName);

	Blob findLogoByCompanyOid(String selectedCompanyOid) throws Exception;

	String findCompanyNameByOid(String companyOid) throws Exception;

	String findManagerNameByOid(String managerOid) throws Exception;

	String findInspectorNameByOid(String inspectorOid) throws Exception;

}
