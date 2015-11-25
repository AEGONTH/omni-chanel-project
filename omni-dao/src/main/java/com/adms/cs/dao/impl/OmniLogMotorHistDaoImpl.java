package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.OmniLogMotorHistDao;
import com.adms.entity.cs.OmniLogMotorHist;

@Repository("omniLogMotorHistDao")
public class OmniLogMotorHistDaoImpl extends GenericDaoHibernate<OmniLogMotorHist, Long> implements OmniLogMotorHistDao {
	
	public OmniLogMotorHistDaoImpl() {
		super(OmniLogMotorHist.class);
	}
}
