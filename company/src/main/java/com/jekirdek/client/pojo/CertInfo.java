package com.jekirdek.client.pojo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CertInfo implements IsSerializable, Serializable {

	private static final long serialVersionUID = -6328559780575183051L;

	private String cardSerialNumber;
	private String cardSerialNumberHex;
	private String cardEmail;
	private String subjectName;
	private String subjectCountry;
	private String subjectLocalityName;
	private String subjectSerialNumber;
	private String issuerName;
	private String issuerOrganizationName;
	private String issuerOrganizationUnitName;
	private String issuerCountry;

	public String getCardSerialNumber() {
		return cardSerialNumber;
	}

	public void setCardSerialNumber(String cardSerialNumber) {
		this.cardSerialNumber = cardSerialNumber;
	}

	public String getCardSerialNumberHex() {
		return cardSerialNumberHex;
	}

	public void setCardSerialNumberHex(String cardSerialNumberHex) {
		this.cardSerialNumberHex = cardSerialNumberHex;
	}

	public String getCardEmail() {
		return cardEmail;
	}

	public void setCardEmail(String cardEmail) {
		this.cardEmail = cardEmail;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCountry() {
		return subjectCountry;
	}

	public void setSubjectCountry(String subjectCountry) {
		this.subjectCountry = subjectCountry;
	}

	public String getSubjectLocalityName() {
		return subjectLocalityName;
	}

	public void setSubjectLocalityName(String subjectLocalityName) {
		this.subjectLocalityName = subjectLocalityName;
	}

	public String getSubjectSerialNumber() {
		return subjectSerialNumber;
	}

	public void setSubjectSerialNumber(String subjectSerialNumber) {
		this.subjectSerialNumber = subjectSerialNumber;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getIssuerOrganizationName() {
		return issuerOrganizationName;
	}

	public void setIssuerOrganizationName(String issuerOrganizationName) {
		this.issuerOrganizationName = issuerOrganizationName;
	}

	public String getIssuerOrganizationUnitName() {
		return issuerOrganizationUnitName;
	}

	public void setIssuerOrganizationUnitName(String issuerOrganizationUnitName) {
		this.issuerOrganizationUnitName = issuerOrganizationUnitName;
	}

	public String getIssuerCountry() {
		return issuerCountry;
	}

	public void setIssuerCountry(String issuerCountry) {
		this.issuerCountry = issuerCountry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardEmail == null) ? 0 : cardEmail.hashCode());
		result = prime * result + ((cardSerialNumber == null) ? 0 : cardSerialNumber.hashCode());
		result = prime * result + ((cardSerialNumberHex == null) ? 0 : cardSerialNumberHex.hashCode());
		result = prime * result + ((issuerCountry == null) ? 0 : issuerCountry.hashCode());
		result = prime * result + ((issuerName == null) ? 0 : issuerName.hashCode());
		result = prime * result + ((issuerOrganizationName == null) ? 0 : issuerOrganizationName.hashCode());
		result = prime * result + ((issuerOrganizationUnitName == null) ? 0 : issuerOrganizationUnitName.hashCode());
		result = prime * result + ((subjectCountry == null) ? 0 : subjectCountry.hashCode());
		result = prime * result + ((subjectLocalityName == null) ? 0 : subjectLocalityName.hashCode());
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
		result = prime * result + ((subjectSerialNumber == null) ? 0 : subjectSerialNumber.hashCode());
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
		CertInfo other = (CertInfo) obj;
		if (cardEmail == null) {
			if (other.cardEmail != null)
				return false;
		} else if (!cardEmail.equals(other.cardEmail))
			return false;
		if (cardSerialNumber == null) {
			if (other.cardSerialNumber != null)
				return false;
		} else if (!cardSerialNumber.equals(other.cardSerialNumber))
			return false;
		if (cardSerialNumberHex == null) {
			if (other.cardSerialNumberHex != null)
				return false;
		} else if (!cardSerialNumberHex.equals(other.cardSerialNumberHex))
			return false;
		if (issuerCountry == null) {
			if (other.issuerCountry != null)
				return false;
		} else if (!issuerCountry.equals(other.issuerCountry))
			return false;
		if (issuerName == null) {
			if (other.issuerName != null)
				return false;
		} else if (!issuerName.equals(other.issuerName))
			return false;
		if (issuerOrganizationName == null) {
			if (other.issuerOrganizationName != null)
				return false;
		} else if (!issuerOrganizationName.equals(other.issuerOrganizationName))
			return false;
		if (issuerOrganizationUnitName == null) {
			if (other.issuerOrganizationUnitName != null)
				return false;
		} else if (!issuerOrganizationUnitName.equals(other.issuerOrganizationUnitName))
			return false;
		if (subjectCountry == null) {
			if (other.subjectCountry != null)
				return false;
		} else if (!subjectCountry.equals(other.subjectCountry))
			return false;
		if (subjectLocalityName == null) {
			if (other.subjectLocalityName != null)
				return false;
		} else if (!subjectLocalityName.equals(other.subjectLocalityName))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		if (subjectSerialNumber == null) {
			if (other.subjectSerialNumber != null)
				return false;
		} else if (!subjectSerialNumber.equals(other.subjectSerialNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CertInfo [cardSerialNumber=" + cardSerialNumber + ", cardSerialNumberHex=" + cardSerialNumberHex + ", cardEmail="
				+ cardEmail + ", subjectName=" + subjectName + ", subjectCountry=" + subjectCountry + ", subjectLocalityName="
				+ subjectLocalityName + ", subjectSerialNumber=" + subjectSerialNumber + ", issuerName=" + issuerName
				+ ", issuerOrganizationName=" + issuerOrganizationName + ", issuerOrganizationUnitName=" + issuerOrganizationUnitName
				+ ", issuerCountry=" + issuerCountry + "]";
	}

}
