package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.data.domain.Pageable;

import com.adms.entity.cs.OmniMotorHist;

public interface OmniMotorHistService {

	public OmniMotorHist add(OmniMotorHist example, String userLogin) throws Exception;

	public OmniMotorHist update(OmniMotorHist example, String userLogin) throws Exception;

	public List<OmniMotorHist> findAll() throws Exception;
	
	public OmniMotorHist find(Long id) throws Exception;
	
	public List<OmniMotorHist> find(OmniMotorHist example) throws Exception;

	public List<OmniMotorHist> findByHql(String hql, Object...vals) throws Exception;

	public List<OmniMotorHist> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<OmniMotorHist> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

	List<OmniMotorHist> findByCriteria(DetachedCriteria detachedCriteria, Pageable pageable) throws Exception;

}
