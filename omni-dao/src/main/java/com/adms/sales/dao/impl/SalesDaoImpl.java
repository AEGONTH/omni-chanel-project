package com.adms.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.entity.sale.Sales;
import com.adms.sales.dao.SalesDao;

@Repository("salesDao")
public class SalesDaoImpl extends GenericDaoHibernate<Sales, Long> implements SalesDao {

	public SalesDaoImpl() {
		super(Sales.class);
	}

}
