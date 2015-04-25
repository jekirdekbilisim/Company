package com.jekirdek.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update ACTION_LOG set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "ACTION_LOG")
public class ActionLog extends AbstractEntity {

	/**
	 * işlemi yapan kullanıcı
	 */
	@Column(name = "USER_OID", length = 50)
	private String userOid;

	/**
	 * işlemden etkilenen kullanıcı
	 */
	@Column(name = "EFFECTED_USER_OID", length = 50)
	private String effectedUserOid;

	@Column(name = "COMPANY_OID", length = 50)
	private String companyOid;

	@Column(name = "MANAGER_OID", length = 50)
	private String managerOid;

	@Column(name = "INSPECTOR_OID", length = 50)
	private String inspectorOid;

	@Column(name = "ACTION_LOG_TYPE", length = 50)
	private String actionLogType;

	@Column(name = "DOCUMENT_TYPE_OID", length = 50)
	private String documentTypeOid;

	@Column(name = "DOCUMENT_DB_STORE_OID", length = 50)
	private String documentDbStoreOid;

	@Column(name = "SIGNABLE_INPUT", length = 1000)
	private String signableInput;

	@Column(name = "SIGN", length = 255)
	private String sign;

	public String getUserOid() {
		return userOid;
	}

	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	public String getCompanyOid() {
		return companyOid;
	}

	public void setCompanyOid(String companyOid) {
		this.companyOid = companyOid;
	}

	public String getActionLogType() {
		return actionLogType;
	}

	public void setActionLogType(String actionLogType) {
		this.actionLogType = actionLogType;
	}

	public String getDocumentTypeOid() {
		return documentTypeOid;
	}

	public void setDocumentTypeOid(String documentTypeOid) {
		this.documentTypeOid = documentTypeOid;
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
		int result = super.hashCode();
		result = prime * result + ((actionLogType == null) ? 0 : actionLogType.hashCode());
		result = prime * result + ((companyOid == null) ? 0 : companyOid.hashCode());
		result = prime * result + ((managerOid == null) ? 0 : managerOid.hashCode());
		result = prime * result + ((documentDbStoreOid == null) ? 0 : documentDbStoreOid.hashCode());
		result = prime * result + ((documentTypeOid == null) ? 0 : documentTypeOid.hashCode());
		result = prime * result + ((effectedUserOid == null) ? 0 : effectedUserOid.hashCode());
		result = prime * result + ((inspectorOid == null) ? 0 : inspectorOid.hashCode());
		result = prime * result + ((sign == null) ? 0 : sign.hashCode());
		result = prime * result + ((signableInput == null) ? 0 : signableInput.hashCode());
		result = prime * result + ((userOid == null) ? 0 : userOid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionLog other = (ActionLog) obj;
		if (actionLogType == null) {
			if (other.actionLogType != null)
				return false;
		} else if (!actionLogType.equals(other.actionLogType))
			return false;
		if (companyOid == null) {
			if (other.companyOid != null)
				return false;
		} else if (!companyOid.equals(other.companyOid))
			return false;
		if (managerOid == null) {
			if (other.managerOid != null)
				return false;
		} else if (!managerOid.equals(other.managerOid))
			return false;
		if (documentDbStoreOid == null) {
			if (other.documentDbStoreOid != null)
				return false;
		} else if (!documentDbStoreOid.equals(other.documentDbStoreOid))
			return false;
		if (documentTypeOid == null) {
			if (other.documentTypeOid != null)
				return false;
		} else if (!documentTypeOid.equals(other.documentTypeOid))
			return false;
		if (effectedUserOid == null) {
			if (other.effectedUserOid != null)
				return false;
		} else if (!effectedUserOid.equals(other.effectedUserOid))
			return false;
		if (inspectorOid == null) {
			if (other.inspectorOid != null)
				return false;
		} else if (!inspectorOid.equals(other.inspectorOid))
			return false;
		if (sign == null) {
			if (other.sign != null)
				return false;
		} else if (!sign.equals(other.sign))
			return false;
		if (signableInput == null) {
			if (other.signableInput != null)
				return false;
		} else if (!signableInput.equals(other.signableInput))
			return false;
		if (userOid == null) {
			if (other.userOid != null)
				return false;
		} else if (!userOid.equals(other.userOid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActionLog [userOid=" + userOid + ", effectedUserOid=" + effectedUserOid + ", companyOid=" + companyOid + ", managerOid="
				+ managerOid + ", inspectorOid=" + inspectorOid + ", actionLogType=" + actionLogType + ", documentTypeOid="
				+ documentTypeOid + ", documentDbStoreOid=" + documentDbStoreOid + "]";
	}

	public String getEffectedUserOid() {
		return effectedUserOid;
	}

	public void setEffectedUserOid(String effectedUserOid) {
		this.effectedUserOid = effectedUserOid;
	}

	public String getManagerOid() {
		return managerOid;
	}

	public void setManagerOid(String managerOid) {
		this.managerOid = managerOid;
	}

	public String getInspectorOid() {
		return inspectorOid;
	}

	public void setInspectorOid(String inspectorOid) {
		this.inspectorOid = inspectorOid;
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