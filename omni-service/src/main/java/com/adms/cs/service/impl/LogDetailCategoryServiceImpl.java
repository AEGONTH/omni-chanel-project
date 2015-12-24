package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.LogDetailCategoryDao;
import com.adms.cs.service.LogDetailCategoryService;
import com.adms.entity.cs.LogDetailCategory;

@Service("logDetailCategoryService")
@Transactional
public class LogDetailCategoryServiceImpl implements LogDetailCategoryService {

	@Autowired
	private LogDetailCategoryDao callLogDetailCategoryDao;
	
	public LogDetailCategoryServiceImpl() {
		
	}

	public void setCallLogDetailCategoryDao(LogDetailCategoryDao callLogDetailCategoryDao) {
		this.callLogDetailCategoryDao = callLogDetailCategoryDao;
	}
	
	@Override
	public List<LogDetailCategory> findAll() throws Exception {
		return callLogDetailCategoryDao.findAll();
	}

	@Override
	public LogDetailCategory add(LogDetailCategory example, String userLogin) throws Exception {
		return callLogDetailCategoryDao.save(example);
	}
	
	@Override
	public LogDetailCategory update(LogDetailCategory example, String userLogin) throws Exception {
		return callLogDetailCategoryDao.save(example);
	}

	@Override
	public LogDetailCategory find(Long id) throws Exception {
		return callLogDetailCategoryDao.find(id);
	}
	
	@Override
	public List<LogDetailCategory> find(LogDetailCategory example) throws Exception {
		return callLogDetailCategoryDao.find(example);
	}
	
	@Override
	public List<LogDetailCategory> findByHql(String hql, Object...vals) throws Exception {
		return callLogDetailCategoryDao.findByHQL(hql, vals);
	}

	@Override
	public List<LogDetailCategory> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return callLogDetailCategoryDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<LogDetailCategory> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return callLogDetailCategoryDao.findByCriteria(detachedCriteria);
	}
	
}
