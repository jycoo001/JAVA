package com.jyc.model;

import java.util.List;

/**
 * 地址实体
 * 
 * @author 12430
 *
 */
public class Address {

	private Integer id;
	private String name;
	private Integer parentId;
	private Address parent;
	private List<Address> childrens;

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
		if (parentId == null) {
			return parentId;
		} else {
			return null;
		}
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Address getParent() {
		return parent;
	}

	public void setParent(Address parent) {
		this.parent = parent;
	}

	public List<Address> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Address> childrens) {
		this.childrens = childrens;
	}

}
