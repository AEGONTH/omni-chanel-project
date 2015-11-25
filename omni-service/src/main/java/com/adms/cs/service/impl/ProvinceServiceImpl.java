package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.ProvinceDao;
import com.adms.cs.service.ProvinceService;
import com.adms.entity.cs.Province;

@Service("provinceService")
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDao provinceDao;
	
	public ProvinceServiceImpl() {
		
	}

	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	@Override
	public List<Province> findAll() throws Exception {
		return provinceDao.findAll();
	}

	@Override
	public Province add(Province example, String userLogin) throws Exception {
		return provinceDao.save(example);
	}
	
	@Override
	public Province update(Province example, String userLogin) throws Exception {
		return provinceDao.save(example);
	}

	@Override
	public Province find(Long id) throws Exception {
		return provinceDao.find(id);
	}
	
	@Override
	public List<Province> find(Province example) throws Exception {
		return provinceDao.find(example);
	}
	
	@Override
	public List<Province> findByHql(String hql, Object...vals) throws Exception {
		return provinceDao.findByHQL(hql, vals);
	}

	@Override
	public List<Province> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return provinceDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<Province> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return provinceDao.findByCriteria(detachedCriteria);
	}
	
}
