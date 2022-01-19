package com.jyc.model;

import java.util.List;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 类别类
 * 
 * @author 12430
 *
 */
public class GoodsType {
	@ExcelIgnore
	private Integer id;
	@ExcelProperty("类型id")
	private Integer typeId;
	@ExcelProperty("类型名")
	private String name;
	@ExcelProperty("父类id")
	private Integer parentId;
	@ExcelIgnore
	private String hidden;

	@ExcelIgnore
	private GoodsType parent;
	@ExcelIgnore
	private List<GoodsType> childrens;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public List<GoodsType> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<GoodsType> childrens) {
		this.childrens = childrens;
	}

	public GoodsType getParent() {
		return parent;
	}

	public void setParent(GoodsType parent) {
		this.parent = parent;
	}

}
