package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.InsurerDao;
import com.adms.cs.service.InsurerService;
import com.adms.entity.cs.Insurer;

@Service("insurerService")
@Transactional
public class InsurerServiceImpl implements InsurerService {

	@Autowired
	private InsurerDao insurerDao;
	
	public InsurerServiceImpl() {
		
	}

	public void setInsurerDao(InsurerDao insurerDao) {
		this.insurerDao = insurerDao;
	}
	
	@Override
	public List<Insurer> findAll() throws Exception {
		return insurerDao.findAll();
	}

	@Override
	public Insurer add(Insurer example, String userLogin) throws Exception {
		return insurerDao.save(example);
	}
	
	@Override
	public Insurer update(Insurer example, String userLogin) throws Exception {
		return insurerDao.save(example);
	}

	@Override
	public Insurer find(Long id) throws Exception {
		return insurerDao.find(id);
	}
	
	@Override
	public List<Insurer> find(Insurer example) throws Exception {
		return insurerDao.find(example);
	}
	
	@Override
	public List<Insurer> findByHql(String hql, Object...vals) throws Exception {
		return insurerDao.findByHQL(hql, vals);
	}

	@Override
	public List<Insurer> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return insurerDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<Insurer> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return insurerDao.findByCriteria(detachedCriteria);
	}
	
}
