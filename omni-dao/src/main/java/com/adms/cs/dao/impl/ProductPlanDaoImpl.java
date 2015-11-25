package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.ProductPlanDao;
import com.adms.entity.cs.ProductPlan;

@Repository("productPlanDao")
public class ProductPlanDaoImpl extends GenericDaoHibernate<ProductPlan, Long> implements ProductPlanDao {
	
	public ProductPlanDaoImpl() {
		super(ProductPlan.class);
	}
}
