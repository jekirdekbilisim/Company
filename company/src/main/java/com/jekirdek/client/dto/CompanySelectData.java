package com.jekirdek.client.dto;

public class CompanySelectData extends BasicDTO {

	private static final long serialVersionUID = 5274341408287475735L;

	private String companyOid;

	private String alias;

	private String tradeName;

	public CompanySelectData() {
		super();
	}

	public CompanySelectData(String companyOid, String alias, String tradeName) {
		super();
		this.companyOid = companyOid;
		this.alias = alias;
		this.tradeName = tradeName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCompanyOid() {
		return companyOid;
	}

	public void setCompanyOid(String companyOid) {
		this.companyOid = companyOid;
	}

	@Override
	public String toString() {
		return "CompanySelectData [alias=" + alias + ", companyOid=" + companyOid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((companyOid == null) ? 0 : companyOid.hashCode());
		result = prime * result + ((tradeName == null) ? 0 : tradeName.hashCode());
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
		CompanySelectData other = (CompanySelectData) obj;
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
		if (tradeName == null) {
			if (other.tradeName != null)
				return false;
		} else if (!tradeName.equals(other.tradeName))
			return false;
		return true;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

}
