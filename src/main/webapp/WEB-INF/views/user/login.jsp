<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>iPush消息定时推送系统</title>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">iPush</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
							 <a href="#">Link</a>
						</li>
						<li>
							 <a href="#">Link</a>
						</li>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">Action</a>
								</li>
								<li>
									 <a href="#">Another action</a>
								</li>
								<li>
									 <a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">Separated link</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">One more separated link</a>
								</li>
							</ul>
						</li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li>
							 <a href="#">Link</a>
						</li>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">Action</a>
								</li>
								<li>
									 <a href="#">Another action</a>
								</li>
								<li>
									 <a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">Separated link</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-8 column">
			<div class="row clearfix">
				<div class="col-md-6 column">
					<img alt="140x140" src="./logo.png" class="img-rounded" height="200" width="200"/>
				</div>
				<div class="col-md-6 column">
					<dl>
						<dt>
							Description lists
						</dt>
						<dd>
							A description list is perfect for defining terms.
						</dd>
						<dt>
							Euismod
						</dt>
						<dd>
							Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.
						</dd>
						<dd>
							Donec id elit non mi porta gravida at eget metus.
						</dd>
						<dt>
							Malesuada porta
						</dt>
						<dd>
							Etiam porta sem malesuada magna mollis euismod.
						</dd>
						<dt>
							Felis euismod semper eget lacinia
						</dt>
						<dd>
							Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
						</dd>
					</dl>
				</div>
			</div>
			<div class="jumbotron">
				<h1>
					Hello, world!
				</h1>
				<p>
					This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.
				</p>
				<p>
					 <a class="btn btn-primary btn-large" href="#">Learn more</a>
				</p>
			</div>
		</div>
		<div class="col-md-4 column" style="margin: 30px 0 0 0">
			<form role="form" action="<%=request.getContextPath()%>/user/checklogin.htmls" method="post">
				<div class="form-group">
					 <label for="email">邮箱</label><input type="email" class="form-control" id="email" name="email"/>
					 <span style="color:red"><%
						if(request.getAttribute("wrongEmail")!=null){
							out.println(request.getAttribute("wrongEmail"));
						}
					 %></span>
				</div>
				<div class="form-group">
					 <label for="password">密码</label><input type="password" class="form-control" id="password" name="password"/>
					 <span style="color:red"><%
						if(request.getAttribute("wrongPassword")!=null){
							out.println(request.getAttribute("wrongPassword"));
						}
					 %></span>
				</div>
				
				<div class="row">
				
					<div class="col-md-6">
					 <label><input type="checkbox" />自动登录</label>
					</div>
					<div class="text-right col-md-6">
						<label><a href="#"/>忘记密码？</label>		
					</div>
				</div>
				
				
				<div class="row form-group" style="margin: 30px 0 0 0">
					<button type="submit" class="btn btn-primary btn-block" id="login">登录</button>
				</div>
				
				
				
			</form>
			<div class="row" style="margin: 30px 0 0 0">
					<button type="button" class="btn btn-primary btn-block" id="register" 
					onclick="javascript:window.location.href='<%=request.getContextPath()%>/user/register.htmls'">注册</button>

				</div>
		</div>
	</div>
</div>
</body>
</html>