package com.jekirdek.client.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DocumentGrid implements IsSerializable, Serializable {

	private static final long	serialVersionUID	= -859340668983893170L;

	private String				documentName;

	private Date				announcementDate;

	private String				documentStoreOid;

	private Date				uploadDate;

	public DocumentGrid() {
		super();
	}

	public DocumentGrid(String fileName, Date announcementDate) {
		super();
		this.documentName = fileName;
		this.announcementDate = announcementDate;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String fileName) {
		this.documentName = fileName;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String getDocumentStoreOid() {
		return documentStoreOid;
	}

	public void setDocumentStoreOid(String fileObjid) {
		this.documentStoreOid = fileObjid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcementDate == null) ? 0 : announcementDate.hashCode());
		result = prime * result + ((documentName == null) ? 0 : documentName.hashCode());
		result = prime * result + ((documentStoreOid == null) ? 0 : documentStoreOid.hashCode());
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
		DocumentGrid other = (DocumentGrid) obj;
		if (announcementDate == null) {
			if (other.announcementDate != null)
				return false;
		} else if (!announcementDate.equals(other.announcementDate))
			return false;
		if (documentName == null) {
			if (other.documentName != null)
				return false;
		} else if (!documentName.equals(other.documentName))
			return false;
		if (documentStoreOid == null) {
			if (other.documentStoreOid != null)
				return false;
		} else if (!documentStoreOid.equals(other.documentStoreOid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DocumentGrid [fileName=" + documentName + ", announcementDate=" + announcementDate + ", fileObjid=" + documentStoreOid
				+ "]";
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
}
