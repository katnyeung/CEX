package org.cex.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.cex.domain.ItemTag;
import org.cex.domain.Request;
import org.cex.domain.Tag;

public interface RequestMapper {
	@Insert("INSERT INTO request (itemId,userId,createdate,modifydate) values ({itemId},{userId},now(),now())")
	@Options(useGeneratedKeys = true, keyProperty = "requestId", flushCache = true, keyColumn = "itemId")
	public int insertRequest(Request request);

	@Insert("INSERT INTO tag(value) VALUES (#{value})")
	@Options(useGeneratedKeys = true, keyProperty = "tagId", flushCache = true, keyColumn = "tagId")
	public int insertTag(Tag tag);

	@Select("SELECT distinct t.tagId, it.itemId,t.value tagValue FROM tag t INNER JOIN item_tag it ON (it.tagId = t.tagId) ORDER BY t.tagId asc")
	public List<ItemTag> getAllItemTags();
}