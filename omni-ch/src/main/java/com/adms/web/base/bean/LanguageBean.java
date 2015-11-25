package com.adms.web.base.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import com.adms.util.PropertyConfig;

@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1482050801277636503L;
//	private static final String PARAM_LANG = "LANG";
	private String localeCode;
	
	private MenuModel languageMenuModel = new DefaultMenuModel();
	
	private static Map<String, String> countries;
	
	static {
		try {
			countries = new LinkedHashMap<>();
			countries.put(PropertyConfig.getInstance().getValue("cfg.language.selection.th"), PropertyConfig.getInstance().getValue("cfg.language.selection.th.local"));
			countries.put(PropertyConfig.getInstance().getValue("cfg.language.selection.en"), PropertyConfig.getInstance().getValue("cfg.language.selection.en.local"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LanguageBean() {
		try {
			if(StringUtils.isBlank(localeCode)) {
				localeCode = PropertyConfig.getInstance().getValue("cfg.language.selection.th.local");
			}
			String commandAction = "#{language.changeLanguage('#param')}";
			
			DefaultMenuItem itemTh = new DefaultMenuItem(PropertyConfig.getInstance().getValue("cfg.language.selection.th"));
			itemTh.setAjax(false);
			itemTh.setIcon("ui-flag-th custom-icon-fw");
			itemTh.setCommand(commandAction.replace("#param", PropertyConfig.getInstance().getValue("cfg.language.selection.th.local")));
			
			DefaultMenuItem itemEn = new DefaultMenuItem(PropertyConfig.getInstance().getValue("cfg.language.selection.en"));
			itemEn.setAjax(false);
			itemEn.setIcon("ui-flag-us custom-icon-fw");
			itemEn.setCommand(commandAction.replace("#param", PropertyConfig.getInstance().getValue("cfg.language.selection.th.local")));
			
			this.languageMenuModel.addElement(itemTh);
			this.languageMenuModel.addElement(itemEn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeLanguage(String localeCode) {
//		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		String langCode = params.get(PARAM_LANG);
		if(StringUtils.isBlank(localeCode)) {
			System.out.println("LocalCode Blank");
		} else {
			this.localeCode = localeCode;
			setFacesContextLocale(localeCode);
		}
	}
	
	public Map<String, String> getCountriesInMap() {
		return countries;
	}
	
	public String getLocaleCode() {
		return localeCode;
	}
	
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	
	public void countryLocaleCodeChanged(ValueChangeEvent value) {
		String newLocaleValue = value.getNewValue().toString();
		//loop country map to compare the locale code
		for (Map.Entry<String, String> entry : countries.entrySet()) {
			if(entry.getValue().toString().equals(newLocaleValue)){
				this.localeCode = newLocaleValue;
				setFacesContextLocale(this.localeCode);
			}
		}
	}
	
	public void setFacesContextLocale(String localeCode) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(localeCode));
	}

	public MenuModel getLanguageMenuModel() {
		return languageMenuModel;
	}

	public void setLanguageMenuModel(MenuModel languageMenuModel) {
		this.languageMenuModel = languageMenuModel;
	}
}
