package org.cex.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.cex.domain.Item;
import org.cex.domain.ItemTag;
import org.cex.domain.RatingHistory;
import org.cex.domain.Request;
import org.cex.domain.Tag;
import org.cex.mappers.ItemMapper;
import org.cex.mappers.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {
	Logger logger = Logger.getLogger(ItemServiceImpl.class);

	MongoClient mongoClient = new MongoClient("localhost", 27017);
	
	//MongoClient mongoClient = new MongoClient("localhost", 27017);

	MongoDatabase database = mongoClient.getDatabase("cex");

	MongoCollection<Document> collection = database.getCollection("tag");

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	RequestMapper requestMapper;

	@Transactional
	public void insertItem(Item item) {

		itemMapper.insertItem(item);

		for (Tag tag : item.getTagList()) {
			if (tag.getTagId() == 0) {
				itemMapper.insertTag(tag);
			}

			ItemTag iTag = new ItemTag();
			iTag.setItemId(item.getItemId());
			iTag.setTagId(tag.getTagId());

			itemMapper.insertItemTag(iTag);
		}

	}

	public Tag getTagByValue(String value) {
		return itemMapper.getTagByValue(value);
	}

	public List<Item> getItemsByItemId(List<Integer> itemIds) {
		StringBuilder itemSb = new StringBuilder();
		for (int itemId : itemIds) {
			itemSb.append(itemId);
			itemSb.append(",");
		}
		String tagString = itemSb.toString().substring(0, itemSb.length() - 1);
		logger.info("tagString : " + tagString);
		return itemMapper.getItemsByItemId(tagString);
	}

	public List<ItemTag> getAllItemTags() {
		return itemMapper.getAllItemTags();
	}

	public List<Integer> getItemIdByValue(String value) {
		List<Integer> listItemId = new ArrayList<Integer>();

		Iterator<Document> docIter = collection.find(new Document("value", new Document("$regex", value))).iterator();

		while (docIter.hasNext()) {
			Document doc = docIter.next();

			logger.info("getTagIdByValue : " + value + "," + doc);
			listItemId.add(doc.getInteger("itemId"));
		}

		return listItemId;

	}

	public Item getItemByItemId(int itemId) {
		return itemMapper.getItemByItemId(itemId);
	}

	public void updateRatingByItemIds(int rate, List<Integer> itemIds) {
		StringBuilder itemSb = new StringBuilder();
		for (int itemId : itemIds) {
			itemSb.append(itemId);
			itemSb.append(",");
		}
		String tagString = itemSb.toString().substring(0, itemSb.length() - 1);
		logger.info("tagString : " + tagString);
		itemMapper.updateRatingByItemIds(rate, tagString);
	}

	public void updateRatingByItemId(int rate, int itemId) {

		itemMapper.updateRatingByItemId(rate, itemId);
	}

	public void insertRatingHistory(RatingHistory ratingHistory) {
		// TODO Auto-generated method stub
		itemMapper.insertRatingHistory(ratingHistory);
	}

	public RatingHistory getRatingHistory(int userId, int itemId) {
		// TODO Auto-generated method stub
		return itemMapper.getRatingHistory(itemId, userId);
	}

	public List<Item> getItemsByUserId(Integer userId) {
		return itemMapper.getItemsByUserId(userId);
	}

	public void removeRatingHistory(RatingHistory ratingHistory) {
		// TODO Auto-generated method stub
		itemMapper.removeRatingHistory(ratingHistory);
	}

	public void insertRequest(Request request) {
		requestMapper.insertRequest(request);
	}

	public void updateItemStatus(int itemId, String status) {
		itemMapper.updateItemStatus(itemId, status);
	}

	public List<Tag> getTagByItemId(int itemId) {
		return itemMapper.getTagByItemId(itemId);
	}
}
