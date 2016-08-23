package ipush.taskutil;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;
import ipush.service.GroupMemberMappingService;
import ipush.service.MemberService;
import ipush.service.MessageContentService;
import ipush.service.MessageService;
import ipush.service.PushLogService;

/**
 * 任务管理类，包含三个任务线程
 * 1. 扫描消息表中的普通消息，并将消息加入延迟消息队列
 * 2. 扫描消息表中的高级消息，并将消息加入延迟消息队列
 * 3. 从消息队列中获取消息并分发给发送线程
 * @author arlabsurface
 *
 */
@Component("taskManager")
public class TaskManager {


	/**
	 * 1. 什么样的消息能加进来： 如果消息当次推送时间为当天内，就会被扫描出来，并添加到消息队列中
	 * 2. 什么时候消息可以取出去：消息是实现了Delayed接口的，当Delay时间到，既消息到了该发送的时间，就可以将消息从队列中取出
	 */
	public static DelayQueue<Message> pushList = new DelayQueue<Message>();

	private static Logger logger = Logger.getLogger(TaskManager.class);

	@Autowired
	private MessageService messageService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MessageContentService messageContentService;

	@Autowired
	private PushLogService pushLogService;

	@Autowired
	private GroupMemberMappingService groupMemberMappingService;

	/**
	 * 线程池，用于获取线程
	 * 1. 当一个消息从队列中取出时，就需要发送这个消息 
	 * 2. 由于消息的收件方是以“组”为单位的，一个组包含了多个客户，因此需要使用多个线程来发送消息
	 * 3. 对组中的每一个客户，都使用一个线程来发送消息
	 * 4. 使用线程池，目前设定为固定50个线程，还需要根据实际使用情况进行修改
	 * 
	 * 
	 * ps.微信、邮件等本身是支持群发的，既一次可以提交一个消息、多个用户，交给微信/邮件的服务器去发送，他们服务器群发的效率要高于我们分次提交多个消息
	 * 因此，是否应该考虑形如   “每五条消息合成为一个群发消息，统一交给微信/邮件服务器来群发，而我们的服务器每五条消息使用一个线程” 这样的发送方式？
	 */
	public static ExecutorService executorService = Executors.newFixedThreadPool(50);

	/**
	 * 扫描普通消息并加入消息队列
	 * 1. 目前设定为每三秒执行一次，频率过高，需要修改
	 */
	@Scheduled(fixedRate = 3000L)
	public void scanOrdinaryMessages() {
		logger.info("进行一次普通扫描");
		List<Message> messages = messageService.getOrdinaryMessages();
		if (messages.size() > 0) {
			//将消息加入消息队列
			pushList.addAll(messages);
			// 修改消息的状态为“待发送”
			messageService.setMessageListStatus(messages, Message.STATUS_GOINGTOPUSH);
		}
	}
	/**
	 * 扫描高级消息并加入消息队列
	 * 1. 目前设定为每三秒执行一次，频率过高，需要修改
	 */
	@Scheduled(fixedRate = 3000L)
	public void scanAdavancedMessages() {
		logger.info("进行一次高级扫描");
		//从数据库中获取所有尚未发送的高级消息
		List<Message> messages = messageService.getAdavancedMessages();
		if (messages.size() > 0) {
			//将消息加入消息队列
			pushList.addAll(messages);
			// 修改消息的状态为“待发送”
			messageService.setMessageListStatus(messages, Message.STATUS_GOINGTOPUSH);
		}
	}

	/**
	 * 消息发送任务，每次从延迟队列中取消息，交给线程池中的线程去执行发送操作
	 * 1. 目前设定为每延迟1ms执行一次，既完成A任务后1ms就执行B任务，此处无法设为0
	 * 2. 实际上该线程永远不会返回，会一直在while循环中从消息队列获取消息并分发给线程进行发送
	 */

	@Scheduled(fixedDelay = 1L)
	public void sendMessages() {

		while (true) {
			Message message = null;
			try {
				//取出一条消息
				message = pushList.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//获取消息内容的id
			int contentId = message.getContentId();
			//获取消息内容
			MessageContentWithBLOBs content = messageContentService.selectByPrimaryKey(contentId);
			//获取目标组
			int toGroupId = message.getToGroupId();
			//根据目标组id获取该组所有客户的id
			List<Integer> memberIdList = groupMemberMappingService.selectMemberByGroupId(toGroupId);
			
			// 对于memberId列表中的每个id， 将id、message、和messageContent都传到一个runnable中，并在内部执行sql操作
			for (Integer memberId : memberIdList) {
				//发送消息的具体工作
				SendMessageTask sendMessageTask = new SendMessageTask(pushLogService, messageService, message, content,
						memberService, memberId);
				//开一个线程执行具体的发送任务
				executorService.execute(sendMessageTask);

			}

		}

	}

	/**
	 * 用于删除消息时，将消息从发送列表中移除
	 * 
	 * @param id
	 */
	public static void removeFormPushList(int id) {
		Iterator<Message> iterator = pushList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
				break;
			}
		}
	}

}
