<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>后台用户登录</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/login.css" >
	<script type="text/javascript" src="${path }/login.js"></script>
  </head>
  <body>
    <div class="login">
    <form action="${path }/userLogin?op=login" method="post">
	    <div class="login_c"><label>用户名: </label><input type="text" name="userName" value="lyp"/><span id="namemsg"> ${namemsg }</span><c:remove var="namemsg"/></div>
	    <div class="login_c"><label>密&nbsp;&nbsp;&nbsp;码: </label><input type="password" name="userPass" value="admin"/><span id="passmsg"> ${passmsg }</span><c:remove var="passmsg"/></div>
	    <div class="login_btn">
		    <input class="btns" type="submit" value="" />
			<input class="btnr" type="reset" value="" />
		</div>
    	<div class="login_c" id="msg">${msg }</div>
    	<c:remove var="msg"/>
    </form>
    </div>
  </body>
</html>
