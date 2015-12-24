package com.adms.web.bean.motor;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.springframework.data.domain.PageRequest;

import com.adms.cs.service.OmniMotorHistService;
import com.adms.entity.cs.OmniMotorHist;
import com.adms.web.base.bean.AbstractSearchBean;
import com.adms.web.base.model.LazyModel;

@Deprecated
@ManagedBean
@SessionScoped
public class MotorModel extends AbstractSearchBean<OmniMotorHist> {

	private static final long serialVersionUID = -7744057931986505271L;
	private LazyDataModel<OmniMotorHist> data;
	
	private OmniMotorHistService omniLogMotorHistService = Faces.evaluateExpressionGet("#{omniLogMotorHistService}");
	
	public void initialData() {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniMotorHist.class);
		criteria.add(Restrictions.sqlRestriction("this_.ID in (select MAX(d.ID) from OMNI_LOG_MOTOR_HIST d GROUP BY d.LOG_MOTOR_ID)"));
		criteria.add(Restrictions.in("status.code", new String[]{"OMNI_TKS_OPEN", "OMNI_TKS_IN_PROGRESS"}));
		criteria.addOrder(Order.asc("dueDate"));
		
		data = new LazyModel<>(criteria, this);
	}
	
	@Override
	public List<OmniMotorHist> search(OmniMotorHist object, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OmniMotorHist> search(DetachedCriteria criteria, PageRequest pageRequest) {
		List<OmniMotorHist> list = null;
		try {
			list = omniLogMotorHistService.findByCriteria(criteria, pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public OmniMotorHist getRow(List<OmniMotorHist> data, String rowKey) {
		for(OmniMotorHist o : data)
			if(o.getId().toString().equals(rowKey)) return o;
		return null;
	}

	@Override
	public int getTotalCount(OmniMotorHist object) {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public int getTotalCount(DetachedCriteria detachedCriteria) {
		try {
			List<OmniMotorHist> list = omniLogMotorHistService.findByCriteria(detachedCriteria);
			list.forEach(p -> System.out.println(p.getId()));
			return list.size();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public LazyDataModel<OmniMotorHist> getData() {
		return data;
	}

	public void setData(LazyDataModel<OmniMotorHist> data) {
		this.data = data;
	}

}
