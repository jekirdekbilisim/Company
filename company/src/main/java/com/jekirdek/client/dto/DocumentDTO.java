package com.jekirdek.client.dto;

import java.util.Date;

public class DocumentDTO extends BasicDTO {

	private static final long	serialVersionUID	= -6975672805952245423L;

	private String				docType;

	private String				fileSessionKey;

	private Date				announcementDate;

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getFileSessionKey() {
		return fileSessionKey;
	}

	public void setFileSessionKey(String fileSessionKey) {
		this.fileSessionKey = fileSessionKey;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcementDate == null) ? 0 : announcementDate.hashCode());
		result = prime * result + ((docType == null) ? 0 : docType.hashCode());
		result = prime * result + ((fileSessionKey == null) ? 0 : fileSessionKey.hashCode());
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
		DocumentDTO other = (DocumentDTO) obj;
		if (announcementDate == null) {
			if (other.announcementDate != null)
				return false;
		} else if (!announcementDate.equals(other.announcementDate))
			return false;
		if (docType == null) {
			if (other.docType != null)
				return false;
		} else if (!docType.equals(other.docType))
			return false;
		if (fileSessionKey == null) {
			if (other.fileSessionKey != null)
				return false;
		} else if (!fileSessionKey.equals(other.fileSessionKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DocumentDTO [docType=" + docType + ", fileSessionKey=" + fileSessionKey + ", announcementDate=" + announcementDate + "]";
	}

}