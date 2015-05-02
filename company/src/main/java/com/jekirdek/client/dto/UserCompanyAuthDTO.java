package com.jekirdek.client.dto;

import java.util.List;

import com.jekirdek.client.util.ListItem;

public class UserCompanyAuthDTO extends BasicDTO {

	private static final long	serialVersionUID	= 4590863652343138762L;

	private String				tckn;

	private String				name;

	private String				surname;

	private String				userOid;

	private List<ListItem>		authorizedCompanyList;

	private List<ListItem>		notAuthorizedCompanyList;

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

	public String getUserOid() {
		return userOid;
	}

	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	public List<ListItem> getAuthorizedCompanyList() {
		return authorizedCompanyList;
	}

	public void setAuthorizedCompanyList(List<ListItem> authorizedCompanyList) {
		this.authorizedCompanyList = authorizedCompanyList;
	}

	public List<ListItem> getNotAuthorizedCompanyList() {
		return notAuthorizedCompanyList;
	}

	public void setNotAuthorizedCompanyList(List<ListItem> notAuthorizedCompanyList) {
		this.notAuthorizedCompanyList = notAuthorizedCompanyList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorizedCompanyList == null) ? 0 : authorizedCompanyList.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notAuthorizedCompanyList == null) ? 0 : notAuthorizedCompanyList.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((tckn == null) ? 0 : tckn.hashCode());
		result = prime * result + ((userOid == null) ? 0 : userOid.hashCode());
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
		UserCompanyAuthDTO other = (UserCompanyAuthDTO) obj;
		if (authorizedCompanyList == null) {
			if (other.authorizedCompanyList != null)
				return false;
		} else if (!authorizedCompanyList.equals(other.authorizedCompanyList))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notAuthorizedCompanyList == null) {
			if (other.notAuthorizedCompanyList != null)
				return false;
		} else if (!notAuthorizedCompanyList.equals(other.notAuthorizedCompanyList))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (tckn == null) {
			if (other.tckn != null)
				return false;
		} else if (!tckn.equals(other.tckn))
			return false;
		if (userOid == null) {
			if (other.userOid != null)
				return false;
		} else if (!userOid.equals(other.userOid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserCompanyAuthDTO [tckn=" + tckn + ", name=" + name + ", surname=" + surname + ", userOid=" + userOid
				+ ", authorizedCompanyList=" + authorizedCompanyList + ", notAuthorizedCompanyList=" + notAuthorizedCompanyList + "]";
	}

	public String getTckn() {
		return tckn;
	}

	public void setTckn(String tckn) {
		this.tckn = tckn;
	}

}
