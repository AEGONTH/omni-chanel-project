package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.OmniMotorHistDao;
import com.adms.cs.service.OmniMotorHistService;
import com.adms.entity.cs.OmniMotorHist;

@Service("omniMotorHistService")
@Transactional
public class OmniMotorHistServiceImpl implements OmniMotorHistService {

	@Autowired
	private OmniMotorHistDao omniMotorHistDao;
	
	public OmniMotorHistServiceImpl() {
		
	}

	public void setOmniMotorHistDao(OmniMotorHistDao omniMotorHistDao) {
		this.omniMotorHistDao = omniMotorHistDao;
	}
	
	@Override
	public List<OmniMotorHist> findAll() throws Exception {
		return omniMotorHistDao.findAll();
	}

	@Override
	public OmniMotorHist add(OmniMotorHist example, String userLogin) throws Exception {
		return omniMotorHistDao.save(example);
	}
	
	@Override
	public OmniMotorHist update(OmniMotorHist example, String userLogin) throws Exception {
		return omniMotorHistDao.save(example);
	}

	@Override
	public OmniMotorHist find(Long id) throws Exception {
		return omniMotorHistDao.find(id);
	}
	
	@Override
	public List<OmniMotorHist> find(OmniMotorHist example) throws Exception {
		return omniMotorHistDao.find(example);
	}
	
	@Override
	public List<OmniMotorHist> findByHql(String hql, Object...vals) throws Exception {
		return omniMotorHistDao.findByHQL(hql, vals);
	}

	@Override
	public List<OmniMotorHist> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return omniMotorHistDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<OmniMotorHist> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return omniMotorHistDao.findByCriteria(detachedCriteria);
	}
	
	@Override
	public List<OmniMotorHist> findByCriteria(DetachedCriteria detachedCriteria, Pageable pageable) throws Exception {
		return omniMotorHistDao.findByCriteria(detachedCriteria, pageable);
	}
	
}
