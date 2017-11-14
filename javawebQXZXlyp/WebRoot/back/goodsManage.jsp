<%@page import="lyp.entity.PageModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="barPath" value="${path }/back/goodsManage?op=${op }&keywords=${keywords }&" />
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>商品信息管理</title>
<link rel="stylesheet" type="text/css" href="${path }/css/goodsManage.css">
<script type="text/javascript" src="${path }/js/manage.js"></script>
</head>
<body>
	<form name="myForm" action="${path}/back/goodsManage" method="post" onsubmit="return search();">
	<input type="hidden"  id="op" name="op" value="">
	<input type="hidden"  id="id" name="id" value="">
	<input type="hidden"  id="cleckid" name="cleckid" value="">
		<div id="send">
			<label>商品管理</label><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
			<span class="topSpan"> 
			<input class="search" type="text" id="keywords" name="keywords" value="${keywords }"/><c:remove var="keywords"/>
			<input class="btn" type="submit" value="查询公告" onclick="return search();" /> 
			<input class="topBtn btn" type="button" value="添加公告" onclick="location.href='${path}/back/goodsManage?op=toAdd'" /> 
			<input class="topBtn btn" type="button" value="删除公告" onclick="delclick();" />
			</span>
		</div>
	</form>
	<hr />
	<div class="dd">
		<table>
			<tr>
				<th class="checkbox"><input type="checkbox" id="checkAll" onclick="all_check();" /></th>
				<th>商品编号</th>
				<th>商品类型名称</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>商品折扣</th>
				<th>商品图片</th>
				<th>是否新品</th>
				<th>是否推荐</th>
				<th>商品状态</th>
				<th>商品描述</th>
				<th>操作</th>
			</tr>
				<c:forEach var="goods" items="${pm.data }">
				<tr>
					<td class="checkbox"><input type="checkbox" name="checks" onclick="clickid(this);" value="${goods.goodsId }"/></td>
					<td class="btitle">${goods.goodsId}</td>
					<td class="btitle">${goods.goodsType.typeName}</td>
					<td class="btitle">${goods.goodsName}</td>
					<td class="btitle">￥${goods.price}</td>
					<td class="btitle">${goods.discount==10?'-':goods.discount }</td>
					<td class="btitle"><img src="${path }/product/${goods.photo}" width="50"/></td>
					<td class="btitle">${goods.isNew==0?"新品":"非新品"}</td>
					<td class="btitle">${goods.isRecommend==0?"推荐":"非推荐"}</td>
					<td class="btitle">${goods.status==0?"上架":"下架"}</td>
					<td class="btitle">${goods.remark }</td>
					<td class="btitle">
					<div class="tableBtn">
					<input type="button" value="修改" onclick="location.href='${path }/back/goodsManage?op=toUpdate&id=${goods.goodsId }'" />
					<input type="button" value="删除" onclick='if(confirm("确定删除该数据")){location.href="${path }/back/goodsManage?op=goodsDel&id=${goods.goodsId }"}' />
					</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="12">
			<%@ include file="pagebar.html" %>
			</td>
			</tr>
		</table>

	</div>
	
</body>
</html>

