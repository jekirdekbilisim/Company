package com.jekirdek.client.dto;

public class CompanySearchDTO extends BasicDTO {

	private static final long serialVersionUID = 5274341408287475735L;

	private String alias;

	private String companyOid;;

	private Boolean withObjId;

	public CompanySearchDTO() {
		withObjId = Boolean.FALSE;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean getWithObjId() {
		return withObjId;
	}

	public void setWithObjId(Boolean withObjId) {
		this.withObjId = withObjId;
	}

	public String getCompanyOid() {
		return companyOid;
	}

	public void setCompanyOid(String companyOid) {
		this.companyOid = companyOid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((companyOid == null) ? 0 : companyOid.hashCode());
		result = prime * result + ((withObjId == null) ? 0 : withObjId.hashCode());
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
		CompanySearchDTO other = (CompanySearchDTO) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (companyOid == null) {
			if (other.companyOid != null)
				return false;
		} else if (!companyOid.equals(other.companyOid))
			return false;
		if (withObjId == null) {
			if (other.withObjId != null)
				return false;
		} else if (!withObjId.equals(other.withObjId))
			return false;
		return true;
	}
}
