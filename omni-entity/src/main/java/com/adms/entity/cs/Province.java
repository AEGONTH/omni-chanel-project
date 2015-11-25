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
@Table(name="PROVINCE")
public class Province extends BaseDomain {

	private static final long serialVersionUID = -7478960292958486075L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="REGION_CODE", referencedColumnName="REGION_CODE")
	private CountryRegion region;
	
	@Column(name="PROVINCE_CODE")
	private String provinceCode;
	
	@Column(name="PROVINCE_NAME_TH")
	private String provinceNameTh;
	
	@Column(name="PROVINCE_NAME_EN")
	private String provinceNameEn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CountryRegion getRegion() {
		return region;
	}

	public void setRegion(CountryRegion region) {
		this.region = region;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceNameTh() {
		return provinceNameTh;
	}

	public void setProvinceNameTh(String provinceNameTh) {
		this.provinceNameTh = provinceNameTh;
	}

	public String getProvinceNameEn() {
		return provinceNameEn;
	}

	public void setProvinceNameEn(String provinceNameEn) {
		this.provinceNameEn = provinceNameEn;
	}
	
}
