package com.sbs.untact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.Board;
import com.sbs.untact.dto.Reply;


@Mapper
public interface ArticleDao {
	public Article getArticle(@Param(value = "id") int id);
	
	public Article getForPrintArticle(@Param(value = "id") int id);

	public void addArticle(Map<String, Object> param);

	public void deleteArticle(@Param(value = "id") int id);

	public void modifyArticle(@Param(value = "id") int id, @Param(value = "title") String title,
			@Param(value = "body") String body);

	public List<Article> getArticles(@Param(value = "searchKeywordType") String searchKeywordType,
			@Param(value = "searchKeyword") String searchKeyword);

	public List<Article> getForPrintArticles(@Param(value = "searchKeywordType") String searchKeywordType
			,@Param(value = "searchKeyword") String searchKeyword
			,@Param(value = "limitStart") int limitStart
			,@Param(value = "limitTake") int limitTake
			,@Param(value = "boardId") int boardId);

	public Board getBoard(@Param(value = "boardId") int boardId);

	public void addReply(Map<String, Object> param);

	public void modifyReply(@Param(value = "id") Integer id,@Param(value = "body") String body);

	public Reply getReply(@Param(value = "id") Integer id);

	public void deleteReply(@Param(value = "id") Integer id);

	public List<Reply> getForPrintReplies(@Param(value = "articleId") Integer articleId);

}


