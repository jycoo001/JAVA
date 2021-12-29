package com.jyc.model;

import java.util.List;

/**
 * 一级分类
 * 
 * @author 12430
 *
 */
public class OneType {

	private Integer id;
	private String name;
	private String hidden;
	private List<TwoType> twoTypes;

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

	public List<TwoType> getTwoTypes() {
		return twoTypes;
	}

	public void setTwoTypes(List<TwoType> twoTypes) {
		this.twoTypes = twoTypes;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

}
