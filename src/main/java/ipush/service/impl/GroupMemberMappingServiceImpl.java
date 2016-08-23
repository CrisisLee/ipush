package ipush.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.GroupMemberMappingMapper;
import ipush.model.GroupMemberMapping;
import ipush.service.GroupMemberMappingService;

@Service("groupMemberMappingService")
public class GroupMemberMappingServiceImpl implements GroupMemberMappingService {

	@Autowired
	private GroupMemberMappingMapper groupMemberMappingMapper;
	
	@Override
	public int insert(GroupMemberMapping record) {
		// TODO Auto-generated method stub
		return groupMemberMappingMapper.insert(record);
	}

	@Override
	public List<GroupMemberMapping> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountOfMember(Integer groupId) {
		// TODO Auto-generated method stub
		return groupMemberMappingMapper.selectCountOfMember(groupId);
	}

	@Override
	public List<Integer> selectMemberByGroupId(Integer groupId) {
		// TODO Auto-generated method stub
		return groupMemberMappingMapper.selectMemberByGroupId(groupId);
	}

}
