<%@page import="lyp.entity.PageModel"%>
<%@page import="lyp.entity.Bulletin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="barPath" value="${path }/back/bulletinManage?op=${op }&keywords=${keywords }&" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>公告信息管理</title>
<link rel="stylesheet" type="text/css" href="${path }/css/bulletinManage.css">
<script type="text/javascript" src="${path }/js/manage.js"></script>
</head>
<body>
	<form name="myForm" action="${path}/back/bulletinManage" method="post" onsubmit="return search();">
	<input type="hidden"  id="op" name="op" value="">
	<input type="hidden"  id="id" name="id" value="">
	<input type="hidden"  id="cleckid" name="cleckid" value="">
		<div id="send">
			<label>公告信息管理</label><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
			<span class="topSpan"> 
			<input class="search" type="text" id="keywords" name="keywords" value="${keywords }"/><c:remove var="keywords"/>
			<input class="btn" type="submit" value="查询公告" onclick="return search();" /> 
			<input class="topBtn btn" type="button" value="添加公告" onclick="location.href='${path}/back/bulletinManage?op=toAdd'" /> 
			<input class="topBtn btn" type="button" value="删除公告" onclick="delclick();" />
			</span>
		</div>
	</form>
	<hr />
	<div class="dd">
		<table>
			<tr>
				<th class="checkbox"><input type="checkbox" id="checkAll" onclick="all_check();" /></th>
				<th class="btitle">公告编号</th>
				<th class="btitle">公告标题</th>
				<th class="btitle">公告内容</th>
				<th class="btitle">发布者</th>
				<th class="btitle">发布时间</th>
				<th class="btitle">操作</th>
			</tr>
				<c:forEach var="bulletin" items="${pm.data }">
				<fmt:formatDate var="date" value="${bulletin.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				<tr>
					<td class="checkbox"><input type="checkbox" name="checks" onclick="clickid(this);" value="${bulletin.id}"/></td>
					<td style="width:76px;">${bulletin.id}</td>
					<td style="width:76px;">${bulletin.title}</td>
					<td class="btitle">${bulletin.content}</td>
					<td style="width:76px;">${bulletin.user.userName}</td>
					<td class="btitle">${date }</td>
					<td class="btitle">
					<div class="tableBtn">
					<input class="btn" type="button" value="修改" onclick="location.href='${path}/back/bulletinManage?op=toUpdate&id=${bulletin.id}'" /> 
					<input class="btn" type="button" value="删除" onclick="if(confirm('确定删除该数据')){location.href='${path}/back/bulletinManage?op=bulletinDelete&id=${bulletin.id}'}" />
					</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="7">
			<%@ include file="pagebar.html" %>
			</td>
			</tr>
		</table>
	</div>
</body>
</html>

