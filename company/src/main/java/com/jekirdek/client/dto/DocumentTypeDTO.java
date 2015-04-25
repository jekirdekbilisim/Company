package com.jekirdek.client.dto;

public class DocumentTypeDTO extends BasicDTO {

	private static final long	serialVersionUID	= -8494258187242038532L;

	private String				objid;
	private String				group;
	private String				name;

	public DocumentTypeDTO() {
		super();
	}

	public DocumentTypeDTO(String objid, String group, String name) {
		super();
		this.objid = objid;
		this.group = group;
		this.name = name;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
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
		DocumentTypeDTO other = (DocumentTypeDTO) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
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
		return true;
	}

	@Override
	public String toString() {
		return "DocumentTypeDTO [objid=" + objid + ", group=" + group + ", name=" + name + "]";
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}