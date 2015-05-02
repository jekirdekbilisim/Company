package com.jekirdek.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.controller.AuthorizationController;
import com.jekirdek.client.dto.UserCompanyAuthDTO;
import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.dao.ActionLogDAO;
import com.jekirdek.server.dao.AuthorizationDAO;
import com.jekirdek.server.dao.CompanyDAO;
import com.jekirdek.server.dao.UserDAO;
import com.jekirdek.server.entity.User;
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
	public UserCompanyAuthDTO loadUserCompany(UserCompanyAuthDTO dto) throws MthsException {

		if (dto == null || StringUtils.isEmpty(dto.getTckn()))
			throw new MthsException("Kullanıcı kimlik bilgisi bos olamaz");

		SessionUser sessionUser = SessionUtil.getSessionUser();
		List<ListItem> sourceCompanyList = null;
		if (RoleType.ADMIN.equals(sessionUser.getAuthorizarionRole())) {
			sourceCompanyList = authorizationDAO.getAllCompany();
		} else if (RoleType.MEMBER_LOGIN.equals(sessionUser.getAuthorizarionRole())) {
			sourceCompanyList = authorizationDAO.getCompanyByUserOid(sessionUser.getObjid());
		}

		List<ListItem> targetCompanyList = authorizationDAO.getCompanyByUserTckn(dto.getTckn());
		User user = userDAO.findUserByTckn(dto.getTckn());

		if (targetCompanyList.size() > 0)
			sourceCompanyList.removeAll(targetCompanyList);

		UserCompanyAuthDTO data = new UserCompanyAuthDTO();
		data.setName(user.getFirstname());
		data.setSurname(user.getLastname());
		data.setUserOid(user.getObjid());
		data.setNotAuthorizedCompanyList(sourceCompanyList);
		data.setAuthorizedCompanyList(targetCompanyList);

		return data;
	}

	@Override
	@Transactional
	public List<String> saveUserAuthCompany(UserCompanyAuthDTO dto) throws MthsException {

		// TODO bu kullanici bilgisi client taraftan alinmamasi gerekli, session a atilabilir ilk sorguda
		if (dto == null || StringUtils.isEmpty(dto.getTckn()))
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
		List<ListItem> targetCompanyDBList = authorizationDAO.getCompanyByUserTckn(dto.getTckn());

		// silinmis olanlar tespit ediliyor
		for (ListItem targetItem : dto.getAuthorizedCompanyList()) {
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
				userRole.setUser(userDAO.findUserByTckn(dto.getTckn()));
				userRole.setRole(authorizationDAO.findRoleByRoleName(RoleType.MEMBER_LOGIN));
				authorizationDAO.persistOrUpdate(userRole);
			}
		}
	}

	private void processDeletedUserCompanyAuth(UserCompanyAuthDTO dto) throws MthsException {

		List<ListItem> targetCompanyDBList = authorizationDAO.getCompanyByUserTckn(dto.getTckn());

		// silinmis olanlar tespit ediliyor
		for (ListItem companyItem : targetCompanyDBList) {
			boolean isDeleted = true;
			if (dto.getAuthorizedCompanyList() != null) {
				for (ListItem targetItem : dto.getAuthorizedCompanyList()) {
					if (companyItem.getKey().equals(targetItem.getKey())) {
						isDeleted = false;
						break;
					}
				}
			}
			if (isDeleted) {
				authorizationDAO.deleteRoleByUserAndCompany(dto.getTckn(), companyItem.getKey());
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