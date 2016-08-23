<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加客户|iPush</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="padding: 0 50px 0 50px">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h1 align="center">添加客户</h1>
			</div>
		</div>

		<div class="row clearfix">

			<form role="form" enctype="multipart/form-data" action="<%=request.getContextPath()%>/member/addMember.htmls" method="post">
				<div class="form-group row clearfix" style="margin: 30px 0 15px 0">
					<div class="row clearfix" align="center">
							<div class="row clearfix" align="center"
								style="margin-bottom: 15px">
								<label> 从文件导入 </label>
							</div>
							<div class="row clearfix" align="center"
								style="margin-bottom: 15px">
								<a href="<%=request.getContextPath()%>/res/通讯录模板.xlsx" download="通讯录模板.xlsx">点击下载通讯录模板</a>
							</div>
							<div class="row clearfix" align="center" style="margin-top: 15px">
								<label for="exampleInputFile">上传通讯录</label><input class="form-group"
								type="file" name="address" id="address" />
							</div>
						</div>

				</div>
				<div class="form-group row clearfix">
					<div align="center">
						<button type="submit" class="btn btn-lg">提交</button>
					</div>
				</div>
			</form>




		</div>

	</div>
</body>
</html>