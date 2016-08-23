package ipush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.UserSettingMapper;
import ipush.model.UserSetting;
import ipush.service.UserSettingService;

@Service("userSettingService")
public class UserSettingServiceImpl implements UserSettingService {

	@Autowired
	private UserSettingMapper userSettingMapper;

	@Override
	public int update(UserSetting userSetting) {
		// TODO Auto-generated method stub
		return userSettingMapper.updateByPrimaryKeySelective(userSetting);
	}

	@Override
	public UserSetting getUserSetting(Integer id) {
		// TODO Auto-generated method stub
		return userSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(UserSetting userSetting) {
		// TODO Auto-generated method stub
		return userSettingMapper.insertSelective(userSetting);
	}

}
