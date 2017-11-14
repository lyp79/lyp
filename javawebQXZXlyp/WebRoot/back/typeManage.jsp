<%@ page import="lyp.entity.PageModel"%>
<%@ page import="lyp.entity.GoodsType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="barPath" value="${path }/back/typeManage?op=${op }&keywords=${keywords }&" />
<!DOCTYPE HTML>
<html>
  <head>
    <title>商品类型管理</title>
	<link rel="stylesheet" type="text/css" href="${path}/css/typeManage.css">
	<script type="text/javascript" src="${path }/js/manage.js"></script>
  </head>
  <body>
  	<form name="myForm" action="${path}/back/typeManage" method="post" onsubmit="return search();">
		<input type="hidden"  id="op" name="op" value="">
		<input type="hidden"  id="id" name="id" value="">
		<input type="hidden"  id="cleckid" name="cleckid" value="">
		<div id="send">
			<label>商品类型管理</label><span class="dd msgfont">${msg }<c:remove var="msg" /></span>
			<span class="topSpan"> 
			<input class="search" type="text" id="keywords" name="keywords" value="${keywords }"/><c:remove var="keywords"/>
			<input class="btn" type="submit" value="查询公告" onclick="return search();" /> 
			<input class="topBtn" type="button" value="添加类型" onclick="location.href='${path}/back/typeManage?op=typeAdd'" /> 
			<input class="topBtn" type="button" value="删除类型" onclick="delclick();" />
			</span>
		</div>
	</form>
	<hr />
	<div class="dd">
		<table>
			<tr>
				<th class="checkbox"><input type="checkbox" onclick="all_check();" /></th>
				<th>类型编号</th>
				<th>类型名称</th>
				<th class="tableBtn">操作</th>
			</tr>
			<c:forEach var="goodsType" items="${pm.data }">
			<tr>
				<td class="checkbox"><input type="checkbox" /></td>
				<td class="tyid">${goodsType.typeId }</td>
				<td class="td">${goodsType.typeName }</td>
				<td>
				<input type="button" value="修改" onclick="location.href='${path}/back/typeManage?op=toUpdate&typeId=${goodsType.typeId }'" /> 
				<input type="button" value="删除" onclick="if(confirm('确定要删除该数据？')){location.href='${path}/back/typeManage?op=typeDel&typeId=${goodsType.typeId }'}" />
				</td>
			</tr>
			</c:forEach>
			<tr>
			<td colspan="4">
			<%@ include file="pagebar.html" %>
			</td>
			</tr>
		</table>

	</div>
  </body>
</html>
