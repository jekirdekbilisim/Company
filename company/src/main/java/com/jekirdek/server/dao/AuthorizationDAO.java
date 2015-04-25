package com.jekirdek.server.dao;

import java.util.List;

import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.entity.Role;
import com.jekirdek.server.entity.UserRole;

public interface AuthorizationDAO extends AbstractDAO<String, UserRole> {

	List<ListItem> getAllCompany() throws Exception;

	List<ListItem> getCompanyByUserOid(String userOid) throws Exception;

	List<ListItem> getCompanyByUserTckn(String userId) throws Exception;

	void deleteRoleByUserAndCompany(String userTckn, String companyOid) throws Exception;

	Role findRoleByRoleName(String roleName) throws Exception;

}
