package com.jyc.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 退货单
 * 
 * @author 12430
 *
 */
public class Back {
	@ExcelIgnore
	private Integer id;
	@ExcelProperty("退货编号")
	private String backId;
	@ExcelProperty("订单编号")
	private String orderId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackId() {
		return backId;
	}

	public void setBackId(String backId) {
		this.backId = backId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
