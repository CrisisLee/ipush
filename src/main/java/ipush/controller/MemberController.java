package ipush.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import ipush.model.Member;
import ipush.model.User;
import ipush.service.MemberService;
import ipush.util.ExcelParser;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//页面跳转
	@RequestMapping("/addPage")
	public String toAddPage() {
		return "/member/addMember";
	}

	@RequestMapping("/addMember")
	public String toAddressPage(HttpServletRequest httpServletRequest, HttpSession session) {

		MultipartResolver resolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
		MultipartHttpServletRequest request = resolver.resolveMultipart(httpServletRequest);

		// 将通过文件导入进来的所有客户添加到客户表中
		MultipartFile file = request.getFile("address");// 用户上传的文件
		// 文件要设置成跟当前用户和当前时间相关的名字，否则多用户上传会冲突

		User currentUser = (User)session.getAttribute("currentUser");
		int userId = currentUser.getId();
		@SuppressWarnings("deprecation")
		String path = request.getRealPath("/") + "uploads\\files\\" + userId + "" + System.currentTimeMillis()
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
		for(Member member : membersFromFile) {
			memberService.insert(member);
		}
		
		//更新客户数据，为了前台显示
		List<Member> members = memberService.selectByCreateUserId(currentUser.getId());
		session.setAttribute("members", members);
		

		return "redirect:/user/home.htmls";
	}
}
