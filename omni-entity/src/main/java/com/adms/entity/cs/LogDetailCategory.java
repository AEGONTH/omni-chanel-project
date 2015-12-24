package com.adms.entity.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="LOG_DETAIL_CATEGORY")
public class LogDetailCategory extends BaseDomain {

	private static final long serialVersionUID = -936976666936406413L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="CALL_NATURE")
	private LogStatusGroup levelCallNature;
	
	@ManyToOne
	@JoinColumn(name="CALL_CATEGORY")
	private LogStatusGroup levelCallCategory;

	@ManyToOne
	@JoinColumn(name="CALL_DETAIL_VALUE")
	private LogValue logValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogStatusGroup getLevelCallNature() {
		return levelCallNature;
	}

	public void setLevelCallNature(LogStatusGroup levelCallNature) {
		this.levelCallNature = levelCallNature;
	}

	public LogStatusGroup getLevelCallCategory() {
		return levelCallCategory;
	}

	public void setLevelCallCategory(LogStatusGroup levelCallCategory) {
		this.levelCallCategory = levelCallCategory;
	}

	public LogValue getLogValue() {
		return logValue;
	}

	public void setLogValue(LogValue logValue) {
		this.logValue = logValue;
	}
	
}
