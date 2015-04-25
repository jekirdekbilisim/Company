package com.jekirdek.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update MENU set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "MENU")
public class Menu extends AbstractEntity {

	@Column(name = "NAME", nullable = false)
	private String		name;

	@Column(name = "FRIENDLY_URL", nullable = false)
	private String		friendlyUrl;

	@Column(name = "MENU_CLASS", nullable = false)
	private String		menuClass;

	@Where(clause = "status='A'")
	@JoinColumn(name = "PRIVILEGE_OID", nullable = false)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Privilege	privilege;

	@Column(name = "ORDER_NUMBER", nullable = true)
	private Integer		orderNumber;

	@Column(name = "VISIBLE", nullable = false)
	private Boolean		visible;

	public Menu(String name) {
		super();
		this.name = name;
		visible = Boolean.TRUE;
	}

	public Menu() {
		super();
		visible = Boolean.TRUE;
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
		result = prime * result + ((friendlyUrl == null) ? 0 : friendlyUrl.hashCode());
		result = prime * result + ((menuClass == null) ? 0 : menuClass.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
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
		Menu other = (Menu) obj;
		if (friendlyUrl == null) {
			if (other.friendlyUrl != null)
				return false;
		} else if (!friendlyUrl.equals(other.friendlyUrl))
			return false;
		if (menuClass == null) {
			if (other.menuClass != null)
				return false;
		} else if (!menuClass.equals(other.menuClass))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		return true;
	}

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", friendlyUrl=" + friendlyUrl + ", menuClass=" + menuClass + ", privilege=" + privilege
				+ ", orderNumber=" + orderNumber + ", visible=" + visible + "]";
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

}
