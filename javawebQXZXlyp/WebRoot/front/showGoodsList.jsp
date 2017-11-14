<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="barPath" value="${path }/front/productServlet?op=${op }&keywords=${keywords }&"/>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
<link rel="stylesheet" type="text/css" href="${path}/front/css/common.css">
<link rel="stylesheet" type="text/css" href="${path}/front/css/index.css">
</head>
<body>
	<%@ include file="top.jsp"%>
		<div style="width: 690px;border: 0px solid blue;margin:0px auto;">
			<div style="margin-bottom:10px;">
				<table class="product_table">
					<tr>
						<c:forEach var="goods" items="${pm.data }" varStatus="st">
							<td><a class="img" href="${path }/front/product.jsp?id=${goods.goodsId }"><img src="${path }/product/${goods.photo }" /></a>
								<ul>
									<c:if test="${goods.discount<10 }">
										<li><span class="discount">${goods.discount }</span>折</li>
									</c:if>
									<li><a
										href="${path }/front/product.jsp?id=${goods.goodsId }">${goods.goodsName }</a></li>
									<li><c:if test="${goods.discount<10 }" var="rel">
											<div class="before_price">
												原价：&yen;<fmt:formatNumber value="${goods.price }" pattern="#.00" />
											</div>

											<div class="discount_price">
												现价：&yen;
												<fmt:formatNumber value="${goods.discountPrice }"
													pattern="#.00" />
											</div>
										</c:if> <c:if test="${rel==false }">
											<div class="discount_price">
												&yen;
												<fmt:formatNumber value="${goods.price }" pattern="#.00" />
											</div>
										</c:if>
										</li>
								</ul>
								</td>
							<c:if test="${st.count%3==0 }">
							</tr>
							<tr>
						    </c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
				<%@ include file="pagebar.html"%>
	</div>
	<%@ include file="bottom.jsp"%>
</body>
</html>
