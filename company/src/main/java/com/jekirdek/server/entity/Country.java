package com.jekirdek.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update COUNTRY set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "COUNTRY")
public class Country extends AbstractEntity {

	@Column(name = "COUNTRY_NAME", nullable = false)
	private String countryName;

	@Column(name = "LICENCE_PLATE", nullable = false)
	private String licencePlate;

	public Country() {
	}

	public Country(String countryName, String licencePlate) {
		super();
		this.countryName = countryName;
		this.licencePlate = licencePlate;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((licencePlate == null) ? 0 : licencePlate.hashCode());
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
		Country other = (Country) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (licencePlate == null) {
			if (other.licencePlate != null)
				return false;
		} else if (!licencePlate.equals(other.licencePlate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", licencePlate=" + licencePlate + "]";
	}

}
