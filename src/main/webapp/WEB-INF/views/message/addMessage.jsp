<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ipush.model.Group"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建消息|iPush</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/Spinner.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/Spinner.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/hashmap.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/cron.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.DG-spinner {
	width: 60px;
}

.STYLE1 {
	color: #FF0000
}

body {
	margin-left: 0px;
	margin-top: 0px;
}
</style>


<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>

	<div class="modal fade bs-example-modal-lg" id="groupModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">客户组</h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
						<table class="table table-striped table-hover" id="groupTable">
							<thead>
								<tr>
									<th>选择</th>
									<th>组名</th>
									<th>组内人数</th>
									<th>创建时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="afterselect"
						onClick="chk()">完成</button>
				</div>
			</div>
		</div>
	</div>


	<div class="container container-fluid">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h1 class="text-center">新建消息</h1>
			</div>
		</div>
		<div class="row clearfix">
			<form role="form"
				action="<%=request.getContextPath()%>/message/addMessage.htmls"
				method="post">
				<div class="form-group row clearfix">
					<div class="col-md-4 column">
						<div class="form-group row clearfix" style="margin: 5px 0 5px 0"
							align="left">
							<label for="inputTitle">标题</label> <input type="text"
								class="form-control" id="title" name="title" />
						</div>

						<div class="form-group row clearfix" style="margin: 5px 0 5px 0"
							align="left">
							<label for="name">发送渠道</label> <select class="form-control"
								name="channel" id="channel" onchange="editWeixin()">
								<option value="2">短信</option>
								<option value="1">邮件</option>
								<option value="8">微信</option>
								<option value="4">微博</option>
							</select>
						</div>

						<div class="form-group row clearfix" style="margin: 5px 0 5px 0"
							align="left">
							<label for="inputTitle">收信组</label>
							<div class="row">
								<div class="col-md-8 column">
									<input type="text" class="form-control" id="groupnames" name="groupnames" />
								</div>
								<div class="col-md-4 column" align="right">
									<button type="button" class="btn btn-default"
										onclick="groupFilter()">选择收信组</button>
								</div>
							</div>
							<input type="text" class="form-control" id="groupids" name="groupids" />
						</div>

						<div class="form-group row clearfix" style="margin: 5px 0 5px 0"
							align="left">
							<label for="inputTitle">推送方式</label>

							<div class="tabbable" id="tabs-669928">
								<input type="text" id="pushtype"  name="pushtype" value='0' style="display: none">
								<ul class="nav nav-tabs" name="type">
									<li class="active" value="0" onclick="singleClick()"><a href="#panel-647024"
										data-toggle="tab">单次推送</a></li>
									<li value="1" onclick="cronClick()"><a href="#panel-46538" data-toggle="tab">周期性推送</a></li>
								</ul>
								<div class="tab-content" height="200px">
									<div class="tab-pane active" id="panel-647024">
										<p style="margin-top: 10px">请选择推送时间：</p>
										<div class="box">

											<!--<input class="laydate-icon" id="demo" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">-->
											<input type="text" name="singleTime" id="d241"
												onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日 HH时mm分ss秒'})"
												class="Wdate" style="height: 30px; width: 250px" />
										</div>
									</div>
									<div class="tab-pane" id="panel-46538">
										<table width="360" border="0" cellpadding="0" cellspacing="0"
											class="solid-bottom">
											<tr>
												<td width="360" height="20"><table border="0"
														cellpadding="0" cellspacing="0" id="secTable">
														<tr>
															<td height="25" width="60" align="center" class="sec2"
																onclick="secBoard(0)">分钟</td>
															<td width="60" align="center" class="sec1"
																onclick="secBoard(1)">小时</td>
															<td width="60" align="center" class="sec1"
																onclick="secBoard(2)">天</td>
															<td width="60" align="center" class="sec1"
																onclick="secBoard(3)">月</td>
															<td width="60" align="center" class="sec1"
																onclick="secBoard(4)">周</td>
															<td width="60" align="center" class="sec1"
																onclick="secBoard(5)">开始结束</td>
														</tr>
													</table></td>
											</tr>
										</table>
										<table border="0" cellpadding="0" width="360" height="200"
											id="mainTable" bgcolor="f2f2f2">
											<tbody style="display: block;">
												<tr>
													<td height="120">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="27" align="left" valign="middle" width="20%">
																	<div align="left">
																		<input name="fz" id="fz" type="radio" value="fz1"
																			onclick="fz_radio_click(this)">
																	</div>
																</td>
																<td align="left" valign="middle" width="40%">
																	<div align="left">秒周期循环：</div>
																</td>
																<td height="27">&nbsp;</td>
																<td height="27">&nbsp;</td>
																<td height="27">&nbsp;</td>
																<td height="27">&nbsp;</td>
															</tr>

															<tr height="27">
																<td align="right" valign="middle" width="20%"
																	colspan="2">
																	<div align="right">从</div>
																</td>

																<td align="left" valign="middle" colspan="2" width="40%"><div
																		align="left">
																		<input name="mks" id="mks" type="text"
																			class="DG-spinner" id="testElement" value="0"
																			maxlength="3"
																			properties="maxValue:59,minValue:0,shiftIncrement:20">
																	</div></td>


																<td align="right" colspan="2" valign="middle"
																	width="40%"><div align="right">秒钟开始，</div></td>
															</tr>
															<tr>

																<td align="right" valign="middle" colspan="2">
																	<div align="right">间隔</div>
																</td>
																<td align="left" valign="middle" colspan="2">
																	<div align="left">
																		<input name="mzx" id="mzx" type="text"
																			class="DG-spinner" id="text" value="0" maxlength="3"
																			properties="maxValue:59,minValue:0,shiftIncrement:20">
																	</div>
																</td>
																<td align="right" valign="middle" colspan="2"><div
																		align="right">秒钟执行一次</div></td>
															</tr>
															<tr>
																<td height="25" valign="middle" width="20%">
																	<div align="left">
																		<input name="fz" id="fz" type="radio"
																			onclick="fz_radio_click(this)" value="fz2" checked>
																	</div>
																</td>
																<td height="25" width="40%"><div align="left">分周期循环：</div></td>
																<td height="27">&nbsp;</td>
																<td height="27">&nbsp;</td>
																<td height="27">&nbsp;</td>
																<td height="27">&nbsp;</td>
															</tr>
															<tr>
																<td align="right" valign="middle" width="20%"
																	colspan="2">
																	<div align="right">从</div>
																</td>
																<td align="left" valign="middle" colspan="2"><div
																		align="left">
																		<input name="fks" id="fks" type="text"
																			class="DG-spinner" value="0" maxlength="3"
																			properties="maxValue:59,minValue:0,shiftIncrement:20">
																	</div></td>
																<td align="right" colspan="2" valign="middle"><div
																		align="right">分钟开始，</div></td>

															</tr>
															<tr>
																<td align="right" valign="middle" colspan="2">
																	<div align="right">间隔</div>
																</td>
																<td align="left" valign="middle" colspan="2"><div
																		align="left">
																		<input name="fzx" id="fzx" type="text"
																			class="DG-spinner" value="1" maxlength="3"
																			properties="maxValue:59,minValue:0,shiftIncrement:20">
																	</div></td>
																<td align="right" valign="middle" colspan="2"><div
																		align="right">分钟执行一次</div></td>
															</tr>
															<tr>
																<td height="25" valign="middle">
																	<div align="left">
																		<input name="fz" id="fz" type="radio" value="fz3"
																			onclick="fz_radio_click(this)">
																	</div>
																</td>
																<td height="25"><div align="left">手动指定</div></td>
																<td height="25">&nbsp;</td>
																<td height="25">&nbsp;</td>
																<td height="25">&nbsp;</td>
																<td height="25">&nbsp;</td>
															</tr>
															<tr>
																<td height="100" colspan="6" align="center"><table
																		width="99%" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td><input type="checkbox" name="chk1" id="chk1"
																				value="1" disabled="disabled"></td>
																			<td>1</td>
																			<td><input type="checkbox" name="chk2" id="chk2"
																				value="2" disabled="disabled"></td>
																			<td>2</td>
																			<td><input type="checkbox" name="chk3" id="chk3"
																				value="3" disabled="disabled"></td>
																			<td>3</td>
																			<td><input type="checkbox" name="chk4" id="chk4"
																				value="4" disabled="disabled"></td>
																			<td>4</td>
																			<td><input type="checkbox" name="chk5" id="chk5"
																				value="5" disabled="disabled"></td>
																			<td>5</td>
																			<td><input type="checkbox" name="chk6" id="chk6"
																				value="6" disabled="disabled"></td>
																			<td>6</td>
																			<td><input type="checkbox" name="chk7" id="chk7"
																				value="7" disabled="disabled"></td>
																			<td>7</td>
																			<td><input type="checkbox" name="chk8" id="chk8"
																				value="8" disabled="disabled"></td>
																			<td>8</td>
																			<td><input type="checkbox" name="chk9" id="chk9"
																				value="9" disabled="disabled"></td>
																			<td>9</td>
																			<td><input type="checkbox" name="chk10"
																				id="chk10" value="10" disabled="disabled"></td>
																			<td>10</td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="chk11"
																				id="chk11" value="11" disabled="disabled"></td>
																			<td>11</td>
																			<td><input type="checkbox" name="chk12"
																				id="chk12" value="12" disabled="disabled"></td>
																			<td>12</td>
																			<td><input type="checkbox" name="chk13"
																				id="chk13" value="13" disabled="disabled"></td>
																			<td>13</td>
																			<td><input type="checkbox" name="chk14"
																				id="chk14" value="14" disabled="disabled"></td>
																			<td>14</td>
																			<td><input type="checkbox" name="chk15"
																				id="chk15" value="15" disabled="disabled"></td>
																			<td>15</td>
																			<td><input type="checkbox" name="chk16"
																				id="chk16" value="16" disabled="disabled"></td>
																			<td>16</td>
																			<td><input type="checkbox" name="chk17"
																				id="chk17" value="17" disabled="disabled"></td>
																			<td>17</td>
																			<td><input type="checkbox" name="chk18"
																				id="chk18" value="18" disabled="disabled"></td>
																			<td>18</td>
																			<td><input type="checkbox" name="chk19"
																				id="chk19" value="19" disabled="disabled"></td>
																			<td>19</td>
																			<td><input type="checkbox" name="chk20"
																				id="chk20" value="20" disabled="disabled"></td>
																			<td>20</td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="chk21"
																				id="chk21" value="21" disabled="disabled"></td>
																			<td>21</td>
																			<td><input type="checkbox" name="chk22"
																				id="chk22" value="22" disabled="disabled"></td>
																			<td>22</td>
																			<td><input type="checkbox" name="chk23"
																				id="chk23" value="23" disabled="disabled"></td>
																			<td>23</td>
																			<td><input type="checkbox" name="chk24"
																				id="chk24" value="24" disabled="disabled"></td>
																			<td>24</td>
																			<td><input type="checkbox" name="chk25"
																				id="chk25" id="chk1" value="25" disabled="disabled"></td>
																			<td>25</td>
																			<td><input type="checkbox" name="chk26"
																				id="chk26" value="26" disabled="disabled"></td>
																			<td>26</td>
																			<td><input type="checkbox" name="chk27"
																				id="chk27" value="27" disabled="disabled"></td>
																			<td>27</td>
																			<td><input type="checkbox" name="chk28"
																				id="chk28" value="28" disabled="disabled"></td>
																			<td>28</td>
																			<td><input type="checkbox" name="chk29"
																				id="chk29" value="29" disabled="disabled"></td>
																			<td>29</td>
																			<td><input type="checkbox" name="chk30"
																				id="chk30" value="30" disabled="disabled"></td>
																			<td>30</td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="chk31"
																				id="chk31" value="31" disabled="disabled"></td>
																			<td>31</td>
																			<td><input type="checkbox" name="chk32"
																				id="chk32" value="32" disabled="disabled"></td>
																			<td>32</td>
																			<td><input type="checkbox" name="chk33"
																				id="chk33" value="33" disabled="disabled"></td>
																			<td>33</td>
																			<td><input type="checkbox" name="chk34"
																				id="chk34" value="34" disabled="disabled"></td>
																			<td>34</td>
																			<td><input type="checkbox" name="chk35"
																				id="chk35" value="35" disabled="disabled"></td>
																			<td>35</td>
																			<td><input type="checkbox" name="chk36"
																				id="chk36" value="36" disabled="disabled"></td>
																			<td>36</td>
																			<td><input type="checkbox" name="chk37"
																				id="chk37" value="37" disabled="disabled"></td>
																			<td>37</td>
																			<td><input type="checkbox" name="chk38"
																				id="chk38" value="38" disabled="disabled"></td>
																			<td>38</td>
																			<td><input type="checkbox" name="chk39"
																				id="chk39" value="39" disabled="disabled"></td>
																			<td>39</td>
																			<td><input type="checkbox" name="chk40"
																				id="chk40" value="40" disabled="disabled"></td>
																			<td>40</td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="chk41"
																				id="chk41" value="41" disabled="disabled"></td>
																			<td>41</td>
																			<td><input type="checkbox" name="chk42"
																				id="chk42" value="42" disabled="disabled"></td>
																			<td>42</td>
																			<td><input type="checkbox" name="chk43"
																				id="chk43" value="43" disabled="disabled"></td>
																			<td>43</td>
																			<td><input type="checkbox" name="chk44"
																				id="chk44" value="44" disabled="disabled"></td>
																			<td>44</td>
																			<td><input type="checkbox" name="chk45"
																				id="chk45" value="45" disabled="disabled"></td>
																			<td>45</td>
																			<td><input type="checkbox" name="chk46"
																				id="chk46" value="46" disabled="disabled"></td>
																			<td>46</td>
																			<td><input type="checkbox" name="chk47"
																				id="chk47" value="47" disabled="disabled"></td>
																			<td>47</td>
																			<td><input type="checkbox" name="chk48"
																				id="chk48" value="48" disabled="disabled"></td>
																			<td>48</td>
																			<td><input type="checkbox" name="chk49"
																				id="chk49" value="49" disabled="disabled"></td>
																			<td>49</td>
																			<td><input type="checkbox" name="chk50"
																				id="chk50" value="50" disabled="disabled"></td>
																			<td>50</td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="chk51"
																				id="chk51" value="51" disabled="disabled"></td>
																			<td>51</td>
																			<td><input type="checkbox" name="chk52"
																				id="chk52" value="52" disabled="disabled"></td>
																			<td>52</td>
																			<td><input type="checkbox" name="chk53"
																				id="chk53" value="53" disabled="disabled"></td>
																			<td>53</td>
																			<td><input type="checkbox" name="chk54"
																				id="chk54" value="54" disabled="disabled"></td>
																			<td>54</td>
																			<td><input type="checkbox" name="chk55"
																				id="chk55" value="55" disabled="disabled"></td>
																			<td>55</td>
																			<td><input type="checkbox" name="chk56"
																				id="chk56" value="56" disabled="disabled"></td>
																			<td>56</td>
																			<td><input type="checkbox" name="chk57"
																				id="chk57" value="57" disabled="disabled"></td>
																			<td>57</td>
																			<td><input type="checkbox" name="chk58"
																				id="chk58" value="58" disabled="disabled"></td>
																			<td>58</td>
																			<td><input type="checkbox" name="chk59"
																				id="chk59" value="59" disabled="disabled"></td>
																			<td>59</td>
																			<td><input type="checkbox" name="chk0" id="chk0"
																				value="0" disabled="disabled"></td>
																			<td>0</td>
																		</tr>
																	</table></td>
															</tr>
														</table>
													</td>
												</tr>
											</tbody>
											<tbody style="display: none;">
												<tr>
													<td height="120">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="10" height="26"><div align="left">
																		<input name="xs" id="xs" type="radio" value="xs1"
																			checked onClick="xs_radio_click(this)">
																	</div></td>
																<td width="97%" align="left"><div align="left">每一小时</div></td>
															</tr>
															<tr>
																<td height="20" width="10"><div align="left">
																		<input name="xs" id="xs" type="radio" value="xs2"
																			onClick="xs_radio_click(this)">
																	</div></td>
																<td height="20" align="left"><div align="left">手动指定</div></td>
															</tr>
															<tr>
																<td height="100" colspan="2" align="center"><table
																		width="99%" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>AM：</td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="sj0" id="sj0"
																				value="0" disabled="disabled"></td>
																			<td>0</td>
																			<td><input type="checkbox" name="sj1" id="sj1"
																				value="1" disabled="disabled"></td>
																			<td>1</td>
																			<td><input type="checkbox" name="sj2" id="sj2"
																				value="2" disabled="disabled"></td>
																			<td>2</td>
																			<td><input type="checkbox" name="sj3" id="sj3"
																				value="3" disabled="disabled"></td>
																			<td>3</td>
																			<td><input type="checkbox" name="sj4" id="sj4"
																				value="4" disabled="disabled"></td>
																			<td>4</td>
																			<td><input type="checkbox" name="sj5" id="sj5"
																				value="5" disabled="disabled"></td>
																			<td>5</td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="sj6" id="sj6"
																				value="6" disabled="disabled"></td>
																			<td>6</td>
																			<td><input type="checkbox" name="sj7" id="sj7"
																				value="7" disabled="disabled"></td>
																			<td>7</td>
																			<td><input type="checkbox" name="sj8" id="sj8"
																				value="8" disabled="disabled"></td>
																			<td>8</td>
																			<td><input type="checkbox" name="sj9" id="sj9"
																				value="9" disabled="disabled"></td>
																			<td>9</td>
																			<td><input type="checkbox" name="sj10" id="sj10"
																				value="10" disabled="disabled"></td>
																			<td>10</td>
																			<td><input type="checkbox" name="sj11" id="sj11"
																				value="11" disabled="disabled"></td>
																			<td>11</td>
																		</tr>
																		<tr>
																			<td>PM：</td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="sj12" id="sj12"
																				value="12" disabled="disabled"></td>
																			<td>12</td>
																			<td><input type="checkbox" name="sj13" id="sj13"
																				value="13" disabled="disabled"></td>
																			<td>13</td>
																			<td><input type="checkbox" name="sj14" id="sj14"
																				value="14" disabled="disabled"></td>
																			<td>14</td>
																			<td><input type="checkbox" name="sj15" id="sj15"
																				value="15" disabled="disabled"></td>
																			<td>15</td>
																			<td><input type="checkbox" name="sj16" id="sj16"
																				value="16" disabled="disabled"></td>
																			<td>16</td>
																			<td><input type="checkbox" name="sj17" id="sj17"
																				value="17" disabled="disabled"></td>
																			<td>17</td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="sj18" id="sj18"
																				value="18" disabled="disabled"></td>
																			<td>18</td>
																			<td><input type="checkbox" name="sj19" id="sj19"
																				value="19" disabled="disabled"></td>
																			<td>19</td>
																			<td><input type="checkbox" name="sj20" id="sj20"
																				value="20" disabled="disabled"></td>
																			<td>20</td>
																			<td><input type="checkbox" name="sj21" id="sj21"
																				value="21" disabled="disabled"></td>
																			<td>21</td>
																			<td><input type="checkbox" name="sj22" id="sj22"
																				value="22" disabled="disabled"></td>
																			<td>22</td>
																			<td><input type="checkbox" name="sj23" id="sj23"
																				value="23" disabled="disabled"></td>
																			<td>23</td>
																		</tr>
																	</table></td>
															</tr>
														</table>
													</td>
												</tr>
											</tbody>
											<tbody style="display: none;">
												<tr>
													<td height="120">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="10" height="24"><div align="left">
																		<input name="mt" id="mt" type="radio" value="mt1"
																			checked onClick="mt_radio_click(this)">
																	</div></td>
																<td width="97%" align="left"><div align="left">
																		每一天（<span class="STYLE1">温馨提示：如果使用了周循环天循环不起效！</span>）
																	</div></td>
															</tr>
															<tr>
																<td height="20" width="10"><div align="left">
																		<input name="mt" id="mt" type="radio" value="mt2"
																			onClick="mt_radio_click(this)">
																	</div></td>
																<td height="20" align="left"><div align="left">手动指定</div></td>
															</tr>
															<tr>
																<td height="100" colspan="2" align="center"><table
																		width="100%" border="0" cellpadding="0"
																		cellspacing="0">
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="t1" id="t1"
																				value="1" disabled="disabled"></td>
																			<td>1</td>
																			<td><input type="checkbox" name="t2" id="t2"
																				value="2" disabled="disabled"></td>
																			<td>2</td>
																			<td><input type="checkbox" name="t3" id="t3"
																				value="3" disabled="disabled"></td>
																			<td>3</td>
																			<td><input type="checkbox" name="t4" id="t4"
																				value="4" disabled="disabled"></td>
																			<td>4</td>
																			<td><input type="checkbox" name="t5" id="t5"
																				value="5" disabled="disabled"></td>
																			<td>5</td>
																			<td><input type="checkbox" name="t6" id="t6"
																				value="6" disabled="disabled"></td>
																			<td>6</td>
																			<td><input type="checkbox" name="t7" id="t7"
																				value="7" disabled="disabled"></td>
																			<td>7</td>
																			<td><input type="checkbox" name="t8" id="t8"
																				value="8" disabled="disabled"></td>
																			<td>8</td>
																			<td><input type="checkbox" name="t9" id="t9"
																				value="9" disabled="disabled"></td>
																			<td>9</td>
																			<td><input type="checkbox" name="t10" id="t10"
																				value="10" disabled="disabled"></td>
																			<td>10</td>
																			<td><input type="checkbox" name="t11" id="t11"
																				value="11" disabled="disabled"></td>
																			<td>11</td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="t12" id="t12"
																				value="12" disabled="disabled"></td>
																			<td>12</td>
																			<td><input type="checkbox" name="t13" id="t13"
																				value="13" disabled="disabled"></td>
																			<td>13</td>
																			<td><input type="checkbox" name="t14" id="t14"
																				value="14" disabled="disabled"></td>
																			<td>14</td>
																			<td><input type="checkbox" name="t15" id="t15"
																				value="15" disabled="disabled"></td>
																			<td>15</td>
																			<td><input type="checkbox" name="t16" id="t16"
																				value="16" disabled="disabled"></td>
																			<td>16</td>
																			<td><input type="checkbox" name="t17" id="t17"
																				value="17" disabled="disabled"></td>
																			<td>17</td>
																			<td><input type="checkbox" name="t18" id="t18"
																				value="18" disabled="disabled"></td>
																			<td>18</td>
																			<td><input type="checkbox" name="t19" id="t19"
																				value="19" disabled="disabled"></td>
																			<td>19</td>
																			<td><input type="checkbox" name="t20" id="t20"
																				value="20" disabled="disabled"></td>
																			<td>20</td>
																			<td><input type="checkbox" name="t21" id="t21"
																				value="21" disabled="disabled"></td>
																			<td>21</td>
																			<td><input type="checkbox" name="t22" id="t22"
																				value="22" disabled="disabled"></td>
																			<td>22</td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td><input type="checkbox" name="t23" id="t23"
																				value="23" disabled="disabled"></td>
																			<td>23</td>
																			<td><input type="checkbox" name="t24" id="t24"
																				value="24" disabled="disabled"></td>
																			<td>24</td>
																			<td><input type="checkbox" name="t25" id="t25"
																				value="25" disabled="disabled"></td>
																			<td>25</td>
																			<td><input type="checkbox" name="t26" id="t26"
																				value="26" disabled="disabled"></td>
																			<td>26</td>
																			<td><input type="checkbox" name="t27" id="t27"
																				value="27" disabled="disabled"></td>
																			<td>27</td>
																			<td><input type="checkbox" name="t28" id="t28"
																				value="28" disabled="disabled"></td>
																			<td>28</td>
																			<td><input type="checkbox" name="t29" id="t29"
																				value="29" disabled="disabled"></td>
																			<td>29</td>
																			<td><input type="checkbox" name="t30" id="t30"
																				value="30" disabled="disabled"></td>
																			<td>30</td>
																			<td><input type="checkbox" name="t31" id="t31"
																				value="31" disabled="disabled"></td>
																			<td>31</td>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																		</tr>
																	</table></td>
															</tr>
														</table>
													</td>
												</tr>
											</tbody>
											<tbody style="display: none;">
												<tr>
													<td height="120">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="20" width="20"><div align="left">
																		<input name="my" id="my" type="radio" value="my1"
																			checked onClick="my_radio_click(this)">
																	</div></td>
																<td width="570"><div align="left">每一月</div></td>
															</tr>
															<tr>
																<td height="20" width="20"><div align="left">
																		<input name="my" id="my" type="radio" value="my2"
																			onClick="my_radio_click(this)">
																	</div></td>
																<td height="20"><div align="left">手动指定</div></td>
															</tr>
															<tr>
																<td height="100" colspan="2" align="center"><table
																		width="95%" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="4%"><input type="checkbox" name="y1"
																				id="y1" value="1" disabled="disabled"></td>
																			<td width="11%">1</td>
																			<td width="5%"><input type="checkbox" name="y2"
																				id="y2" value="2" disabled="disabled"></td>
																			<td width="12%">2</td>
																			<td width="5%"><input type="checkbox" name="y3"
																				id="y3" value="3" disabled="disabled"></td>
																			<td width="12%">3</td>
																			<td width="4%"><input type="checkbox" name="y4"
																				id="y4" value="4" disabled="disabled"></td>
																			<td width="13%">4</td>
																			<td width="4%"><input type="checkbox" name="y5"
																				id="y5" value="5" disabled="disabled"></td>
																			<td width="13%">5</td>
																			<td width="4%"><input type="checkbox" name="y6"
																				id="y6" value="6" disabled="disabled"></td>
																			<td width="13%">6</td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="y7" id="y7"
																				value="7" disabled="disabled"></td>
																			<td>7</td>
																			<td><input type="checkbox" name="y8" id="y8"
																				value="8" disabled="disabled"></td>
																			<td>8</td>
																			<td><input type="checkbox" name="y9" id="y9"
																				value="9" disabled="disabled"></td>
																			<td>9</td>
																			<td><input type="checkbox" name="y10" id="y10"
																				value="10" disabled="disabled"></td>
																			<td>10</td>
																			<td><input type="checkbox" name="y11" id="y11"
																				value="11" disabled="disabled"></td>
																			<td>11</td>
																			<td><input type="checkbox" name="y12" id="y12"
																				value="12" disabled="disabled"></td>
																			<td>12</td>
																		</tr>
																	</table></td>
															</tr>
														</table>
													</td>
												</tr>
											</tbody>
											<tbody style="display: none;">
												<tr>
													<td height="120">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="20" width="10"><input name="mzhou"
																	id="mzhou" type="checkbox" value="checkbox"
																	onclick="zhou_checkbox_click()"></td>
																<td width="100%"><div align="left">
																		每一周(<span class="STYLE1">温馨提示：选择周循环则天循环失效！</span>)
																	</div></td>
															</tr>
															<tr>
																<td height="50" colspan="2" align="center"><table
																		width="90%" border="0" cellpadding="0"
																		cellspacing="0 ">
																		<tr>
																			<td width="51"><div align="left">
																					<input name="mzh" id="mzh" type="radio"
																						disabled="disabled" value="mzh1" checked
																						onclick="zhou_radio_click(this)">
																				</div></td>
																			<td colspan="14"><div align="left">每一周</div></td>
																		</tr>
																		<tr>
																			<td><div align="left">
																					<input name="mzh" id="mzh" type="radio"
																						value="mzh2" disabled="disabled"
																						onclick="zhou_radio_click(this)">
																				</div></td>
																			<td colspan="14"><div align="left">手动指定</div></td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td width="10"><input type="checkbox"
																				name="zhou1" id="zhou1" value="1"
																				disabled="disabled"></td>
																			<td width="35">周一</td>
																			<td width="10"><input type="checkbox"
																				name="zhou2" id="zhou2" value="2"
																				disabled="disabled"></td>
																			<td width="35">周二</td>
																			<td width="10"><input type="checkbox"
																				name="zhou3" id="zhou3" value="3"
																				disabled="disabled"></td>
																			<td width="35">周三</td>
																			<td width="10"><input type="checkbox"
																				name="zhou4" id="zhou4" value="4"
																				disabled="disabled"></td>
																			<td width="35">周四</td>
																			<td width="10"><input type="checkbox"
																				name="zhou5" id="zhou5" value="5"
																				disabled="disabled"></td>
																			<td width="35">周五</td>
																			<td width="10"><input type="checkbox"
																				name="zhou6" id="zhou6" value="6"
																				disabled="disabled"></td>
																			<td width="35">周六</td>
																			<td width="10"><input type="checkbox"
																				name="zhou7" id="zhou7" value="7"
																				disabled="disabled"></td>
																			<td width="35">周日</td>
																		</tr>
																	</table></td>
															</tr>
														</table>
													</td>
												</tr>
											</tbody>

											<tbody style="display: none;">
												<tr>
													<td height="120"><table width="100%" border="0">
															<tr>
																<td width="30%"><div align="center">开始时间：</div></td>
																<td width="70%" align="left"><input
																	style="height: 20px; width: 200px" id="d4311"
																	class="Wdate" type="text"
																	onFocus="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01 00:00:00\'}'})" />
																</td>
															</tr>
															<tr>
																<td><div align="center">结束时间：</div></td>
																<td align="left"><input
																	style="height: 20px; width: 200px" id="d4312"
																	class="Wdate" type="text"
																	onFocus="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01 00:00:00'})" /></td>
															</tr>
														</table></td>
												</tr>
											</tbody>
										</table>
										<div align="center" class="row" valign="middle"
											style="margin-top: 5px">
											<div class="col-md-3" style="padding: 0">
												<button type="button" class="btn btn-sm"
													onclick="getvalues()">生成表达式</button>
											</div>
											<div class="col-md-9"
												style="padding: 0; margin-top: 2px; border: 1px solid grey">
												<div class="col-md-4" align="left" valign="middle"
													style="padding: 0; margin-left: 8px; margin-top: 2px;">
													<label valign="middle">解析结果：</label>
												</div>
												<div class="col-md-6" align="left"
													style="padding: 0; margin-top: 2px;">
													<input type="text" id="cronTime" name="cronTime"
														style="border: 0"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>

						<div>
							<button type="button" class="btn btn-block" onclick="chk()">选择模板</button>
						</div>

					</div>
					<div class="col-md-7 column form-group"
						style="margin: 30px 0 0 50px; height: 700px">
						<script id="myEditor" name="content" type="text/plain">
							请在此处编辑内容
						</script>
						<script type="text/javascript"
							src="<%=request.getContextPath()%>/ueditor/ueditor.config.js"></script>
						<script type="text/javascript"
							src="<%=request.getContextPath()%>/ueditor/ueditor.all.js"></script>
						<script type="text/javascript" name="editor">
							var ue = UE.getEditor('myEditor', {
								initialFrameHeight : 550,
								autoHeightEnabled : false
							});
						</script>
					</div>
				</div>
				<div class="form-group" style="margin: 30px 0 30px 0" align="center">
					<textarea id="nonehtmlarea" name="nonehtmlarea" style="display: none"></textarea><!-- display: none -->
				</div>
				<div class="form-group" style="margin: 30px 0 30px 0" align="center">
					<button type="submit" class="btn btn-lg" onclick="beforeSubmit()">完成</button>
				</div>
			</form>
		</div>
	</div>

