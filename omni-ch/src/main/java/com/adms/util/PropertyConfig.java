package com.adms.util;

import com.adms.utils.PropertyResource;

public class PropertyConfig {

	private static PropertyConfig instance;
//	private final String CONFIG_PROP_PATH = this.getClass().getClassLoader().getResource("config/config.properties").getPath();
	private final String CONFIG_PROP_PATH = "config/config.properties";
	private PropertyResource prop;
	
	public static PropertyConfig getInstance() {
		if(instance == null) {
			instance = new PropertyConfig();
		}
		return instance;
	}
	
	public PropertyConfig() {
		prop = PropertyResource.getInstance(CONFIG_PROP_PATH);
	}
	
	public String getValue(String name) throws Exception {
		return prop.getValue(name);
	}
	
}
