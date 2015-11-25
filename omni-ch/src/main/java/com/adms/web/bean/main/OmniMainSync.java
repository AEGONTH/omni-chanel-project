package com.adms.web.bean.main;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.adms.entity.cs.OmniLogMotor;
import com.adms.web.base.bean.BaseBean;

@ManagedBean
@ApplicationScoped
public class OmniMainSync extends BaseBean {

	private static final long serialVersionUID = 1554453898132979388L;

	private volatile List<OmniLogMotor> datas;
}
