<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示订单详细信息</title>
<link type="text/css" rel="stylesheet" href="${path }/back/css/commons.css">
<style type="text/css">
.name_td{width:120px;display:inline;
float:none;
margin-right:0px;height: 40px;
	line-height: 40px;}
.addTable td {
	height: 40px;
	line-height: 40px;
	border-bottom: 1px dashed #CBCCCE;
	padding: 3px;
}
table{ border-collapse: collapse;}
#dataTable_div{height:200px;overflow-y:scroll;border-bottom:1px dashed #CBCCCE;}
</style>
</head>
<body>
	<div class="opDiv">
		<div class="titlebar">订单详细信息</div>
	</div>
	<table class="addTable">
		<tr>
			<td class="name_td">订单编号:</td>
			<td>${oi.orderId }</td>
			<td class="name_td">订单状态:</td>
			<td>${oi.status==0?'未确认':'已确认' }</td>
			<td class="name_td">下单时间:</td>
			<td colspan="3"><fmt:formatDate value="${oi.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td class="name_td">客户编号:</td>
			<td>${oi.cust.custId }</td>
			<td class="name_td">客户账户/邮箱:</td>
			<td>
				<a href="${path }/manage/CustomerManage?op=toEdit&id=${oi.cust.custId }">
					${oi.cust.email }</a>
			</td>
			<td class="name_td">注册时间:</td>
			<td><fmt:formatDate value="${oi.cust.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td class="name_td">收货人姓名:</td>
			<td>${oi.cust.custDetail.custName }</td>
			<td class="name_td">固定电话:</td>
			<td>${oi.cust.custDetail.telphone }</td>
			<td class="name_td">移动电话:</td>
			<td>${oi.cust.custDetail.movePhone }</td>
		</tr>
		<tr>
			<td class="name_td">收货地址:</td>
			<td colspan="5">${oi.cust.custDetail.address }</td>
		</tr>
	</table>
	<div id="dataTable_div">
		<table id="dataTable">
			<tr>
				<th>商品编号</th>
				<th>商品类别</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>商品折扣</th>
				<th>订购数量</th>
				<th>小计</th>
			</tr>
			<c:set var="total" value="0" />
			<c:forEach var="ogi" items="${oi.goodsList }">
			<tr>
				<td>${ogi.goods.goodsId }</td>
				<td>
					<a href="${path }/manage/GoodsTypeManage?op=toEdit&id=${ogi.goods.goodsType.typeId }">
						${ogi.goods.goodsType.typeName }</a>
				</td>
				<td>
					<a href="${path }/manage/GoodsManage?op=toEdit&id=${ogi.goods.goodsId }">${ogi.goods.goodsName }</a>
				</td>
				<td>&yen;${ogi.goods.price }</td>
				<td>${ogi.goods.discount<10?ogi.goods.discount:'-' }</td>
				<td>${ogi.quantity }</td>
				<td>${ogi.caculMoney }</td>
			</tr>
			  <c:set var="total" value="${total+ogi.caculMoney }" />
			</c:forEach>
			
			
			<tr>
				<td>总金额：</td>
				<td colspan="6">&yen;&nbsp;${total }</td>
			</tr>
		</table>
	</div>
	<div style="margin-top:20px;padding-right:20px;text-align: right;">
		<input class="btn61_21" type="button" onclick="window.history.back();" value="返回" />
	</div>
</body>
</html>