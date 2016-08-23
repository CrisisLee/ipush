package ipush.dao;

import java.util.List;

import ipush.model.UserGroupMapping;

public interface UserGroupMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupMapping record);

    int insertSelective(UserGroupMapping record);

    UserGroupMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroupMapping record);

    int updateByPrimaryKey(UserGroupMapping record);
    
    List<UserGroupMapping> selectAll();
    
}