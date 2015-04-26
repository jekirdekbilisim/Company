package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.dto.UserCompanyAuthDTO;
import com.jekirdek.client.dto.UserCompanyAuthData;
import com.jekirdek.client.util.MthsException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proxyServlet/authorizationController")
public interface AuthorizationController extends RemoteService {

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	public UserCompanyAuthData loadUserCompany(UserCompanyAuthDTO dto) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN })
	public List<String> saveUserAuthCompany(UserCompanyAuthDTO dto) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN, RoleType.NOT_LOGIN })
	public void changeSelectedCompanyWithOid(String companyOid) throws MthsException;

	@Authorization(roles = { RoleType.MEMBER_LOGIN, RoleType.ADMIN, RoleType.NOT_LOGIN })
	public void changeSelectedCompanyWithAlias(String companyAlias) throws MthsException;

}
