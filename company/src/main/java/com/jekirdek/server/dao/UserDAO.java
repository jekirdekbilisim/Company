package com.jekirdek.server.dao;

import com.jekirdek.client.dto.AdminLoginDTO;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.entity.User;

public interface UserDAO extends AbstractDAO<String, User> {

	User findUserByTckn(String tckn);

	User loginControlUser(AdminLoginDTO dto);

	String findUserNameByOid(String effectedUserOid) throws MthsException;

}
