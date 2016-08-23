<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iPush注册</title>
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script>
	function validate(){
		var psw1=document.regForm.password.value;
		var psw2=document.regForm.passwordAssure.value;
		var email=document.regForm.email.value;
		if(email==""){
			alert("请输入邮箱！");
			document.regForm.email.focus();//光标定位在文本框acccountName中
			return false;
		}
		else if(psw2 != psw1){
			alert("两次密码不同，请重新输入！");
			document.regForm.password.focus();//光标定位在文本框acccountName中
			return false;
		}
		else {
			alert("注册成功！");
			return true;
			
		}
	}
	
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header">
					<h1>
						iPush注册 <small>消息定时推送系统</small>
					</h1>
				</div>
			</div>
		</div>
		<div class="row clearfix" style="margin: 80px 0 0 0">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-4 column"></div>
					<div class="col-md-4 column">
						<form role="form" action="<%=request.getContextPath()%>/user/checkregister.htmls" method="post"
						name="regForm" onSubmit="return validate()">
							<div class="form-group">
								<label for="inputEmail">邮箱</label><input type="email"
									class="form-control" id="inputEmail" name="email"/>
							</div>
							<span style="color:red"><%
								if(request.getAttribute("msg")!=null){
									out.println(request.getAttribute("msg"));
								}
							 %></span>
							<div class="form-group">
								<label for="inputPassword">密码</label><input type="password"
									class="form-control" id="inputPassword" name="password"/>
							</div>
							<div class="form-group">
								<label for="assurePassword">确认密码</label><input type="password"
									class="form-control" id="assurePassword" name="passwordAssure"/>
							</div>

							<div class="row" style="margin: 30px 0 0 0">
								<button type="submit" class="btn btn-primary btn-block margin: 20px">
									提交
								</button>
							</div>


						</form>
					</div>
					<div class="col-md-4 column"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>