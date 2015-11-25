package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.ParamConfig;

public interface ParamConfigService {

	public ParamConfig add(ParamConfig example, String userLogin) throws Exception;

	public ParamConfig update(ParamConfig example, String userLogin) throws Exception;

	public List<ParamConfig> findAll() throws Exception;
	
	public ParamConfig find(Long id) throws Exception;
	
	public List<ParamConfig> find(ParamConfig example) throws Exception;

	public List<ParamConfig> findByHql(String hql, Object...vals) throws Exception;

	public List<ParamConfig> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<ParamConfig> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
