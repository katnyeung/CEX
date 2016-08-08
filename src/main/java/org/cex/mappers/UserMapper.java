package org.cex.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cex.domain.Request;
import org.cex.domain.Tag;
import org.cex.domain.User;

public interface UserMapper {
	@Insert("INSERT INTO request (itemId,userId,createdate,modifydate) values (#{itemId},#{userId},now(),now())")
	@Options(useGeneratedKeys = true, keyProperty = "requestId", flushCache = true, keyColumn = "itemId")
	public int insertRequest(Request request);

	@Insert("INSERT INTO tag(value) VALUES (#{value})")
	@Options(useGeneratedKeys = true, keyProperty = "tagId", flushCache = true, keyColumn = "tagId")
	public int insertTag(Tag tag);

	@Select("SELECT * FROM user WHERE email = #{email} LIMIT 1")
	public User getUserByEmail(@Param("email") String email);

	@Insert("INSERT INTO user (email,password,displayName,itemCount,buyRating,sellRating,createdate,lastupdatedate,status) VALUES (#{email},#{password},#{displayName},0,0,0,now(),now(),#{status}) ")
	@Options(useGeneratedKeys = true, keyProperty = "userId", flushCache = true, keyColumn = "userId")
	public int insertUser(User user);

}