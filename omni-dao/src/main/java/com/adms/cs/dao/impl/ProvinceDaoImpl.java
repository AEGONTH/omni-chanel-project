package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.ProvinceDao;
import com.adms.entity.cs.Province;

@Repository("provinceDao")
public class ProvinceDaoImpl extends GenericDaoHibernate<Province, Long> implements ProvinceDao {
	
	public ProvinceDaoImpl() {
		super(Province.class);
	}
}
