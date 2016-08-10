package org.cex.domain;

import java.util.Date;
import java.util.List;

public class Item {
	int itemId;
	int userId;
	String title;
	String remark;
	String url;
	String type;

	int bidPrice;
	int buyoutPrice;
	String status;
	Date createdate;
	Date lastupdatedate;

	Date expiryDate;

	List<Tag> tagList;

	int rating;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getBuyoutPrice() {
		return buyoutPrice;
	}

	public void setBuyoutPrice(int buyoutPrice) {
		this.buyoutPrice = buyoutPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", userId=" + userId + ", title=" + title + ", remark=" + remark + ", url=" + url + ", type=" + type + ", bidPrice=" + bidPrice + ", buyoutPrice=" + buyoutPrice + ", status=" + status + ", createdate=" + createdate + ", lastupdatedate=" + lastupdatedate + ", expiryDate=" + expiryDate + ", tagList=" + tagList + ", rating=" + rating + "]";
	}

}
