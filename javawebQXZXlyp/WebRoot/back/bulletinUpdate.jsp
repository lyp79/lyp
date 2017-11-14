<%@page import="lyp.entity.Bulletin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE>
<html>
  <head>
    
    <title>修改公告</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/bulletinAdd.css">
  </head>
  
  <body>
  <div id="send">修改公告信息</div><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
  <fmt:formatDate var="date" value="${bulletin.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
  <form action="${path }/back/bulletinManage?op=bulletinUpdate" method="post">
  <fieldset>
  <legend>修 改 公 告</legend>
  <div class="d"><label class="update1">公告编号: </label><input type="text" readonly="readonly" name="id" class="delk" value="${bulletin.id }"/></div>
  <div class="d"><label class="update1">标题: </label><input type="text" name="title" class="input1" value="${bulletin.title }"/></div>
  <div class="d"><label class="update1">内容: </label><textarea rows="12%" cols="85%" name="content" maxlength="90%" >${bulletin.content }</textarea></div>
  <div class="d"><label class="update1">发布者: </label><input type="text" readonly="readonly" name="userName" class="delk" value="${bulletin.user.userName }"/></div>
  <div class="d"><label class="update1">发布时间: </label><input type="text" readonly="readonly" name="createTime" class="delk" value="${date }"/></div>
  <div class="d" id="btn2">
  <input class="btn" type="submit" value="修改公告"/>
  <input class="btn" type="reset" value="重置内容"/>
  <input class="btn" type="button" value="返回" onclick="history.go(-1)"/>
  </div>
  </fieldset>
  </form>
  </body>
</html>
