package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.data.domain.Pageable;

import com.adms.entity.cs.OmniLogMotorHist;

public interface OmniLogMotorHistService {

	public OmniLogMotorHist add(OmniLogMotorHist example, String userLogin) throws Exception;

	public OmniLogMotorHist update(OmniLogMotorHist example, String userLogin) throws Exception;

	public List<OmniLogMotorHist> findAll() throws Exception;
	
	public OmniLogMotorHist find(Long id) throws Exception;
	
	public List<OmniLogMotorHist> find(OmniLogMotorHist example) throws Exception;

	public List<OmniLogMotorHist> findByHql(String hql, Object...vals) throws Exception;

	public List<OmniLogMotorHist> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<OmniLogMotorHist> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

	List<OmniLogMotorHist> findByCriteria(DetachedCriteria detachedCriteria, Pageable pageable) throws Exception;

}
