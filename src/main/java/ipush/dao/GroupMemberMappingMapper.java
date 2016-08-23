package ipush.dao;

import java.util.List;

import ipush.model.GroupMemberMapping;

public interface GroupMemberMappingMapper {
    int insert(GroupMemberMapping record);

    int insertSelective(GroupMemberMapping record);
    
    
    List<GroupMemberMapping> selectAll();
    
    int selectCountOfMember(Integer id);
    
    List<Integer> selectMemberByGroupId(Integer id);
}