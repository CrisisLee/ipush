package ipush.service;

import java.util.List;

import ipush.model.Member;


public interface MemberService {

	
	int insert(Member member);
	
	List<Member> selectAll();
	
	List<Member> selectByCreateUserId(Integer id);
	
	int deleteByPrimaryKey(Integer id);
	
	Member selectByPrimaryKey(Integer id);
}