</body>
<script language="JavaScript" type="text/javascript">
	function secBoard(n) {
		for (i = 0; i < secTable.rows[0].cells.length; i++)
			secTable.rows[0].cells[i].className = "sec1";
		secTable.rows[0].cells[n].className = "sec2";
		for (i = 0; i < mainTable.tBodies.length; i++)
			mainTable.tBodies[i].style.display = "none";
		mainTable.tBodies[n].style.display = "block";
	};
	
	function groupFilter() {
		//alert("!");
		var group_id = new Array();
		var group_name = new Array();
		var member_count = new Array();
		var create_time = new Array();
		var channels = new Array();
		var table = document.getElementById("groupTable");
		$("#groupTable  tr:not(:first)").empty("");
		var newBody = document.createElement("tbody");
		var channel = document.getElementById("channel").value;
		
		<%
		@SuppressWarnings("unchecked")
		List<Group> groups = (List<Group>)session.getAttribute("groups");
		if(groups != null) {
			for(int i = 0; i < groups.size(); i++) 
			{ %>
				group_id[<%=i%>] = <%=((Group)groups.get(i)).getId()%>;
				group_name[<%=i%>] = '<%=((Group)groups.get(i)).getName()%>';
				member_count[<%=i%>] = <%=((Group)groups.get(i)).getCount()%>;
				create_time[<%=i%>] = '<%=((SimpleDateFormat)session.getAttribute("dateFormat")).
						format(((Group)groups.get(i)).getCreateTime())%>';
				channels[<%=i%>] = <%=((Group)groups.get(i)).getChannel()%>;
				if(channel == channels[<%=i%>]) {
					var tr=document.createElement("tr");
					var td1=document.createElement("td");
					var td2=document.createElement("td");
					var td3=document.createElement("td");
					var td4=document.createElement("td");
					
					var checkBox=document.createElement("input");
					checkBox.setAttribute("type","checkbox");
					checkBox.setAttribute("name",group_name[<%=i%>]);
					checkBox.setAttribute("value",group_id[<%=i%>]);
					var textValue1=document.createTextNode(group_name[<%=i%>]);
					var textValue2=document.createTextNode(member_count[<%=i%>]);
					var textValue3=document.createTextNode(create_time[<%=i%>]);
					td1.appendChild(checkBox);
					td2.appendChild(textValue1);
					td3.appendChild(textValue2);
					td4.appendChild(textValue3);
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					newBody.appendChild(tr);
				}
			
			
			
			<%}
		}%>
		table.appendChild(newBody);
		$("#groupModal").modal('show');
		
	};
	
	function chk() {
		
		var inputs = document.getElementsByTagName("input");//获取所有的input标签对象
		var checkboxArray = [];//初始化空数组，用来存放checkbox对象。
		for(var i=0;i<inputs.length;i++){
		  var obj = inputs[i];
		  if(obj.type=='checkbox'){
		     checkboxArray.push(obj);
		  }
		}
		
		
		//var obj = document.getElementsByType('checkbox');
		var ids = '';
		var names = '';
		for(var i = 0; i < checkboxArray.length; i++) {
			if(checkboxArray[i].checked) {
				ids += checkboxArray[i].value + ' ';
				names += checkboxArray[i].name + ' ';
			}
		}
		if(ids != '') {
			document.getElementById('groupids').value=ids;
			document.getElementById('groupnames').value=names;
		}
		$('#groupModal').modal('hide');
		
	};
	
	function editWeixin() {
		var value = document.getElementById('channel').value;
		if(value == 8) {
			UE.getEditor('myEditor').setContent('<p>模版id：</p>' + 
					'<p>转向连接：</p>' + 
					'<p>首行：</p>' + 
					'<p>标题：</p>' + 
					'<p>时间：</p>' +
					'<p>内容：</p>' +
					'<p>附加说明：</p>');
		} else {
			UE.getEditor('myEditor').setContent('<p>请编辑内容</p>');
		}
		
	}
	
	
	function beforeSubmit() {
		//检查各个项是否为空，值是否违法
		
		//在提交之前将编辑框内的“纯文本内容”拷贝到一个隐藏域中
		var content = UE.getEditor('myEditor').getContentTxt();
		document.getElementById('nonehtmlarea').innerText = content;
	};
	
	function singleClick() {
		document.getElementById('pushtype').value = 0;
	}
	function cronClick() {
		document.getElementById('pushtype').value = 1;
	}
	
	
	
</script>
</html>
