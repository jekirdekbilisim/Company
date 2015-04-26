package com.jekirdek.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.controller.AuthorizationController;
import com.jekirdek.client.dto.UserCompanyAuthDTO;
import com.jekirdek.client.dto.UserCompanyAuthData;
import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.dao.ActionLogDAO;
import com.jekirdek.server.dao.AuthorizationDAO;
import com.jekirdek.server.dao.CompanyDAO;
import com.jekirdek.server.dao.UserDAO;
import com.jekirdek.server.entity.UserRole;
import com.jekirdek.server.util.SessionUtil;

/**
 * The server side implementation of the RPC service.
 */
@Service("authorizationController")
@Transactional
public class AuthorizationControllerImpl extends AbstractController implements AuthorizationController {

	@Autowired
	private transient AuthorizationDAO	authorizationDAO;

	@Autowired
	private transient UserDAO			userDAO;

	@Autowired
	private transient CompanyDAO		companyDAO;

	@Autowired
	private transient ActionLogDAO		actionLogDAO;

	@Override
	@Transactional
	public UserCompanyAuthData loadUserCompany(UserCompanyAuthDTO dto) throws MthsException {

		if (dto == null || StringUtils.isEmpty(dto.getUserTckn()))
			throw new MthsException("Kullanıcı kimlik bilgisi bos olamaz");

		SessionUser user = SessionUtil.getSessionUser();
		List<ListItem> sourceCompanyList = null;
		if (RoleType.MEMBER_LOGIN.equals(user.getAuthorizarionRole())) {
			sourceCompanyList = authorizationDAO.getAllCompany();
		} else if (RoleType.MEMBER_LOGIN.equals(user.getAuthorizarionRole())) {
			sourceCompanyList = authorizationDAO.getCompanyByUserOid(user.getObjid());
		}

		List<ListItem> targetCompanyList = authorizationDAO.getCompanyByUserTckn(dto.getUserTckn());

		if (targetCompanyList.size() > 0)
			sourceCompanyList.removeAll(targetCompanyList);

		UserCompanyAuthData data = new UserCompanyAuthData();
		data.setSourceItemList(sourceCompanyList);
		data.setTargetItemList(targetCompanyList);

		return data;
	}

	@Override
	@Transactional
	public List<String> saveUserAuthCompany(UserCompanyAuthDTO dto) throws MthsException {

		// TODO bu kullanici bilgisi client taraftan alinmamasi gerekli, session a atilabilir ilk sorguda
		if (dto == null || StringUtils.isEmpty(dto.getUserTckn()))
			throw new MthsException("Kullanıcı kimlik bilgisi bos olamaz");

		// yetkisi alinmis sirketler bulunup db den siliniyor
		processDeletedUserCompanyAuth(dto);
		// yeni yetki verilmis sirketler db ekleniyor
		processAddUserCompanyAuth(dto);

		SessionUser user = SessionUtil.getSessionUser();
		user.setAuthorizedCompanyOidList(companyDAO.findUserAuthorizedCompanyOidListByRole(user.getObjid(), user.getAuthorizarionRole()));

		return user.getAuthorizedCompanyOidList();
	}

	private void processAddUserCompanyAuth(UserCompanyAuthDTO dto) throws MthsException {
		List<ListItem> targetCompanyDBList = authorizationDAO.getCompanyByUserTckn(dto.getUserTckn());

		// silinmis olanlar tespit ediliyor
		for (ListItem targetItem : dto.getTargetItemList()) {
			boolean isNew = true;
			if (targetCompanyDBList != null) {
				for (ListItem companyItem : targetCompanyDBList) {
					if (targetItem.getKey().equals(companyItem.getKey())) {
						isNew = false;
						break;
					}
				}
			}
			if (isNew) {
				UserRole userRole = new UserRole();
				userRole.setCompany(companyDAO.findByOid(targetItem.getKey()));
				userRole.setUser(userDAO.findUserByTckn(dto.getUserTckn()));
				userRole.setRole(authorizationDAO.findRoleByRoleName(RoleType.MEMBER_LOGIN));
				authorizationDAO.persistOrUpdate(userRole);
			}
		}
	}

	private void processDeletedUserCompanyAuth(UserCompanyAuthDTO dto) throws MthsException {

		List<ListItem> targetCompanyDBList = authorizationDAO.getCompanyByUserTckn(dto.getUserTckn());

		// silinmis olanlar tespit ediliyor
		for (ListItem companyItem : targetCompanyDBList) {
			boolean isDeleted = true;
			if (dto.getTargetItemList() != null) {
				for (ListItem targetItem : dto.getTargetItemList()) {
					if (companyItem.getKey().equals(targetItem.getKey())) {
						isDeleted = false;
						break;
					}
				}
			}
			if (isDeleted) {
				authorizationDAO.deleteRoleByUserAndCompany(dto.getUserTckn(), companyItem.getKey());
				// actionLogDAO.deleteUserPrivilege
			}
		}
	}

	@Override
	public void changeSelectedCompanyWithOid(String companyOid) throws MthsException {
		SessionUtil.getSessionUser().setSelectedCompanyOid(companyOid);
		SessionUtil.getSessionUser().setSelectedCompanyAlias(companyDAO.findCompanyAliasByOid(companyOid));
	}

	@Override
	public void changeSelectedCompanyWithAlias(String companyAlias) throws MthsException {
		SessionUtil.getSessionUser().setSelectedCompanyOid(companyDAO.findCompanyOidByAlias(companyAlias));
		SessionUtil.getSessionUser().setSelectedCompanyAlias(companyAlias);
	}
}