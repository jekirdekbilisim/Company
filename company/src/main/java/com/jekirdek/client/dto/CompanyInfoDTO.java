package com.jekirdek.client.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyInfoDTO extends BasicDTO {

	private static final long serialVersionUID = -8316812081423171011L;

	private CompanyDTO companyDTO;

	private List<ManagerDTO> managerDtoList;

	private List<InspectorDTO> inspectorDtoList;

	private String signableInput;
	private String sign;

	public CompanyDTO getCompanyDTO() {
		if (companyDTO == null) {
			companyDTO = new CompanyDTO();
		}
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO company) {
		this.companyDTO = company;
	}

	public List<ManagerDTO> getManagerDtoList() {
		if (managerDtoList == null)
			managerDtoList = new ArrayList<ManagerDTO>();
		return managerDtoList;
	}

	public void setManagerDtoList(List<ManagerDTO> managerDtoList) {
		this.managerDtoList = managerDtoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDTO == null) ? 0 : companyDTO.hashCode());
		result = prime * result + ((managerDtoList == null) ? 0 : managerDtoList.hashCode());
		result = prime * result + ((inspectorDtoList == null) ? 0 : inspectorDtoList.hashCode());
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
		CompanyInfoDTO other = (CompanyInfoDTO) obj;
		if (companyDTO == null) {
			if (other.companyDTO != null)
				return false;
		} else if (!companyDTO.equals(other.companyDTO))
			return false;
		if (managerDtoList == null) {
			if (other.managerDtoList != null)
				return false;
		} else if (!managerDtoList.equals(other.managerDtoList))
			return false;
		if (inspectorDtoList == null) {
			if (other.inspectorDtoList != null)
				return false;
		} else if (!inspectorDtoList.equals(other.inspectorDtoList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyInfoDTO [companyDTO=" + companyDTO + ", managerDtoList=" + managerDtoList + ", inspectorDtoList="
				+ inspectorDtoList + "]";
	}

	public List<InspectorDTO> getInspectorDtoList() {
		if (inspectorDtoList == null)
			inspectorDtoList = new ArrayList<InspectorDTO>();
		return inspectorDtoList;
	}

	public void setInspectorDtoList(List<InspectorDTO> inspectorDtoList) {
		this.inspectorDtoList = inspectorDtoList;
	}

	public String getSignableInput() {
		return signableInput;
	}

	public void setSignableInput(String signableInput) {
		this.signableInput = signableInput;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
