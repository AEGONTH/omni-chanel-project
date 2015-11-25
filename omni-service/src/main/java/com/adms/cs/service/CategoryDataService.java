package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.CategoryData;

public interface CategoryDataService {

	public CategoryData add(CategoryData example, String userLogin) throws Exception;

	public CategoryData update(CategoryData example, String userLogin) throws Exception;

	public List<CategoryData> findAll() throws Exception;
	
	public CategoryData find(Long id) throws Exception;
	
	public List<CategoryData> find(CategoryData example) throws Exception;

	public List<CategoryData> findByHql(String hql, Object...vals) throws Exception;

	public List<CategoryData> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<CategoryData> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
