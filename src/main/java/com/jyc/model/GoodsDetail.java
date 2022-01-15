package com.jyc.model;

/**
 * 商品详情
 * 
 * @author 12430
 *
 */
public class GoodsDetail extends Base {

	private Integer id;
	private Integer goodsId;
	private String goodsIntroduce;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "goodsId" });
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

	public String getGoodsIntroduce() {
		return goodsIntroduce;
	}

	public void setGoodsIntroduce(String goodsIntroduce) {
		this.goodsIntroduce = goodsIntroduce;
	}

}
