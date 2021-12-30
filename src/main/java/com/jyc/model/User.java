package com.jyc.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;

/**
 * 前端用户
 * 
 * @author 12430
 *
 */
public class User extends Base {

	private Integer id;
	private String userId;
	private String password;
	private String secret;
	private String sex;
	private String phone;
	private String qq;
	private String email;
	private String cardId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastTime;
	private Double userMoney;
	private String userStatus;
	private String userPicture;
	private List<UserAddress> address;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "name", "sex", "secret", "userStatus", "lastTime", "userMany" });
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLocalPassword() {
		if (this.password.length() > 30) {
			return null;
		}
		String str = this.userId + "{" + this.password + "}";
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecret() {
		if (secret != null && secret.trim().length() > 0) {
			return secret;
		} else {
			String str = this.userId + "{" + this.password + "安全码}";
			return DigestUtils.md5DigestAsHex(str.getBytes());
		}
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public String getLocalCreatTime() {
		if (creatTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(creatTime);
	}

	public String getLocalLastTime() {
		if (lastTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(lastTime);
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getLastTime() {
		if (lastTime == null) {
			return null;
		}
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		if (lastTime != null) {
			this.lastTime = lastTime;
		}
	}

	public Double getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(Double userMoney) {
		this.userMoney = userMoney;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public List<UserAddress> getAddress() {
		return address;
	}

	public void setAddress(List<UserAddress> address) {
		this.address = address;
	}

	public UserAddress getUserAddressOne() {
		for (int i = 0; i < this.address.size(); i++) {
			if (this.address.get(i).getDefaults().equals("1")) {
				return this.address.get(i);
			}
		}
		return null;
	}

}
