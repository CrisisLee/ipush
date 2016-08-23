package ipush.service;

import ipush.model.MessageContentWithBLOBs;

public interface MessageContentService {

	int insert(MessageContentWithBLOBs record);
	
	MessageContentWithBLOBs selectByPrimaryKey(int id); 
}
