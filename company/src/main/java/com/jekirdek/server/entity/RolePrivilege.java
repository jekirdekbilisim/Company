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
@SQLDelete(sql = "update ROLE_PRIVILEGE set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "ROLE_PRIVILEGE")
public class RolePrivilege extends AbstractEntity {

	@Where(clause = "status='A'")
	@JoinColumn(name = "ROLE_OID", nullable = false)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Role role;

	@Where(clause = "status='A'")
	@JoinColumn(name = "PRIVILEGE_OID", nullable = false)
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Privilege privilege;

	public RolePrivilege() {
		super();
	}

	public RolePrivilege(Role role, Privilege privilege) {
		super();
		this.role = role;
		this.privilege = privilege;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		RolePrivilege other = (RolePrivilege) obj;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
