package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.OmniChLog;

public interface OmniChLogService {

	public OmniChLog add(OmniChLog example, String userLogin) throws Exception;

	public OmniChLog update(OmniChLog example, String userLogin) throws Exception;

	public List<OmniChLog> findAll() throws Exception;
	
	public OmniChLog find(Long id) throws Exception;
	
	public List<OmniChLog> find(OmniChLog example) throws Exception;

	public List<OmniChLog> findByHql(String hql, Object...vals) throws Exception;

	public List<OmniChLog> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<OmniChLog> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
