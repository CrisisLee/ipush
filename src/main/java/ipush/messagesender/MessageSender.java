package ipush.messagesender;

import org.apache.log4j.Logger;

import ipush.model.Member;
import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;

/**
 * An interface of message senders.
 * Only one method: sendMessage.
 * @author arlabsurface
 *
 */
public interface MessageSender {

	public static Logger logger = Logger.getLogger(MessageSender.class);
	
	/**
	 * send the message using channel 
	 * @param message
	 * @return
	 */
	boolean sendMessage(Message message, Member member, MessageContentWithBLOBs messageContent);
}
