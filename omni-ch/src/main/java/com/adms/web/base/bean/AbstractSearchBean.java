package com.adms.web.base.bean;

public abstract class AbstractSearchBean<T> extends BaseBean implements ISearchBean<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 55877643186617076L;
	
	private final Integer rowPerPage = new Integer(20);

	public Integer getRowPerPage() {
		return rowPerPage;
	}
}
