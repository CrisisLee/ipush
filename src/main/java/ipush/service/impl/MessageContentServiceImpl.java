package ipush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ipush.dao.MessageContentMapper;
import ipush.model.MessageContentWithBLOBs;
import ipush.service.MessageContentService;


@Service("messageContentService")
public class MessageContentServiceImpl implements MessageContentService {

	@Autowired
	private MessageContentMapper messageContentMapper;
	
	/**
	 * @return 返回插入数据库中的记录的id
	 */
	@Override
	public int insert(MessageContentWithBLOBs record) {
		// TODO Auto-generated method stub
		return messageContentMapper.insertSelective(record);
	}

	@Override
	public MessageContentWithBLOBs selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return messageContentMapper.selectByPrimaryKey(id);
	}

}
