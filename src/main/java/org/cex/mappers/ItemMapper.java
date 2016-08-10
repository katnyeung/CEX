package org.cex.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cex.domain.Item;
import org.cex.domain.ItemTag;
import org.cex.domain.RatingHistory;
import org.cex.domain.Tag;

public interface ItemMapper {
	@Insert("INSERT INTO item(userId,title,url,remark,type,bidprice,buyoutprice,status,createdate,lastupdatedate,expirydate) VALUES (#{userId},#{title},#{url},#{remark},#{type},#{bidPrice},#{buyoutPrice},#{status},now(),now(),#{expiryDate})")
	@Options(useGeneratedKeys = true, keyProperty = "itemId", flushCache = true, keyColumn = "itemId")
	public int insertItem(Item item);

	@Insert("INSERT INTO item_tag(itemId,tagId) VALUES (#{itemId},#{tagId})")
	@Options(flushCache = true)
	public int insertItemTag(ItemTag itemTag);

	@Select("SELECT distinct i.* FROM item i INNER JOIN item_tag it ON (i.itemId = it.itemId) WHERE tagId in (${tagString}) ORDER BY createdate DESC")
	public List<Item> getItemsByTagId(@Param("tagString") String tagString);

	@Select("SELECT i.* FROM item i WHERE itemId in (${tagString}) ORDER BY createdate DESC")
	public List<Item> getItemsByItemId(@Param("tagString") String tagString);

	@Select("SELECT * FROM item i WHERE itemId = #{itemId} ORDER BY createdate DESC")
	public Item getItemByItemId(@Param("itemId") int itemId);

	@Select("SELECT * FROM item i WHERE userId = #{userId} ORDER BY createdate DESC")
	public List<Item> getItemsByUserId(@Param("userId") int userId);

	/* TAG */
	@Insert("INSERT INTO tag(value) VALUES (#{value})")
	@Options(useGeneratedKeys = true, keyProperty = "tagId", flushCache = true, keyColumn = "tagId")
	public int insertTag(Tag tag);

	@Select("SELECT * FROM tag WHERE tagId = #{tagId}")
	public Tag getTagById(int tagId);

	@Select("SELECT * FROM tag WHERE value = #{value}")
	public Tag getTagByValue(String value);

	@Select("SELECT distinct t.tagId, it.itemId,t.value tagValue FROM tag t INNER JOIN item_tag it ON (it.tagId = t.tagId) INNER JOIN item i ON (it.itemId = i.itemId) WHERE i.expiryDate > now() ORDER BY t.tagId asc")
	public List<ItemTag> getAllItemTags();

	/* Rating History */
	@Select("SELECT * FROM rating_history WHERE itemId = #{itemId} AND userId = #{userId}")
	public RatingHistory getRatingHistory(@Param("itemId") int itemId, @Param("userId") int userId);

	@Update("UPDATE rating_history SET dir = #{dir} WHERE itemId = #{itemId} AND userId = #{userId}")
	public int updateRatingHistory(@Param("dir") String dir, @Param("itemId") int itemId, @Param("userId") int userId);

	@Update("UPDATE item SET rating = rating + ${rate} WHERE itemId in (${tagString})")
	public void updateRatingByItemIds(@Param("rate") int rate, @Param("tagString") String tagString);

	@Update("UPDATE item SET rating = rating + ${rate} WHERE itemId = #{itemId}")
	public void updateRatingByItemId(@Param("rate") int rate, @Param("itemId") int itemId);

	@Update("INSERT INTO rating_history (itemId,userId,dir) VALUES (#{itemId},#{userId},#{dir})")
	public void insertRatingHistory(@Param("ratingHistory") RatingHistory ratingHistory);

	@Update("DELETE rating_hisotry WHERE itemId = #{itemId} AND userId = #{userId}")
	public void removeRatingHistory(RatingHistory ratingHistory);

	@Update("UPDATE item SET status = #{status} WHERE itemId = #{itemId}")
	public void updateItemStatus(int itemId, String status);

}