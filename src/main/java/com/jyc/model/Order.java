package com.jyc.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 订单总表
 * 
 * @author 12430
 *
 */
@JsonIgnoreProperties(value = { "handler", "user", "back", "details" })
public class Order {
	@ExcelIgnore
	private Integer id;

	@ExcelProperty("订单编号")
	private String orderId;
	@ExcelProperty("用户编号")
	private Integer userId;
	@ExcelProperty("是否付款")
	private String pay;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelProperty("创建日期")
	@com.alibaba.excel.annotation.format.DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelProperty("修改日期")
	@com.alibaba.excel.annotation.format.DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@ExcelProperty("地址编号")
	private Integer addressId;
	@ExcelProperty("总价")
	private Double price;
	@ExcelProperty("物流状态")
	private String status;
	@ExcelProperty("是否收货")
	private String userStatus;
	@ExcelProperty("订单评价")
	private String desc;
	@ExcelIgnore
	private User user;
	@ExcelIgnore
	private Back back;
	@ExcelIgnore
	private List<OrderDetail> details;

	public Back getBack() {
		return back;
	}

	public void setBack(Back back) {
		this.back = back;
	}

	public String getLocalCreatTime() {
		if (creatTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(creatTime);
	}

	public String getLocalUpdateTime() {
		if (updateTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(updateTime);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getAllPrice() {
		Double d = 0.0;
		if (getDetails() != null && getDetails().size() > 0) {
			for (int i = 0; i < getDetails().size(); i++) {
				d += this.details.get(i).getPrice() * this.details.get(i).getCount();
			}
		}
		return d;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
