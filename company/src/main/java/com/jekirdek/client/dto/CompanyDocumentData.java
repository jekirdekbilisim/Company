package com.jekirdek.client.dto;

import java.util.Date;

public class CompanyDocumentData extends BasicDTO {

	private static final long	serialVersionUID	= 2051613800756853516L;

	private String				docDbStoreOid;

	private String				docType;

	private String				docTypeOid;

	private String				docSize;

	private String				fileName;

	private Date				announcementDate;

	private String				uploadUser;

	private Date				uploadDate;

	public String getDocDbStoreOid() {
		return docDbStoreOid;
	}

	public void setDocDbStoreOid(String docDbStoreOid) {
		this.docDbStoreOid = docDbStoreOid;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcementDate == null) ? 0 : announcementDate.hashCode());
		result = prime * result + ((docDbStoreOid == null) ? 0 : docDbStoreOid.hashCode());
		result = prime * result + ((docSize == null) ? 0 : docSize.hashCode());
		result = prime * result + ((docType == null) ? 0 : docType.hashCode());
		result = prime * result + ((docTypeOid == null) ? 0 : docTypeOid.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
		result = prime * result + ((uploadUser == null) ? 0 : uploadUser.hashCode());
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
		CompanyDocumentData other = (CompanyDocumentData) obj;
		if (announcementDate == null) {
			if (other.announcementDate != null)
				return false;
		} else if (!announcementDate.equals(other.announcementDate))
			return false;
		if (docDbStoreOid == null) {
			if (other.docDbStoreOid != null)
				return false;
		} else if (!docDbStoreOid.equals(other.docDbStoreOid))
			return false;
		if (docSize == null) {
			if (other.docSize != null)
				return false;
		} else if (!docSize.equals(other.docSize))
			return false;
		if (docType == null) {
			if (other.docType != null)
				return false;
		} else if (!docType.equals(other.docType))
			return false;
		if (docTypeOid == null) {
			if (other.docTypeOid != null)
				return false;
		} else if (!docTypeOid.equals(other.docTypeOid))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		if (uploadUser == null) {
			if (other.uploadUser != null)
				return false;
		} else if (!uploadUser.equals(other.uploadUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyDocumentData [docDbStoreOid=" + docDbStoreOid + ", docType=" + docType + ", docTypeOid=" + docTypeOid + ", docSize="
				+ docSize + ", fileName=" + fileName + ", announcementDate=" + announcementDate + ", uploadUser=" + uploadUser
				+ ", uploadDate=" + uploadDate + "]";
	}

	public String getDocTypeOid() {
		return docTypeOid;
	}

	public void setDocTypeOid(String docTypeOid) {
		this.docTypeOid = docTypeOid;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

}