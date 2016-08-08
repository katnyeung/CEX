package org.cex.domain;

public class RatingHistory {
	int itemId;
	int userId;
	int dir;

	public int getItemId() {
		return itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
