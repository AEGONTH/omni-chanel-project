package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.ProductPlanDao;
import com.adms.cs.service.ProductPlanService;
import com.adms.entity.cs.ProductPlan;

@Service("productPlanService")
@Transactional
public class ProductPlanServiceImpl implements ProductPlanService {

	@Autowired
	private ProductPlanDao productPlanDao;
	
	public ProductPlanServiceImpl() {
		
	}

	public void setProductPlanDao(ProductPlanDao productPlanDao) {
		this.productPlanDao = productPlanDao;
	}
	
	@Override
	public List<ProductPlan> findAll() throws Exception {
		return productPlanDao.findAll();
	}

	@Override
	public ProductPlan add(ProductPlan example, String userLogin) throws Exception {
		return productPlanDao.save(example);
	}
	
	@Override
	public ProductPlan update(ProductPlan example, String userLogin) throws Exception {
		return productPlanDao.save(example);
	}

	@Override
	public ProductPlan find(Long id) throws Exception {
		return productPlanDao.find(id);
	}
	
	@Override
	public List<ProductPlan> find(ProductPlan example) throws Exception {
		return productPlanDao.find(example);
	}
	
	@Override
	public List<ProductPlan> findByHql(String hql, Object...vals) throws Exception {
		return productPlanDao.findByHQL(hql, vals);
	}

	@Override
	public List<ProductPlan> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return productPlanDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<ProductPlan> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return productPlanDao.findByCriteria(detachedCriteria);
	}
	
}
