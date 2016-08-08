package org.cex.domain;

public class Tag {

	int tagId;
	String value;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", value=" + value + "]";
	}

}
