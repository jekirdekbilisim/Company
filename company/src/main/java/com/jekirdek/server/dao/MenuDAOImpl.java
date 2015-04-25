package com.jekirdek.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.server.entity.Menu;

@Repository("menuDAO")
@Transactional
public class MenuDAOImpl extends AbstractDAOImpl<String, Menu> implements MenuDAO {

	@Override
	public List<Menu> findMenuByRole(String roleName) {
		String sqlStr = "select m from RolePrivilege rp, Role r, Privilege p, Menu m "
				+ "where r.name= :roleName and rp.role=r.objid and rp.privilege = p.objid "
				+ "and p.objid=m.privilege order by m.orderNumber ";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("roleName", roleName);
		return query.getResultList();
	}

}
