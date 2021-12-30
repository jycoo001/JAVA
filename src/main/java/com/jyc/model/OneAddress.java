package com.jyc.model;

import java.util.List;

/**
 * 一级地址
 * 
 * @author 12430
 *
 */
public class OneAddress {

	private Integer id;
	private String name;
	private List<TwoAddress> childrens;

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

	public List<TwoAddress> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<TwoAddress> childrens) {
		this.childrens = childrens;
	}

}
