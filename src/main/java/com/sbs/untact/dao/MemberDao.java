package com.sbs.untact.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Member;

@Mapper
public interface MemberDao {

	public void doJoin(Map<String, Object> param);

	public Member getMember(@Param(value = "id") int id);
	
	public Member getMemberByLoginId(@Param(value = "loginId") String loginId);

	public void modifyMember(Map<String, Object> param);
}
