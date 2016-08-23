package ipush.messagesender;

import ipush.messagesender.messagesenderImpl.EmailMessageSender;
import ipush.messagesender.messagesenderImpl.SmsMessageSender;
import ipush.messagesender.messagesenderImpl.WeiboMessageSender;
import ipush.messagesender.messagesenderImpl.WeixinMessageSender;
import ipush.model.Message;

/**
 * 工厂类，用于判断使用那个消息发送的实现类
 * @author arlabsurface
 *
 */
public class MessageSenderFactory {
	
	/**
	 * 传入发送渠道来选择实现类
	 * @param channel 渠道
	 * @return
	 */
	public static MessageSender getMessageSender(int channel) {
		switch (channel) {
		case 8:
			return new WeixinMessageSender();
		case 4:
			return new WeiboMessageSender();
		case 2:
			return new SmsMessageSender();
		case 1:
			return new EmailMessageSender();

		default:
			return null;
		}
	}
	
	/**
	 * 传入消息本身来选择实现类
	 * @param message 消息
	 * @return
	 */
	public static MessageSender getMessageSender(Message message) {
		return getMessageSender(message.getChannel());
	}
}
