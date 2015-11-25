package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.ParamConfigDao;
import com.adms.cs.service.ParamConfigService;
import com.adms.entity.cs.ParamConfig;

@Service("paramConfigService")
@Transactional
public class ParamConfigServiceImpl implements ParamConfigService {

	@Autowired
	private ParamConfigDao paramConfigDao;
	
	public ParamConfigServiceImpl() {
		
	}

	public void setParamConfigDao(ParamConfigDao paramConfigDao) {
		this.paramConfigDao = paramConfigDao;
	}
	
	@Override
	public List<ParamConfig> findAll() throws Exception {
		return paramConfigDao.findAll();
	}

	@Override
	public ParamConfig add(ParamConfig example, String userLogin) throws Exception {
		return paramConfigDao.save(example);
	}
	
	@Override
	public ParamConfig update(ParamConfig example, String userLogin) throws Exception {
		return paramConfigDao.save(example);
	}

	@Override
	public ParamConfig find(Long id) throws Exception {
		return paramConfigDao.find(id);
	}
	
	@Override
	public List<ParamConfig> find(ParamConfig example) throws Exception {
		return paramConfigDao.find(example);
	}
	
	@Override
	public List<ParamConfig> findByHql(String hql, Object...vals) throws Exception {
		return paramConfigDao.findByHQL(hql, vals);
	}

	@Override
	public List<ParamConfig> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return paramConfigDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<ParamConfig> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return paramConfigDao.findByCriteria(detachedCriteria);
	}
	
}
