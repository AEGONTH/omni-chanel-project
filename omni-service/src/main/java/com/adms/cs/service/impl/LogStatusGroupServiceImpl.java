package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.LogStatusGroupDao;
import com.adms.cs.service.LogStatusGroupService;
import com.adms.entity.cs.LogStatusGroup;

@Service("logStatusGroupService")
@Transactional
public class LogStatusGroupServiceImpl implements LogStatusGroupService {

	@Autowired
	private LogStatusGroupDao callLogGroupStatusDao;
	
	public LogStatusGroupServiceImpl() {
		
	}

	public void setCallLogGroupStatusDao(LogStatusGroupDao callLogGroupStatusDao) {
		this.callLogGroupStatusDao = callLogGroupStatusDao;
	}
	
	@Override
	public List<LogStatusGroup> findAll() throws Exception {
		return callLogGroupStatusDao.findAll();
	}

	@Override
	public LogStatusGroup add(LogStatusGroup example, String userLogin) throws Exception {
		return callLogGroupStatusDao.save(example);
	}
	
	@Override
	public LogStatusGroup update(LogStatusGroup example, String userLogin) throws Exception {
		return callLogGroupStatusDao.save(example);
	}

	@Override
	public LogStatusGroup find(Long id) throws Exception {
		return callLogGroupStatusDao.find(id);
	}
	
	@Override
	public List<LogStatusGroup> find(LogStatusGroup example) throws Exception {
		return callLogGroupStatusDao.find(example);
	}
	
	@Override
	public List<LogStatusGroup> findByHql(String hql, Object...vals) throws Exception {
		return callLogGroupStatusDao.findByHQL(hql, vals);
	}

	@Override
	public List<LogStatusGroup> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return callLogGroupStatusDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<LogStatusGroup> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return callLogGroupStatusDao.findByCriteria(detachedCriteria);
	}
	
}
