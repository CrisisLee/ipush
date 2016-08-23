package ipush.controller;

import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ipush.model.User;
import ipush.model.UserSetting;
import ipush.service.UserSettingService;
import weixin.base.AccessTokenManager;

@Controller
@RequestMapping("setting")
public class UserSettingController {

	@Autowired
	UserSettingService userSettingService;
	
	private static Logger logger = Logger.getLogger(UserSettingController.class);
	
	@RequestMapping("update")
	public String updateSetting(HttpServletRequest request, HttpSession session) {
		String domain = request.getParameter("email");
		String sign = request.getParameter("smsSign");
		String weixinAppId = request.getParameter("weixinAppId");
		String weixinAppSecret = request.getParameter("weixinAppSecret");
		String weiboAppKey = request.getParameter("weiboAppKey");
		String weiboAppSecret = request.getParameter("weiboAppSecret");
		User user = (User)session.getAttribute("currentUser");
		logger.info(user);
		//先判断当前配置照比之前是否有改变
		
		
		//如果微信有改变，则重新拉取一下用户的组信息
		
		UserSetting setting = new UserSetting(user.getId(), domain, sign, weixinAppId, weixinAppSecret, weiboAppKey, weiboAppSecret);
		int result = userSettingService.update(setting);
		logger.info(result);
		session.setAttribute("setting", setting);
		
		//如果是用户刚刚注册或修改微信配置，则在提交配置处开启该线程
		if (setting.getWeixinAppId() != null && setting.getWeixinAppSecret() != null) {
			AccessTokenManager manager = new AccessTokenManager(setting.getWeixinAppId(), setting.getWeixinAppSecret());
			logger.info(AccessTokenManager.accesstoken);
			Timer timer = new Timer();
			timer.schedule(manager, 0, 7000);
		}
		
		
		return "redirect:/user/home.htmls";
	}
	
}
