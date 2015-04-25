package com.jekirdek.server.dao;

import java.util.List;

import com.jekirdek.server.entity.Privilege;

public interface PrivilegeDAO extends AbstractDAO<String, Privilege> {

	List<String> findPrivNameByRole(String notLogin);

}
