package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.CustomerDao;
import com.adms.entity.cs.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends GenericDaoHibernate<Customer, Long> implements CustomerDao {
	
	public CustomerDaoImpl() {
		super(Customer.class);
	}
}
