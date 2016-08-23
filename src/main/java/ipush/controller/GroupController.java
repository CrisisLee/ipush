package ipush.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import ipush.model.Group;
import ipush.model.GroupMemberMapping;
import ipush.model.Member;
import ipush.model.User;
import ipush.model.UserGroupMapping;
import ipush.service.GroupMemberMappingService;
import ipush.service.GroupService;
import ipush.service.MemberService;
import ipush.service.UserGroupMappingService;
import ipush.util.ExcelParser;
import weixin.base.AccessTokenManager;
import weixin.user.TagManager;
import weixin.user.UserManager;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private GroupMemberMappingService groupMemberMappingService;

	@Autowired
	private UserGroupMappingService userGroupMappingService;

	private static Logger loger = Logger.getLogger(GroupController.class);
	
	/**
	 * 跳转到添加群组的页面
	 * @param session
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String toAddPage(HttpSession session, ModelMap modelMap, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		List<Member> members = memberService.selectByCreateUserId(currentUser.getId());
		request.setAttribute("members", members);
		return "/group/newGroup";
	}

	@RequestMapping("/addGroup")
	public String toAddressPage(HttpServletRequest httpServletRequest, HttpSession session) {

		MultipartResolver resolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
		MultipartHttpServletRequest request = resolver.resolveMultipart(httpServletRequest);

		String groupName = request.getParameter("groupName");
		int channel = Integer.parseInt(request.getParameter("contact"));
		String members = request.getParameter("list");

		// 将组添加到数据库中
		User currentUser = (User) session.getAttribute("currentUser");
		int userId = currentUser.getId();
		Date createTime = new Date(System.currentTimeMillis());
		
		Group group = new Group(null, groupName, userId, createTime, channel, 0);
		groupService.insert(group);
		int groupId = group.getId();// 返回的值为刚插入进去的group的id

		loger.debug("groupid: " + groupId);

		// 将用户与组映射记录添加到数据库
		UserGroupMapping record = new UserGroupMapping(groupId, userId);
		userGroupMappingService.insert(record);

		// 将通过文件导入进来的所有客户添加到客户表中
		MultipartFile file = request.getFile("address");// 用户上传的文件
		// 文件要设置成跟当前用户和当前时间相关的名字，否则多用户上传会冲突
		
		@SuppressWarnings("deprecation")
		String path = request.getRealPath("/") + "uploads\\files" + userId + "" + System.currentTimeMillis()
				+ file.getOriginalFilename();// 存储到服务器本地路径
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(new File(path));
			os.write(file.getBytes());
			os.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {

				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// 获得excel文件中的所有客户信息，存到list中
		List<Member> membersFromFile = ExcelParser.parseFile(new File(path), userId);
		// 获取新存入数据库中的客户的id，用于之后将客户存入客户与组对照表中
		List<Integer> idList = new LinkedList<Integer>();

		if (membersFromFile != null) {
			for (Member member : membersFromFile) {
				// 将记录插到客户组，然后再判断该客户是否符合本次添加组的要求，如果符合，添加到list中，否则不添加
				memberService.insert(member);
				int id = member.getId();
				int channelProp = member.getChannelProp();
				if ((channelProp & channel) > 0) {
					idList.add(id);
				}
			}

		}

		// 将该组的所有客户添加到组与客户对照表中
		StringTokenizer st = new StringTokenizer(members, ",");
		List<Integer> memberList = new LinkedList<Integer>();
		while (st.hasMoreTokens()) {
			int temp = Integer.parseInt(st.nextToken());
			memberList.add(temp);
		}

		memberList.addAll(idList);// 本次该组新添加进来的所有客户

		GroupMemberMapping temp = null;
		if (memberList != null) {
			for (Integer i : memberList) {
				temp = new GroupMemberMapping(groupId, i);
				groupMemberMappingService.insert(temp);//将每个客户添加到映射表中
			}
			//更新组内客户数量
			groupService.updateCountByGroupId(groupId);
		}
		
		//将组信息添加到session
		List<Group> groups = groupService.selectByUserId(currentUser.getId());
		session.setAttribute("groups", groups);

		return "redirect:/user/home.htmls";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/addWeiXinGroup")
	public String addWeiXinGroup(HttpServletRequest request) {
		
		//需要修改，用户每次点击这个按钮，都需要先删除所有跟微信有关的用户、分组信息，否则很容易起冲突。
		
		
		loger.info(AccessTokenManager.accesstoken);
		
		/**
		 * 获取所有用户，并统一存入微信组
		 */
		UserManager userManager = new UserManager();
		//假定用户数小于1000，先只获取一次
		List<String> userList = userManager.getSubscribeList("");
		loger.info(userList);
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		int userId = currentUser.getId();
		Date createTime = new Date(System.currentTimeMillis());
		Group group = new Group(null, "【微信】所有用户", userId, createTime, 8, userList.size());
		groupService.insert(group);
		int groupId = group.getId();
		//组与用户对照表
		UserGroupMapping record = new UserGroupMapping(groupId, userId);
		userGroupMappingService.insert(record);
		//添加所有用户
		for(String openid : userList) {
			String memberName = null;
			//暂时先不要微信用户名，有的用户名会引发sql报错
//			try {
//				memberName = userManager.getNicknameByOpenid(openid);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Member member = new Member(null, userId, memberName, null, null, openid, null, 8);//微信用户
			memberService.insert(member);
			int memberId = member.getId();
			//增加一条组与客户对照表的记录
			GroupMemberMapping record2 = new GroupMemberMapping(groupId, memberId);
			groupMemberMappingService.insert(record2);
			
			
		}
		
		/**
		 * 分组获取用户，获取所有的组以及组内所有用户，分别添加到组中
		 */
		TagManager tagManager = new TagManager();
		List<HashMap<String, String>> tags = tagManager.getTag("");
		loger.info(tags);
		
		
		for(HashMap<String, String> tag : tags) {
			loger.info(tag);
			//根据tag获取tagid，并根据tagid获取改组所有的openid
			int id = Integer.parseInt(tag.get("id"));
			List<String> openids = tagManager.getOpenidListByTag(id, "");
			
			if (openids.size() > 0) {//如果标签中有用户
				//新建一个微信组，将这些openid存进去
				//1.创建组
				createTime = new Date(System.currentTimeMillis());
				group = new Group(null, "【微信】" + tag.get("name"), userId, createTime, 8, openids.size());
				groupService.insert(group);
				groupId = group.getId();
				//组与用户对照表
				record = new UserGroupMapping(groupId, userId);
				userGroupMappingService.insert(record);
				
				//2.添加这些用户
				
				
				for(String openid : openids) {
					String memberName = null;
//					try {
//						memberName = userManager.getNicknameByOpenid(openid);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					Member member = new Member(null, userId, memberName, null, null, openid, null, 8);//微信用户
					memberService.insert(member);
					int memberId = member.getId();
					//增加一条组与客户对照表的记录
					GroupMemberMapping record2 = new GroupMemberMapping(groupId, memberId);
					groupMemberMappingService.insert(record2);
					
					
				}
			}
			
			
			
		}
		
		//需要更新session中组和用户的对象
		
		
		return "redirect:/user/home.htmls";
	}

}
