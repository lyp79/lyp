<%@page import="lyp.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
  <head>
    <title>用户名修改页面</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/changeName.css">
	<script type="text/javascript" src="${path }/js/changeName.js"></script>
  </head>
  
  <body>
  <div id="loginback">
  		<h2>用户名修改</h2>
		<form action="${path }/back/userManage?op=changeName" method="post" onsubmit="return checkName()">
			<div class="jj">
				<label>当前用户名:</label><input id="inputU" type="text" name="userName" readonly="readonly" value="${user.userName }" maxlength="12" />
			</div>
			<div class="jj">
				<label>确认登录密码:</label><input id="inputP" type="password" name="userPass" value="" maxlength="12" />
			</div>
			<div class="jj">
				<label>新用户名:</label><input id="newInputU" type="text" name="newUserName" value="" maxlength="12" />
			</div>
			<div class="jj">
				<label>确认新用户名:</label><input id="reNewInputU" type="text" name="reNewUserName" value="" maxlength="12" />
			</div>
			<div id="btn" class="jj">
				<input type="submit" class="sub btn" value="确认" />
				<input type="reset" class="res btn" value="重置" />
				<input type="button" class="res btn" value="返回" onclick="history.go(-1)"/>
			</div>
			<div id="msg">${msg }</div> 
			<c:remove var="msg" />
		</form>
	</div>
  </body>
</html>
