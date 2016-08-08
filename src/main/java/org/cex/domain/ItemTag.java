package org.cex.domain;

public class ItemTag {
	int itemId;
	int tagId;

	// for sync use
	String tagValue;

	public int getItemId() {
		return itemId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

}
