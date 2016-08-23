package ipush.service;

import java.util.List;

import ipush.model.GroupMemberMapping;

public interface GroupMemberMappingService {

	
	int insert(GroupMemberMapping record);
	
	List<GroupMemberMapping> selectAll();
	
	int selectCountOfMember(Integer groupId);
	
	List<Integer> selectMemberByGroupId(Integer groupId);
}
