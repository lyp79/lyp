<%@page import="lyp.entity.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>千里之行后台管理</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/index.css">
	<script type="text/javascript" src="${path }/js/index.js">
	
	</script>
  </head>
  <body>
  <div id="out">
    <div id="top">&nbsp;&nbsp;<img alt="这是后台管理logo图片,没被加载是在可惜!" src="${path }/img/logo.gif" /></div>
    <div id="content">
     <div id="cont_top"><!-- 这里只有横条 --></div>
     <!-- 中间左侧开始 -->
   	 <div id="cont_left">
   	 <div>
   	 <div class="cont_left_title">用户登录信息</div>
   	 <div class="loginMsg"><label>登录用户:</label>${user.userName}</div>
   	 <div class="loginMsg"><label>登录时间:</label>${userLoginTime }</div>
   	 <div class="loginMsg"><label>登录IP:</label>${ip }</div>
   	 </div>
   	 
   	 <div class="cont_left_l">
   	 <div class="cont_left_title">公告管理</div>
   	 <div class="divHover"><a href="${path }/back/bulletinManage?op=toAdd" target="myIframe" ><span></span>发布公告</a></div>
   	 <div class="divHover"><a href="${path }/back/bulletinManage?op=manage" target="myIframe" ><span></span>公告管理</a></div>
   	 </div>
   	 
   	 <div class="cont_left_l">
   	 <div class="cont_left_title">商品管理</div>
   	 <div class="divHover"><a href="${path }/back/goodsManage?op=toAdd"  target="myIframe"><span></span>添加商品信息</a></div>
   	 <div class="divHover"><a href="${path }/back/goodsManage?op=manage"  target="myIframe"><span></span>商品信息管理</a></div>
   	 <div class="divHover"><a href="${path }/back/typeManage?op=toAdd"  target="myIframe"><span></span>添加商品类型</a></div>
   	 <div class="divHover"><a href="${path }/back/typeManage?op=manage"  target="myIframe"><span></span>商品类型管理</a></div>
   	 </div>
   	 
   	 <div class="cont_left_l">
   	 <div class="cont_left_title">订单管理</div>
   	 <div class="divHover"><a href="${path }/back/orderManage?op=manage"  target="myIframe"><span></span>订单信息管理</a></div>
   	 </div>
   	 
   	 <div class="cont_left_l">
   	 <div class="cont_left_title">客户管理</div>
   	 <div class="divHover"><a href="${path }/back/customerManage?op=manage"  target="myIframe"><span></span>客户信息管理</a></div>
   	 </div>
   	 
   	 <div class="cont_left_l">
   	 <div class="cont_left_title">系统管理</div>
   	 <div class="divHover"><a href="${path }/back/userManage?op=changePass" ><span></span>修改密码</a></div>
   	 <div class="divHover"><a href="${path }/back/userManage?op=changeName" ><span></span>修改用户名</a></div>
   	 <div class="divHover"><a href="${path }/back/userManage?op=logoutUser"><span></span>退出系统</a></div>
   	 </div>
   	 
   	 </div>
   	 <!-- 中间左侧结束 -->
   	 <div id="center_bar"><img alt="折叠按钮图片" src="${path }/img/close_left.gif" title="折叠左侧" onclick="changeMemu(this)"/></div>
   	 <!-- 中间右侧开始 -->
   	 <iframe id=cont_right name="myIframe" src="${path }/back/welcome.jsp"></iframe>
    </div>
   	 <!-- 中间右侧结束 -->
   	 
    <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
    <div id="bottom">Copyright&copy; (冰河时代-<fmt:formatDate value="${date}" pattern="yyyy"/>) 千里之行  All Rights Reserved</div>
</div>
  </body>
</html>
