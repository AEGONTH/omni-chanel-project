package com.adms.web.base.bean;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.data.domain.PageRequest;

public interface ISearchBean<T> {

	public List<T> search(final T object, final PageRequest pageRequest);
	public List<T> search(final DetachedCriteria criteria, final PageRequest pageRequest);
	public int getTotalCount(final T object);
	public int getTotalCount(final DetachedCriteria detachedCriteria);
	public T getRow(List<T> data, String rowKey);
}
