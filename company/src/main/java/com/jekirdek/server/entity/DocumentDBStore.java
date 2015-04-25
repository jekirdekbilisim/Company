package com.jekirdek.server.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update DOCUMENT_DB_STORE set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "DOCUMENT_DB_STORE")
public class DocumentDBStore extends AbstractEntity {

	@Lob
	@Column(name = "DOCUMENT")
	private Blob document;

	@Column(name = "FILE_NAME", length = 150)
	private String fileName;

	@Column(name = "CONTENT_TYPE", length = 150)
	private String contentType;

	@Column(name = "FORMATTED_SIZE", length = 20)
	private String formattedSize;

	public Blob getDocument() {
		return document;
	}

	public void setDocument(Blob document) {
		this.document = document;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFormattedSize() {
		return formattedSize;
	}

	public void setFormattedSize(String formattedSize) {
		this.formattedSize = formattedSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((formattedSize == null) ? 0 : formattedSize.hashCode());
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
		DocumentDBStore other = (DocumentDBStore) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (formattedSize == null) {
			if (other.formattedSize != null)
				return false;
		} else if (!formattedSize.equals(other.formattedSize))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DocumentDBStore [fileName=" + fileName + ", contentType=" + contentType + ", formattedSize=" + formattedSize + "]";
	}

}