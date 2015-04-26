package com.jekirdek.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.controller.LoginMenuController;
import com.jekirdek.client.dto.AdminLoginDTO;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.MemberLoginDTO;
import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.util.MenuItem;
import com.jekirdek.client.util.MthsException;
import com.jekirdek.server.dao.CompanyDAO;
import com.jekirdek.server.dao.MenuDAO;
import com.jekirdek.server.dao.PrivilegeDAO;
import com.jekirdek.server.dao.UserDAO;
import com.jekirdek.server.entity.Menu;
import com.jekirdek.server.entity.User;
import com.jekirdek.server.util.SessionUtil;

/**
 * The server side implementation of the RPC service.
 */
@Service("loginMenuController")
@Transactional
public class LoginMenuControllerImpl extends AbstractController implements LoginMenuController {

	@Autowired
	private transient UserDAO		userDAO;
	@Autowired
	private transient MenuDAO		menuDAO;
	@Autowired
	private transient PrivilegeDAO	privilegeDAO;
	@Autowired
	private transient CompanyDAO	companyDAO;

	@Override
	public AuthDataDTO loadMenuItemsAndPrivileges(String input) throws MthsException {
		SessionUser userInfo = SessionUtil.getSessionUser();
		List<MenuItem> menuList = loadMenuItems();
		List<String> privilegeList = loadPrivilegeItems();

		return new AuthDataDTO(menuList, privilegeList, userInfo);
	}

	private List<String> loadPrivilegeItems() {
		// kullanici login mi kontrolu
		SessionUser userInfo = SessionUtil.getSessionUser();

		List<String> privilegeList = new ArrayList<String>();
		if (userInfo != null && userInfo.getLogin()) {
			privilegeList = privilegeDAO.findPrivNameByRole(userInfo.getAuthorizarionRole());
		} else {
			privilegeList = privilegeDAO.findPrivNameByRole(RoleType.NOT_LOGIN);
		}

		List<String> privilegeItemList = new ArrayList<String>();
		for (String privilege : privilegeList) {
			privilegeItemList.add(privilege);
		}
		return privilegeItemList;
	}

	private List<MenuItem> loadMenuItems() throws MthsException {
		// kullanici login mi kontrolu
		SessionUser userInfo = SessionUtil.getSessionUser();

		List<Menu> menuList = new ArrayList<Menu>();
		if (userInfo != null && userInfo.getLogin()) {
			menuList = menuDAO.findMenuByRole(userInfo.getAuthorizarionRole());
		} else {
			menuList = menuDAO.findMenuByRole(RoleType.NOT_LOGIN);
		}
		if (menuList == null)
			throw new MthsException("Menu load error, menu list null");

		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		for (Menu menu : menuList) {
			menuItemList.add(generateMenuItem(menu));
		}
		return menuItemList;
	}

	private MenuItem generateMenuItem(Menu menu) {
		MenuItem item = new MenuItem();
		item.setName(menu.getName());
		item.setFriendlyUrl(menu.getFriendlyUrl());
		item.setClassName(menu.getMenuClass());
		item.setVisible(menu.getVisible());
		return item;
	}

	@Transactional
	public AuthDataDTO controlAndLoginWithTckn(MemberLoginDTO dto) throws MthsException {
		SessionUser sessionUser;
		List<MenuItem> menuList;
		List<String> privilegeList;
		try {
			User user = userDAO.findUserByTckn(dto.getCertInfo().getSubjectSerialNumber());
			sessionUser = SessionUtil.getSessionUser();
			if (user != null) {
				if (sessionUser == null)
					sessionUser = new SessionUser();
				sessionUser.setObjid(user.getObjid());
				sessionUser.setName(user.getFirstname());
				sessionUser.setLogin(Boolean.TRUE);
				sessionUser.setEmail(user.getEmail());
				sessionUser.setSurname(user.getLastname());
				sessionUser.setAuthorizedCompanyOidList(companyDAO.findUserAuthorizedCompanyOidListByRole(user.getObjid(),
						RoleType.MEMBER_LOGIN));
				if (sessionUser.getAuthorizedCompanyOidList() != null && sessionUser.getAuthorizedCompanyOidList().size() == 1) {
					sessionUser.setSelectedCompanyOid(sessionUser.getAuthorizedCompanyOidList().get(0));
					sessionUser.setSelectedCompanyAlias(companyDAO.findCompanyAliasByOid(sessionUser.getSelectedCompanyOid()));
					sessionUser.setAuthorizarionRole(RoleType.MEMBER_LOGIN);
					sessionUser.setMenuRole(RoleType.MEMBER_LOGIN);
				} else {
					sessionUser.setAuthorizarionRole(RoleType.MEMBER_COMPANY_SELECT);
					sessionUser.setMenuRole(RoleType.MEMBER_COMPANY_SELECT);
				}
			} else {
				SessionUtil.removeSessionUserFromSession();
				throw new MthsException("Kullanıcı Bulunamadı");
			}
			menuList = loadMenuItems();
			privilegeList = loadPrivilegeItems();
		}
		catch (Exception e) {
			SessionUtil.removeSessionUserFromSession();
			throw new MthsException("Kullanıcı Bulunamadı");
		}

		return new AuthDataDTO(menuList, privilegeList, sessionUser);
	}

