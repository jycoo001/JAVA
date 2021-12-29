package com.jyc.model;

import java.util.List;

/**
 * 二级分类
 * 
 * @author 12430
 *
 */
public class TwoType {

	private Integer id;
	private String name;
	private Integer parentId;
	private List<ThreeType> threeTypes;

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

	public List<ThreeType> getThreeTypes() {
		return threeTypes;
	}

	public void setThreeTypes(List<ThreeType> threeTypes) {
		this.threeTypes = threeTypes;
	}

}
