package com.jekirdek.client.dto;

public class CompanyDTO extends BasicDTO {

	private static final long	serialVersionUID	= -6975672805952245423L;

	private String				objid;

	private String				companyType;

	private String				mersisNo;

	private String				tradeName;

	private String				alias;

	private String				committedCapital;

	private String				paidCapital;

	private String				companyCenter;

	private String				phoneNo;

	private String				faxNo;

	private String				internetAddress;

	private String				contactAddress;

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
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

	public String getCommittedCapital() {
		return committedCapital;
	}

	public void setCommittedCapital(String committedCapital) {
		this.committedCapital = committedCapital;
	}

	public String getPaidCapital() {
		return paidCapital;
	}

	public void setPaidCapital(String paidCapital) {
		this.paidCapital = paidCapital;
	}

	public String getCompanyCenter() {
		return companyCenter;
	}

	public void setCompanyCenter(String companyCenter) {
		this.companyCenter = companyCenter;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getInternetAddress() {
		return internetAddress;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((committedCapital == null) ? 0 : committedCapital.hashCode());
		result = prime * result + ((companyCenter == null) ? 0 : companyCenter.hashCode());
		result = prime * result + ((companyType == null) ? 0 : companyType.hashCode());
		result = prime * result + ((contactAddress == null) ? 0 : contactAddress.hashCode());
		result = prime * result + ((faxNo == null) ? 0 : faxNo.hashCode());
		result = prime * result + ((internetAddress == null) ? 0 : internetAddress.hashCode());
		result = prime * result + ((mersisNo == null) ? 0 : mersisNo.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((paidCapital == null) ? 0 : paidCapital.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
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
		CompanyDTO other = (CompanyDTO) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (committedCapital == null) {
			if (other.committedCapital != null)
				return false;
		} else if (!committedCapital.equals(other.committedCapital))
			return false;
		if (companyCenter == null) {
			if (other.companyCenter != null)
				return false;
		} else if (!companyCenter.equals(other.companyCenter))
			return false;
		if (companyType == null) {
			if (other.companyType != null)
				return false;
		} else if (!companyType.equals(other.companyType))
			return false;
		if (contactAddress == null) {
			if (other.contactAddress != null)
				return false;
		} else if (!contactAddress.equals(other.contactAddress))
			return false;
		if (faxNo == null) {
			if (other.faxNo != null)
				return false;
		} else if (!faxNo.equals(other.faxNo))
			return false;
		if (internetAddress == null) {
			if (other.internetAddress != null)
				return false;
		} else if (!internetAddress.equals(other.internetAddress))
			return false;
		if (mersisNo == null) {
			if (other.mersisNo != null)
				return false;
		} else if (!mersisNo.equals(other.mersisNo))
			return false;
		if (objid == null) {
			if (other.objid != null)
				return false;
		} else if (!objid.equals(other.objid))
			return false;
		if (paidCapital == null) {
			if (other.paidCapital != null)
				return false;
		} else if (!paidCapital.equals(other.paidCapital))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (tradeName == null) {
			if (other.tradeName != null)
				return false;
		} else if (!tradeName.equals(other.tradeName))
			return false;
		return true;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "CompanyDTO [objid=" + objid + ", companyType=" + companyType + ", mersisNo=" + mersisNo + ", tradeName=" + tradeName
				+ ", alias=" + alias + ", committedCapital=" + committedCapital + ", paidCapital=" + paidCapital + ", companyCenter="
				+ companyCenter + ", phoneNo=" + phoneNo + ", faxNo=" + faxNo + ", internetAddress=" + internetAddress
				+ ", contactAddress=" + contactAddress + "]";
	}

}
