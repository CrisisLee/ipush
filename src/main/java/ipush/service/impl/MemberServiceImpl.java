package ipush.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ipush.dao.MemberMapper;
import ipush.model.Member;
import ipush.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.insertSelective(member);
	}

	@Override
	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		return memberMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return memberMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Member> selectByCreateUserId(Integer id) {
		// TODO Auto-generated method stub
		return memberMapper.selectByCreateUserId(id);
	}

	@Override
	public Member selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return memberMapper.selectByPrimaryKey(id);
	}

}
