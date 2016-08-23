package ipush.taskutil;

import java.util.Date;

import org.apache.log4j.Logger;

import ipush.messagesender.MessageSender;
import ipush.messagesender.MessageSenderFactory;
import ipush.model.Member;
import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;
import ipush.model.PushLog;
import ipush.service.MemberService;
import ipush.service.MessageService;
import ipush.service.PushLogService;

/**
 * 发送消息的具体任务
 * 1. 实现了runnable接口，可以直接在线程池中获取线程来执行
 * 2. 只是进行消息发送的准备、与记录工作，具体的发送逻辑在消息发送类中定义
 * @author arlabsurface
 *
 */
public class SendMessageTask implements Runnable {

	private PushLogService pushLogService;

	private MessageService messageService;

	private Message message;

	private MessageSender messageSender;

	private MessageContentWithBLOBs messageContent;

	private MemberService memberService;

	private Integer memberId;//由于消息中只有group id，因此需要具体的member id才能发送消息

	private static Logger logger = Logger.getLogger(SendMessageTask.class);

	/**
	 * @param pushLogService
	 * @param messageService
	 * @param message
	 * @param messageSender
	 * @param messageContent
	 * @param member
	 */

	public void run() {
		// TODO Auto-generated method stub

		//开始时间
		long before = System.currentTimeMillis();
		//根据消息的渠道，从工厂自动获取对应的发送类
		messageSender = MessageSenderFactory.getMessageSender(message);
		Member member = memberService.selectByPrimaryKey(memberId);//根据member id获取member
		//发送消息，并记录发送是否成功（pushed）
		boolean pushed = messageSender.sendMessage(message, member, messageContent);

		// 构造push log
		int status = pushed ? PushLog.STATUS_SUCCESS : PushLog.STATUS_FAILED;
		Date current = new Date(System.currentTimeMillis());
		PushLog pushLog = new PushLog(null, message.getId(), current, message.getCreateUserId(), member.getId(), status,
				message.getChannel(), message.getToGroupId());
		//记录log
		pushLogService.insert(pushLog);

		//更新消息的状态
		messageService.updateAfterPush(message, pushed);
		
		long after = System.currentTimeMillis();//发送结束时间
		long duration = after - before;//发送耗时
		logger.info("above all " + duration + "millseconds used...");
	}

	/**
	 * @param pushLogService
	 * @param messageService
	 * @param message
	 * @param messageSender
	 * @param messageContent
	 * @param memberService
	 * @param memberId
	 */
	public SendMessageTask(PushLogService pushLogService, MessageService messageService, Message message,
			MessageContentWithBLOBs messageContent, MemberService memberService, Integer memberId) {
		super();
		this.pushLogService = pushLogService;
		this.messageService = messageService;
		this.message = message;
		this.messageContent = messageContent;
		this.memberService = memberService;
		this.memberId = memberId;
	}

}
