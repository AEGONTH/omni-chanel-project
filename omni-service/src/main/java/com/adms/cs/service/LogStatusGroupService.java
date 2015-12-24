package com.adms.cs.service;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.adms.entity.cs.LogStatusGroup;

public interface LogStatusGroupService {

	public LogStatusGroup add(LogStatusGroup example, String userLogin) throws Exception;

	public LogStatusGroup update(LogStatusGroup example, String userLogin) throws Exception;

	public List<LogStatusGroup> findAll() throws Exception;
	
	public LogStatusGroup find(Long id) throws Exception;
	
	public List<LogStatusGroup> find(LogStatusGroup example) throws Exception;

	public List<LogStatusGroup> findByHql(String hql, Object...vals) throws Exception;

	public List<LogStatusGroup> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<LogStatusGroup> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
