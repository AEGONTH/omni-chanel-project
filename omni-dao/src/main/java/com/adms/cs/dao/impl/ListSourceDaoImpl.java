package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.ListSourceDao;
import com.adms.entity.cs.ListSource;

@Repository("listSourceDao")
public class ListSourceDaoImpl extends GenericDaoHibernate<ListSource, Long> implements ListSourceDao {
	
	public ListSourceDaoImpl() {
		super(ListSource.class);
	}
}
