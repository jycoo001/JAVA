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
		return super.equals(
				new String[] { "id", "sex", "secret", "userMoney", "userPicture", "address", "creatTime", "lastTime" });
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
		if (this.password != null && this.password.length() <= 30 && this.password.trim().length() > 0) {
			String str = this.userId + "{" + this.password + "}";
			return DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecret() {
		return this.secret;
	}

	public String getLocalSecret() {
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
		return this.sex;
	}

	public String getLocalSex() {
		if (this.sex != null) {
			return sex;
		} else {
			if (getCardId() != null) {
				int id = Integer.valueOf(getCardId().substring(getCardId().length() - 2, getCardId().length() - 1));
				if (id % 2 == 0) {
					this.sex = "女";
				} else {
					this.sex = "男";
				}
			}
		}
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
		if (creatTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(creatTime);
		} else {
			return null;
		}
	}

	public String getLocalLastTime() {
		if (lastTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(lastTime);
		} else {
			return null;
		}
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
		if (this.userMoney == null) {
			return 0.0;
		}
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

	public UserAddress getMyAddress() {
		if (getAddress() != null) {
			for (int i = 0; i < getAddress().size(); i++) {
				if (getAddress().get(i).getDefaults().equals("1")) {
					return getAddress().get(i);
				}
			}
		}
		return null;
	}
}
