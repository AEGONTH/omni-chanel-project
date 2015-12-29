package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.CountryDao;
import com.adms.entity.cs.Country;

@Repository("countryDao")
public class CountryDaoImpl extends GenericDaoHibernate<Country, Long> implements CountryDao {
	
	public CountryDaoImpl() {
		super(Country.class);
	}
}
