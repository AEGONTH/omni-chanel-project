package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.Customer;

public interface CustomerService {

	public Customer add(Customer example, String userLogin) throws Exception;

	public Customer update(Customer example, String userLogin) throws Exception;

	public List<Customer> findAll() throws Exception;
	
	public Customer find(Long id) throws Exception;
	
	public List<Customer> find(Customer example) throws Exception;

	public List<Customer> findByHql(String hql, Object...vals) throws Exception;

	public List<Customer> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<Customer> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
