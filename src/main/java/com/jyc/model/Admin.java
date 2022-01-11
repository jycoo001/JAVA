package com.jyc.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 后台管理员
 * 
 * @author 12430
 *
 */
public class Admin extends Base {

	@ExcelProperty("编号")
	private Integer id;
	@ExcelProperty("用户名")
	private String name;
	@ExcelProperty("密码")
	private String password;
	@ExcelProperty("电话")
	private String phone;
	@ExcelProperty("安全码")
	private String secretKey;
	@ExcelProperty("状态")
	private String staffStatus;
	@ExcelProperty("是否删除")
	private String deleteFlag;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelProperty("创建日期")
	@com.alibaba.excel.annotation.format.DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelProperty("最后登录日期")
	@com.alibaba.excel.annotation.format.DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "secretKey", "deleteFlag", "creatTime", "lastLoginTime" });
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
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLocalPassword() {
		if (this.password == null) {
			return null;
		}
		if (this.password.trim().length() > 30) {
			return this.password;
		}
		if (this.name != null && this.name.trim().length() > 0 && this.password != null
				&& this.password.trim().length() > 0) {
			String str = this.name + "{" + this.password + "}";
			return DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return null;
	}

	public String getLocalSecretKey() {
		if (secretKey != null && secretKey.trim().length() > 0) {
			return secretKey;
		} else {
			if (this.name != null && this.name.trim().length() > 0 && this.password != null
					&& this.password.trim().length() > 0) {
				String str = this.name + "{" + this.password + "安全码}";
				return DigestUtils.md5DigestAsHex(str.getBytes());
			}
		}
		return null;
	}

	public String getLocalCreatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (creatTime != null) {
			return sdf.format(creatTime);
		} else {
			return sdf.format(Calendar.getInstance().getTime());
		}
	}

	public String getLocalLastLoginTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (lastLoginTime == null) {
			return sdf.format(Calendar.getInstance().getTime());
		} else {
			return sdf.format(lastLoginTime);
		}
	}

	public String getLocalDeleteFlag() {
		if (deleteFlag != null) {
			return deleteFlag;
		} else {
			return "0";
		}
	}

}
