package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.CallLog;

public interface CallLogService {

	public CallLog add(CallLog example, String userLogin) throws Exception;

	public CallLog update(CallLog example, String userLogin) throws Exception;

	public List<CallLog> findAll() throws Exception;
	
	public CallLog find(Long id) throws Exception;
	
	public List<CallLog> find(CallLog example) throws Exception;

	public List<CallLog> findByHql(String hql, Object...vals) throws Exception;

	public List<CallLog> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<CallLog> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
