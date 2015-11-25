package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.InsurerDao;
import com.adms.entity.cs.Insurer;

@Repository("insurerDao")
public class InsurerDaoImpl extends GenericDaoHibernate<Insurer, Long> implements InsurerDao {
	
	public InsurerDaoImpl() {
		super(Insurer.class);
	}
}
