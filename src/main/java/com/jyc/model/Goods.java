package com.jyc.model;

import java.util.List;

/**
 * 商品
 * 
 * @author 12430
 *
 */
public class Goods {
	private Integer id;
	private String goodsId;
	private String name;
	private Double price;
	private Double shopPrice;
	private Integer inventory;
	private String goodsUnit;
	private Integer hot;
	private String goodsDesc;
	private Integer typeId;

	private List<GoodsPicture> pictures;
	private ThreeType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public List<GoodsPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<GoodsPicture> pictures) {
		this.pictures = pictures;
	}

	public String getPicture() {
		if (pictures == null || pictures.isEmpty()) {
			return "";
		}
		return pictures.get(0).getPicture();
	}

	public ThreeType getType() {
		return type;
	}

	public void setType(ThreeType type) {
		this.type = type;
	}

}
