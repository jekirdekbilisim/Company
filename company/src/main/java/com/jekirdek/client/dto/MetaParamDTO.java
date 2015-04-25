package com.jekirdek.client.dto;

public class MetaParamDTO extends BasicDTO {

	private static final long serialVersionUID = -8494258187242038532L;

	private String objid;

	private String metaDataOid;

	private String key;

	private String value;

	public MetaParamDTO() {
		super();
	}

	public MetaParamDTO(String objid, String metaDataOid, String key, String value) {
		super();
		this.objid = objid;
		this.metaDataOid = metaDataOid;
		this.key = key;
		this.value = value;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((metaDataOid == null) ? 0 : metaDataOid.hashCode());
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		MetaParamDTO other = (MetaParamDTO) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (metaDataOid == null) {
			if (other.metaDataOid != null)
				return false;
		} else if (!metaDataOid.equals(other.metaDataOid))
			return false;
		if (objid == null) {
			if (other.objid != null)
				return false;
		} else if (!objid.equals(other.objid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetaParamDTO [objid=" + objid + ", key=" + key + ", value=" + value + "]";
	}

	public String getMetaDataOid() {
		return metaDataOid;
	}

	public void setMetaDataOid(String metaDataOid) {
		this.metaDataOid = metaDataOid;
	}

}