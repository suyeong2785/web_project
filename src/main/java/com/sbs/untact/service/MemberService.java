package com.sbs.untact.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untact.dao.MemberDao;
import com.sbs.untact.dto.Member;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	
	public ResultData doJoin(Map<String, Object> param) {
		memberDao.doJoin(param);
		int id = Util.getAsInt( param.get("id"), 0);
		
		return new ResultData("S-1", String.format("%s님 가입되셨습니다.", param.get("name")), "id", id);
	}
	
	public Member getMember(int id) {
		return memberDao.getMember(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public ResultData modifyMember(Map<String, Object> param) {
		memberDao.modifyMember(param);

		return new ResultData("S-1", "회원정보를 수정하였습니다.");
	}

	public boolean isAdmin(int actorId) {
		return actorId == 1;
	}

}
