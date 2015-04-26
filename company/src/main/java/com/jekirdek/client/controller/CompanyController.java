package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.dto.CompanyInfoDTO;
import com.jekirdek.client.dto.CompanyLogoDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.CompanySearchSuggestDTO;
import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.ListItem;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proxyServlet/companyController")
public interface CompanyController extends RemoteService {

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	public void saveUpdateCompany(CompanyInfoDTO companyDTO) throws Exception;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	public void logoUpload(CompanyLogoDTO dto);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.MEMBER_COMPANY_SELECT, RoleType.ADMIN, RoleType.NOT_LOGIN })
	public byte[] getLogoByte(String companyOid) throws Exception;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.MEMBER_COMPANY_SELECT, RoleType.ADMIN, RoleType.NOT_LOGIN })
	public Boolean companyHasLogo(String companyOid);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.MEMBER_COMPANY_SELECT, RoleType.ADMIN, RoleType.NOT_LOGIN })
	public CompanyInfoDTO loadCompanyInfo(CompanySearchDTO searchDTO);

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.MEMBER_COMPANY_SELECT, RoleType.ADMIN, RoleType.NOT_LOGIN })
	public List<ListItem> searchCompanySuggest(CompanySearchSuggestDTO dto) throws Exception;

	@Authorization(roles = { RoleType.MEMBER_COMPANY_SELECT, RoleType.ADMIN })
	List<CompanySelectData> loadAuthCompany(String str);

}
