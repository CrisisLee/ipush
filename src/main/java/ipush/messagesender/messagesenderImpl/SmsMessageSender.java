package ipush.messagesender.messagesenderImpl;


import java.sql.Timestamp;

//import org.apache.commons.httpclient.Header;

import ipush.messagesender.MessageSender;
import ipush.model.Member;
import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;
/**
 * A class implementing MessageSender to deal with works about 
 * sending a message through SMS
 * @author arlabsurface
 *
 */
public class SmsMessageSender implements MessageSender {


	@Override
	public boolean sendMessage(Message message, Member member, MessageContentWithBLOBs messageContent) {
		// TODO Auto-generated method stub
		Timestamp current = new Timestamp(System.currentTimeMillis());
		logger.info("通过短信于" + current + "给" + member.getMemberName() + "发送了消息：" + message.getTitle());
		return true;
	}

}
