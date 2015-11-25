package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.entity.cs.Insurer;

public interface InsurerService {

	public Insurer add(Insurer example, String userLogin) throws Exception;

	public Insurer update(Insurer example, String userLogin) throws Exception;

	public List<Insurer> findAll() throws Exception;
	
	public Insurer find(Long id) throws Exception;
	
	public List<Insurer> find(Insurer example) throws Exception;

	public List<Insurer> findByHql(String hql, Object...vals) throws Exception;

	public List<Insurer> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<Insurer> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
