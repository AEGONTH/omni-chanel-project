package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CategoryDataDao;
import com.adms.cs.service.CategoryDataService;
import com.adms.entity.cs.CategoryData;

@Service("categoryDataService")
@Transactional
public class CategoryDataServiceImpl implements CategoryDataService {

	@Autowired
	private CategoryDataDao categoryDataDao;
	
	public CategoryDataServiceImpl() {
		
	}

	public void setCategoryDataDao(CategoryDataDao categoryDataDao) {
		this.categoryDataDao = categoryDataDao;
	}
	
	@Override
	public List<CategoryData> findAll() throws Exception {
		return categoryDataDao.findAll();
	}

	@Override
	public CategoryData add(CategoryData example, String userLogin) throws Exception {
		return categoryDataDao.save(example);
	}
	
	@Override
	public CategoryData update(CategoryData example, String userLogin) throws Exception {
		return categoryDataDao.save(example);
	}

	@Override
	public CategoryData find(Long id) throws Exception {
		return categoryDataDao.find(id);
	}
	
	@Override
	public List<CategoryData> find(CategoryData example) throws Exception {
		return categoryDataDao.find(example);
	}
	
	@Override
	public List<CategoryData> findByHql(String hql, Object...vals) throws Exception {
		return categoryDataDao.findByHQL(hql, vals);
	}

	@Override
	public List<CategoryData> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return categoryDataDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<CategoryData> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return categoryDataDao.findByCriteria(detachedCriteria);
	}
	
}
