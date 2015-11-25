package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.InceProductDao;
import com.adms.cs.service.InceProductService;
import com.adms.entity.cs.InceProduct;

@Service("inceProductService")
@Transactional
public class InceProductServiceImpl implements InceProductService {

	@Autowired
	private InceProductDao inceProductDao;
	
	public InceProductServiceImpl() {
		
	}

	public void setInceProductDao(InceProductDao inceProductDao) {
		this.inceProductDao = inceProductDao;
	}
	
	@Override
	public List<InceProduct> findAll() throws Exception {
		return inceProductDao.findAll();
	}

	@Override
	public InceProduct add(InceProduct example, String userLogin) throws Exception {
		return inceProductDao.save(example);
	}
	
	@Override
	public InceProduct update(InceProduct example, String userLogin) throws Exception {
		return inceProductDao.save(example);
	}

	@Override
	public InceProduct find(Long id) throws Exception {
		return inceProductDao.find(id);
	}
	
	@Override
	public List<InceProduct> find(InceProduct example) throws Exception {
		return inceProductDao.find(example);
	}
	
	@Override
	public List<InceProduct> findByHql(String hql, Object...vals) throws Exception {
		return inceProductDao.findByHQL(hql, vals);
	}

	@Override
	public List<InceProduct> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return inceProductDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<InceProduct> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return inceProductDao.findByCriteria(detachedCriteria);
	}
	
}
