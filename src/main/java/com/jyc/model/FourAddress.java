package com.jyc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class FourAddress {

	private Integer id;
	private String name;
	private Integer parentId;
	private ThreeAddress parent;

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

	public ThreeAddress getParent() {
		return parent;
	}

	public void setParent(ThreeAddress parent) {
		this.parent = parent;
	}

}
