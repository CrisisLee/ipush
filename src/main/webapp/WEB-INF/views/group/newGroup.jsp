<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="ipush.model.Member"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建群组|iPush</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script>
	function chk() {
		var obj = document.getElementsByName('ckb');
		var s = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked) s += obj[i].value + ',';
		}
		//alert(s);
		if(s != '') 
			document.getElementById('list').innerText=s;
		$('#myModal').modal('hide');
		
	};
	function memFilter() {
		var cha_prop = new Array();
		var member_id = new Array();
		var member_name = new Array();
		var mobile_num = new Array();
		var email = new Array();
		var open_id = new Array();
		var weibo_id = new Array();
		var table = document.getElementById("memberTable");
		$("#memberTable  tr:not(:first)").empty("");
		var newBody = document.createElement("tbody");
		var channel = document.getElementById("contact").value;
		
		<%
		@SuppressWarnings("unchecked")
		List<Member> members = (List<Member>)request.getAttribute("members");
		if(members != null) 
		{
			for(int i = 0; i < members.size(); i++) 
			{%>
				cha_prop[<%=i%>] = <%=((Member)members.get(i)).getChannelProp()%>;
				member_id[<%=i%>] = <%=((Member)members.get(i)).getId()%>;
				member_name[<%=i%>] = '<%=((Member)members.get(i)).getMemberName()%>';
				mobile_num[<%=i%>] = '<%=((Member)members.get(i)).getMobileNum()%>';
				email[<%=i%>] = '<%=((Member)members.get(i)).getEmail()%>';
				open_id[<%=i%>] = '<%=((Member)members.get(i)).getOpenId()%>';
				weibo_id[<%=i%>] = '<%=((Member)members.get(i)).getWeiboId()%>';
				if((cha_prop[<%=i%>] & channel) > 0) 
				{
					var tr=document.createElement("tr");
					var td1=document.createElement("td");
					var td2=document.createElement("td");
					var td3=document.createElement("td");
					var td4=document.createElement("td");
					var td5=document.createElement("td");
					var td6=document.createElement("td");
					
					var checkBox=document.createElement("input");
					checkBox.setAttribute("type","checkbox");
					checkBox.setAttribute("name",'ckb');
					checkBox.setAttribute("value",member_id[<%=i%>]);
					var textValue1=document.createTextNode(member_name[<%=i%>] == 'null' ? '--' : member_name[<%=i%>]);
					var textValue2=document.createTextNode(mobile_num[<%=i%>] == 'null' ? '--' : mobile_num[<%=i%>]);
					var textValue3=document.createTextNode(email[<%=i%>] == 'null' ? '--' : email[<%=i%>]);
					var textValue4=document.createTextNode(open_id[<%=i%>] == 'null' ? '--' : open_id[<%=i%>]);
					var textValue5=document.createTextNode(weibo_id[<%=i%>] == 'null' ? '--' : weibo_id[<%=i%>]);
					td1.appendChild(checkBox);
					td2.appendChild(textValue1);
					td3.appendChild(textValue2);
					td4.appendChild(textValue3);
					td5.appendChild(textValue4);
					td6.appendChild(textValue5);
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
					newBody.appendChild(tr);
				}					
		<%}
		}%>
	
	table.appendChild(newBody);
		
		
		$('#myModal').modal('show');
	};
	
	//function change() {
		//document.getElementById("list").value="";
	//};
	
</script>
</head>
<body>
	<div class="container" style="padding: 0 50px 0 50px">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h1 align="center">新建群组</h1>
			</div>
		</div>

		<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" enctype="multipart/form-data" action="<%=request.getContextPath()%>/group/addGroup.htmls" method="post">
					<div class="form-group" style="margin: 30px 0 30px 0">
						<label for="inputGroupName">组名</label><input type="text"
							class="form-control" name="groupName" />
					</div>
					<div class="form-group" style="margin: 30px 0 30px 0">
						<label for="name">发送渠道</label> <select class="form-control" id="contact" name="contact">
							<option value = "2">短信</option><!-- 0000 0010 -->
							<option value = "1">邮件</option><!-- 0000 0001 -->
							<option value = "8">微信</option><!-- 0000 1000 -->
							<option value = "4">微博</option><!-- 0000 0100 -->
						</select>
					</div>
					<div class="form-group" style="margin: 30px 0 30px 0">

						<div class="panel panel-default" style="margin-top: 30px">
							<div class="panel-heading" style="height: 45px">
								<div class="col-md-12 column" align="left">
									<label> 添加成员 </label>
								</div>
							</div>
							<div class="panel-body">
								<div class="row clearfix" align="left">
									<div class="col-md-12 column">
										<div class="row clearfix" style="margin: 0">
											<div class="col-md-6 column"
												style="border-right: 1px solid grey">
												<div class="row clearfix" align="center"
													style="margin-bottom: 15px">
													<label> 使用文件导入成员 </label>
												</div>
												<div class="row clearfix" align="center"
													style="margin-bottom: 15px">
													<a href="<%=request.getContextPath()%>/res/通讯录模板.xlsx" download="通讯录模板.xlsx">点击下载通讯录模板</a>
												</div>
												<div class="row clearfix" align="center"
													style="margin-top: 15px">
													<label for="exampleInputFile">上传通讯录</label><input class="form-group"
														type="file" name="address" id="address" />
												</div>
											</div>
											<div class="col-md-6 column" align="center">

												<div class="row clearfix" align="center"
													style="margin-bottom: 15px">
													<label> 从已有通讯录导入 </label>
												</div>
												<button type="button" class="btn btn-default btn-link" onclick="memFilter()">打开通讯录</button>
												<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
												      <div class="modal-header">
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												        <h4 class="modal-title" id="myModalLabel">通讯录</h4>
												      </div>
												      <div class="modal-body">
												        <div class="table-responsive">
															<table class="table table-striped table-hover" id="memberTable">
																<thead>
																	<tr>
																		<th>选择</th>
																		<th>姓名</th>
																		<th>电话</th>
																		<th>邮箱</th>
																		<th>微信id</th>
																		<th>微博id</th>
																		
																	</tr>
																</thead>
																<!--<tbody>
																<c:forEach items="${members}" var="member">
																	<tr>
																			<td> <input type="checkbox" value="${member.id}" name="ckb"> </td>
																			<td align="center">${member.memberName}</td>
																			<td align="center">${member.mobileNum}</td>
																			<td align="center">${member.email}</td>
																			<td align="center">${member.openId}</td>
																			<td align="center">${member.weiboId}</td>
																		
																	</tr>
																</c:forEach>
																</tbody>-->
															</table>
														</div>
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
												        <button type="button" class="btn btn-primary" id="afterselect" onClick="chk()">完成</button>
												      </div>
												    </div>
												  </div>
												</div>
												<div>
													<textarea id="list" name="list" style=""></textarea><!-- display: none -->
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>





					</div>
					<div class="form-group" style="margin: 30px 0 30px 0"
						align="center">
						<button type="submit" class="btn btn-lg">提交</button>
					</div>
				</form>
			</div>
		</div>

	</div>
</body>
</html>