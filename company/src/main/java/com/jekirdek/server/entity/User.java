package com.jekirdek.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update USER set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "USER")
public class User extends AbstractEntity {

	@Column(name = "TCKN", nullable = true, length = 11)
	private String	tckn;

	@Column(name = "FIRST_NAME", nullable = true)
	private String	firstname;

	@Column(name = "LAST_NAME", nullable = true)
	private String	lastname;

	@Column(name = "USER_NAME", nullable = true, length = 50)
	private String	userName;

	@Column(name = "PASSWORD", nullable = true, length = 50)
	private String	password;

	@Column(name = "EMAIL", nullable = true)
	private String	email;

	@Column(name = "CELL_PHONE", nullable = true, length = 10)
	private String	cellphone;

	@Column(name = "PHONE", nullable = true, length = 15)
	private String	phone;

	@Column(name = "PHONE_INTERNAL", nullable = true, length = 10)
	private String	phoneinternal;

	@Column(name = "COUNTRY", nullable = true, length = 50)
	private String	country;

	@Column(name = "CITY", nullable = true, length = 50)
	private String	city;

	@Column(name = "DISTRICT", nullable = true, length = 50)
	private String	district;

	@Column(name = "ADDRESS", nullable = true)
	private String	address;

	public User() {
	}

	public User(String tckn, String firstname, String lastname, String email, String cellphone, String phone, String phoneinternal,
			String country, String city, String district, String address) {
		super();
		this.tckn = tckn;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cellphone = cellphone;
		this.phone = phone;
		this.phoneinternal = phoneinternal;
		this.country = country;
		this.city = city;
		this.district = district;
		this.address = address;

	}

	public String getTckn() {
		return tckn;
	}

	public void setTckn(String tckn) {
		this.tckn = tckn;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneinternal() {
		return phoneinternal;
	}

	public void setPhoneinternal(String phoneinternal) {
		this.phoneinternal = phoneinternal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((phoneinternal == null) ? 0 : phoneinternal.hashCode());
		result = prime * result + ((tckn == null) ? 0 : tckn.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (phoneinternal == null) {
			if (other.phoneinternal != null)
				return false;
		} else if (!phoneinternal.equals(other.phoneinternal))
			return false;
		if (tckn == null) {
			if (other.tckn != null)
				return false;
		} else if (!tckn.equals(other.tckn))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return firstname.concat(" ").concat(lastname);
	}
}
