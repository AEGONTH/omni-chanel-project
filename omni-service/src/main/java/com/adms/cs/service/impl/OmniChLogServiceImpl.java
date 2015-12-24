package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.OmniChLogDao;
import com.adms.cs.service.OmniChLogService;
import com.adms.entity.cs.OmniChLog;

@Service("omniChLogService")
@Transactional
public class OmniChLogServiceImpl implements OmniChLogService {

	@Autowired
	private OmniChLogDao omniChLogDao;
	
	public OmniChLogServiceImpl() {
		
	}

	public void setOmniChLogDao(OmniChLogDao omniChLogDao) {
		this.omniChLogDao = omniChLogDao;
	}
	
	@Override
	public List<OmniChLog> findAll() throws Exception {
		return omniChLogDao.findAll();
	}

	@Override
	public OmniChLog add(OmniChLog example, String userLogin) throws Exception {
		return omniChLogDao.save(example);
	}
	
	@Override
	public OmniChLog update(OmniChLog example, String userLogin) throws Exception {
		return omniChLogDao.save(example);
	}

	@Override
	public OmniChLog find(Long id) throws Exception {
		return omniChLogDao.find(id);
	}
	
	@Override
	public List<OmniChLog> find(OmniChLog example) throws Exception {
		return omniChLogDao.find(example);
	}
	
	@Override
	public List<OmniChLog> findByHql(String hql, Object...vals) throws Exception {
		return omniChLogDao.findByHQL(hql, vals);
	}

	@Override
	public List<OmniChLog> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return omniChLogDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<OmniChLog> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return omniChLogDao.findByCriteria(detachedCriteria);
	}
	
}
