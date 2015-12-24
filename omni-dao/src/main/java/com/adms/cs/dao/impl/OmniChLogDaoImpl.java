package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.OmniChLogDao;
import com.adms.entity.cs.OmniChLog;

@Repository("omniChLogDao")
public class OmniChLogDaoImpl extends GenericDaoHibernate<OmniChLog, Long> implements OmniChLogDao {
	
	public OmniChLogDaoImpl() {
		super(OmniChLog.class);
	}
}
