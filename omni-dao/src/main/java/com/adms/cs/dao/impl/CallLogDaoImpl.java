package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.CallLogDao;
import com.adms.entity.cs.CallLog;

@Repository("callLogDao")
public class CallLogDaoImpl extends GenericDaoHibernate<CallLog, Long> implements CallLogDao {
	
	public CallLogDaoImpl() {
		super(CallLog.class);
	}
}
