package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.CustomerYesRecordDao;
import com.adms.entity.cs.CustomerYesRecord;

@Repository("customerYesRecordDao")
public class CustomerYesRecordDaoImpl extends GenericDaoHibernate<CustomerYesRecord, Long> implements CustomerYesRecordDao {
	
	public CustomerYesRecordDaoImpl() {
		super(CustomerYesRecord.class);
	}
}
