<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="lyp.entity.UserInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
  <head>
    <title>密码修改页面</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/changePass.css">
	<script type="text/javascript" src="${path }/js/changePass.js"></script>
  </head>
  
  <body>
  <div id="loginback">
		<h2>用户密码修改</h2>
		<form action="${path }/back/userManage?op=changePass" method="post" onsubmit="return checkPass()">
			<div class="jj">
				<label>当前用户名:</label><input id="inputU" type="text" name="userName" readonly="readonly" value="${user.userName }" maxlength="12" />
			</div>
			<div class="jj">
				<label>旧原密码:</label><input id="oinputP" type="password" name="oldUserPass" maxlength="16" />
			</div>
			<div class="jj">
				<label>输入新密码:</label><input id="ninputP" type="password" name="newUserPass" maxlength="16" />
			</div>
			<div class="jj">
				<label>确认新密码:</label><input id="reninputP" type="password" name="reNewUserPass" maxlength="16" />
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
