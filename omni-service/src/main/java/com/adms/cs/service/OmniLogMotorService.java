package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.OmniLogMotor;

public interface OmniLogMotorService {

	public OmniLogMotor add(OmniLogMotor example, String userLogin) throws Exception;

	public OmniLogMotor update(OmniLogMotor example, String userLogin) throws Exception;

	public List<OmniLogMotor> findAll() throws Exception;
	
	public OmniLogMotor find(Long id) throws Exception;
	
	public List<OmniLogMotor> find(OmniLogMotor example) throws Exception;

	public List<OmniLogMotor> findByHql(String hql, Object...vals) throws Exception;

	public List<OmniLogMotor> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<OmniLogMotor> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
