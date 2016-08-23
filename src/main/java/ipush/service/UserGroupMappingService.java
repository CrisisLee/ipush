package ipush.service;

import java.util.List;

import ipush.model.UserGroupMapping;

public interface UserGroupMappingService {

	int insert(UserGroupMapping record);
	
	List<UserGroupMapping> selectAll();
	
}
