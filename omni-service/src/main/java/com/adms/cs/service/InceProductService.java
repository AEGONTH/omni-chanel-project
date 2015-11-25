package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.InceProduct;

public interface InceProductService {

	public InceProduct add(InceProduct example, String userLogin) throws Exception;

	public InceProduct update(InceProduct example, String userLogin) throws Exception;

	public List<InceProduct> findAll() throws Exception;
	
	public InceProduct find(Long id) throws Exception;
	
	public List<InceProduct> find(InceProduct example) throws Exception;

	public List<InceProduct> findByHql(String hql, Object...vals) throws Exception;

	public List<InceProduct> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<InceProduct> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
