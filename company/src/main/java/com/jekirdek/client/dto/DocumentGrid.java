package com.jekirdek.client.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DocumentGrid implements IsSerializable, Serializable {

	private static final long	serialVersionUID	= -859340668983893170L;

	private String				fileName;

	private Date				announcementDate;

	private String				fileObjid;

	public DocumentGrid() {
		super();
	}

	public DocumentGrid(String fileName, Date announcementDate) {
		super();
		this.fileName = fileName;
		this.announcementDate = announcementDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String getFileObjid() {
		return fileObjid;
	}

	public void setFileObjid(String fileObjid) {
		this.fileObjid = fileObjid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcementDate == null) ? 0 : announcementDate.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((fileObjid == null) ? 0 : fileObjid.hashCode());
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
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileObjid == null) {
			if (other.fileObjid != null)
				return false;
		} else if (!fileObjid.equals(other.fileObjid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DocumentGrid [fileName=" + fileName + ", announcementDate=" + announcementDate + ", fileObjid=" + fileObjid + "]";
	}
}
