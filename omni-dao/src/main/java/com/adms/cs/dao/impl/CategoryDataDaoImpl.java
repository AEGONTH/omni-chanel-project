package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.CategoryDataDao;
import com.adms.entity.cs.CategoryData;

@Repository("categoryDataDao")
public class CategoryDataDaoImpl extends GenericDaoHibernate<CategoryData, Long> implements CategoryDataDao {
	
	public CategoryDataDaoImpl() {
		super(CategoryData.class);
	}
}
