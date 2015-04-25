package com.jekirdek.client.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SessionUser implements Serializable, IsSerializable {

	private static final long serialVersionUID = 4119698866980181287L;

	private String objid;
	private String name;
	private String surname;
	private String email;
	private String gsm;
	private Boolean login;
	private String clientIp;

	private String selectedCompanyOid;
	private String selectedCompanyAlias;
	private List<String> authorizedCompanyOidList;

	private String menuRole;
	private String authorizarionRole;

	public SessionUser() {
		login = Boolean.FALSE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorizarionRole == null) ? 0 : authorizarionRole.hashCode());
		result = prime * result + ((authorizedCompanyOidList == null) ? 0 : authorizedCompanyOidList.hashCode());
		result = prime * result + ((clientIp == null) ? 0 : clientIp.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gsm == null) ? 0 : gsm.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((menuRole == null) ? 0 : menuRole.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((selectedCompanyAlias == null) ? 0 : selectedCompanyAlias.hashCode());
		result = prime * result + ((selectedCompanyOid == null) ? 0 : selectedCompanyOid.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		SessionUser other = (SessionUser) obj;
		if (authorizarionRole == null) {
			if (other.authorizarionRole != null)
				return false;
		} else if (!authorizarionRole.equals(other.authorizarionRole))
			return false;
		if (authorizedCompanyOidList == null) {
			if (other.authorizedCompanyOidList != null)
				return false;
		} else if (!authorizedCompanyOidList.equals(other.authorizedCompanyOidList))
			return false;
		if (clientIp == null) {
			if (other.clientIp != null)
				return false;
		} else if (!clientIp.equals(other.clientIp))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gsm == null) {
			if (other.gsm != null)
				return false;
		} else if (!gsm.equals(other.gsm))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (menuRole == null) {
			if (other.menuRole != null)
				return false;
		} else if (!menuRole.equals(other.menuRole))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (objid == null) {
			if (other.objid != null)
				return false;
		} else if (!objid.equals(other.objid))
			return false;
		if (selectedCompanyAlias == null) {
			if (other.selectedCompanyAlias != null)
				return false;
		} else if (!selectedCompanyAlias.equals(other.selectedCompanyAlias))
			return false;
		if (selectedCompanyOid == null) {
			if (other.selectedCompanyOid != null)
				return false;
		} else if (!selectedCompanyOid.equals(other.selectedCompanyOid))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SessionUser [objid=" + objid + ", name=" + name + ", surname=" + surname + ", email=" + email + ", gsm=" + gsm + ", login="
				+ login + ", clientIp=" + clientIp + ", selectedCompanyOid=" + selectedCompanyOid + ", selectedCompanyAlias="
				+ selectedCompanyAlias + ", menuRole=" + menuRole + ", authorizarionRole=" + authorizarionRole + "]";
	}

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	public String getMenuRole() {
		return menuRole;
	}

	public void setMenuRole(String menuRole) {
		this.menuRole = menuRole;
	}

	public String getAuthorizarionRole() {
		return authorizarionRole;
	}

	public void setAuthorizarionRole(String authorizarionRole) {
		this.authorizarionRole = authorizarionRole;
	}

	public String getSelectedCompanyOid() {
		return selectedCompanyOid;
	}

	public void setSelectedCompanyOid(String selectedCompanyOid) {
		this.selectedCompanyOid = selectedCompanyOid;
	}

	public String getSelectedCompanyAlias() {
		return selectedCompanyAlias;
	}

	public void setSelectedCompanyAlias(String selectedCompanyAlias) {
		this.selectedCompanyAlias = selectedCompanyAlias;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public List<String> getAuthorizedCompanyOidList() {
		return authorizedCompanyOidList;
	}

	public void setAuthorizedCompanyOidList(List<String> authorizedCompanyOidList) {
		this.authorizedCompanyOidList = authorizedCompanyOidList;
	}

	public String getFullName() {
		return name.concat(" ").concat(surname);
	}
}