	@Override
	public AuthDataDTO logout(MemberLoginDTO dto) throws MthsException {
		String selectedCompanyOid = SessionUtil.getSessionUser().getSelectedCompanyOid();
		String selectedCompanyAlias = SessionUtil.getSessionUser().getSelectedCompanyAlias();
		// user remove from session
		SessionUtil.removeSessionUserFromSession();
		// create new session user
		SessionUser sessionUser = new SessionUser();
		sessionUser.setLogin(Boolean.FALSE);
		sessionUser.setMenuRole(RoleType.NOT_LOGIN);
		sessionUser.setAuthorizarionRole(RoleType.NOT_LOGIN);
		sessionUser.setSelectedCompanyAlias(selectedCompanyAlias);
		sessionUser.setSelectedCompanyOid(selectedCompanyOid);
		SessionUtil.putSessionUser(sessionUser);

		List<MenuItem> menuList = loadMenuItems();
		List<String> privilegeList = loadPrivilegeItems();

		return new AuthDataDTO(menuList, privilegeList, sessionUser);
	}

	@Override
	public AuthDataDTO authCompanySelected(CompanySearchDTO searchDTO) throws MthsException {
		SessionUser sessionUser = SessionUtil.getSessionUser();
		List<MenuItem> menuList;
		List<String> privilegeList;
		try {
			controlCompanyAuthorized(searchDTO);
			sessionUser.setAuthorizarionRole(RoleType.MEMBER_LOGIN);
			sessionUser.setMenuRole(RoleType.MEMBER_LOGIN);
			sessionUser.setSelectedCompanyOid(searchDTO.getCompanyOid());
			sessionUser.setSelectedCompanyAlias(searchDTO.getAlias());
			menuList = loadMenuItems();
			privilegeList = loadPrivilegeItems();
		}
		catch (Exception e) {
			SessionUtil.removeSessionUserFromSession();
			throw new MthsException("Kullanıcı Bulunamadı");
		}

		return new AuthDataDTO(menuList, privilegeList, sessionUser);
	}

	private void controlCompanyAuthorized(CompanySearchDTO searchDTO) throws MthsException {
		Boolean companyAuthorized = Boolean.FALSE;
		for (String companyOid : SessionUtil.getSessionUser().getAuthorizedCompanyOidList()) {
			if (companyOid.equals(searchDTO.getCompanyOid()))
				companyAuthorized = Boolean.TRUE;
		}
		if (!companyAuthorized)
			throw new MthsException("Seçtiğiniz şirket yetkili olduğunuz şirketler arasında bulunmamaktadır");
	}

	@Override
	public AuthDataDTO adminLogin(AdminLoginDTO dto) throws MthsException {

		User user = userDAO.loginControlUser(dto);
		if (user == null) {
			throw new MthsException("Kullanıcı adı ve şifre uyuşmuyor. Lütfen Tekrar Deneyiniz");
		}

		SessionUser sessionUser;
		List<MenuItem> menuList;
		List<String> privilegeList;
		try {
			sessionUser = SessionUtil.getSessionUser();
			if (user != null) {
				if (sessionUser == null)
					sessionUser = new SessionUser();
				sessionUser.setObjid(user.getObjid());
				sessionUser.setName(user.getFirstname());
				sessionUser.setLogin(Boolean.TRUE);
				sessionUser.setEmail(user.getEmail());
				sessionUser.setSurname(user.getLastname());
				sessionUser.setAuthorizarionRole(RoleType.ADMIN);
				sessionUser.setMenuRole(RoleType.ADMIN);
				sessionUser.setAuthorizedCompanyOidList(companyDAO.findUserAuthorizedCompanyOidListByRole(user.getObjid(), RoleType.ADMIN));
				if (sessionUser.getAuthorizedCompanyOidList() != null && sessionUser.getAuthorizedCompanyOidList().size() == 1) {
					sessionUser.setSelectedCompanyOid(sessionUser.getAuthorizedCompanyOidList().get(0));
					sessionUser.setSelectedCompanyAlias(companyDAO.findCompanyAliasByOid(sessionUser.getSelectedCompanyOid()));

				}
			}
			menuList = loadMenuItems();
			privilegeList = loadPrivilegeItems();
		}
		catch (Exception e) {
			SessionUtil.removeSessionUserFromSession();
			throw new MthsException("Kullanıcı Bulunamadı");
		}

		return new AuthDataDTO(menuList, privilegeList, sessionUser);

	}
}
