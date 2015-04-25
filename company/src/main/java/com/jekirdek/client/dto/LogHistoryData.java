package com.jekirdek.client.dto;

public class LogHistoryData extends BasicDTO {

	private static final long	serialVersionUID	= 1482472890908846703L;

	private String				user;

	private String				effectedUser;

	private String				company;

	private String				manager;

	private String				inspector;

	private String				actionLogType;

	private String				documentType;

	private String				documentDbStoreOid;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEffectedUser() {
		return effectedUser;
	}

	public void setEffectedUser(String effectedUser) {
		this.effectedUser = effectedUser;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getActionLogType() {
		return actionLogType;
	}

	public void setActionLogType(String actionLogType) {
		this.actionLogType = actionLogType;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentDbStoreOid() {
		return documentDbStoreOid;
	}

	public void setDocumentDbStoreOid(String documentDbStoreOid) {
		this.documentDbStoreOid = documentDbStoreOid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionLogType == null) ? 0 : actionLogType.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((documentDbStoreOid == null) ? 0 : documentDbStoreOid.hashCode());
		result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
		result = prime * result + ((effectedUser == null) ? 0 : effectedUser.hashCode());
		result = prime * result + ((inspector == null) ? 0 : inspector.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		LogHistoryData other = (LogHistoryData) obj;
		if (actionLogType == null) {
			if (other.actionLogType != null)
				return false;
		} else if (!actionLogType.equals(other.actionLogType))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (documentDbStoreOid == null) {
			if (other.documentDbStoreOid != null)
				return false;
		} else if (!documentDbStoreOid.equals(other.documentDbStoreOid))
			return false;
		if (documentType == null) {
			if (other.documentType != null)
				return false;
		} else if (!documentType.equals(other.documentType))
			return false;
		if (effectedUser == null) {
			if (other.effectedUser != null)
				return false;
		} else if (!effectedUser.equals(other.effectedUser))
			return false;
		if (inspector == null) {
			if (other.inspector != null)
				return false;
		} else if (!inspector.equals(other.inspector))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogHistoryData [user=" + user + ", effectedUser=" + effectedUser + ", company=" + company + ", manager=" + manager
				+ ", inspector=" + inspector + ", actionLogType=" + actionLogType + ", documentType=" + documentType
				+ ", documentDbStoreOid=" + documentDbStoreOid + "]";
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}