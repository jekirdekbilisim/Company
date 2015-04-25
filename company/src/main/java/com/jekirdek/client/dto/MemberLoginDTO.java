package com.jekirdek.client.dto;

import com.jekirdek.client.pojo.CertInfo;

public class MemberLoginDTO extends BasicDTO {

	private static final long serialVersionUID = -8316812081423171011L;

	private CertInfo certInfo;

	public CertInfo getCertInfo() {
		return certInfo;
	}

	public void setCertInfo(CertInfo certInfo) {
		this.certInfo = certInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((certInfo == null) ? 0 : certInfo.hashCode());
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
		MemberLoginDTO other = (MemberLoginDTO) obj;
		if (certInfo == null) {
			if (other.certInfo != null)
				return false;
		} else if (!certInfo.equals(other.certInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberLoginDTO [certInfo=" + certInfo + "]";
	}

}
