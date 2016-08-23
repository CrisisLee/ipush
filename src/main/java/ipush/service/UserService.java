package ipush.service;

import ipush.model.User;

public interface UserService {

	int insert(User user);
	
	User getUserByEmail(String email);
	
	boolean loginSucceed(User user);
}
