package com.jyc.model;

import java.util.List;

/**
 * 二级地址
 * 
 * @author 12430
 *
 */
public class TwoAddress {

	private Integer id;
	private String name;
	private Integer parentId;
	private OneAddress parent;

	private List<ThreeAddress> childrens;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ThreeAddress> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<ThreeAddress> childrens) {
		this.childrens = childrens;
	}

	public OneAddress getParent() {
		return parent;
	}

	public void setParent(OneAddress parent) {
		this.parent = parent;
	}

}
