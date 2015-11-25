package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.InceProductDao;
import com.adms.entity.cs.InceProduct;

@Repository("inceProductDao")
public class InceProductDaoImpl extends GenericDaoHibernate<InceProduct, Long> implements InceProductDao {
	
	public InceProductDaoImpl() {
		super(InceProduct.class);
	}
}
