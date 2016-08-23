<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心|iPush</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<ul id="myTab" class="nav nav-tabs">
		<li style="margin: 0 10px 0 0"><img
			src="<%=request.getContextPath() %>/res/img/lee.png" class="img-thumbnail"
			width="57px" height="57px"></li>

		<li class="active" style="margin: 15px 5px 0 0"><a href="#home"
			data-toggle="tab"> 个人中心 </a></li>
		<li style="margin: 15px 5px 0 5px"><a href="#setting"
			data-toggle="tab">个人配置</a></li>
		<li style="margin: 15px 5px 0 5px"><a href="#group"
			data-toggle="tab">群组</a></li>
		<li style="margin: 15px 5px 0 5px"><a href="#address"
			data-toggle="tab">通讯录</a></li>
		<li style="margin: 15px 5px 0 5px"><a href="#message"
			data-toggle="tab">我的消息</a></li>
		<li style="margin: 15px 5px 0 5px" class="navbar-text navbar-right"><a
			href="#" class="navbar-link">退出登录</a></li>
	</ul>


	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="home" align="center">
			<div class="container fluid" style="padding: 0; margin: 0">


				<div class="panel panel-default" style="margin-top: 30px">
					<div class="panel-heading" style="height: 45px">
						<div class="col-md-12 column" align="left">
							<span class="glyphicon glyphicon-user" style="position: 0 0 0 0"></span>
							个人信息
						</div>
					</div>
					<div class="panel-body">
						<div class="row clearfix" align="left">
							<div class="col-md-12 column">
								<div class="row clearfix">
									<div class="col-md-4 column">
										<label>邮箱：${currentUser.email }</label>
									</div>
									<div class="col-md-4 column">
										<label>用户名：${currentUser.userName }</label>
									</div>
									<div class="col-md-4 column">
										<label>注册时间：${dateFormat.format(currentUser.registerTime) }</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="panel panel-default" style="margin-top: 30px">
					<div class="panel-heading" style="height: 45px">
						<div class="col-md-6 column" align="left">
							<span class="glyphicon glyphicon-envelope"
								style="position: 0 0 0 0"></span> 发送统计
						</div>

						<div class="col-md-6 column" align="left">
							<span class="glyphicon glyphicon-list-alt"
								style="position: 0 0 0 0"></span> 最新通知
						</div>
					</div>
					<div class="panel-body">
						<div class="row clearfix" align="left">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="carousel slide" id="carousel-489621">
										<ol class="carousel-indicators">
											<li data-slide-to="0" data-target="#carousel-489621"></li>
											<li data-slide-to="1" data-target="#carousel-489621"></li>
											<li data-slide-to="2" data-target="#carousel-489621"
												class="active"></li>
										</ol>
										<div class="carousel-inner">
											<div class="item">
												<img alt="" src="<%=request.getContextPath() %>/res/img/sms.jpg" />
												<div class="carousel-caption">
													<h4 style="color: red" align="right">短信发送记录</h4>
													<p style="color: red" align="right">今日发送短信1条，剩余许可100条</p>
												</div>
											</div>
											<div class="item">
												<img alt="" src="<%=request.getContextPath() %>/res/img/mail.jpg" />
												<div class="carousel-caption">
													<h4 style="color: red" align="right">邮件发送记录</h4>
													<p style="color: red" align="right">今日发送邮件100封，剩余1000封
													</p>
												</div>
											</div>
											<div class="item active">
												<img alt="" src="<%=request.getContextPath() %>/res/img/weixin.jpg" />
												<div class="carousel-caption">
													<h4 style="color: red" align="right">微信发送记录</h4>
													<p style="color: red" align="right">
														今日发送微信1000条，剩余许可1000条</p>
												</div>
											</div>
										</div>
										<a class="left carousel-control" href="#carousel-489621"
											data-slide="prev"><span
											class="glyphicon glyphicon-chevron-left"></span></a> <a
											class="right carousel-control" href="#carousel-489621"
											data-slide="next"><span
											class="glyphicon glyphicon-chevron-right"></span></a>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="panel-group" id="panel-450682">
										<div class="panel panel-default">
											<div class="panel-heading">
												<a class="panel-title" data-toggle="collapse"
													data-parent="#panel-450682" href="#panel-element-107754">最新通知1</a>
											</div>
											<div id="panel-element-107754" class="panel-collapse in">
												<div class="panel-body">欢迎使用iPush</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading">
												<a class="panel-title collapsed" data-toggle="collapse"
													data-parent="#panel-450682" href="#panel-element-399553">最新通知2</a>
											</div>
											<div id="panel-element-399553"
												class="panel-collapse collapse">
												<div class="panel-body">最全的消息推送平台！</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="tab-pane fade" id="setting">
			<p align="center" style="margin: 50px 0 0 0; color: red">使用特定渠道发送信息前，需要配置对应渠道的信息！</p>
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="row clearfix">
							<div class="col-md-4 column"></div>
							<div class="col-md-4 column" style="margin: 50px 0 0 0">
								<form role="form"
									action="<%=request.getContextPath()%>/setting/update.htmls"
									method="post">
									<div class="form-group">
										<label for="email">发信邮箱</label><input type="email"
											class="form-control" id="email" name="email"
											value="${setting.domain}" />
									</div>
									<div class="form-group">
										<label for="smsSign">短信签名</label><input type="text"
											class="form-control" id="smsSign" name="smsSign"
											value="${setting.sign}" />
									</div>

									<div class="form-group">
										<label for="weixinAppId">微信AppId</label><input type="text"
											class="form-control" id="weixinAppId" name="weixinAppId"
											value="${setting.weixinAppId}" />
									</div>
									<div class="form-group">
										<label for="weixinAppSecret">微信AppSecret</label><input
											type="text" class="form-control" id="weixinAppSecret"
											name="weixinAppSecret" value="${setting.weixinAppSecret}" />
									</div>

									<div class="form-group">
										<label for="weiboAppKey">微博AppKey</label><input type="text"
											class="form-control" id="weiboAppKey" name="weiboAppKey"
											value="${setting.weiboAppKey}" />
									</div>
									<div class="form-group">
										<label for="weiboAppSecret">微博AppSecret</label><input
											type="text" class="form-control" id="weiboAppSecret"
											name="weiboAppSecret" value="${setting.weiboAppSecret}" />
									</div>

									<div class="submit" style="margin: 50px 0 0 0">
										<button type="submit" class="btn btn-primary btn-block">提交</button>
									</div>
								</form>
							</div>
							<div class="col-md-4 column"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="group">
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="row clearfix">
							<div class="col-md-4 column"></div>
							<div class="col-md-4 column" align="center"
								style="margin-bottom: 50px">
								<h1>群组列表</h1>
							</div>
							<div class="col-md-4 column" align="right">
								<button type="button" class="btn btn-link btn-lg"
									onclick="javascript:window.location.href='<%=request.getContextPath()%>/group/addPage.htmls'">
									添加群组</button>
								<form action="<%=request.getContextPath()%>/group/addWeiXinGroup.htmls" method="post">
									<button type="submit" class="btn btn-link btn-lg">获取微信组</button>
								</form>
									
							</div>
						</div>
					</div>
				</div>
				<div class="row clearfix">
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>组名</th>
									<th>推送渠道</th>
									<th>组内人数</th>
									<th>创建时间</th>
									<th colspan="3" style="text-align: center">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${groups}" var="group">
									<tr>
										<td>${group.name}</td>
										<td><c:choose>
												<c:when test="${group.channel==1 }">邮件</c:when>
												<c:when test="${group.channel==2 }">短信</c:when>
												<c:when test="${group.channel==4 }">微博</c:when>
												<c:when test="${group.channel==8 }">微信</c:when>
											</c:choose></td>
										<td>${group.count }</td>
										<td>${dateFormat.format(group.createTime)}</td>
										<td><a href="#">编辑</a></td>
										<td><a href="#">删除</a></td>
										<td><a href="#">发送消息</a></td>

									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
		<div class="tab-pane fade" id="address">
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="row clearfix">
							<div class="col-md-4 column"></div>
							<div class="col-md-4 column" align="center"
								style="margin-bottom: 50px">
								<h1>通讯录</h1>
							</div>
							<div class="col-md-4 column" align="right">
								<button type="button" class="btn btn-link btn-lg"
									onclick="javascript:window.location.href='<%=request.getContextPath()%>/member/addPage.htmls'">
									添加客户</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row clearfix">
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>姓名</th>
									<th>电话</th>
									<th>邮箱</th>
									<th>微信id</th>
									<th>微博id</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${members}" var="member">
									<tr>
										<td>${member.memberName}</td>
										<td>${member.mobileNum}</td>
										<td>${member.email}</td>
										<td>${member.openId}</td>
										<td>${member.weiboId}</td>

									</tr>
								</c:forEach>



							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
		<div class="tab-pane fade" id="message">
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="row clearfix">
							<div class="col-md-4 column"></div>
							<div class="col-md-4 column" align="center"
								style="margin-bottom: 50px">
								<h1>消息列表</h1>
							</div>
							<div class="col-md-4 column" align="right">
								<button type="button" class="btn btn-link btn-lg"
									onclick="javascript:window.location.href='<%=request.getContextPath()%>/message/addPage.htmls'">
									新建消息</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row clearfix">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>标题</th>
									<th>群组</th>
									<th>推送渠道</th>
									<th>推送时间</th>
									<th>推送状态</th>
									<th>创建时间</th>
									<th>更新时间</th>
									<th colspan="2" style="text-align: center">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${messages}" var="message">
									<tr>
										<td>${message.title}</td>
										<td>${message.toGroupId}</td>
										<td><c:choose>
												<c:when test="${message.channel == 1}">邮件</c:when>
												<c:when test="${message.channel == 2}">短信</c:when>
												<c:when test="${message.channel == 4}">微博</c:when>
												<c:when test="${message.channel == 8}">微信</c:when>
											</c:choose></td>
										<td>${dateFormat2.format(message.pushTime)}</td>
										<td><c:choose>
												<c:when test="${message.status == 0}">可编辑</c:when>
												<c:when test="${message.status == 1}">不可编辑</c:when>
												<c:when test="${message.status == 2}">准备发送</c:when>
												<c:when test="${message.status == 3}">发送成功</c:when>
												<c:when test="${message.status == 4}">发送失败</c:when>
											</c:choose></td>
										<td>${dateFormat2.format(message.createTime)}</td>
										<td>${dateFormat2.format(message.updateTime)}</td>
										<td><a href="#">编辑</a></td>
										<td><a href="#">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>