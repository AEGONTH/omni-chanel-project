package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.OmniMotorHistDao;
import com.adms.entity.cs.OmniMotorHist;

@Repository("omniMotorHistDao")
public class OmniMotorHistDaoImpl extends GenericDaoHibernate<OmniMotorHist, Long> implements OmniMotorHistDao {
	
	public OmniMotorHistDaoImpl() {
		super(OmniMotorHist.class);
	}
}
