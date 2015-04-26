package com.jekirdek.client.dto;

import java.util.List;

import com.jekirdek.client.pojo.SessionUser;
import com.jekirdek.client.util.MenuItem;

public class AuthDataDTO extends BasicDTO {

	private static final long	serialVersionUID	= -8316812081423171011L;

	private List<MenuItem>		menuItemList;
	private List<String>		privilegeItemList;
	private SessionUser			sessionUser;

	public AuthDataDTO(List<MenuItem> menuItemList, List<String> privilegeItemList, SessionUser sessionUser) {
		super();
		this.menuItemList = menuItemList;
		this.privilegeItemList = privilegeItemList;
		this.sessionUser = sessionUser;
	}

	public AuthDataDTO() {
		super();
	}

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	public List<String> getPrivilegeItemList() {
		return privilegeItemList;
	}

	public void setPrivilegeItemList(List<String> privilegeItemList) {
		this.privilegeItemList = privilegeItemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuItemList == null) ? 0 : menuItemList.hashCode());
		result = prime * result + ((privilegeItemList == null) ? 0 : privilegeItemList.hashCode());
		result = prime * result + ((sessionUser == null) ? 0 : sessionUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthDataDTO other = (AuthDataDTO) obj;
		if (menuItemList == null) {
			if (other.menuItemList != null)
				return false;
		} else if (!menuItemList.equals(other.menuItemList))
			return false;
		if (privilegeItemList == null) {
			if (other.privilegeItemList != null)
				return false;
		} else if (!privilegeItemList.equals(other.privilegeItemList))
			return false;
		if (sessionUser == null) {
			if (other.sessionUser != null)
				return false;
		} else if (!sessionUser.equals(other.sessionUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuthDataDTO [menuItemList=" + menuItemList + ", privilegeItemList=" + privilegeItemList + ", sessionUser=" + sessionUser
				+ "]";
	}

	public SessionUser getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(SessionUser sessionUser) {
		this.sessionUser = sessionUser;
	}

}
