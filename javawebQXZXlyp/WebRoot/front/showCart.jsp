<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车-千里之行，在线销售旅游产品</title>
<link rel="stylesheet" type="text/css" href="${path }/front/css/common.css" />
<link rel="stylesheet" type="text/css" href="${path }/front/css/showCart.css" />
<script type="text/javascript">
	//更改商品数量
	//参数id为商品编号
	function changeQuantity(id) {
		var quantity_input = document.getElementById("quantity_" + id);
		if (quantity_input != null) {
			var quantity = quantity_input.value;
			if (isNaN(quantity) || parseInt(quantity) < 0) {
				alert("商品数量应该是大于等于0的整数！");
				return;
			}
			document.changeForm.id.value = id;
			document.changeForm.quantity.value = quantity;
			document.changeForm.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div id="middle_div">
		<div class="sitemap">
			您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;购物车
		</div>
		<form name="changeForm" action="${path }/front/cartManage" method="post">
			<input type="hidden" name="op" value="change" /> <input
				type="hidden" name="id" value="" /> <input type="hidden"
				name="quantity" value="" />
		</form>
		<img src="images/buycart_logo.gif" style="margin:8px 3px 3px 0px;" />
		<c:if test="${empty cart.cart }">
		<div style="color:red; font-size: 16px; margin: 20px 20px;">你的购物车空空如也！
		<div style="margin-top: 20px;"><a target="_top" href="${path }/front/index.jsp"><img src="${path }/front/images/jxgm.gif" /></a></div>
		</div>
		</c:if>
		<br />
		<c:if test="${not empty cart.cart }">
		<div class="daxiao">
		<table>
			<tr>
				<th>商品名称</th>
				<th>商品类型</th>
				<th>价格</th>
				<th>折扣</th>
				<th>数量</th>
				<th>小计</th>
				<th>操作</th>
			</tr>
			<c:forEach var="et" items="${cart.cart }">
				<tr>
					<td><img class="goodsImg"
						src="${path }/product/${et.value.goods.photo }">
						<div class="goodsName">
							<a href="${path }/front/product.jsp?id=${et.value.goods.goodsId }">${et.value.goods.goodsName }</a>
						</div></td>
					<td><a target="_top"
						href="${path }/front/products.jsp?op=showTypeGoods&id=${et.value.goods.goodsType.typeId }">
							${et.value.goods.goodsType.typeName } </a></td>
					<td><fmt:formatNumber value="${et.value.goods.price }" pattern="#.00"/>元</td>
					<td>${et.value.goods.discount<10?et.value.goods.discount:"-" }
					</td>
					<td class="duiqi"><input class="quantity"
						id="quantity_${et.value.goods.goodsId }" type="text"
						value="${et.value.quantity }" /></td>
					<td><fmt:formatNumber value="${et.value.caculMoney }" pattern="#.00"/>元</td>
					<td class="duiqi"><a
						href="javascript:changeQuantity('${et.value.goods.goodsId }')">更改数量</a>
						<a href="${path }/front/cartManage?op=remove&id=${et.value.goods.goodsId }" onclick='confirm("确定要删除该商品吗？")'>删除商品</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div class="sumPrice_div">
			<div class="sum">
				商品金额总计：<span>&yen;<fmt:formatNumber value="${cart.totalMoney }" pattern="#.00"/></span>&nbsp;&nbsp;
			</div>
			<div class="clear">
				<a href="${path }/front/cartManage?op=clear" onclick='confirm("确定要清空购物车吗？")'>清空购物车</a>
			</div>
		</div>
		<div class="btn_div">
			您可以&nbsp;<a target="_top" href="${path }/front/payManage?op=peisong"><img src="${path }/front/images/jrjs.gif" /></a>,也可以&nbsp;
			<a target="_top" href="${path }/front/index.jsp"><img src="${path }/front/images/jxgm.gif" /></a>
		</div>
		</c:if>
		</div>
	<%@ include file="bottom.jsp"%>
</body>
</html>