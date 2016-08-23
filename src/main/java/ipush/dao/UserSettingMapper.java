package ipush.dao;

import ipush.model.UserSetting;

public interface UserSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSetting record);

    int insertSelective(UserSetting record);

    UserSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSetting record);

    int updateByPrimaryKey(UserSetting record);
}