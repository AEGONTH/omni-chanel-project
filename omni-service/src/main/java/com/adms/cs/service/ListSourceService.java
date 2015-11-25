package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.ListSource;

public interface ListSourceService {

	public ListSource add(ListSource example, String userLogin) throws Exception;

	public ListSource update(ListSource example, String userLogin) throws Exception;

	public List<ListSource> findAll() throws Exception;
	
	public ListSource find(Long id) throws Exception;
	
	public List<ListSource> find(ListSource example) throws Exception;

	public List<ListSource> findByHql(String hql, Object...vals) throws Exception;

	public List<ListSource> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<ListSource> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
