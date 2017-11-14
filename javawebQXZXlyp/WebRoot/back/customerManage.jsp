<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var = "barPath" value = "${path }/back/customerManage?op=${op }&keywords=${keywords }&" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="${path }/css/customer.css">
<script type="text/javascript" src="${path }/js/manage.js"></script>
</head>
<body>
	<form name="myForm" action="${path}/back/customerManage" method="post" onsubmit="return search();">
	<input type="hidden"  id="op" name="op" value="">
	<input type="hidden"  id="id" name="id" value="">
	<input type="hidden"  id="cleckid" name="cleckid" value="">
		<div id="send">
			<label>客户信息管理</label><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
			<span class="topSpan"> 
			<input class="search" type="text" id="keywords" name="keywords" value="${keywords }"/><c:remove var="keywords"/>
			<input class="btn" type="submit" value="查询" onclick="return search();" /> 
			<input class="topBtn btn" type="button" value="删除账户" onclick="delclick();" />
			</span>
		</div>
	</form>
	<hr />
	<div class="dd">
		<table>
			<tr>
				<th class="checkbox"><input type="checkbox" id="checkAll" onclick="all_check();" /></th>
				<th class="btitle">客户编号</th>
				<th class="btitle">客户账号/邮箱</th>
				<th class="btitle">注册时间</th>
				<th class="btitle">收货人姓名</th>
				<th class="btitle">固定电话</th>
				<th class="btitle">移动电话</th>
				<th class="btitle">收货地址</th>
				<th class="btitle">操作</th>
			</tr>
				<c:forEach var="customer" items="${pm.data }">
				<fmt:formatDate var="date" value="${customer.regTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				<tr>
					<td class="checkbox"><input type="checkbox" name="checks" onclick="clickid(this);" value="${customer.id}"/></td>
					<td>${customer.id}</td>
					<td>${customer.email}</td>
					<td>${date}</td>
					<td>${customer.custDetail.name}</td>
					<td>${customer.custDetail.telPhone}</td>
					<td>${customer.custDetail.movePhone}</td>
					<td>${customer.custDetail.address }</td>
					<td class="btitle">
					<div class="tableBtn">
					<input class="btn" type="button" value="修改" onclick="location.href='${path}/back/customerManage?op=toUpdate&id=${customer.id}'" /> 
					<input class="btn" type="button" value="删除" onclick="if(confirm('确定删除该数据')){location.href='${path}/back/customerManage?op=customerDel&id=${customer.id}'}" />
					</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="9">
			<%@ include file="pagebar.html" %>
			</td>
			</tr>
		</table>
	</div>
</body>
</html>
