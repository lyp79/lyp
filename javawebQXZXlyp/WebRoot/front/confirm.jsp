<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML>
<html>
 <meta charset="UTF-8">
<title>订单确认-千里之行，在线销售旅游产品</title>
<link rel="stylesheet" href="${path }/front/css/common.css" type="text/css" />
<link rel="stylesheet" href="${path }/front/css/confirm.css" type="text/css" />
</head>
<body>
<%@ include file="top.jsp" %>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;
		<a target="_top" href="showCart.jsp">购物车</a>&nbsp;&gt;&nbsp;订单确认
	</div>
	
	<div class="title"><span>千里之行</span>购物订单确认表</div>
	<div>
		<div class="item_title">客户信息</div>
		<div class="item_content">
			<table>
				<tr>
					<td class="title">客户编号</td>
					<td>${customerLogin.id }</td>
					<td class="title">注册时间</td>
					<td>${customerLogin.regTime }</td>
				</tr>
				<tr>
					<td class="title">客户账户/邮箱</td>
					<td>${customerLogin.email }</td>
					<td class="title">收货人姓名</td>
					<td>${customerLogin.custDetail.name }</td>
				</tr>
				<tr>
					<td class="title">固定电话</td>
					<td>${customerLogin.custDetail.telPhone }</td>
					<td class="title">移动电话</td>
					<td>${customerLogin.custDetail.movePhone }</td>
				</tr>
				<tr>
					<td class="title">收货地址</td>
					<td colspan="3">${customerLogin.custDetail.address }</td>
				</tr>
			</table>
		</div>
		<div class="item_title">订单信息</div>
		<div class="item_content">
			
			<table>
				<tr>
					<th>编号</th>
					<th>商品名称</th>
					<th>商品类型</th>
					<th>价格</th>
					<th>折扣</th>
					<th>数量</th>
					<th>小计</th>
				</tr>
				<c:forEach var="et" items="${cart.cart }">
				<tr>
					<td>36</td>
					<td>
						<img class="goodsImg" src="${path }/product/${et.value.goods.photo }">
						<div class="goodsName">${et.value.goods.goodsName }</div>
					</td>
					<td>
						${et.value.goods.goodsType.typeName }
					</td>
					<td>
					<fmt:formatNumber value="${et.value.goods.price }" pattern="#.00"/>
						元
					</td>
					<td>
						${et.value.goods.discount<10?et.value.goods.discount:"-" }
					</td>
					<td>
						${et.value.quantity }
					</td>
					<td>
						<fmt:formatNumber value="${et.value.caculMoney }" pattern="#.00"/> 元
					</td>
				</tr>
				</c:forEach>
				
				
				<tr>
					<td colspan="7" class="sum">
						此订单中共有<span>${cart.count }</span>件商品，总计金额<span><fmt:formatNumber value="${cart.totalMoney }" pattern="#.00"/></span>元！
					</td>
				</tr>
			</table>
		</div>
		
	</div>
	<div class="op">
		<form action="${path }/front/payManage" method="post">
			<input type="hidden" name="op" value="add" />
			<input type="submit" class="btn61_21" value="确认订单" />&nbsp;&nbsp;
			<input type="button" onclick="window.top.location.href='${path}/front/showCart.jsp'" class="btn61_21" value="返&nbsp;回" />
		</form>
	</div>
</div>
<%@ include file="bottom.jsp" %>
  </body>
</html>
