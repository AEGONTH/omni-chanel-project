package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CallLogDao;
import com.adms.cs.service.CallLogService;
import com.adms.entity.cs.CallLog;

@Service("callLogService")
@Transactional
public class CallLogServiceImpl implements CallLogService {

	@Autowired
	private CallLogDao callLogDao;
	
	public CallLogServiceImpl() {
		
	}

	public void setCallLogDao(CallLogDao callLogDao) {
		this.callLogDao = callLogDao;
	}
	
	@Override
	public List<CallLog> findAll() throws Exception {
		return callLogDao.findAll();
	}

	@Override
	public CallLog add(CallLog example, String userLogin) throws Exception {
		return callLogDao.save(example);
	}
	
	@Override
	public CallLog update(CallLog example, String userLogin) throws Exception {
		return callLogDao.save(example);
	}

	@Override
	public CallLog find(Long id) throws Exception {
		return callLogDao.find(id);
	}
	
	@Override
	public List<CallLog> find(CallLog example) throws Exception {
		return callLogDao.find(example);
	}
	
	@Override
	public List<CallLog> findByHql(String hql, Object...vals) throws Exception {
		return callLogDao.findByHQL(hql, vals);
	}

	@Override
	public List<CallLog> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return callLogDao.findByNamedQuery(namedQuery, vals);
	}
	
	@Override
	public List<CallLog> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return callLogDao.findByCriteria(detachedCriteria);
	}
	
}
