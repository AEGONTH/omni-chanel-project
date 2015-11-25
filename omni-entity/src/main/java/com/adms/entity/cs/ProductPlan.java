package com.adms.entity.cs;

import java.math.BigDecimal;

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
@Table(name="PRODUCT_PLAN")
public class ProductPlan extends BaseDomain {

	private static final long serialVersionUID = -6871588317626558926L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_CODE", referencedColumnName="PRODUCT_CODE")
	private InceProduct product;
	
	@Column(name="PLAN_CODE")
	private String planCode;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="SUM_ASSURED")
	private BigDecimal sumAssured;
	
	@Column(name="PREMIUM")
	private BigDecimal premium;
	
	@Column(name="EXCESS_AMT")
	private BigDecimal excessAmt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InceProduct getProduct() {
		return product;
	}

	public void setProduct(InceProduct product) {
		this.product = product;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigDecimal getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(BigDecimal sumAssured) {
		this.sumAssured = sumAssured;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public BigDecimal getExcessAmt() {
		return excessAmt;
	}

	public void setExcessAmt(BigDecimal excessAmt) {
		this.excessAmt = excessAmt;
	}
	
}
