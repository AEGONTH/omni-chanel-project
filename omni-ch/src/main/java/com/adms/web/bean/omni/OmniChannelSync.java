package com.adms.web.bean.omni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;

import com.adms.cs.service.CategoryDataService;
import com.adms.cs.service.CountryService;
import com.adms.cs.service.InceProductService;
import com.adms.cs.service.InsurerService;
import com.adms.cs.service.ListSourceService;
import com.adms.cs.service.OmniMotorHistService;
import com.adms.cs.service.OmniTravelHistService;
import com.adms.cs.service.ParamConfigService;
import com.adms.cs.service.ProductPlanService;
import com.adms.cs.service.ProvinceService;
import com.adms.entity.cs.CategoryData;
import com.adms.entity.cs.Country;
import com.adms.entity.cs.InceProduct;
import com.adms.entity.cs.Insurer;
import com.adms.entity.cs.ListSource;
import com.adms.entity.cs.OmniMotorHist;
import com.adms.entity.cs.OmniTravelHist;
import com.adms.entity.cs.ParamConfig;
import com.adms.entity.cs.ProductPlan;
import com.adms.entity.cs.Province;
import com.adms.web.base.bean.BaseBean;

@ManagedBean
@ApplicationScoped
public class OmniChannelSync extends BaseBean {

	private static final long serialVersionUID = 6768902774313879396L;

	private final String Y = "Y";
	
	private final ParamConfigService paramConfigService = Faces.evaluateExpressionGet("#{paramConfigService}");
	private final CategoryDataService categoryDataService = Faces.evaluateExpressionGet("#{categoryDataService}");
	private final CountryService countryService = Faces.evaluateExpressionGet("#{countryService}");
	private final InceProductService inceProductService = Faces.evaluateExpressionGet("#{inceProductService}");
	private final ProductPlanService productPlanService = Faces.evaluateExpressionGet("#{productPlanService}");
	private final InsurerService insurerService = Faces.evaluateExpressionGet("#{insurerService}");
	private final ListSourceService listSourceService = Faces.evaluateExpressionGet("#{listSourceService}");
	private final ProvinceService provinceService = Faces.evaluateExpressionGet("#{provinceService}");
	
	private final OmniMotorHistService omniMotorHistService = Faces.evaluateExpressionGet("#{omniMotorHistService}");
	private final OmniTravelHistService omniTravelHistService = Faces.evaluateExpressionGet("#{omniTravelHistService}");
	private final String TRACKING_STATUS_OPEN = "OMNI_TKS_OPEN";
	private final String TRACKING_STATUS_IN_PROGRESS = "OMNI_TKS_IN_PROGRESS";
	
	private volatile List<OmniTravelHist> logTravelList;
	private volatile List<OmniMotorHist> logMotorList;
	
	private Map<String, ParamConfig> paramConfigMap;
	private Map<String, CategoryData> categoryMap;
	private Map<String, Country> countryMap;
	private Map<String, InceProduct> inceProductMap;
	private Map<String, ProductPlan> productPlanMap;
	private Map<String, Insurer> insurerMap;
	private Map<String, ListSource> listSourceMap;
	private Map<String, Province> provinceMap;
	
	@PostConstruct
	private void init() {
		initAll();
	}
	
	public void initAll() {
		try {
			initCategoryData();
			initCountry();
			initCountry();
			initInceProduct();
			initInsurer();
			initListSource();
			initParamConfig();
			initProductPlan();
			initProvince();
			
			initOmniMotorData();
			initOmniTravelData();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void initOmniMotorData() throws Throwable {
		logMotorList = findOmniLogMotorHist(TRACKING_STATUS_OPEN, TRACKING_STATUS_IN_PROGRESS);
	}
	
	public void initOmniTravelData() throws Throwable {
		logTravelList = findOmniLogTravelrHist(TRACKING_STATUS_OPEN, TRACKING_STATUS_IN_PROGRESS);
	}
	
	public void initParamConfig() throws Throwable {
		paramConfigMap = paramConfigService.findAll().stream().collect(Collectors.toMap(ParamConfig::getParamKey, m -> m));
	}
	
	public void initCategoryData() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(CategoryData.class);
		criteria.add(Restrictions.eq("visible", Y));
		categoryMap = categoryDataService.findByCriteria(criteria).stream().collect(Collectors.toMap(CategoryData::getCode, m -> m));
	}
	
	public void initCountry() throws Throwable {
		countryMap = countryService.findAll().stream().collect(Collectors.toMap(Country::getCountryCode , m -> m));
	}
	
	public void initInceProduct() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(InceProduct.class);
		criteria.add(Restrictions.eq("visible", Y));
		inceProductMap = inceProductService.findByCriteria(criteria).stream().collect(Collectors.toMap(InceProduct::getProductCode, m -> m));
	}
	
