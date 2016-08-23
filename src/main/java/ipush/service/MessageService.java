package ipush.service;

import java.util.List;

import ipush.model.Message;

public interface MessageService {

	Message getMessageById(int id);
	
	List<Message> selectAll();
	
	List<Message> selectByUserId(int id);
	
	int insert(Message message);
	
	int deleteByPrimaryKey(int id);
	
	List<Message> getAdavancedMessages();
	
	List<Message> getOrdinaryMessages();
	
	int updateAfterPush(Message message, boolean pushed);
	
	int setMessageListStatus(List<Message> messages, int status);
	
	int setMessageStatus(Message message, int status);
	
	
}
