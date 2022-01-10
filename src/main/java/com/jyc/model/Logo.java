package com.jyc.model;

public class Logo extends Base {

	private Integer id;
	private String address;
	private String status;

	@Override
	public boolean equals(Object obj) {
		return super.equals(new String[] { "id", "address" });
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
