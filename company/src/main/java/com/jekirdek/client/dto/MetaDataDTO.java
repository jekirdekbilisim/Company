package com.jekirdek.client.dto;

import java.util.List;

public class MetaDataDTO extends BasicDTO {

	private static final long serialVersionUID = -8494258187242038532L;

	private String objid;

	private String fieldId;

	private String fieldType;

	private String fieldName;

	private Integer fieldOrder;

	private List<MetaParamDTO> metaParamList;

	public MetaDataDTO(String objid, String fieldId, String fieldType, String fieldName, Integer fieldOrder) {
		super();
		this.objid = objid;
		this.fieldId = fieldId;
		this.fieldType = fieldType;
		this.fieldName = fieldName;
		this.fieldOrder = fieldOrder;
	}

	public MetaDataDTO() {
		super();
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(Integer fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((fieldOrder == null) ? 0 : fieldOrder.hashCode());
		result = prime * result + ((fieldType == null) ? 0 : fieldType.hashCode());
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
		MetaDataDTO other = (MetaDataDTO) obj;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (fieldOrder == null) {
			if (other.fieldOrder != null)
				return false;
		} else if (!fieldOrder.equals(other.fieldOrder))
			return false;
		if (fieldType == null) {
			if (other.fieldType != null)
				return false;
		} else if (!fieldType.equals(other.fieldType))
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
		return "MetaDataDTO [objid=" + objid + ", fieldType=" + fieldType + ", fieldName=" + fieldName + ", fieldOrder=" + fieldOrder + "]";
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public List<MetaParamDTO> getMetaParamList() {
		return metaParamList;
	}

	public void setMetaParamList(List<MetaParamDTO> metaParamList) {
		this.metaParamList = metaParamList;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

}