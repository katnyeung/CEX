package org.cex.domain;

import java.util.Date;

public class Request {
	int requestId;
	int itemId;
	int userId;

	String type;
	
	int price; 
	
	Date createDate;
	Date modifyDate;

	public int getRequestId() {
		return requestId;
	}

	public int getItemId() {
		return itemId;
	}

	public int getUserId() {
		return userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
