package com.jyc.model;

/**
 * 三级分类
 * 
 * @author 12430
 *
 */
public class ThreeType {

	private Integer id;
	private String name;
	private Integer parentId;
	private TwoType parent;

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

	public TwoType getParent() {
		return parent;
	}

	public void setParent(TwoType parent) {
		this.parent = parent;
	}

}
