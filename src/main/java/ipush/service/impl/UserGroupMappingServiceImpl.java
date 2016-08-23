package ipush.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.UserGroupMappingMapper;
import ipush.model.UserGroupMapping;
import ipush.service.UserGroupMappingService;


@Service("userGroupMappingServiceImpl")
public class UserGroupMappingServiceImpl implements UserGroupMappingService {

	
	@Autowired
	private UserGroupMappingMapper userGroupMappingMapper;
	
	@Override
	public int insert(UserGroupMapping record) {
		// TODO Auto-generated method stub
		return userGroupMappingMapper.insert(record);
	}

	@Override
	public List<UserGroupMapping> selectAll() {
		// TODO Auto-generated method stub
		return userGroupMappingMapper.selectAll();
	}

}
