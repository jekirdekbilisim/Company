package com.jekirdek.server.dao;

import java.util.List;

import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.entity.Role;
import com.jekirdek.server.entity.UserRole;

public interface AuthorizationDAO extends AbstractDAO<String, UserRole> {

	List<ListItem> getAllCompany() throws MthsException;

	List<ListItem> getCompanyByUserOid(String userOid) throws MthsException;

	List<ListItem> getCompanyByUserTckn(String userId) throws MthsException;

	void deleteRoleByUserAndCompany(String userTckn, String companyOid) throws MthsException;

	Role findRoleByRoleName(String roleName) throws MthsException;

}
