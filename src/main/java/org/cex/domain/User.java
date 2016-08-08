package org.cex.domain;

import java.util.Date;

public class User {
	int userId;
	String email;
	String password;
	String displayName;
	int itemCount;
	int buyRating;
	int sellRating;

	Date createDate;
	Date lastupdateDate;

	String status;

	public int getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getItemCount() {
		return itemCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastupdateDate() {
		return lastupdateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setLastupdateDate(Date lastupdateDate) {
		this.lastupdateDate = lastupdateDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBuyRating() {
		return buyRating;
	}

	public int getSellRating() {
		return sellRating;
	}

	public void setBuyRating(int buyRating) {
		this.buyRating = buyRating;
	}

	public void setSellRating(int sellRating) {
		this.sellRating = sellRating;
	}

}
