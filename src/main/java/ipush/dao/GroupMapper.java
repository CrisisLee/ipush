package ipush.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import ipush.model.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    
    List<Group> selectAll();
    
    List<Group> selectByUserId(Integer id);
   
    int updateCountByGroupId(@Param("id") Integer id, @Param("count") Integer count);
}