package com.adms.sales.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.sale.Sales;

public interface SalesService {

	public List<Sales> findAll() throws Exception;

	public Sales add(Sales example, String userLogin) throws Exception;

	public Sales update(Sales example, String userLogin) throws Exception;
	
	public List<Sales> find(Sales example) throws Exception;

	public List<Sales> findByHql(String hql, Object...vals) throws Exception;

	public List<Sales> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<Sales> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
