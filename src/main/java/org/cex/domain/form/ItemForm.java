package org.cex.domain.form;

public class ItemForm {

	String title;
	String remark;
	String url;

	String type;

	String tags;

	int bidPrice;
	int buyoutPrice;

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getType() {
		return type;
	}

	public String getTags() {
		return tags;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public int getBuyoutPrice() {
		return buyoutPrice;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public void setBuyoutPrice(int buyoutPrice) {
		this.buyoutPrice = buyoutPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ItemForm [title=" + title + ", remark=" + remark + ", url=" + url + ", type=" + type + ", tags=" + tags + ", bidPrice=" + bidPrice + ", buyoutPrice=" + buyoutPrice + "]";
	}

}
