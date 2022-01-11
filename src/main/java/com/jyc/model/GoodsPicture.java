package com.jyc.model;

/**
 * 商品图片
 * 
 * @author 12430
 *
 */
public class GoodsPicture extends Base {

	private Integer id;
	private Integer goodsId;
	private String picture;
	private Goods parent;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "goodsId", "parent" });
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Goods getParent() {
		return parent;
	}

	public void setParent(Goods parent) {
		this.parent = parent;
	}

}
