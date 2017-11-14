<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录/注册-千里之行-在线销售旅游产品</title>
<link rel="stylesheet" href="${path }/front/css/common.css" type="text/css" />
<link rel="stylesheet" href="${path }/front/css/reg.css" type="text/css" />
<script type="text/javascript" src="${path }/front/js/ajax.js"></script>
<script type="text/javascript" src="${path }/front/js/reg.js"></script>
</head>
<body>
<%@ include file="top.jsp" %>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;注册/登录
	</div>
	<div id="login_div">
		<img src="${path }/front/images/login_logo.gif"  />
		<form name="loginForm" action="${path }/front/custFrontManage" onsubmit="return loginFormCheck()" method="post" target="_top">
			<input type="hidden" name="op" value="login" />
			<table>
				<tr>
					<td>用户账户/邮箱:</td>
					<td><input class="input_box1" type="text" name="userName" value="admin@admin.com"/></td>		
					<td rowspan="3"><input type="submit" class="btn_login" value="" /></td>			
				</tr>
				<tr>
					<td>用户密码:</td>
					<td><input class="input_box1" type="password" name="userPwd" value="admin"/></td>				
				</tr>
				<tr>
					<td>验证码:</td>
					<td><input class="input_box1 short_width" type="text" name="yzm" /><img class="yzm" src="${path }/front/yzmServlet"/></td>				
				</tr>
			</table>
			<div class="errorDiv" id="msg">${msg }</div><c:remove var="msg"/>
		</form>
	</div>
	<div id="register_div">
		<img src="${path }/front/images/register_logo.gif" />
		<form name="registerForm" action="${path }/front/custFrontManage" method="post">
			<input type="hidden" name="op" value="register" />
			<table class="register_table">
				<tr>
					<td>用户账户/邮箱:</td>
					<td>
						<input class="input_box1" type="text" name="email" />
						<input type="button" class="btn61_21" onclick="validateEmail('${path}')" value="检测邮箱" />
						<span id="emailMsg"></span><c:remove var="emailMsg"/>
					</td>		
				</tr>
				<tr>
					<td>密码:</td>
					<td>
						<input class="input_box1" type="password" name="pwd" />	
						<span class="tooltip">登录密码。</span>
						<span id="msg">${remsg }</span><c:remove var="msg"/>
					</td>			
				</tr>
				<tr>
					<td>确认密码:</td>
					<td>
						<input class="input_box1" type="password" name="repwd" />
						<span class="tooltip">请再次输入密码。</span>
					</td>		
				</tr>
				<tr>
					<td colspan="2" style="font-weight:bold;">
						<input type="checkbox" onclick="showGaojiOption(this)" name="chkGaoji" />
						高级选项<span class="tooltip">高级选项填写关于配送的信息。若勾选此选项，则以下必填。</span>
					</td>
				</tr>
			</table>
			<div id="gaoji" style="display:none;">
				<div>收货人姓名:
					<input class="input_box1" name="name" type="text" value="" />
					<span class="tooltip">请填写真实的姓名。</span>
				</div>	
				<div>固定电话:&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="input_box"  type="text" name="telPhone" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</div>	
				<div>移动电话:&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="input_box1"  type="text" name="movePhone" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</div>
				<div>收货地址:&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="input_box_long" type="text" name="address"  />
				</div>	
			</div>
		<div style="text-align:left;"><input type="button" class="btn_register" onclick="submitForm('${path}')" /></div>
		</form>
	</div>
</div>
<%@ include file="bottom.jsp" %>
</body>
</html>