package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.LogDetailCategoryDao;
import com.adms.entity.cs.LogDetailCategory;

@Repository("callLogDetailCategoryDao")
public class LogDetailCategoryDaoImpl extends GenericDaoHibernate<LogDetailCategory, Long> implements LogDetailCategoryDao {
	
	public LogDetailCategoryDaoImpl() {
		super(LogDetailCategory.class);
	}
}
