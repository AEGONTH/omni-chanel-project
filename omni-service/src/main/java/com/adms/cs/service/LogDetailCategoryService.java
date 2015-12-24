package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.LogDetailCategory;

public interface LogDetailCategoryService {

	public LogDetailCategory add(LogDetailCategory example, String userLogin) throws Exception;

	public LogDetailCategory update(LogDetailCategory example, String userLogin) throws Exception;

	public List<LogDetailCategory> findAll() throws Exception;
	
	public LogDetailCategory find(Long id) throws Exception;
	
	public List<LogDetailCategory> find(LogDetailCategory example) throws Exception;

	public List<LogDetailCategory> findByHql(String hql, Object...vals) throws Exception;

	public List<LogDetailCategory> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<LogDetailCategory> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
