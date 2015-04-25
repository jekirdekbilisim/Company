package com.jekirdek.client.dto;

public class InspectorDTO extends BasicDTO {

	private static final long serialVersionUID = -7098796332883234829L;

	private String objid;

	private String name;

	private String surname;

	private String title;

	private String address;

	private String registeredBranch;

	// must be generate at constructor
	private Long tempKeyForClientDelete;

	public InspectorDTO() {
		super();
		tempKeyForClientDelete = System.currentTimeMillis();
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegisteredBranch() {
		return registeredBranch;
	}

	public void setRegisteredBranch(String registeredBranch) {
		this.registeredBranch = registeredBranch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((registeredBranch == null) ? 0 : registeredBranch.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((tempKeyForClientDelete == null) ? 0 : tempKeyForClientDelete.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		InspectorDTO other = (InspectorDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (objid == null) {
			if (other.objid != null)
				return false;
		} else if (!objid.equals(other.objid))
			return false;
		if (registeredBranch == null) {
			if (other.registeredBranch != null)
				return false;
		} else if (!registeredBranch.equals(other.registeredBranch))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (tempKeyForClientDelete == null) {
			if (other.tempKeyForClientDelete != null)
				return false;
		} else if (!tempKeyForClientDelete.equals(other.tempKeyForClientDelete))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InspectorDTO [objid=" + objid + ", name=" + name + ", surname=" + surname + ", title=" + title + ", address=" + address
				+ ", registeredBranch=" + registeredBranch + ", tempKeyForClientDelete=" + tempKeyForClientDelete + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTempKeyForClientDelete() {
		return tempKeyForClientDelete;
	}

	public void setTempKeyForClientDelete(Long tempKeyForClientDelete) {
		this.tempKeyForClientDelete = tempKeyForClientDelete;
	}

}