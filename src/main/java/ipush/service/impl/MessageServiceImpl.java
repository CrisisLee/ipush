package ipush.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.MessageMapper;
import ipush.model.Message;
import ipush.service.MessageService;
import ipush.util.CronParser;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public Message getMessageById(int id) {
		// TODO Auto-generated method stub
		return messageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Message> selectAll() {
		// TODO Auto-generated method stub
		return messageMapper.selectAll();
	}

	@Override
	public int insert(Message message) {
		// TODO Auto-generated method stub
		return messageMapper.insert(message);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return messageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Message> selectByUserId(int id) {
		// TODO Auto-generated method stub
		return messageMapper.selectByUserId(id);
	}

	@Override
	public List<Message> getAdavancedMessages() {
		// TODO Auto-generated method stub
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return messageMapper.selectByPushType(Message.ADVANCED, timestamp);
	}

	@Override
	public List<Message> getOrdinaryMessages() {
		// TODO Auto-generated method stub
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return messageMapper.selectByPushType(Message.ORDINARY, timestamp);
	}

	@Override
	public int updateAfterPush(Message message, boolean pushed) {
		// TODO Auto-generated method stub
		int result = 0;
		if (pushed) {
			setMessageStatus(message, Message.STATUS_PUSHED);
		} else {
			setMessageStatus(message, Message.STATUS_FAILTOPUSH);
		}
		// if it is an advanced message, update its push-time
		if (message.getPushType() == Message.ADVANCED) {
			
			Date nextPushTime = CronParser.getNextTime(message.getCronExpression());
			message.setPushTime(nextPushTime);
			// reset status to 0
			message.setStatus(Message.STATUS_EDITABLE);
			result = messageMapper.updateByPrimaryKey(message);
		}
		return result;
	}

	@Override
	public int setMessageListStatus(List<Message> messages, int status) {
		// TODO Auto-generated method stub
		int changeCount = 0;
		for (Message message : messages)
			changeCount += setMessageStatus(message, status);
		return changeCount;
	}

	@Override
	public int setMessageStatus(Message message, int status) {
		// TODO Auto-generated method stub
		return messageMapper.updateStatus(message.getId(), status);
	}

}
