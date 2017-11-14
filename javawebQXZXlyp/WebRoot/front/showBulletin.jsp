<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${empty requestScope.bull }">
<jsp:forward page="/front/showBulletin">
	<jsp:param value="showBulletin" name="op"/>
</jsp:forward>
</c:if>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path}/front/css/common.css" type="text/css"></link>
	<title>测试数据-公告标题5-公告信息-千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
	<style type="text/css">
	.div_bulletin1 {border: 1px solid #CCE3F1;padding:20px 10px 20px 10px;min-height: 400px;}
	.content1 {font-size: 14px;text-align: left;padding:10px;margin-left:auto;margin-right:auto;padding:10px 50px 10px 50px;}
	p{text-indent: 2em;word-spacing: 5px;line-height: 24px;}
	.title1 {font-size: 20px;margin-bottom:15px;text-align: center;font-weight: bold;}
	.info1 {font-size: 12px;margin: 3px;text-align: center;}
	</style>
</head>
<body>
	<%@ include file="top.jsp" %>
	<div id="middle_div">
	    <div class="sitemap">
			您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;
			<a href="${path }/front/showBulletinList.jsp?pageNo=1">网站公告</a>&nbsp;&gt;&nbsp;公告信息
		</div>
		<div class="div_bulletin1">
			<div class="title1">${bull.title }</div>
			<div class="info1">发布者：${bull.user.userName }&nbsp;&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value="${bull.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;</div>
			<div class="content1">${bull.content }</div>
		</div>
	</div>
  <%@ include file="bottom.jsp" %>
  </body>
</html>
