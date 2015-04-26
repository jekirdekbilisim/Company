package com.jekirdek.client.controller;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.dto.AdminLoginDTO;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.MemberLoginDTO;
import com.jekirdek.client.util.MthsException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proxyServlet/loginMenuController")
public interface LoginMenuController extends RemoteService {

	@Authorization(roles = { RoleType.NOT_LOGIN })
	AuthDataDTO controlAndLoginWithTckn(MemberLoginDTO dto) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.MEMBER_COMPANY_SELECT, RoleType.ADMIN })
	AuthDataDTO logout(MemberLoginDTO dto) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN, RoleType.NOT_LOGIN })
	AuthDataDTO loadMenuItemsAndPrivileges(String input) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_COMPANY_SELECT })
	AuthDataDTO authCompanySelected(CompanySearchDTO searchDTO) throws MthsException;

	@Authorization(roles = { RoleType.NOT_LOGIN })
	AuthDataDTO adminLogin(AdminLoginDTO dto) throws MthsException;

}
