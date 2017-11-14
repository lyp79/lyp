<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${empty orderList }">
	<jsp:forward page="/front/index.jsp"></jsp:forward>
</c:if>
<!DOCTYPE HTML>
<html>
<head>
<title>查看个人信息</title>
<link rel="stylesheet" type="text/css" href="${path }/front/css/customerShow.css">
</head>

<body>
	<jsp:include page="top.jsp" />
	
	<fmt:formatDate var="date" value="${cust.regTime }"
		pattern="yyyy-MM-dd HH:mm:ss" />
	<div class="send">
		<table>
			<tr>
				<th colspan="6">个人信息</th>
			</tr>
			<tr>
				<td>我的账号/邮箱</td>
				<td>${cust.email }</td>
				<td>注册时间</td>
				<td>${date }</td>
				<td>姓名</td>
				<td>${cust.custDetail.name }</td>
			</tr>
			<tr>
				<td>固定电话</td>
				<td>${cust.custDetail.telPhone }</td>
				<td>移动电话</td>
				<td>${cust.custDetail.movePhone }</td>
				<td>收货地址</td>
				<td>${cust.custDetail.address }</td>
			</tr>
		</table>
		<table>
			<tr>
				<th colspan="3">我的订单</th>
			</tr>
			<c:forEach var="orderInfo" items="${orderList }">
			<tr>
				<td class = "orders">订单编号: ${orderInfo.orderId }</td>
				<c:if test="${orderInfo.orderStatus==0 }">
				<td class = "orders">
				订单状态: <span class="no">未确认</span>
				</td>
				</c:if>
				<c:if test="${orderInfo.orderStatus==1 }">
				<td class = "orders">
				订单状态: <span class="yes">已确认</span>
				</td>
				</c:if>
				<td class = "orders">下单时间: <fmt:formatDate value="${orderInfo.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<th class = "goods">商品名称</th>
				<th class = "goods">购买价格</th>
				<th class = "goods">商品数量</th>
			</tr>
			<c:forEach var="orderDetail" items="${orderInfo.goodsList }">
			<tr>
			<td class = "goods">${orderDetail.goods.goodsName }&nbsp;<img src="${path }/product/${orderDetail.goods.photo }" width="30px"/></td>
			<td class = "goods">&yen;&nbsp;<fmt:formatNumber value="${orderDetail.buyPrice }" pattern="#.00" /></td>
			<td class = "goods">${orderDetail.quantity }</td>
			</tr>
			</c:forEach>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="bottom.jsp" />
</body>
</html>
