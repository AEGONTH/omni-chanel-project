package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.Province;

public interface ProvinceService {

	public Province add(Province example, String userLogin) throws Exception;

	public Province update(Province example, String userLogin) throws Exception;

	public List<Province> findAll() throws Exception;
	
	public Province find(Long id) throws Exception;
	
	public List<Province> find(Province example) throws Exception;

	public List<Province> findByHql(String hql, Object...vals) throws Exception;

	public List<Province> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<Province> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
