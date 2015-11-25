package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CustomerDao;
import com.adms.cs.service.CustomerService;
import com.adms.entity.cs.Customer;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public List<Customer> findAll() throws Exception {
		return customerDao.findAll();
	}

	@Override
	public Customer add(Customer example, String userLogin) throws Exception {
		return customerDao.save(example);
	}
	
	@Override
	public Customer update(Customer example, String userLogin) throws Exception {
		return customerDao.save(example);
	}

	@Override
	public Customer find(Long id) throws Exception {
		return customerDao.find(id);
	}
	
	@Override
	public List<Customer> find(Customer example) throws Exception {
		return customerDao.find(example);
	}
	
	@Override
	public List<Customer> findByHql(String hql, Object...vals) throws Exception {
		return customerDao.findByHQL(hql, vals);
	}

	@Override
	public List<Customer> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return customerDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<Customer> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return customerDao.findByCriteria(detachedCriteria);
	}
	
}
