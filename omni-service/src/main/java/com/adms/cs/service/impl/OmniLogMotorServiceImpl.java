package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.OmniLogMotorDao;
import com.adms.cs.service.OmniLogMotorService;
import com.adms.entity.cs.OmniLogMotor;

@Service("omniLogMotorService")
@Transactional
public class OmniLogMotorServiceImpl implements OmniLogMotorService {

	@Autowired
	private OmniLogMotorDao omniLogMotorDao;
	
	public OmniLogMotorServiceImpl() {
		
	}

	public void setOmniLogMotorDao(OmniLogMotorDao omniLogMotorDao) {
		this.omniLogMotorDao = omniLogMotorDao;
	}
	
	@Override
	public List<OmniLogMotor> findAll() throws Exception {
		return omniLogMotorDao.findAll();
	}

	@Override
	public OmniLogMotor add(OmniLogMotor example, String userLogin) throws Exception {
		return omniLogMotorDao.save(example);
	}
	
	@Override
	public OmniLogMotor update(OmniLogMotor example, String userLogin) throws Exception {
		return omniLogMotorDao.save(example);
	}

	@Override
	public OmniLogMotor find(Long id) throws Exception {
		return omniLogMotorDao.find(id);
	}
	
	@Override
	public List<OmniLogMotor> find(OmniLogMotor example) throws Exception {
		return omniLogMotorDao.find(example);
	}
	
	@Override
	public List<OmniLogMotor> findByHql(String hql, Object...vals) throws Exception {
		return omniLogMotorDao.findByHQL(hql, vals);
	}

	@Override
	public List<OmniLogMotor> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return omniLogMotorDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<OmniLogMotor> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return omniLogMotorDao.findByCriteria(detachedCriteria);
	}
	
}
