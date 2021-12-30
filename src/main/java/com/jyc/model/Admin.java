package com.jyc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;

/**
 * 后台管理员
 * 
 * @author 12430
 *
 */
public class Admin {

	private Integer id;
	private String name;
	private String password;
	private String phone;
	private String secretKey;
	private String staffStatus;
	private String deleteFlag;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "secretKey", "staffStatus", "deleteFlag", "lastLoginTime" });
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

	public String getPassword() {
		return password;
	}

	public String getLocalPassword() {
		if (this.password.length() > 30) {
			return null;
		}
		String str = this.name + "{" + this.password + "}";
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getLastLoginTime() {
		if (lastLoginTime != null) {
			return lastLoginTime;
		} else {
			return null;
		}
	}

	public void setLastLoginTime(Date lastLoginTime) {
		if (lastLoginTime != null) {
			this.lastLoginTime = lastLoginTime;
		}
	}

	public String getLocalCreatTime() {
		if (creatTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(creatTime);
	}

	public String getLocalLastLoginTime() {
		if (lastLoginTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(lastLoginTime);
	}

}
