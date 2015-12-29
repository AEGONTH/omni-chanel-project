package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.OmniTravelHistDao;
import com.adms.entity.cs.OmniTravelHist;

@Repository("omniTravelHistDao")
public class OmniTravelHistDaoImpl extends GenericDaoHibernate<OmniTravelHist, Long> implements OmniTravelHistDao {
	
	public OmniTravelHistDaoImpl() {
		super(OmniTravelHist.class);
	}
}
