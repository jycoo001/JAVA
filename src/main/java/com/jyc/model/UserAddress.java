package com.jyc.model;

/**
 * 用户地址连接
 * 
 * @author 12430
 *
 */
public class UserAddress {

	private Integer id;
	private Integer userId;
	private Integer addressId;
	private String defaults;
	private FourAddress address;

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

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public FourAddress getAddress() {
		return address;
	}

	public void setAddress(FourAddress address) {
		this.address = address;
	}

}
