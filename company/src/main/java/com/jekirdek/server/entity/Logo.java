package com.jekirdek.server.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update LOGO set status='P' where objid=?")
@Where(clause = "status='A'")
@Table(name = "LOGO")
public class Logo extends AbstractEntity {

	@Lob
	@Column(name = "LOGO")
	private Blob logo;

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

}