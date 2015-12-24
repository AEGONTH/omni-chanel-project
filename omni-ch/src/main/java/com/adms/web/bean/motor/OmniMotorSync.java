package com.adms.web.bean.motor;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;

import com.adms.cs.service.OmniMotorHistService;
import com.adms.entity.cs.OmniMotorHist;
import com.adms.web.base.bean.BaseBean;

@ManagedBean
@ApplicationScoped
public class OmniMotorSync extends BaseBean {

	private static final long serialVersionUID = 1817046525231279681L;
	
	private final OmniMotorHistService omniMotorHistService = Faces.evaluateExpressionGet("#{omniMotorHistService}");
	private final String TRACKING_STATUS_OPEN = "OMNI_TKS_OPEN";
	private final String TRACKING_STATUS_IN_PROGRESS = "OMNI_TKS_IN_PROGRESS";
	
	private volatile List<OmniMotorHist> logMotorList;
	
	@PostConstruct
	private void init() {
		try {
			initialData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialData() throws Exception {
		logMotorList = findOmniLogMotorHist(TRACKING_STATUS_OPEN, TRACKING_STATUS_IN_PROGRESS);
	}

	public void refreshData() throws Throwable {
		try {
			initialData();
		} catch(Exception e) {
			throw e;
		}
	}
	
	private List<OmniMotorHist> findOmniLogMotorHist(String... status) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniMotorHist.class);
		criteria.add(Restrictions.eq("latest", "Y"));
//		criteria.add(Restrictions.sqlRestriction("this_.ID in (select MAX(d.ID) from OMNI_LOG_MOTOR_HIST d GROUP BY d.LOG_MOTOR_ID)"));
		if(status != null) {
			criteria.add(Restrictions.in("status.code", status));
		}
		criteria.addOrder(Order.asc("dueDate"));
		return omniMotorHistService.findByCriteria(criteria);
	}

	public List<OmniMotorHist> getLogMotorList() {
		return logMotorList;
	}

	public void setLogMotorList(List<OmniMotorHist> logMotorList) {
		this.logMotorList = logMotorList;
	}
}
