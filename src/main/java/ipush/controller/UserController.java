package ipush.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ipush.model.Group;
import ipush.model.Member;
import ipush.model.Message;
import ipush.model.User;
import ipush.model.UserSetting;
import ipush.service.GroupService;
import ipush.service.MemberService;
import ipush.service.MessageService;
import ipush.service.UserService;
import ipush.service.UserSettingService;
import weixin.base.AccessTokenManager;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSettingService userSettingService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MessageService messageService;
	
	private static Logger logger = Logger.getLogger(UserController.class);  
	
	@RequestMapping("login")
	public String toLogin() {
		return "/user/login";
	}
	
	@RequestMapping("/register")
	public String reg() {
		return "/user/register";
	}
	
	/**
	 * 对注册进行验证，主要验证是否已经有该用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkregister")
	public String regCheck(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(userService.getUserByEmail(email) == null) {
			Date registerTime = new Date(System.currentTimeMillis());
			userService.insert(new User(null, false, email, password, 0, email, registerTime));
			Integer id = userService.getUserByEmail(email).getId();
			userSettingService.insert(new UserSetting(id, email, null, null, null, null, null));
			return "/user/login";
		} else {
			
			request.setAttribute("msg", "用户已经存在！");
			return "/user/register";
		}
		
		
		
		
	}
	
	/**
	 * 对登录进行验证
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/checklogin")
	public String login(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		logger.info(email +  " " + password);
		User currentUser = userService.getUserByEmail(email);
		if (currentUser == null) {
			//用户名不存在！
			logger.info("用户不存在");
			request.setAttribute("wrongEmail", "用户不存在！");
			return "/user/login";
		} else if (!currentUser.getPassword().equals(password)) {
			//密码错误！
			logger.info(currentUser.getPassword() + " " + password);
			logger.info("密码错误");
			request.setAttribute("wrongPassword", "密码错误！");
			return "/user/login";
		} else {
			//登录成功
			logger.info("登录成功");
			//将组信息添加到session
			List<Group> groups = groupService.selectByUserId(currentUser.getId());
			session.setAttribute("groups", groups);
			
			//将客户信息添加到session
			List<Member> members = memberService.selectByCreateUserId(currentUser.getId());
			session.setAttribute("members", members);
			
			//将消息信息添加到session
			List<Message> messages = messageService.selectByUserId(currentUser.getId());
			session.setAttribute("messages", messages);
			
			//将当前用户添加到session
			session.setAttribute("currentUser", currentUser);
			
			//将时间格式化的对象添加到session
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
			session.setAttribute("dateFormat", dateFormat);
			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
			session.setAttribute("dateFormat2", dateFormat2);
			//将用户设置信息添加到session
			UserSetting setting = userSettingService.getUserSetting(currentUser.getId());
			session.setAttribute("setting", setting);
			//跳转到主页
			
			//用户登录之后，开始获取accessToken
			//如果是用户刚刚注册，则在提交配置处开启该线程
			if (setting.getWeixinAppId() != null && setting.getWeixinAppSecret() != null) {
				AccessTokenManager manager = new AccessTokenManager(setting.getWeixinAppId(), setting.getWeixinAppSecret());
				logger.info(AccessTokenManager.accesstoken);
				Timer timer = new Timer();
				timer.schedule(manager, 0, 7000);
			}
			
			//需要改变地址栏显示、以及对db产生修改的行为需要使用重定向
			//一是为了地址栏中地址显示的有意义
			//二是为了防止重复的提交
			return "redirect:/user/home.htmls";
		}
		
	}
	@RequestMapping("/home")
	public String goHome() {
		return "/user/home";
	}
	
	

}
