package ipush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.UserMapper;
import ipush.model.User;
import ipush.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userMapper.selectByEmail(email);
	}

	@Override
	public boolean loginSucceed(User user) {
		// TODO Auto-generated method stub
		User temp = getUserByEmail(user.getEmail());
		if (temp == null) {
			return false;
		}
		if (temp.getPassword() == user.getPassword()) {
			return true;
		}
		return false;
	}

}
