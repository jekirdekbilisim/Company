package com.jekirdek.client.dto;

import java.util.List;

import com.jekirdek.client.util.ListItem;

public class UserCompanyAuthDTO extends BasicDTO {

	private static final long serialVersionUID = 4590863652343138762L;

	private String userTckn;

	private List<ListItem> sourceItemList;

	private List<ListItem> targetItemList;

	public List<ListItem> getSourceItemList() {
		return sourceItemList;
	}

	public void setSourceItemList(List<ListItem> sourceItemList) {
		this.sourceItemList = sourceItemList;
	}

	public List<ListItem> getTargetItemList() {
		return targetItemList;
	}

	public void setTargetItemList(List<ListItem> targetItemList) {
		this.targetItemList = targetItemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceItemList == null) ? 0 : sourceItemList.hashCode());
		result = prime * result + ((targetItemList == null) ? 0 : targetItemList.hashCode());
		result = prime * result + ((userTckn == null) ? 0 : userTckn.hashCode());
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
		UserCompanyAuthDTO other = (UserCompanyAuthDTO) obj;
		if (sourceItemList == null) {
			if (other.sourceItemList != null)
				return false;
		} else if (!sourceItemList.equals(other.sourceItemList))
			return false;
		if (targetItemList == null) {
			if (other.targetItemList != null)
				return false;
		} else if (!targetItemList.equals(other.targetItemList))
			return false;
		if (userTckn == null) {
			if (other.userTckn != null)
				return false;
		} else if (!userTckn.equals(other.userTckn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserCompanyAuthDTO [userId=" + userTckn + ", sourceItemList=" + sourceItemList + ", targetItemList=" + targetItemList + "]";
	}

	public String getUserTckn() {
		return userTckn;
	}

	public void setUserTckn(String userId) {
		this.userTckn = userId;
	}

}
