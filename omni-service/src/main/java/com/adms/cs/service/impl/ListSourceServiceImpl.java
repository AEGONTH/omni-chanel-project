package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.ListSourceDao;
import com.adms.cs.service.ListSourceService;
import com.adms.entity.cs.ListSource;

@Service("listSourceService")
@Transactional
public class ListSourceServiceImpl implements ListSourceService {

	@Autowired
	private ListSourceDao listSourceDao;
	
	public ListSourceServiceImpl() {
		
	}

	public void setListSourceDao(ListSourceDao listSourceDao) {
		this.listSourceDao = listSourceDao;
	}
	
	@Override
	public List<ListSource> findAll() throws Exception {
		return listSourceDao.findAll();
	}

	@Override
	public ListSource add(ListSource example, String userLogin) throws Exception {
		return listSourceDao.save(example);
	}
	
	@Override
	public ListSource update(ListSource example, String userLogin) throws Exception {
		return listSourceDao.save(example);
	}

	@Override
	public ListSource find(Long id) throws Exception {
		return listSourceDao.find(id);
	}
	
	@Override
	public List<ListSource> find(ListSource example) throws Exception {
		return listSourceDao.find(example);
	}
	
	@Override
	public List<ListSource> findByHql(String hql, Object...vals) throws Exception {
		return listSourceDao.findByHQL(hql, vals);
	}

	@Override
	public List<ListSource> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return listSourceDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<ListSource> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return listSourceDao.findByCriteria(detachedCriteria);
	}
	
}
