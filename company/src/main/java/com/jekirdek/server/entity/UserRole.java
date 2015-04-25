package com.jekirdek.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update USER_ROLE set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "USER_ROLE")
public class UserRole extends AbstractEntity {

	@Where(clause = "status='A'")
	@JoinColumn(name = "USER_OID", nullable = false)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;

	@Where(clause = "status='A'")
	@JoinColumn(name = "ROLE_OID", nullable = false)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Role role;

	@Where(clause = "status='A'")
	@JoinColumn(name = "COMPANY_OID", nullable = true)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Company company;

	public UserRole() {
		super();
	}

	public UserRole(User user, Role role, Company company) {
		super();
		this.user = user;
		this.role = role;
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserRole other = (UserRole) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [user=" + user + ", role=" + role + ", company=" + company + "]";
	}

}
