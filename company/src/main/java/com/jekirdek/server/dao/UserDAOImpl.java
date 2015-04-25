package com.jekirdek.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jekirdek.client.dto.AdminLoginDTO;
import com.jekirdek.server.entity.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends AbstractDAOImpl<String, User> implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByTckn(String tckn) {
		String sqlStr = "select u from User u where u.tckn = :tckn";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("tckn", tckn);
		List<User> userList = query.getResultList();
		if (userList != null && userList.size() == 1)
			return userList.get(0);
		return null;
	}

	@Override
	public User loginControlUser(AdminLoginDTO dto) {
		String sqlStr = "select u from User u where u.userName = :userName and u.password = :password";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userName", dto.getUserName());
		query.setParameter("password", dto.getPassword());
		List<User> userList = query.getResultList();
		if (userList != null && userList.size() == 1)
			return userList.get(0);
		return null;
	}

	@Override
	public String findUserNameByOid(String userOid) throws Exception {
		if (StringUtils.isEmpty(userOid))
			return null;
		User user = findByOid(userOid);
		if (user == null)
			throw new Exception("Kullanıcı bulunamadı");
		return user.getFullName();
	}

}
