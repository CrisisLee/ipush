package ipush.dao;

import java.util.List;

import ipush.model.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    List<Member> selectAll();
    
    List<Member> selectByCreateUserId(Integer id);
}