<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
 <head>
<title>欢迎登录后台管理页面</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path }/css/welcome.css">
</head>

<body>
	<div class="pic">
		
		
		<div class="pic1">
			<img alt="欢迎登录图片" src="${path }/img/welcome.png" />
		<span class="msgs">${msg }</span>
		<c:remove var="msg"/>
		</div>
		<div class="pic2">
			<img alt="后台管理图片" src="${path }/img/welcome_2.gif" />
		</div>
	</div>
</body>
</html>
