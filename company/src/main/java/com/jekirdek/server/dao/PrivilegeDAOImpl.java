package com.jekirdek.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.server.entity.Privilege;

@Repository("privilegeDAO")
@Transactional
public class PrivilegeDAOImpl extends AbstractDAOImpl<String, Privilege> implements PrivilegeDAO {

	@Override
	public List<String> findPrivNameByRole(String roleName) {
		String sqlStr = "select rp.privilege.name from RolePrivilege rp " + "  where rp.role.name = :roleName";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("roleName", roleName);
		return query.getResultList();
	}

}
