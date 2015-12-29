package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.data.domain.Pageable;

import com.adms.entity.cs.OmniTravelHist;

public interface OmniTravelHistService {

	public OmniTravelHist add(OmniTravelHist example, String userLogin) throws Exception;

	public OmniTravelHist update(OmniTravelHist example, String userLogin) throws Exception;

	public List<OmniTravelHist> findAll() throws Exception;
	
	public OmniTravelHist find(Long id) throws Exception;
	
	public List<OmniTravelHist> find(OmniTravelHist example) throws Exception;

	public List<OmniTravelHist> findByHql(String hql, Object...vals) throws Exception;

	public List<OmniTravelHist> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<OmniTravelHist> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

	List<OmniTravelHist> findByCriteria(DetachedCriteria detachedCriteria, Pageable pageable) throws Exception;

}
