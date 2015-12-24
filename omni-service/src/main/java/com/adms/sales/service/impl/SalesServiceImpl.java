package com.adms.sales.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.entity.sale.Sales;
import com.adms.sales.dao.SalesDao;
import com.adms.sales.service.SalesService;

@Service("salesService")
@Transactional
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesDao salesDao;
	
	public SalesServiceImpl() {
		
	}

	public void setSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}
	
	@Override
	public List<Sales> findAll() throws Exception {
		return salesDao.findAll();
	}

	@Override
	public Sales add(Sales example, String userLogin) throws Exception {
		return salesDao.save(example);
	}
	
	@Override
	public Sales update(Sales example, String userLogin) throws Exception {
		return salesDao.save(example);
	}
	
	@Override
	public List<Sales> find(Sales example) throws Exception {
		return salesDao.find(example);
	}
	
	@Override
	public List<Sales> findByHql(String hql, Object...vals) throws Exception {
		return salesDao.findByHQL(hql, vals);
	}

	@Override
	public List<Sales> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return salesDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<Sales> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return salesDao.findByCriteria(detachedCriteria);
	}
	
}
