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
@Table(name="OMNI_CH_LOG")
public class OmniChLog extends BaseAuditDomain {
	
	private static final long serialVersionUID = 1600363556075023965L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
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
