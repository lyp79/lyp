<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="barPath" value="${path }/front/showBulletinList.jsp?op=${op }&" />
<c:if test="${empty requestScope.pm }">
<jsp:forward page="/front/showBulletin">
	<jsp:param value="showBulletinList" name="op"/>
</jsp:forward>
</c:if>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>公告列表-千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
	<link rel="stylesheet" href="${path }/front/css/common.css" type="text/css" />
	<style type="text/css">
	#pagebar{text-align: center;}
	#bulletinList{padding:10px 3px 3px 3px;min-height: 400px;}
	#bulletinList span{font-size:11pt;color:#727171;margin-left:30px;border:0px;}
	#bulletinList a{display:block;text-indent:1em;font-size:11pt;color:#4B8ECE;text-decoration:none;height:35px;line-height:35px;border-bottom:1px dashed #727171;}
	#bulletinList a:hover{color:#FE8802;}
	.pager{text-align:right;height:25px;line-height:25px;border-bottom:0px;font-size:10pt;}
	.bullTime{display: inline-block;float: right;}
	</style>
  </head>
  <body>
<%@ include file="top.jsp" %>  	
	<div id="middle_div">
	    <div class="sitemap">
			您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;网站公告
		</div>
		<div id="bulletinList">
			<c:forEach var="bullList" items="${pm.data }">
			<a href="${path }/front/showBulletin.jsp?id=${bullList.id}">
				${bullList.title }
				<span class="bullTime"><fmt:formatDate value="${bullList.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</a>
			</c:forEach>
		</div>
		<%@ include file="pagebar.html" %>
	</div>
	<%@ include file="bottom.jsp" %>
  </body>
</html>
