package com.jyc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 三级地址实体
 * 
 * @author 12430
 *
 */
@JsonIgnoreProperties(value = { "handler", "childrens" })
public class ThreeAddress {

	private Integer id;
	private String name;
	private Integer parentId;
	private TwoAddress parent;
	private List<FourAddress> childrens;

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
			return null;
		} else {
			return parentId;
		}
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public TwoAddress getParent() {
		return parent;
	}

	public void setParent(TwoAddress parent) {
		this.parent = parent;
	}

	public List<FourAddress> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<FourAddress> childrens) {
		this.childrens = childrens;
	}

}
