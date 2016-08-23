package ipush.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ipush.model.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    List<Message> selectAll();
    
    List<Message> selectByUserId(Integer id);
    
    int updateStatus(@Param("id") int id, @Param("newStatus") int newStatus);
    
    List<Message> selectByPushType(@Param("pushType") int pushType, @Param("currentDay") Timestamp currentDay);
}