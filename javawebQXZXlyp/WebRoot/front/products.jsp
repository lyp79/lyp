<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/front/productServlet">
	<jsp:param value="showTypeGoods" name="op"/>
</jsp:include>
<c:set var="barPath" value="${path }/front/products.jsp?op=${op }&id=${id }&" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
<link rel="stylesheet" href="${path }/front/css/common.css" type="text/css"></link>
<link rel="stylesheet" href="${path }/front/css/products.css" type="text/css"></link>
	</head>
	<body>
		<%@include file="top.jsp" %>
		<div id="middle_div">
			<div class="sitemap">
				您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;分类商品
			</div>
			<div id="middle_left_div">
				<div id="paihang">
					<div class="product_title">销售排行</div>
					<table>
						<c:forEach var="ogi" items="${list }" varStatus="st">
						<tr>
							<td class="goods_${st.index }">
								<a target="_top" href="${path }/front/product.jsp?id=${ogi.goods.goodsId }">
								${fn:length(ogi.goods.goodsName)<15?ogi.goods.goodsName:fn:substring(ogi.goods.goodsName,0,15) }${fn:length(ogi.goods.goodsName)>=15?'...':'' }
								</a>
							</td>
							<td class="num"><span>${ogi.quantity }</span>件</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div id="middle_right_div">
				<div style="margin-bottom:10px;">
					<table class="product_table">
						<tr>
					<c:forEach var="goods" items="${pm.data }" varStatus="st">
					<td>
						<a class="img" href="${path }/front/product.jsp?id=${goods.goodsId }">
							<img src="${path }/product/${goods.photo }" /></a>
						<ul>
							<c:if test="${goods.discount<10 }" var="rel">
							<li><span class="discount">${goods.discount }</span>折</li>
							</c:if>
							<li><a href="${path }/front/product.jsp?id=${goods.goodsId }">${goods.goodsName }</a></li>
							<li>
							   <c:if test="${rel }">
								   原价:<span class="before_price">&yen;<fmt:formatNumber value="${goods.price }" pattern="#.00" /></span>&nbsp;&nbsp;</br>
								   现价:<span class="discount_price">&yen;<fmt:formatNumber value="${goods.discountPrice }" pattern="#.00" /></span>
								</c:if>
								<c:if test="${rel==false }">
								<div class="discount_price">&yen;<fmt:formatNumber value="${goods.price }" pattern="#.00" /></div>
								</c:if>
							</li>
						</ul>
					</td>
					<c:if test="${st.count%3==0 }">
						</tr><tr>
					</c:if>
					</c:forEach>
					</tr>
					</table>
					<%@ include file="pagebar.html" %>
				</div>
			</div>
		</div>
		<%@include file="bottom.jsp" %>
</body>
</html>