package com.jekirdek.server.dao;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jekirdek.client.constant.ManagerType;
import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.entity.Company;
import com.jekirdek.server.entity.Manager;
import com.jekirdek.server.entity.Inspector;

@Repository("companyDAO")
@Transactional
public class CompanyDAOImpl extends AbstractDAOImpl<String, Company> implements CompanyDAO {

	@Override
	public Boolean companyHasLogo(String selectedCompanyOid) {

		if (StringUtils.isEmpty(selectedCompanyOid))
			return false;

		String sqlStr = "select count(c.logo) from Company c where c.objid = :companyOid and c.logo is not null";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("companyOid", selectedCompanyOid);
		Long count = (Long) query.getSingleResult();
		if (count == null || count.equals(Long.valueOf(0)))
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Company findByAlias(String companyAlias) {

		if (companyAlias == null)
			return null;

		String sqlStr = "select c from Company c where c.alias = :alias";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("alias", companyAlias);

		List<Company> companyList = query.getResultList();

		if (companyList != null && companyList.size() == 1) {
			return companyList.get(0);
		}
		return null;
	}

	@Override
	public void deleteManagerByOid(String objid) {
		Manager manager = getEntityManager().find(Manager.class, objid);
		if (manager != null)
			getEntityManager().remove(manager);
	}

	@Override
	public void deleteInspectorByOid(String objid) {

		Inspector inspector = getEntityManager().find(Inspector.class, objid);
		if (inspector != null)
			getEntityManager().remove(inspector);
	}

	@Override
	public List<ListItem> searchAllCompanyForSuggest() {
		String sqlStr = "select c.objid, c.alias, c.tradeName from Company c ";
		Query query = getEntityManager().createQuery(sqlStr);

		List<Object[]> result = (List<Object[]>) query.getResultList();
		List<ListItem> companyList = new ArrayList<ListItem>();
		if (result != null) {
			for (Object[] stringArr : result) {
				String objid = (String) stringArr[0];
				String alias = (String) stringArr[1];
				String name = (String) stringArr[2];
				companyList.add(new ListItem(objid, name + "-" + alias));
			}
		}
		return companyList;
	}

	@Override
	public String findCompanyOidByAlias(String companyAlias) throws Exception {

		if (companyAlias == null)
			return null;

		String sqlStr = "select c.objid from Company c where c.alias = :companyAlias";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("companyAlias", companyAlias);

		List<String> result = (List<String>) query.getResultList();
		if (result == null || result.size() != 1) {
			throw new Exception(" 1 den fazla şirket bulundu, sadece 1 tane olmasi gerekli");
		}
		return result.get(0);
	}

	@Override
	public List<String> findUserAuthorizedCompanyOidListByRole(String userOid, String roleName) {
		String sqlStr = "select ur.company.objid from UserRole ur where ur.company is not null "
				+ "and ur.user.objid= :userOid and ur.role.name = :roleName";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userOid", userOid);
		query.setParameter("roleName", roleName);
		return query.getResultList();
	}

	@Override
	public List<CompanySelectData> findUserAuthorizedCompanyListByRole(String userOid, String roleName) {
		String sqlStr = "select ur.company.objid, ur.company.tradeName, ur.company.alias from UserRole ur where ur.company is not null "
				+ "and ur.user.objid= :userOid and ur.role.name = :roleName";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userOid", userOid);
		query.setParameter("roleName", roleName);

		List<Object[]> result = (List<Object[]>) query.getResultList();

		List<CompanySelectData> companyList = new ArrayList<CompanySelectData>();
		if (result != null) {
			for (Object[] stringArr : result) {
				String objid = (String) stringArr[0];
				String name = (String) stringArr[1];
				String alias = (String) stringArr[2];
				companyList.add(new CompanySelectData(objid, alias, name));
			}
		}
		return companyList;
	}

	@Override
	public String findCompanyAliasByOid(String companyOid) throws Exception {

		if (companyOid == null)
			return null;

		String sqlStr = "select c.alias from Company c where c.objid = :companyOid";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("companyOid", companyOid);

		List<String> result = (List<String>) query.getResultList();
		if (result == null || result.size() != 1) {
			throw new Exception(" 1 den fazla şirket bulundu, sadece 1 tane olmasi gerekli");
		}
		return result.get(0);
	}

	@Override
	public Blob findLogoByCompanyOid(String selectedCompanyOid) throws Exception {

		if (selectedCompanyOid == null)
			return null;

		String sqlStr = "select c.logo.logo from Company c where c.objid = :companyOid";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("companyOid", selectedCompanyOid);

		List<Object> result = (List<Object>) query.getResultList();
		if (result == null || result.size() > 1) {
			throw new Exception(" 1 den fazla şirket logosu bulundu, sadece 1 tane olmasi gerekli");
		}
		if (result.get(0) instanceof Blob)
			return ((Blob) result.get(0));
		return null;
	}

	@Override
	public String findCompanyNameByOid(String companyOid) throws Exception {

		if (companyOid == null)
			return null;

		Company result = findByOid(companyOid);
		if (result == null) {
			throw new Exception(" 1 den fazla şirket bulundu, sadece 1 tane olmasi gerekli");
		}
		return result.getTradeName();
	}

	@Override
	public String findManagerNameByOid(String managerOid) throws Exception {

		if (StringUtils.isEmpty(managerOid))
			return null;

		String sqlStr = "select d from Manager d where d.objid = :managerOid";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("managerOid", managerOid);

		List<Manager> result = (List<Manager>) query.getResultList();
		if (result == null || result.size() != 1) {
			throw new Exception(" 1 den fazla Yönetici bulundu, sadece 1 tane olmasi gerekli");
		}
		if (ManagerType.REAL.toString().equals(result.get(0).getManagerTye()))
			return result.get(0).getName();
		else
			result.get(0).getTradeName();

		return null;
	}

	@Override
	public String findInspectorNameByOid(String inspectorOid) throws Exception {
		if (StringUtils.isEmpty(inspectorOid))
			return null;

		String sqlStr = "select i from Inspector i where i.objid = :inspectorOid";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("inspectorOid", inspectorOid);

		List<Inspector> result = (List<Inspector>) query.getResultList();
		if (result == null || result.size() != 1) {
			throw new Exception(" 1 den fazla Denetçi bulundu, sadece 1 tane olmasi gerekli");
		}
		return result.get(0).getName();
	}
}
