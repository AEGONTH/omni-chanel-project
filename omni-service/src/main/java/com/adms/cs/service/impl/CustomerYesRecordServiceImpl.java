package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CustomerYesRecordDao;
import com.adms.cs.service.CustomerYesRecordService;
import com.adms.entity.cs.CustomerYesRecord;

@Service("customerYesRecordService")
@Transactional
public class CustomerYesRecordServiceImpl implements CustomerYesRecordService {

	@Autowired
	private CustomerYesRecordDao customerYesRecordDao;
	
	public CustomerYesRecordServiceImpl() {
		
	}

	public void setCustomerYesRecordDao(CustomerYesRecordDao customerYesRecordDao) {
		this.customerYesRecordDao = customerYesRecordDao;
	}
	
	@Override
	public List<CustomerYesRecord> findAll() throws Exception {
		return customerYesRecordDao.findAll();
	}

	@Override
	public CustomerYesRecord add(CustomerYesRecord example, String userLogin) throws Exception {
		return customerYesRecordDao.save(example);
	}
	
	@Override
	public CustomerYesRecord update(CustomerYesRecord example, String userLogin) throws Exception {
		return customerYesRecordDao.save(example);
	}
	
	@Override
	public List<CustomerYesRecord> find(CustomerYesRecord example) throws Exception {
		return customerYesRecordDao.find(example);
	}
	
	@Override
	public List<CustomerYesRecord> findByHql(String hql, Object...vals) throws Exception {
		return customerYesRecordDao.findByHQL(hql, vals);
	}

	@Override
	public List<CustomerYesRecord> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return customerYesRecordDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<CustomerYesRecord> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return customerYesRecordDao.findByCriteria(detachedCriteria);
	}
	
}
