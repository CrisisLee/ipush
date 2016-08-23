package ipush.messagesender.messagesenderImpl;

import java.sql.Timestamp;

import ipush.messagesender.MessageSender;
import ipush.model.Member;
import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;

public class WeiboMessageSender implements MessageSender {


	@Override
	public boolean sendMessage(Message message, Member member, MessageContentWithBLOBs messageContent) {
		// TODO Auto-generated method stub
		Timestamp current = new Timestamp(System.currentTimeMillis());
		logger.info("通过微博于" + current + "给" + member.getMemberName() + "发送了消息：" + message.getTitle());
		return true;
	}

}
