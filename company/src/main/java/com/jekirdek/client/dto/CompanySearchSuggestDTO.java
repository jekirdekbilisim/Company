package com.jekirdek.client.dto;


public class CompanySearchSuggestDTO extends BasicDTO {

	private static final long serialVersionUID = 4590863652343138762L;

	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((searchText == null) ? 0 : searchText.hashCode());
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
		CompanySearchSuggestDTO other = (CompanySearchSuggestDTO) obj;
		if (searchText == null) {
			if (other.searchText != null)
				return false;
		} else if (!searchText.equals(other.searchText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanySearchSuggestDTO [searchText=" + searchText + "]";
	}

}
