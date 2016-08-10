package org.cex.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.cex.domain.ItemTag;
import org.cex.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SyncTag {
	final static Logger logger = Logger.getLogger(SyncTag.class);

	public static void main(String args[]) {

		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/dispatcher-servlet.xml");

		ItemService itemService = context.getBean(ItemService.class);

		List<ItemTag> itemTagList = itemService.getAllItemTags();

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase database = mongoClient.getDatabase("cex");

		MongoCollection<Document> collection = database.getCollection("tag");

		List<Document> documentsInsert = new ArrayList<Document>();
		collection.deleteMany(new Document());

		for (ItemTag itemTag : itemTagList) {
			logger.info("Adding Tag : " + itemTag);

			documentsInsert.add(new Document("value", itemTag.getTagValue().toLowerCase()).append("itemId", itemTag.getItemId()));

		}
		collection.insertMany(documentsInsert);

		FindIterable<Document> documentList = collection.find();

		Iterator<Document> iter = documentList.iterator();
		while (iter.hasNext()) {
			Document doc = iter.next();
			logger.info("tag : " + doc);
		}
		mongoClient.close();

	}
}
