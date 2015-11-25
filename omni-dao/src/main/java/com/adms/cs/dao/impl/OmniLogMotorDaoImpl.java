package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.OmniLogMotorDao;
import com.adms.entity.cs.OmniLogMotor;

@Repository("omniLogMotorDao")
public class OmniLogMotorDaoImpl extends GenericDaoHibernate<OmniLogMotor, Long> implements OmniLogMotorDao {
	
	public OmniLogMotorDaoImpl() {
		super(OmniLogMotor.class);
	}
}
