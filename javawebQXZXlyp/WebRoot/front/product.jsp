<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/front/productServlet">
	<jsp:param value="detail" name="op"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>产品信息-千里之行，在线销售旅游产品</title>
<link type="text/css" rel="stylesheet" href="${path }/front/css/common.css" />
<link type="text/css" rel="stylesheet" href="${path }/front/css/product.css" />
<script type="text/javascript" src="${path }/front/js/product.js" ></script>
</head>
<body>
<%@include file="top.jsp" %>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;产品详细信息
	</div>
	<div>
		<div id="goodsImg">
			<img id="goodsPhoto" src="${path }/product/${goods.photo }"></img>
			<c:if test="${goods.discount<10 }">
			   <div class="discount_div">
				 <div><span class="discount">${goods.discount }</span>折</div>
			   </div>
			</c:if>			
		</div>
		<div id="detail">
			<p class="title">${goods.goodsName }</p>
			<p>
			<c:if test="${goods.discount<10 }" var="rel">
			     原价:<span class="before_price">&yen;<fmt:formatNumber value="${goods.price }" pattern="#.00" /></span>&nbsp;&nbsp;</br>
			     现价:<span class="discount_price">&yen;<fmt:formatNumber value="${goods.discountPrice }" pattern="#.00" /></span>
			</c:if>
			<c:if test="${rel==false}">
			   现价:<span class="discount_price">&yen;<fmt:formatNumber value="${goods.price }" pattern="#.00" /></span>
			</c:if>
			 </p>
			<p>商品类别：<a target="_top" href="${path }/front/products.jsp?id=${goods.goodsType.typeId }">
				${goods.goodsType.typeName }</a></p>
			<p>描述：${goods.remark }</p>
			<form name="goodsForm" action="${path }/front/cartManage" method="post" onsubmit="return submit_form()">
				<input type="hidden" name="op" value="add" />
				<input type="hidden" name="id" value="${goods.goodsId }" />
				<div id="op_div" onmouseover="changebg(this,'#FFF3D9','1px solid #E37A08')" onmouseout="changebg(this,'#E5EAF0','1px solid #98BFF0')">
				
					<div class="num">我&nbsp;要&nbsp;买：<input type="text" name="num" value="1" />&nbsp;件</div>
					<div class="btn">
						<input type="submit" value="" />&nbsp;&nbsp;
						<a href="${path }/front/showCart.jsp">查看购物车</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="bottom.jsp" %>
</body>
</html>