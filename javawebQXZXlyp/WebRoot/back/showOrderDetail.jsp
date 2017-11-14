<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查看订单详情</title>
    <link rel="stylesheet" type="text/css" href="${path }/css/goodsManage.css" />
    <style type="text/css">
    td{padding-left: 20px;text-align: left;}
    .fanhui{float: right;display: inline;margin-right: 20px;}
    </style>
  </head>
  <body>
  <div>
   <div><span id="send">查看订单详情</span><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span><button class="fanhui" onclick="history.go(-1)">返回</button></div>
  <table>
  <tr>
  <td class="d"><label class="update1">订单编号: </label>${orderInfo.orderId }</td>
  <td class="d"><label class="update1">订单状态: </label>${orderInfo.orderStatus==0?"未确认":"已确认 "}</td>
  <td class="d"><label class="update1">下单时间: </label><fmt:formatDate value="${orderInfo.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
  <td class="d"><label class="update1">客户编号: </label>${orderInfo.customer.id }</td>
  <td class="d"><label class="update1">客户账号/邮箱: </label>${orderInfo.customer.email }</td>
  <td class="d"><label class="update1">注册时间: </label><fmt:formatDate value="${orderInfo.customer.regTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
  <td class="d"><label class="update1">收货人姓名: </label>${orderInfo.customer.custDetail.name }</td>
  <td class="d"><label class="update1">固定电话: </label>${orderInfo.customer.custDetail.telPhone }</td>
  <td class="d"><label class="update1">移动电话: </label>${orderInfo.customer.custDetail.movePhone }</td>
  <tr>
  <td class="d" colspan="3"><label class="update1">收货地址: </label>${orderInfo.customer.custDetail.address }</td>
  </tr>
  </table>
  </div>
  
  <table>
			<tr>
				<th>商品编号</th>
				<th>商品类型</th>
				<th>商品名称</th>
				<th>购买价格</th>
				<th>订单数量</th>
				<th>小计</th>
			</tr>
				<c:set var = "totle" value="0"></c:set>
				<c:forEach var="orderDetail" items="${orderInfo.goodsList }">
				<tr>
					<td class="btitle">${orderDetail.goods.goodsId}</td>
					<td class="btitle">${orderDetail.goods.goodsType.typeName}</td>
					<td class="btitle">${orderDetail.goods.goodsName}</td>
					<td class="btitle">&yen;&nbsp;<fmt:formatNumber value="${orderDetail.buyPrice}" pattern="#.00"/></td>
					<td class="btitle">${orderDetail.quantity }</td>
					<td class="btitle">&yen;&nbsp;<fmt:formatNumber value="${orderDetail.quantity*orderDetail.buyPrice }" pattern="#.00"/></td>
					<c:set var = "totle" value="${totle+orderDetail.quantity*orderDetail.buyPrice }"></c:set>
				</tr>
			</c:forEach>
			<tr>
			<td class="btitle" colspan="7">总计：&yen;&nbsp;<fmt:formatNumber value="${totle }" pattern="#.00" /> </td>
			</tr>
		</table>
  </body>
</html>
