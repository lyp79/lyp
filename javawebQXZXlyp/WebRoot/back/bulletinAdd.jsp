<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
  <head>
    
    <title>添加公告</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/bulletinAdd.css">
  </head>
  
  <body>
  <div id="send">发布公告信息</div><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
  <form action="${path }/back/bulletinManage?op=bulletinAdd" method="post">
  <fieldset>
  <legend>发 布 公 告</legend>
  <div class="d"><label>标题: </label><input type="text" class="input1" name="title" value="${title }" /></div>
  <div class="d"><label>内容: </label><textarea rows="12%" cols="72%" name="content" >${content }</textarea></div>
  <div class="d" id="btn2">
  <input class="btn" type="submit" value="发布公告"/>
  <input class="btn" type="reset" value="重置内容"/>
  <input class="btn" type="button" value="返回" onclick="history.go(-1)"/>
  </div>
  </fieldset>
  </form>
  </body>
</html>
