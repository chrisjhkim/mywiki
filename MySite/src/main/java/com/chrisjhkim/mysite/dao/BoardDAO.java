package com.chrisjhkim.mysite.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chrisjhkim.mysite.vo.Board;
import com.chrisjhkim.mysite.vo.Tag;

@Mapper
public interface BoardDAO {
	
	public List<Board> getAllList();
	public int insertBoard(Board board);

	public List<HashMap<String, Object>> getActiveBoardList(Map<String, Object> map);
	public Board getBoardContent(int contentNo);
	
	
//	public int insertTag(Tag tag);
	public int insertTags(List<Tag> list);
	public List<Tag> getTagList(int contentNo);
	// select * from tag table where value = map.value ;
//	public Tag getTag(Hashmap map);
	// insert into tag table
//	public int insertNewTag(String name);
	// insert into tag_content_relation table 
//	public int insertTagContentRelation(List<Map<String,Object>> list);
	
	
	
	
}
