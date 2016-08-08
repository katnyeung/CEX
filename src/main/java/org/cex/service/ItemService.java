package org.cex.service;

import java.util.List;

import org.cex.domain.Item;
import org.cex.domain.ItemTag;
import org.cex.domain.RatingHistory;
import org.cex.domain.Request;
import org.cex.domain.Tag;

public interface ItemService {

	public List<Item> getItemsByItemId(List<Integer> itemIds);

	public List<Item> getItemsByUserId(Integer userId);

	public Item getItemByItemId(int itemId);

	public void insertItem(Item item);

	public void updateItemStatus(int itemId, String status);
	
	public Tag getTagByValue(String value);

	public List<ItemTag> getAllItemTags();

	public List<Integer> getItemIdByValue(String value);

	public void updateRatingByItemIds(int rate, List<Integer> itemIds);

	public void updateRatingByItemId(int rate, int itemId);

	public void insertRatingHistory(RatingHistory ratingHistory);

	public RatingHistory getRatingHistory(int userId, int itemId);

	public void removeRatingHistory(RatingHistory ratingHistory);

	public void insertRequest(Request request);

}
