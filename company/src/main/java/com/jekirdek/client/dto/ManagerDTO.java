package com.jekirdek.client.dto;

public class ManagerDTO extends BasicDTO {

	private static final long	serialVersionUID	= -7098796332883234829L;

	private String				title;

	private String				managerType;

	private String				objid;

	private String				mersisNo;

	private String				tradeName;

	private String				companyCenter;

	private String				name;

	private String				surname;

	private String				profile;

	// must be generate at constructor
	private Long				tempKeyForClientDelete;

	public ManagerDTO() {
		super();
		tempKeyForClientDelete = System.currentTimeMillis();
	}

	public String getMersisNo() {
		return mersisNo;
	}

	public void setMersisNo(String mersisNo) {
		this.mersisNo = mersisNo;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getCompanyCenter() {
		return companyCenter;
	}

	public void setCompanyCenter(String companyCenter) {
		this.companyCenter = companyCenter;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyCenter == null) ? 0 : companyCenter.hashCode());
		result = prime * result + ((managerType == null) ? 0 : managerType.hashCode());
		result = prime * result + ((mersisNo == null) ? 0 : mersisNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((tempKeyForClientDelete == null) ? 0 : tempKeyForClientDelete.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tradeName == null) ? 0 : tradeName.hashCode());
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
		ManagerDTO other = (ManagerDTO) obj;
		if (companyCenter == null) {
			if (other.companyCenter != null)
				return false;
		} else if (!companyCenter.equals(other.companyCenter))
			return false;
		if (managerType == null) {
			if (other.managerType != null)
				return false;
		} else if (!managerType.equals(other.managerType))
			return false;
		if (mersisNo == null) {
			if (other.mersisNo != null)
				return false;
		} else if (!mersisNo.equals(other.mersisNo))
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
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (tempKeyForClientDelete == null) {
			if (other.tempKeyForClientDelete != null)
				return false;
		} else if (!tempKeyForClientDelete.equals(other.tempKeyForClientDelete))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (tradeName == null) {
			if (other.tradeName != null)
				return false;
		} else if (!tradeName.equals(other.tradeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ManagerDTO [title=" + title + ", managerType=" + managerType + ", objid=" + objid + ", mersisNo=" + mersisNo
				+ ", tradeName=" + tradeName + ", companyCenter=" + companyCenter + ", name=" + name + ", surname=" + surname
				+ ", profile=" + profile + ", tempKeyForClientDelete=" + tempKeyForClientDelete + "]";
	}

	public Long getTempKeyForClientDelete() {
		return tempKeyForClientDelete;
	}

	public void setTempKeyForClientDelete(Long tempKeyForClientDelete) {
		this.tempKeyForClientDelete = tempKeyForClientDelete;
	}

	public String getManagerType() {
		return managerType;
	}

	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}