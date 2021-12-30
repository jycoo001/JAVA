package com.jyc.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单总表
 * 
 * @author 12430
 *
 */
public class Order {
	private Integer id;
	private Integer userId;
	private String pay;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private Integer addressId;
	private Double price;
	private String status;
	private String userStatus;
	private User user;
	private List<OrderDetail> details;

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

}
