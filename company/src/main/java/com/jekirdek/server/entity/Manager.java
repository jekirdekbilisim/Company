package com.jekirdek.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update MANAGER set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "MANAGER")
public class Manager extends AbstractEntity {

	@Column(name = "MANAGER_TYPE", nullable = false, length = 25)
	private String	managerTye;

	@Column(name = "TITLE", length = 50)
	private String	title;

	@Column(name = "MERSIS_NO")
	private String	mersisNo;

	@Column(name = "TRADE_NAME")
	private String	tradeName;

	@Column(name = "COMPANY_CENTER")
	private String	companyCenter;

	@Column(name = "NAME")
	private String	name;

	@Column(name = "SURNAME")
	private String	surname;

	@Column(name = "PROFILE", length = 2000)
	private String	profile;

	@Where(clause = "status='A'")
	@ManyToOne
	@JoinColumn(name = "COMPANY_OID")
	private Company	company;

	public String getManagerTye() {
		return managerTye;
	}

	public void setManagerTye(String managerTye) {
		this.managerTye = managerTye;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((companyCenter == null) ? 0 : companyCenter.hashCode());
		result = prime * result + ((managerTye == null) ? 0 : managerTye.hashCode());
		result = prime * result + ((mersisNo == null) ? 0 : mersisNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Manager other = (Manager) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (companyCenter == null) {
			if (other.companyCenter != null)
				return false;
		} else if (!companyCenter.equals(other.companyCenter))
			return false;
		if (managerTye == null) {
			if (other.managerTye != null)
				return false;
		} else if (!managerTye.equals(other.managerTye))
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
		return "Manager [managerTye=" + managerTye + ", title=" + title + ", mersisNo=" + mersisNo + ", tradeName=" + tradeName
				+ ", companyCenter=" + companyCenter + ", name=" + name + ", surname=" + surname + ", profile=" + profile + ", company="
				+ company + "]";
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