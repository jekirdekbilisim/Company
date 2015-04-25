package com.jekirdek.server.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update COMPANY set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "COMPANY")
public class Company extends AbstractEntity {

	@Column(name = "COMPANY_TYPE", length = 50)
	private String companyType;

	@Column(name = "MERSIS_NO", length = 20)
	private String mersisNo;

	@Column(name = "TRADE_NAME", length = 255)
	private String tradeName;

	@Column(name = "COMMITTED_CAPITAL")
	private String committedCapital;

	@Column(name = "PAID_CAPITAL")
	private String paidCapital;

	@Column(name = "COMPANY_CENTER")
	private String companyCenter;

	@Column(name = "PHONE_NO")
	private String phoneNo;

	@Column(name = "FAX_NO")
	private String faxNo;

	@Column(name = "ALIAS")
	private String alias;

	@Column(name = "INTERNET_ADDRESS")
	private String internetAddress;

	@Column(name = "CONTACT_ADDRESS")
	private String contactAddress;

	@Where(clause = "status='A'")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "LOGO_OID", nullable = true)
	private Logo logo;

	@Where(clause = "status='A'")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company")
	private Set<Manager> managerList;

	@Where(clause = "status='A'")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company")
	private Set<Inspector> inspectorList;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((committedCapital == null) ? 0 : committedCapital.hashCode());
		result = prime * result + ((companyCenter == null) ? 0 : companyCenter.hashCode());
		result = prime * result + ((companyType == null) ? 0 : companyType.hashCode());
		result = prime * result + ((contactAddress == null) ? 0 : contactAddress.hashCode());
		result = prime * result + ((faxNo == null) ? 0 : faxNo.hashCode());
		result = prime * result + ((internetAddress == null) ? 0 : internetAddress.hashCode());
		result = prime * result + ((mersisNo == null) ? 0 : mersisNo.hashCode());
		result = prime * result + ((paidCapital == null) ? 0 : paidCapital.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((tradeName == null) ? 0 : tradeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
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

	@Override
	public String toString() {
		return "Company [companyType=" + companyType + ", mersisNo=" + mersisNo + ", tradeName=" + tradeName + ", committedCapital="
				+ committedCapital + ", paidCapital=" + paidCapital + ", companyCenter=" + companyCenter + ", phoneNo=" + phoneNo
				+ ", faxNo=" + faxNo + ", alias=" + alias + ", internetAddress=" + internetAddress + ", contactAddress=" + contactAddress
				+ "]";
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

	public Set<Manager> getManagerList() {
		if (managerList == null)
			managerList = new HashSet<Manager>();
		return managerList;
	}

	public void setManagerList(Set<Manager> managerList) {
		this.managerList = managerList;
	}

	public Set<Inspector> getInspectorList() {
		if (inspectorList == null)
			inspectorList = new HashSet<Inspector>();
		return inspectorList;
	}

	public void setInspectorList(Set<Inspector> inspectorList) {
		this.inspectorList = inspectorList;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Logo getLogo() {
		return logo;
	}

	public void setLogo(Logo logo) {
		this.logo = logo;
	}

}
