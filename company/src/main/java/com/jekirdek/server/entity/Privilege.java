package com.jekirdek.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update PRIVILEGE set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "PRIVILEGE")
public class Privilege extends AbstractEntity {

	@Column(name = "NAME", nullable = false)
	private String name;

	public Privilege() {
		super();
	}

	public Privilege(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Privilege [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Privilege other = (Privilege) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
