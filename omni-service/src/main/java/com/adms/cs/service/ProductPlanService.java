package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.ProductPlan;

public interface ProductPlanService {

	public ProductPlan add(ProductPlan example, String userLogin) throws Exception;

	public ProductPlan update(ProductPlan example, String userLogin) throws Exception;

	public List<ProductPlan> findAll() throws Exception;
	
	public ProductPlan find(Long id) throws Exception;
	
	public List<ProductPlan> find(ProductPlan example) throws Exception;

	public List<ProductPlan> findByHql(String hql, Object...vals) throws Exception;

	public List<ProductPlan> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<ProductPlan> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
