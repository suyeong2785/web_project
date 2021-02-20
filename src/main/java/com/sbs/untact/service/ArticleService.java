package com.sbs.untact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untact.dao.ArticleDao;
import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.Board;
import com.sbs.untact.dto.Reply;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberService memberService;

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}

	public ResultData addArticle(Map<String, Object> param) {
		articleDao.addArticle(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		articleDao.deleteArticle(id);

		return new ResultData("S-1", "삭제하였습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(searchKeywordType, searchKeyword);
	}

	public ResultData getActorCanModifyRd(Article article, int actorId) {
		if (article.getMemberId() == actorId) {
			return new ResultData("S-1", "가능합니다.");
		}

		if (memberService.isAdmin(actorId)) {
			return new ResultData("S-2", "가능합니다.");
		}

		return new ResultData("F-1", "권한이 없습니다.");
	}

	public ResultData getActorCanDeleteRd(Article article, int actorId) {
		return getActorCanModifyRd(article, actorId);
	}

	public List<Article> getForPrintArticles(int boardId, String searchKeywordType, String searchKeyword,
			int itemsAPage, int page) {
		int limitStart = (page - 1) * itemsAPage;
		int limitTake = itemsAPage;

		return articleDao.getForPrintArticles(searchKeywordType, searchKeyword, limitStart, limitTake, boardId);
	}

	public Board getBoard(int boardId) {
		return articleDao.getBoard(boardId);
	}

	public ResultData addReply(Map<String, Object> param) {
		articleDao.addReply(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData getActorCanModifyReplyRd(Reply reply, int actorId) {
		if (reply.getMemberId() == actorId) {
			return new ResultData("S-1", "가능합니다.");
		}

		if (memberService.isAdmin(actorId)) {
			return new ResultData("S-2", "가능합니다.");
		}

		return new ResultData("F-1", "권한이 없습니다.");
	}

	public ResultData modifyReply(Integer id, String body) {
		articleDao.modifyReply(id, body);

		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}

	public Reply getReply(Integer id) {
		return articleDao.getReply(id);
	}

	public ResultData getActorCanDeleteReplyRd(Reply reply, int loginedMemberId) {
		return getActorCanModifyReplyRd(reply,loginedMemberId);
	}

	public ResultData deleteReply(Integer id) {
		articleDao.deleteReply(id);

		return new ResultData("S-1", "삭제하였습니다.", "id", id);
	}

	public List<Reply> getForPrintReplies(Integer articleId) {
		return articleDao.getForPrintReplies(articleId);
	}

}
