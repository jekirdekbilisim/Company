package com.jekirdek.server.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update COMPANY_DOCUMENT set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "COMPANY_DOCUMENT")
public class CompanyDocument extends AbstractEntity {

	@Where(clause = "status='A'")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "COMPANY_OID", nullable = false)
	private Company			company;

	@Where(clause = "status='A'")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "DOCUMENT_TYPE_OID", nullable = false)
	private DocumentType	documentType;

	@Where(clause = "status='A'")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "DOCUMENT_OID", nullable = false)
	private DocumentStore	document;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ANNOUNCEMENT_DATE")
	private Date			announcementDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPLOAD_DATE")
	private Date			uploadDate;

	@Column(name = "UPLOAD_USER")
	private String			uploadUser;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public DocumentStore getDocument() {
		return document;
	}

	public void setDocument(DocumentStore document) {
		this.document = document;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
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
		CompanyDocument other = (CompanyDocument) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (documentType == null) {
			if (other.documentType != null)
				return false;
		} else if (!documentType.equals(other.documentType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyDocument [company=" + company + ", documentType=" + documentType + ", document=" + document + "]";
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

}