package com.adms.web.base.bean;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import com.adms.util.PropertyConfig;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{globalMsg}")
	private ResourceBundle globalMsg;

	private TimeZone timeZone;
	private int defaultRowPerPage;
	private String defaultPaginatorTemplate;
	private String currencyCode;
	private String currencySymbol;
	
	private String currentVersrion;

	protected final String DEFAULT_SYSTEM_LOG_NAME = "CS System";

	public BaseBean() {
		try {
			timeZone = TimeZone.getTimeZone(PropertyConfig.getInstance().getValue("cfg.timezone.asia.bangkok"));
			currentVersrion = PropertyConfig.getInstance().getValue("cfg.current.version");
			defaultRowPerPage = Integer.valueOf(PropertyConfig.getInstance().getValue("cfg.default.table.max.row"));
			defaultPaginatorTemplate = PropertyConfig.getInstance().getValue("cfg.default.table.paginator.template");
			currencySymbol = PropertyConfig.getInstance().getValue("cfg.currency.symbol");
			currencyCode = PropertyConfig.getInstance().getValue("cfg.currency.code");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	private void init() {
//		String renderKitId = FacesContext.getCurrentInstance().getViewRoot().getRenderKitId();
//		System.out.println("Current RenderKitID: " + renderKitId);
	}

	public String getGlobalMsgValue(String key) {
		return globalMsg.getString(key);
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public void setGlobalMsg(ResourceBundle globalMsg) {
		this.globalMsg = globalMsg;
	}

	public String getCurrentVersrion() {
		return currentVersrion;
	}

	public void setCurrentVersrion(String currentVersrion) {
		this.currentVersrion = currentVersrion;
	}

	public int getDefaultRowPerPage() {
		return defaultRowPerPage;
	}

	public void setDefaultRowPerPage(int defaultRowPerPage) {
		this.defaultRowPerPage = defaultRowPerPage;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getDefaultPaginatorTemplate() {
		return defaultPaginatorTemplate;
	}

	public void setDefaultPaginatorTemplate(String defaultPaginatorTemplate) {
		this.defaultPaginatorTemplate = defaultPaginatorTemplate;
	}
	
}
