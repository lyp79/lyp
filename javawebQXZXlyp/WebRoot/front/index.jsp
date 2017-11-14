<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty requestScope.indexMap }">
	<jsp:forward page="/front/indexServlet"></jsp:forward>
</c:if>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>千里之行-在线销售旅游用品 登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</title>
<link rel="stylesheet" type="text/css" href="${path}/front/css/common.css">
<link rel="stylesheet" type="text/css" href="${path}/front/css/index.css">

</head>
<body>
	<%@ include file="top.jsp" %>
	<div id="middle_div">
		<div id="middle_left_div">
			<div id="gonggao">
				<div class="title">网站公告</div>
				<div class="more">
					<a href="${path }/front/showBulletinList.jsp?pageNo=1">更多&gt;&gt;</a>
				</div>
				<c:forEach var="bulletin" items="${indexMap['bullList'] }">
					<a href="${path }/front/showBulletin.jsp?id=${bulletin.id }"
						title="${bulletin.title }">${fn:length(bulletin.title)<15?bulletin.title:fn:substring(bulletin.title,0,15) }${fn:length(bulletin.title)>=15?'...':''}<span
						class="bullTime"><fmt:formatDate pattern="yyyy-MM-dd"
								value="${bulletin.createTime }" /></span></a>
				</c:forEach>
			</div>
			<div id="product_daohang">
				<div class="product_title">产品导航</div>

				<c:forEach var="type" items="${indexMap['typeList'] }">
					<c:if test="${not empty type.list }">
						<table class="left_box_normal"
							onmouseover="table_mouse_over(this)"
							onmouseout="table_mouse_out(this)">
							<tr>
								<td class="title">${type.typeName }</td>
								<td width="250px"><c:forEach var="tgoods"
										items="${type.list }">
										<a href="product.jsp?id=${tgoods.goodsId }">${fn:length(tgoods.goodsName)<20?tgoods.goodsName:fn:substring(tgoods.goodsName,0,20) }${fn:length(tgoods.goodsName)>=20?'...':'' }
										</a>
									</c:forEach> 
									<a class="more" href="${path }/front/products.jsp?id=${type.typeId }">更多&gt;&gt;</a>
								</td>
							</tr>
						</table>
					</c:if>
				</c:forEach>

			</div>
		</div>
		<div id="middle_right_div">
			<div style="margin-bottom:10px;">
				<div style="height:38px;border-bottom:1px solid #888888;">
					<img src="${path }/front/images/recommend2.gif" height="33" />
				</div>
				<table class="product_table">
					<tr>
						<c:forEach var="rgoods" items="${indexMap['rGoodsList'] }"
							varStatus="st">
							<td><a class="img"
								href="${path }/front/product.jsp?id=${rgoods.goodsId }"><img
									src="${path }/product/${rgoods.photo }" /></a>
								<ul>
									<c:if test="${rgoods.discount<10 }">
										<li><span class="discount">${rgoods.discount }</span>折</li>
									</c:if>
									<li><a
										href="${path }/front/product.jsp?id=${rgoods.goodsId }">${rgoods.goodsName }</a></li>
									<li><c:if test="${rgoods.discount<10 }" var="rel">
											<div class="before_price">
												原价：&yen;<fmt:formatNumber value="${rgoods.price }" pattern="#.00" />
											</div>

											<div class="discount_price">
												现价：&yen;
												<fmt:formatNumber value="${rgoods.discountPrice }"
													pattern="#.00" />
											</div>
										</c:if> <c:if test="${rel==false }">
											<div class="discount_price">
												&yen;
												<fmt:formatNumber value="${rgoods.price }" pattern="#.00" />
											</div>
										</c:if></li>
								</ul></td>
							<c:if test="${st.count%3==0 }">
					</tr>
					<tr>
						</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
			<div style="margin-bottom:15px;">
				<div style="height:27px;border-bottom:1px solid #53BEE6;">
					<img src="${path }/front/images/newproduct5.gif" height="22px"></img>
				</div>
				<table class="product_table">
					<tr>
						<c:forEach var="ngoods" items="${indexMap['nGoodsList'] }"
							varStatus="st">
							<td><a class="img"
								href="${path }/front/product.jsp?id=${ngoods.goodsId }"><img
									src="${path }/product/${ngoods.photo }" /></a>
								<ul>

									<c:if test="${ngoods.discount<10 }">
										<li><span class="discount">${ngoods.discount }</span>折</li>
									</c:if>
									<li><a
										href="${path }/front/product.jsp?id=${ngoods.goodsId }">${ngoods.goodsName }</a></li>
									<li><c:if test="${ngoods.discount<10 }" var="rel">
											<div class="before_price">
												原价：&yen;
												<fmt:formatNumber value="${ngoods.price }" pattern="#.00" />
											</div>

											<div class="discount_price">
												现价：&yen;
												<fmt:formatNumber value="${ngoods.discountPrice }"
													pattern="#.00" />
											</div>
										</c:if> <c:if test="${rel==false }">
											<div class="discount_price">
												&yen;
												<fmt:formatNumber value="${ngoods.price }" pattern="#.00" />
											</div>
										</c:if></li>
								</ul></td>
							<c:if test="${st.count%3==0 }">
					</tr>
					<tr>
						</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>

			<div style="margin-bottom:10px;">
				<div style="height:25px;border-bottom:1px solid #5FCB95;">
					<img src="${path }/front/images/specilproduct2.gif" height="20"></img>
				</div>
				<table class="product_table">
					<tr>
						<c:forEach var="dgoods" items="${indexMap['dGoodsList'] }"
							varStatus="st">
							<td><a class="img"
								href="${path }/front/product.jsp?id=${dgoods.goodsId }"><img
									src="${path }/product/${dgoods.photo }" /></a>
								<ul>

									<c:if test="${dgoods.discount<10 }">
										<li><span class="discount">${dgoods.discount }</span>折</li>
									</c:if>
									<li><a
										href="${path }/front/product.jsp?id=${dgoods.goodsId }">${dgoods.goodsName }</a></li>
									<li><c:if test="${dgoods.discount<10 }" var="rel">
											<div class="before_price">
												原价：&yen;
												<fmt:formatNumber value="${dgoods.price }" pattern="#.00" />
											</div>

											<div class="discount_price">
												现价：&yen;
												<fmt:formatNumber value="${dgoods.discountPrice }"
													pattern="#.00" />
											</div>
										</c:if> <c:if test="${rel==false }">
											<div class="discount_price">
												&yen;
												<fmt:formatNumber value="${dgoods.price }" pattern="#.00" />
											</div>
										</c:if></li>
								</ul></td>
							<c:if test="${st.count%3==0 }">
					</tr>
					<tr>
						</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="bottom.jsp"%>
</body>
</html>
