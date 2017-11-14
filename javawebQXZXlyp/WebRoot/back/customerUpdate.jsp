<%@page import="lyp.entity.Bulletin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE>
<html>
  <head>
    
    <title>修改客户资料</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/customerUpdate.css">
  </head>
  
  <body>
  <div id="send">修改客户资料</div><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
  <fmt:formatDate var="date" value="${customer.regTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
  <form action="${path }/back/customerManage?op=customerUpdate" method="post">
  <fieldset>
  <legend>修改客户资料</legend>
  <div class="d"><label class="update1">客户编号: </label><input type="text" readonly="readonly" name="id" class="delk" value="${customer.id }"/></div>
  <div class="d"><label class="update1">注册时间: </label><input type="text" readonly="readonly" name="regTime" class="delk" value="${date }"/></div>
  <div class="d"><label class="update1">客户账号/邮箱: </label><input type="email" name="email" class="input1" value="${customer.email }"/></div>
  <div class="d"><label class="update1">客户姓名: </label><input type="text" name="name" value="${customer.custDetail.name }"/></div>
  <div class="d"><label class="update1">固定电话: </label><input type="text" name="telPhone" value="${customer.custDetail.telPhone }"/></div>
  <div class="d"><label class="update1">移动电话: </label><input type="text" name="movePhone" value="${customer.custDetail.movePhone }"/></div>
  <div class="d"><label class="update1">收货地址: </label><input type="text" name="address" value="${customer.custDetail.address }"/></div>
  <div class="d" id="btn2">
  <input class="btn" type="submit" value="修改"/>
  <input class="btn" type="reset" value="重置"/>
  <input class="btn" type="button" value="返回" onclick="history.go(-1)"/>
  </div>
  </fieldset>
  </form>
  </body>
</html>
