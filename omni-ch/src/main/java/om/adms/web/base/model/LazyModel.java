package om.adms.web.base.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.adms.web.base.bean.ISearchBean;
/**
 * <p><b> Generic of Lazy Data Model </b>
 *
 * @param <T> Entity Class
 */
public class LazyModel<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1651280214252741596L;

	private ISearchBean<T> searchBean;
	
	@SuppressWarnings("unused")
	private T example;
	private DetachedCriteria criteria;
	private List<T> datas;
	
	private int pageSize;
	private int rowIndex;
	private int rowCount;
	
	/**
	 * Please use {@link DetachedCriteria} instead.
	 * @param example
	 * @param searchBean
	 */
	@Deprecated
	public LazyModel(final T example, ISearchBean<T> searchBean) {
		this.example = example;
		this.searchBean = searchBean;
	}
	
	public LazyModel(final DetachedCriteria detachedCriteria, ISearchBean<T> searchBean) {
		this.criteria = detachedCriteria;
		this.searchBean = searchBean;
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		try {
			String sorting = null;
			Direction direction = null;
			PageRequest pageRequest = null;
			
			if(!StringUtils.isBlank(sortField) && sortOrder != null) {
				ESqlSort eSql = ESqlSort.valueOf(sortOrder.toString());
				sorting = eSql.getValue();
			}
			
			if(StringUtils.isNotBlank(sorting)) {
				direction = Direction.fromString(sorting);
				pageRequest = new PageRequest(first, first + pageSize, direction, sortField);
			} else {
				pageRequest = new PageRequest(first, first + pageSize);
			}
			
			rowCount = searchBean.getTotalCount(criteria);
			System.out.println("rowCount: " + rowCount);
			datas = searchBean.search(criteria, pageRequest);
			int dataSize = rowCount;
			
			//paginate
	        if(dataSize > pageSize) {
	            try {
	                return datas.subList(first, first + pageSize);
	            }
	            catch(IndexOutOfBoundsException e) {
	                return datas.subList(first, first + (dataSize % pageSize));
	            }
	        }
	        else {
	            return datas;
	        }
			
//			return datas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isRowAvailable() {
		if(datas == null) {
			return false;
		}
		int index = rowIndex % pageSize;
		return index >= 0 && index < datas.size();
	}

	public void setWrappedData(List<T> list) {
		this.datas = list;
	}
	
	public List<T> getWrappedData() {
		return datas;
	}
	
	@Override
	public int getPageSize() {
		return pageSize;
	}
	
	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public int getRowIndex() {
		return rowIndex;
	}
	
	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
    		this.rowIndex = -1;
        } else {
            super.setRowIndex(rowIndex % getPageSize());
    		this.rowIndex = rowIndex % getPageSize();
        }
	}
	
	@Override
	public int getRowCount() {
		return rowCount;
	}
	
	@Override
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	@Override
	public T getRowData() {
		T object = null;
		if(datas != null) {
			int index = rowIndex % pageSize;
			if(index <= datas.size()) {
				object = datas.get(index);
			}
		}
		return object;
	}
	
	@Override
	public T getRowData(String rowKey) {
		return searchBean.getRow(datas, rowKey);
	}
	
	private enum ESqlSort {
		ASCENDING("ASCENDING", "ASC"),
		DESCENDING("DESCENDING", "DESC");
		
		private String code;
		private String value;
		
		private ESqlSort(String code, String value) {
			this.code = code;
			this.value = value;
		}
		@SuppressWarnings("unused")
		public String getCode() {
			return code;
		}
		public String getValue() {
			return value;
		}
	}
	
}
