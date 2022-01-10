package com.jyc.model;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 轮播图
 * 
 * @author 12430
 *
 */
public class Shuffling extends Base {
	@ExcelProperty("编号")
	private Integer id;
	@ExcelProperty("照片名")
	private String name;
	@ExcelProperty("照片地址")
	private String picture;
	@ExcelProperty("状态（0：隐藏/1：显示）")
	private String status;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "picture" });
	}

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
