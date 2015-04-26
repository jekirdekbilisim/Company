package com.jekirdek.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.entity.Role;
import com.jekirdek.server.entity.UserRole;

@Repository("authorizationDAO")
@Transactional
public class AuthorizationDAOImpl extends AbstractDAOImpl<String, UserRole> implements AuthorizationDAO {

	@Override
	public List<ListItem> getAllCompany() throws MthsException {
		String sqlStr = "select r.objid, r.tradeName from Company r ";
		Query query = getEntityManager().createQuery(sqlStr);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		List<ListItem> companyList = new ArrayList<ListItem>();
		if (result != null) {
			for (Object[] stringArr : result) {
				String key = (String) stringArr[0];
				String value = (String) stringArr[1];
				companyList.add(new ListItem(key, value));
			}
		}
		return companyList;
	}

	@Override
	public List<ListItem> getCompanyByUserOid(String userOid) throws MthsException {
		String sqlStr = "select r.company.objid, r.company.tradeName from UserRole r where r.user.objid = :userOid";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userOid", userOid);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		List<ListItem> companyList = new ArrayList<ListItem>();
		if (result != null) {
			for (Object[] stringArr : result) {
				String key = (String) stringArr[0];
				String value = (String) stringArr[1];
				companyList.add(new ListItem(key, value));
			}
		}
		return companyList;
	}

	@Override
	public List<ListItem> getCompanyByUserTckn(String userTckn) throws MthsException {
		String sqlStr = "select r.company.objid, r.company.tradeName from UserRole r where r.user.tckn = :tckn";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("tckn", userTckn);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		List<ListItem> companyList = new ArrayList<ListItem>();
		if (result != null) {
			for (Object[] stringArr : result) {
				String key = (String) stringArr[0];
				String value = (String) stringArr[1];
				companyList.add(new ListItem(key, value));
			}
		}
		return companyList;
	}

	@Override
	public void deleteRoleByUserAndCompany(String userTckn, String companyOid) throws MthsException {
		String sqlStr = "select r from UserRole r where r.user.tckn = :tckn and r.company.objid = :companyOid";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("tckn", userTckn);
		query.setParameter("companyOid", companyOid);

		List<UserRole> result = (List<UserRole>) query.getResultList();
		if (result == null || result.size() != 1)
			throw new MthsException("silme aşamasında 1 den fazla şirket bulundu, sadece 1 tane olmasi gerekli");
		getEntityManager().remove(result.get(0));
	}

	@Override
	public Role findRoleByRoleName(String roleName) throws MthsException {
		String sqlStr = "select r from Role r where r.name = :name ";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("name", roleName);
		List<Role> result = (List<Role>) query.getResultList();
		if (result == null || result.size() != 1)
			return null;
		return result.get(0);
	}
}
