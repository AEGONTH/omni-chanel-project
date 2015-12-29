package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.OmniTravelHistDao;
import com.adms.cs.service.OmniTravelHistService;
import com.adms.entity.cs.OmniTravelHist;

@Service("omniTravelHistService")
@Transactional
public class OmniTravelHistServiceImpl implements OmniTravelHistService {

	@Autowired
	private OmniTravelHistDao omniTravelHistDao;
	
	public OmniTravelHistServiceImpl() {
		
	}

	public void setOmniTravelHistDao(OmniTravelHistDao omniTravelHistDao) {
		this.omniTravelHistDao = omniTravelHistDao;
	}
	
	@Override
	public List<OmniTravelHist> findAll() throws Exception {
		return omniTravelHistDao.findAll();
	}

	@Override
	public OmniTravelHist add(OmniTravelHist example, String userLogin) throws Exception {
		return omniTravelHistDao.save(example);
	}
	
	@Override
	public OmniTravelHist update(OmniTravelHist example, String userLogin) throws Exception {
		return omniTravelHistDao.save(example);
	}

	@Override
	public OmniTravelHist find(Long id) throws Exception {
		return omniTravelHistDao.find(id);
	}
	
	@Override
	public List<OmniTravelHist> find(OmniTravelHist example) throws Exception {
		return omniTravelHistDao.find(example);
	}
	
	@Override
	public List<OmniTravelHist> findByHql(String hql, Object...vals) throws Exception {
		return omniTravelHistDao.findByHQL(hql, vals);
	}

	@Override
	public List<OmniTravelHist> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return omniTravelHistDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<OmniTravelHist> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return omniTravelHistDao.findByCriteria(detachedCriteria);
	}
	
	@Override
	public List<OmniTravelHist> findByCriteria(DetachedCriteria detachedCriteria, Pageable pageable) throws Exception {
		return omniTravelHistDao.findByCriteria(detachedCriteria, pageable);
	}
	
}
