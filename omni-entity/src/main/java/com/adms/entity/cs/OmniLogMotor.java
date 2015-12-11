package com.adms.entity.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name="OMNI_LOG_MOTOR")
public class OmniLogMotor extends BaseAuditDomain {
	
	private static final long serialVersionUID = 203410334685936715L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne
//	@JoinColumn(name="CAR_CODE", referencedColumnName="CATEGORY_CODE")
//	private CategoryData car;
//	
//	@Column(name="CAR_YEAR")
//	private String carYear;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
//	@ManyToOne
//	@JoinColumn(name="PLAN_CODE", referencedColumnName="PLAN_CODE")
//	private ProductPlan productPlan;
//	
//	@ManyToOne
//	@JoinColumn(name="LIST_SOURCE_CODE", referencedColumnName="LIST_SOURCE_CODE")
//	private ListSource listSource;
	
	@ManyToOne
	@JoinColumn(name="POLICY_ID")
	private CustomerYesRecord policy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerYesRecord getPolicy() {
		return policy;
	}

	public void setPolicy(CustomerYesRecord policy) {
		this.policy = policy;
	}
	
}
