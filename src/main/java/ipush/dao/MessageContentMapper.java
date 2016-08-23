package ipush.dao;

import ipush.model.MessageContent;
import ipush.model.MessageContentWithBLOBs;

public interface MessageContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageContentWithBLOBs record);

    int insertSelective(MessageContentWithBLOBs record);

    MessageContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MessageContentWithBLOBs record);

    int updateByPrimaryKey(MessageContent record);
}