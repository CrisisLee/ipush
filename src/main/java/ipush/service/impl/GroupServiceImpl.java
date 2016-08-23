package ipush.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.GroupMapper;
import ipush.model.Group;
import ipush.service.GroupMemberMappingService;
import ipush.service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	
	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private GroupMemberMappingService groupMemberMappingService;
	
	@Override
	public int insert(Group group) {
		// TODO Auto-generated method stub
		return groupMapper.insert(group);
	}

	@Override
	public List<Group> selectAll() {
		// TODO Auto-generated method stub
		return groupMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return groupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Group> selectByUserId(Integer id) {
		// TODO Auto-generated method stub
		return groupMapper.selectByUserId(id);
	}

	@Override
	public int updateCountByGroupId(Integer id) {
		// TODO Auto-generated method stub
		int count = groupMemberMappingService.selectCountOfMember(id);
		return groupMapper.updateCountByGroupId(id, count);
	}

}
