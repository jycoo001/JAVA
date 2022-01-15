package com.jyc.model;

import java.util.List;

import org.springframework.util.DigestUtils;

/**
 * 商品
 * 
 * @author 12430
 *
 */
public class Goods extends Base {
	private Integer id;// 非业务主键
	private String goodsId;// 业务主键
	private String name;// 商品名
	private Double price;// 价格
	private Double shopPrice;// 实价
	private Integer inventory;// 数量
	private String goodsUnit;// 单位
	private Integer hot;// 热度
	private String goodsDesc;// 备注
	private Integer typeId;// 类型ID

	private List<GoodsPicture> pictures;// 商品图片
	private GoodsDetail children;
	private GoodsType type;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "goodsId", "pictures", "type", "typeId", "children" });
	}

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

	public GoodsDetail getChildren() {
		return children;
	}

	public void setChildren(GoodsDetail children) {
		this.children = children;
	}

	public String getLocalGoodsId() {
		if (goodsId != null) {
			return goodsId;
		} else {
			if (this.name != null && this.name.trim().length() > 0) {
				return DigestUtils.md5DigestAsHex(this.name.getBytes());
			} else {
				return goodsId;
			}
		}
	}

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

}
