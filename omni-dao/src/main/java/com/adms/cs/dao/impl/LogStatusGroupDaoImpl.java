package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.LogStatusGroupDao;
import com.adms.entity.cs.LogStatusGroup;

@Repository("logStatusGroupDao")
public class LogStatusGroupDaoImpl extends GenericDaoHibernate<LogStatusGroup, Long> implements LogStatusGroupDao {
	
	public LogStatusGroupDaoImpl() {
		super(LogStatusGroup.class);
	}
}
