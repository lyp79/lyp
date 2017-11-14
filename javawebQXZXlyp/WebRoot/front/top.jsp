<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${path}/front/css/top.css">
<script type="text/javascript"  src="${path }/front/js/top.js" ></script>
<div id="top">
<div id="div_all">
	<div id="top_left_div">
		<img src="${path }/front/images/logo.gif" />
	</div>
	<div id="top_right_div">
		<div class="menu">
			<a class="home" target="_top" href="${path }/front/index.jsp">首页</a> <span>|</span>
				<c:if test="${empty customerLogin }">
				<span>
				[<a target="_top" href="${path }/front/login_register.jsp">请登录</a>]&nbsp;
				[<a target="_top" href="${path }/front/login_register.jsp">免费注册</a>]
				</span>
				</c:if>
				<c:if test="${not empty customerLogin }">
				<a class="cart" target="_top" href="${path }/front/showCart.jsp">购物车</a>
				<span>|</span>
				<a class="userinfo" href="${path }/front/custFrontManage?op=showCustDetail&id=${customerLogin.id}">我的信息</a>
				<span>|</span>
				<a target="_top" class="loginout" href="${path }/front/custFrontManage?op=loginOut">退出</a>
				<span>|</span></c:if>
			<a class="shoucang" href="javascript:add_shouchang();">收藏本站</a><span>|</span>
			<a class="help" href="javascript:;">帮助</a>
		</div>
		<div id="welcome">您好，欢迎光临千里之行购买旅游用品!&nbsp;&nbsp; ${sessionScope.customerLogin.email }</div>
	</div>
	<form action="${path }/front/productServlet" method="get">
	<input type="hidden" name="op" value="search" />
	<div id="search_div">
		商品关键词：<input type="text" name="keywords" value="${keywords }" /> 
		<input type="submit" class="btn_search" value="搜索"/>&nbsp;&nbsp; <span>热门商品：登山攀岩器材、户外服装、户外桌椅、睡袋垫子、野营用品、野营帐篷、运动手表...</span>
	</div>
	</form>
</div>
</div>