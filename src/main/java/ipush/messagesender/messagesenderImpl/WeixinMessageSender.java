package ipush.messagesender.messagesenderImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ipush.messagesender.MessageSender;
import ipush.model.Member;
import ipush.model.Message;
import ipush.model.MessageContentWithBLOBs;
import weixin.msg.Response;
import weixin.msg.ResponseTptMsg;
import weixin.msg.model.tpt.TemplateMsg;

/**
 * 发送微信消息的实现类
 * 1. 使用了weixinmp4j的包
 * 2. 目前默认只能使用一种模板，暂时无法扩展
 * @author arlabsurface
 *
 */
public class WeixinMessageSender implements MessageSender {

	private static Logger logger = Logger.getLogger(WeixinMessageSender.class);
	
	
	@Override
	public boolean sendMessage(Message message, Member member, MessageContentWithBLOBs messageContent) {
		// TODO Auto-generated method stub
		//AccessTokenManager.accesstoken = "ytwSrVpJTU-cDjGtDcBa7iYBqE3ISP_XTCtMlf9qLemWWQ9vpOjUbgH5XY86rgmWp1eD8IO13E0FyLtumVK8FBIK_cYkCLtGVgesS4qm-dCDnaorQUjtHjwCohqrCn1zJQBdADAFMX";
		String content = messageContent.getTmsgContent();
		logger.info(content);
		
		int i1 = content.indexOf("模版id：");
		int i2 = content.indexOf("转向连接：");
		int i3 = content.indexOf("首行：");
		int i4 = content.indexOf("标题：");
		int i5 = content.indexOf("时间：");
		int i6 = content.indexOf("内容：");
		int i7 = content.indexOf("附加说明：");
		
		
		String tmsgId = content.substring(i1 + 5, i2);
		String url = content.substring(i2 + 5, i3);
		String first = content.substring(i3 + 3, i4);
		String title = content.substring(i4 + 3, i5);
		String time = content.substring(i5 + 3, i6);
		String cont = content.substring(i6 + 3, i7);
		String remark = content.substring(i7 + 5);
		
		//使用messageContent构建Tmsg
		TemplateMsg tMsg = new TemplateMsg();
		tMsg.setTemplate_id(tmsgId);
		tMsg.setTouser(member.getOpenId());
		tMsg.setUrl(url);
		//设定labels
		List<String> labels = new ArrayList<String>();
		labels.add("first");
		labels.add("keyword1");
		labels.add("keyword2");
		labels.add("keyword3");
		labels.add("remark");
		//设定values
		List<String> values = new ArrayList<String>();
		values.add(first);
		values.add(title);
		values.add(time);
		values.add(cont);
		values.add(remark);
		//设定values的颜色
		List<String> colors = new ArrayList<String>();
		colors.add("#173177");
		colors.add("#173177");
		colors.add("#173177");
		colors.add("#173177");
		colors.add("#173177");
		//构建tmsg
		tMsg.setLabels(labels);
		tMsg.setValues(values);
		tMsg.setColors(colors);
		//发送tmsg
		Response response = new Response();
		ResponseTptMsg responseTptMsg = new ResponseTptMsg();
		String jsonT = responseTptMsg.template(tMsg);
		response.sendTptMsg(jsonT);
		
		
		//此处需要获取发送结果：成功/失败
		
		
		
		//记录发送日志
		Timestamp current = new Timestamp(System.currentTimeMillis());
		logger.info("通过微信于" + current + "给" + member.getMemberName() + "发送了消息：" + message.getTitle());
		return true;
	}

}
