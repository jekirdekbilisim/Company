package com.jekirdek.client.dto;


public class CompanyLogoDTO extends BasicDTO {

	private static final long serialVersionUID = -9011213593860127376L;

	private String logoFileSessionKey;

	public String getLogoFileSessionKey() {
		return logoFileSessionKey;
	}

	public void setLogoFileSessionKey(String logoFileSessionKey) {
		this.logoFileSessionKey = logoFileSessionKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logoFileSessionKey == null) ? 0 : logoFileSessionKey.hashCode());
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
		CompanyLogoDTO other = (CompanyLogoDTO) obj;
		if (logoFileSessionKey == null) {
			if (other.logoFileSessionKey != null)
				return false;
		} else if (!logoFileSessionKey.equals(other.logoFileSessionKey))
			return false;
		return true;
	}

}
