package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.OmniLogMotorHistDao;
import com.adms.cs.service.OmniLogMotorHistService;
import com.adms.entity.cs.OmniLogMotorHist;

@Service("omniLogMotorHistService")
@Transactional
public class OmniLogMotorHistServiceImpl implements OmniLogMotorHistService {

	@Autowired
	private OmniLogMotorHistDao omniLogMotorHistDao;
	
	public OmniLogMotorHistServiceImpl() {
		
	}

	public void setOmniLogMotorHistDao(OmniLogMotorHistDao omniLogMotorHistDao) {
		this.omniLogMotorHistDao = omniLogMotorHistDao;
	}
	
	@Override
	public List<OmniLogMotorHist> findAll() throws Exception {
		return omniLogMotorHistDao.findAll();
	}

	@Override
	public OmniLogMotorHist add(OmniLogMotorHist example, String userLogin) throws Exception {
		return omniLogMotorHistDao.save(example);
	}
	
	@Override
	public OmniLogMotorHist update(OmniLogMotorHist example, String userLogin) throws Exception {
		return omniLogMotorHistDao.save(example);
	}

	@Override
	public OmniLogMotorHist find(Long id) throws Exception {
		return omniLogMotorHistDao.find(id);
	}
	
	@Override
	public List<OmniLogMotorHist> find(OmniLogMotorHist example) throws Exception {
		return omniLogMotorHistDao.find(example);
	}
	
	@Override
	public List<OmniLogMotorHist> findByHql(String hql, Object...vals) throws Exception {
		return omniLogMotorHistDao.findByHQL(hql, vals);
	}

	@Override
	public List<OmniLogMotorHist> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return omniLogMotorHistDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<OmniLogMotorHist> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return omniLogMotorHistDao.findByCriteria(detachedCriteria);
	}
	
}
