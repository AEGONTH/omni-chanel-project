package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.CustomerYesRecord;

public interface CustomerYesRecordService {

	public List<CustomerYesRecord> findAll() throws Exception;

	public CustomerYesRecord add(CustomerYesRecord example, String userLogin) throws Exception;

	public CustomerYesRecord update(CustomerYesRecord example, String userLogin) throws Exception;
	
	public List<CustomerYesRecord> find(CustomerYesRecord example) throws Exception;

	public List<CustomerYesRecord> findByHql(String hql, Object...vals) throws Exception;

	public List<CustomerYesRecord> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<CustomerYesRecord> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
