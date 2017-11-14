<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<title>操作成功！</title>
    <link rel="stylesheet" href="${path }/front/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${path }/front/css/ok.css" type="text/css" />
  </head>
  <body>
  	<%@ include file="top.jsp" %>
    <div id="middle_div">
    	<div id="msg_tip"><img src="${path }/front/images/ok.png" />操作成功！谢谢惠顾！！<a target="_top" href="${path }/front/index.jsp">返回首页</a></div>
    	<div id="msg_info">${customerLogin.email },恭喜您，添加订单成功！订单号为：${orderId }&nbsp;&nbsp;</div>
  	</div>
  	<%@ include file="bottom.jsp" %>
  </body>
</html>
