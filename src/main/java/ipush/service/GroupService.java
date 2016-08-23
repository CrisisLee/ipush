package ipush.service;


import java.util.List;

import ipush.model.Group;

public interface GroupService {

	
	int insert(Group group);
	
	List<Group> selectAll();
	
	int deleteByPrimaryKey(Integer id);
	
	List<Group> selectByUserId(Integer id);
	
	int updateCountByGroupId(Integer id);
}
