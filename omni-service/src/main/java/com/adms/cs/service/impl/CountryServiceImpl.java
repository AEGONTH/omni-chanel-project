package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CountryDao;
import com.adms.cs.service.CountryService;
import com.adms.entity.cs.Country;

@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao customerDao;
	
	public CountryServiceImpl() {
		
	}

	public void setCountryDao(CountryDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public List<Country> findAll() throws Exception {
		return customerDao.findAll();
	}

	@Override
	public Country add(Country example, String userLogin) throws Exception {
		return customerDao.save(example);
	}
	
	@Override
	public Country update(Country example, String userLogin) throws Exception {
		return customerDao.save(example);
	}

	@Override
	public Country find(Long id) throws Exception {
		return customerDao.find(id);
	}
	
	@Override
	public List<Country> find(Country example) throws Exception {
		return customerDao.find(example);
	}
	
	@Override
	public List<Country> findByHql(String hql, Object...vals) throws Exception {
		return customerDao.findByHQL(hql, vals);
	}

	@Override
	public List<Country> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return customerDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<Country> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return customerDao.findByCriteria(detachedCriteria);
	}
	
}