	public void initProductPlan() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductPlan.class);
		criteria.add(Restrictions.eq("visible", Y));
		productPlanMap = productPlanService.findByCriteria(criteria).stream().collect(Collectors.toMap(ProductPlan::getPlanCode, m -> m));
	}
	
	public void initInsurer() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Insurer.class);
		criteria.add(Restrictions.eq("visible", Y));
		insurerMap = insurerService.findByCriteria(criteria).stream().collect(Collectors.toMap(Insurer::getInsurerCode, m -> m));
	}
	
	public void initListSource() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(ListSource.class);
		criteria.add(Restrictions.eq("visible", Y));
		listSourceMap = listSourceService.findByCriteria(criteria).stream().collect(Collectors.toMap(ListSource::getListSourceCode, m -> m));
	}
	
	public void initProvince() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Province.class);
		criteria.add(Restrictions.eq("visible", Y));
		provinceMap = provinceService.findByCriteria(criteria).stream().collect(Collectors.toMap(Province::getProvinceCode, m -> m));
	}
	
	private List<OmniMotorHist> findOmniLogMotorHist(String... status) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniMotorHist.class);
		criteria.add(Restrictions.eq("latest", Y));
		if(status != null) {
			criteria.add(Restrictions.in("status.code", status));
		}
		criteria.addOrder(Order.asc("dueDate"));
		return omniMotorHistService.findByCriteria(criteria);
	}
	
	private List<OmniTravelHist> findOmniLogTravelrHist(String... status) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniTravelHist.class);
		criteria.add(Restrictions.eq("latest", Y));
		if(status != null) {
			criteria.add(Restrictions.in("status.code", status));
		}
		criteria.addOrder(Order.asc("dueDate"));
		return omniTravelHistService.findByCriteria(criteria);
	}

	public List<OmniTravelHist> getLogTravelList() {
		return logTravelList;
	}

	public void setLogTravelList(List<OmniTravelHist> logTravelList) {
		this.logTravelList = logTravelList;
	}

	public List<OmniMotorHist> getLogMotorList() {
		return logMotorList;
	}

	public void setLogMotorList(List<OmniMotorHist> logMotorList) {
		this.logMotorList = logMotorList;
	}

	public Map<String, ParamConfig> getParamConfigMap() {
		return paramConfigMap;
	}

	public void setParamConfigMap(Map<String, ParamConfig> paramConfigMap) {
		this.paramConfigMap = paramConfigMap;
	}

	public Map<String, CategoryData> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<String, CategoryData> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public Map<String, Country> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<String, Country> countryMap) {
		this.countryMap = countryMap;
	}

	public Map<String, InceProduct> getInceProductMap() {
		return inceProductMap;
	}

	public void setInceProductMap(Map<String, InceProduct> inceProductMap) {
		this.inceProductMap = inceProductMap;
	}

	public Map<String, ProductPlan> getProductPlanMap() {
		return productPlanMap;
	}

	public void setProductPlanMap(Map<String, ProductPlan> productPlanMap) {
		this.productPlanMap = productPlanMap;
	}

	public Map<String, Insurer> getInsurerMap() {
		return insurerMap;
	}

	public void setInsurerMap(Map<String, Insurer> insurerMap) {
		this.insurerMap = insurerMap;
	}

	public Map<String, ListSource> getListSourceMap() {
		return listSourceMap;
	}

	public void setListSourceMap(Map<String, ListSource> listSourceMap) {
		this.listSourceMap = listSourceMap;
	}

	public Map<String, Province> getProvinceMap() {
		return provinceMap;
	}

	public void setProvinceMap(Map<String, Province> provinceMap) {
		this.provinceMap = provinceMap;
	}
	
}
