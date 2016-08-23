package ipush.service;

import ipush.model.UserSetting;

public interface UserSettingService {

	
	int update(UserSetting userSetting);
	
	UserSetting getUserSetting(Integer id);
	
	int insert(UserSetting userSetting);
}
