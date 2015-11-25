package com.adms.entity.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="CATEGORY_DATA")
public class CategoryData extends BaseDomain {
	
	private static final long serialVersionUID = -1722725802489012499L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CATEGORY_CODE", nullable=false)
	private String code;
	
	@Column(name="CATEGORY_VALUE")
	private String value;
	
	@Column(name="CATEGORY_LEVEL", nullable=false)
	private Integer level;

	@ManyToOne
	@JoinColumn(name="PARENT_CATEGORY_CODE", referencedColumnName="CATEGORY_CODE")
	private CategoryData parent;
	
	@Column(name="CATEGORY_DESC")
	private String desc;

//	@OneToMany(mappedBy="parentCategory", fetch=FetchType.EAGER)
//	private List<CategoryData> subCategory;
	
	public Long getId() {
		return id;
	}

	public CategoryData setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCode() {
		return code;
	}

	public CategoryData setCode(String categoryCode) {
		this.code = categoryCode;
		return this;
	}

	public String getValue() {
		return value;
	}

	public CategoryData setValue(String categoryValue) {
		this.value = categoryValue;
		return this;
	}

	public CategoryData getParent() {
		return parent;
	}

	public CategoryData setParent(CategoryData parentCategory) {
		this.parent = parentCategory;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public CategoryData setLevel(Integer categoryLevel) {
		this.level = categoryLevel;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public CategoryData setDesc(String categoryDesc) {
		this.desc = categoryDesc;
		return this;
	}

//	public List<CategoryData> getSubCategory() {
//		return subCategory;
//	}
//
//	public void setSubCategory(List<CategoryData> subCategory) {
//		this.subCategory = subCategory;
//	}

}
