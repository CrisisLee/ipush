package ipush.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;
import ipush.model.User;
import ipush.service.MessageContentService;
import ipush.service.MessageService;
import ipush.util.CronParser;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageContentService messageContentService;

	@Autowired
	private MessageService messageService;

	@RequestMapping("/addPage")
	public String toAddPage() {
		return "/message/addMessage";
	}

	@RequestMapping("/addMessage")
	public String addMessage(HttpServletRequest request) {

		String title = request.getParameter("title");
		int channel = Integer.parseInt(request.getParameter("channel"));
		String group = request.getParameter("groupids");
		String singleTime = request.getParameter("singleTime");
		String cronTime = request.getParameter("cronTime");
		String content = request.getParameter("content");
		String txtContent = request.getParameter("nonehtmlarea").trim();
		int pushType = Integer.parseInt(request.getParameter("pushtype"));
		
		// 根据channel确定content的类型
		int type = -1;
		MessageContentWithBLOBs messageContentWithBLOBs = null;
		// Integer newsId = null;
		String tmsgContent = null;

		// 根据渠道的不同，构造不同的消息体
		switch (channel) {
		case 1:// 邮件
			type = 5;// html富文本
			messageContentWithBLOBs = new MessageContentWithBLOBs(null, type, null, content, null);
			break;
		case 2:// 短信
			type = 1;// 纯文本
			messageContentWithBLOBs = new MessageContentWithBLOBs(null, type, null, txtContent, null);
			break;
		case 4:// 微博
			type = 1;// 纯文本
			messageContentWithBLOBs = new MessageContentWithBLOBs(null, type, null, txtContent, null);
			break;
		case 8:// 微信
			type = 5;// 模板消息
			// 如何判断是模版消息还是多图文？
			// 感觉多图文没有多大意义，正常来说都是通过微信平台发送足够了

			// 模版消息情况，模版消息体是一个json串，存在txt content里
			tmsgContent = txtContent;
			messageContentWithBLOBs = new MessageContentWithBLOBs(null, type, null, null, tmsgContent);
			break;
		default:
			System.out.println("wrong!");
			messageContentWithBLOBs = new MessageContentWithBLOBs(null, type, null, content, null);
			break;
		}

		// 将message content存入数据库并获取记录的id
		int numInsert = messageContentService.insert(messageContentWithBLOBs);
		if (numInsert != 1) {
			// db错误，需要抛异常，有待完善
		}
		int contentId = messageContentWithBLOBs.getId();

		// 用得到的id和其他信息构造message,并存入数据库
		Date createTime = new Date(System.currentTimeMillis());
		Date updateTime = createTime;
		int createUserId = ((User)request.getSession().getAttribute("currentUser")).getId();
		int status = 0;
		Date pushTime = null;
		String cronExpression = null;
		if (pushType == 0) {//设定推送时间，单次

			String[] temp = singleTime.split("[\\u4e00-\\u9fa5]");
			//use input values to construct a time_stamp for push_time
			
			int year = Integer.parseInt(temp[0].trim());
			int month = Integer.parseInt(temp[1].trim()) - 1;
			int day = Integer.parseInt(temp[2].trim());
			int hour = Integer.parseInt(temp[3].trim());
			int minute = Integer.parseInt(temp[4].trim());
			int second = Integer.parseInt(temp[5].trim());
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day, hour, minute, second);
			pushTime = new Date(calendar.getTimeInMillis());
		} else {//cron时间
			cronExpression = cronTime;
			pushTime = CronParser.getNextTime(cronExpression);
		}
		
		//获取session中的messages，以便更新session中的message列表
		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>)request.getSession().getAttribute("messages");
		
		//一次可能选择多个组，因此需要分别构造message并存储
		String[] groupIdTemp = group.split(" ");
		Message message = null;
		for(int i = 0; i < groupIdTemp.length; i++) {
			int toGroupId = Integer.parseInt(groupIdTemp[i]);
			message = new Message(null, title, contentId, toGroupId, channel, pushTime, status, createTime,
					createUserId, updateTime, cronExpression, pushType);
			int result = messageService.insert(message);
			//将新添加到数据库中的记录添加到messages列表中
			if (result > 0) {
				messages.add(message);
			} else {
				//这条记录添加失败
			}
		}
		//更新session中的message列表
		request.getSession().setAttribute("messages", messages);
		
		return "redirect:/user/home.htmls";
	}
}
