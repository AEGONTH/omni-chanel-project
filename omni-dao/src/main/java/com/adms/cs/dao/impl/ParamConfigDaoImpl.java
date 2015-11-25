package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.ParamConfigDao;
import com.adms.entity.cs.ParamConfig;

@Repository("paramConfigDao")
public class ParamConfigDaoImpl extends GenericDaoHibernate<ParamConfig, Long> implements ParamConfigDao {
	
	public ParamConfigDaoImpl() {
		super(ParamConfig.class);
	}
}
