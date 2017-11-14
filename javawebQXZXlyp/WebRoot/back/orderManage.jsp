<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="barPath" value="${path }/back/orderManage?op=${op }&keywords=${keywords }&orderStatus=${orderStatus }&" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理页面</title>
<link href="${path }/css/orderManage.css" type="text/css" rel="stylesheet">
<style type="text/css">
.no_confirm{color:#f00;}
</style>
<script type="text/javascript" src="${path }/js/manage.js"></script>
</head>
<body>
<form name="myForm" action="${path }/back/orderManage" method="post" onsubmit="return search_click();">
	<input type="hidden"  id="op" name="op" value="">
	<input type="hidden"  id="id" name="id" value="">
	<input type="hidden"  id="cleckid" name="cleckid" value="">
		<input type="hidden" id="orderStatus" name="orderStatus" value="${empty param.orderStatus?'null':param.orderStatus }" />
		<div id="send">
			<label>订单信息管理</label><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
			<span class="topSpan"> 
			<input class="search" type="text" id="keywords" name="keywords" value="${keywords }"/><c:remove var="keywords"/>
			<input class="btn" type="submit" value="查询" onclick="return search();" /> 
			<input class="topBtn btn" type="button" value="删除订单" onclick="delclick();" />
			</span>
		</div>
	</form>
	<table id="dataTable">
	<tr>
		<th class="checkbox"><input type="checkbox" id="checkAll" onclick="all_check();" /></th>
		<th class="btitle">订单编号</th>
		<th class="btitle">订单状态</th>
		<th class="btitle">下单时间</th>
		<th class="btitle">客户账户/邮箱</th>
		<th class="btitle">收货人</th>
		<th class="btitle">固定电话</th>
		<th class="btitle">移动电话</th>
		<th class="btitle">操作</th>
	</tr>
	<c:forEach var="oi" items="${pm.data }">
	<tr>
		<td class="checkbox tdcenter">
			<input type="checkbox" ${oi.orderStatus==1?"disabled='disabled'":"" } name="checks" onclick="clickid(this);" value="${oi.orderId }"/>
		</td>
		<td>${oi.orderId }</td>
		<td>
			<a href="${path }/back/orderManage?op=toShowOrder&orderStatus=${oi.orderStatus }" title="查询订单">
			   <c:if test="${oi.orderStatus==0 }" >
				<span class='no_confirm'>未确认</span>
				</c:if>
				<c:if test="${oi.orderStatus==1 }" >
					已确认
				</c:if>
				</a>
				
		</td>
		<td><fmt:formatDate value="${oi.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>
			<a href="${path }/back/orderManage?op=toShow&id=${oi.customer.id }" title="查看客户信息">${oi.customer.email }</a>
		</td>
		<td>${oi.customer.custDetail.name }</td>
		<td>${oi.customer.custDetail.telPhone }</td>
		<td>${oi.customer.custDetail.movePhone }</td>
		<td class="tdcenter">
		    <c:if test="${oi.orderStatus==0 }">
		    <input class="btn" type="button" value="确认订单" onclick="if(confirm('是否要确认订单？')){location.href='${path}/back/orderManage?op=changeStatus&id=${oi.orderId }&orderStatus=${oi.orderStatus }'}" />
			</c:if>
			<c:if test="${oi.orderStatus==1 }">
			<input class="btn" type="button" value="取消确认" onclick="if(confirm('是否要取消订单？')){location.href='${path}/back/orderManage?op=changeStatus&id=${oi.orderId }&orderStatus=${oi.orderStatus }'}" />
			</c:if>
			<input class="btn" type="button" value="详情" onclick="location.href='${path}/back/orderManage?op=showOrderDetail&id=${oi.orderId }'" />
			<c:if test="${oi.orderStatus==0 }">
			<input class="btn" type="button" value="删除" onclick="if(confirm('确定删除该数据')){location.href='${path}/back/orderManage?op=delOrder&id=${oi.orderId }'}" />
			</c:if>
			<c:if test="${oi.orderStatus==1 }">
			<a title="删除此订单">删除</a>
			</c:if>
		</td>
	</tr>
   </c:forEach>
	<tr>
		<td colspan="9">
			<%@ include file="pagebar.html" %>	
		</td>
	</tr>
	</table>
</body>
</html>