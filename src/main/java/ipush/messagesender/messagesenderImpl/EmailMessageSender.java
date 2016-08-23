package ipush.messagesender.messagesenderImpl;


import java.sql.Timestamp;

import config.AppConfig;
import ipush.messagesender.MessageSender;
import ipush.model.Member;
import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;
import lib.MAILSend;
import utils.ConfigLoader;


/**
 * 邮件发送的实现类
 * 1. 该实现使用submail提供的sdk来发送邮件
 * 2. 由于submail的发送域名只能先配置再使用，因此次实现只把service@ex.playhudong.com作为
 * 	       默认发新邮箱并固定在程序中，如果需要扩展，可以考虑使用配置文件动态读取发送邮箱。
 * @author arlabsurface
 *
 */
public class EmailMessageSender implements MessageSender {

	

	@Override
	public boolean sendMessage(Message message, Member member, MessageContentWithBLOBs messageContent) {
		// TODO Auto-generated method stub
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
		MAILSend mail = new MAILSend(config);
		mail.addTo(member.getEmail(), member.getMemberName());
		
		//由于submail无法自定义发信邮箱，因此先默认使用公司配置过的邮箱
		//如果有需要，可以添加其他邮箱
		mail.setSender("service@ex.playhudong.com", "service");
		mail.setReply("service@playhudong.com");
		mail.setSubject(message.getTitle());
		mail.setHtml(messageContent.getContent());
		//此处需要修改send方法，使其返回发送结果的json字符串
		mail.send();
		
		
		
		Timestamp current = new Timestamp(System.currentTimeMillis());
		logger.info("通过邮件于" + current + "给" + member.getMemberName() + "发送了消息：" + message.getTitle());
		return true;
	}

}
