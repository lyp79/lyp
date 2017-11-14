<%@page import="lyp.entity.Bulletin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE>
<html>
  <head>
    <title>查看客户资料</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/customerUpdate.css">
  </head>
  <body>
  <div id="send">查看客户资料</div><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
  <fmt:formatDate var="date" value="${customer.regTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
  <div class="d"><label class="update1">客户编号: </label>${customer.id }</div>
  <div class="d"><label class="update1">注册时间: </label>${date }</div>
  <div class="d"><label class="update1">客户账号/邮箱: </label>${customer.email }</div>
  <div class="d"><label class="update1">客户姓名: </label>${customer.custDetail.name }</div>
  <div class="d"><label class="update1">固定电话: </label>${customer.custDetail.telPhone }</div>
  <div class="d"><label class="update1">移动电话: </label>${customer.custDetail.movePhone }</div>
  <div class="d"><label class="update1">收货地址: </label>${customer.custDetail.address }</div>
  <div class="d"><button  class="update1" onclick="history.go(-1)">返回</button></div>
  </body>
</html>
