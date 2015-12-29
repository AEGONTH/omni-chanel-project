package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.Country;

public interface CountryService {

	public Country add(Country example, String userLogin) throws Exception;

	public Country update(Country example, String userLogin) throws Exception;

	public List<Country> findAll() throws Exception;
	
	public Country find(Long id) throws Exception;
	
	public List<Country> find(Country example) throws Exception;

	public List<Country> findByHql(String hql, Object...vals) throws Exception;

	public List<Country> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<Country> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
