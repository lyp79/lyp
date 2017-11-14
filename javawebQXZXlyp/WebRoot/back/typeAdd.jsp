<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
  <head>
    <title>添加公告</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/typeAdd.css">
  </head>
  
  <body>
  <div id="send">添加商品类型</div><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
  <form action="${path }/back/typeManage?op=typeAdd" method="post">
  <fieldset>
  <legend>添 加 商 品 类 型</legend>
  <div class="d"><label>商品类型名称:</label> <input type="text" class="input1" name="typeName"  /></div>
  <div class="d" id="btn">
  <input class="btn" type="submit" value="添加"/>
  <input class="btn" type="reset" value="重置"/>
  <input class="btn" type="button" value="返回" onclick="history.go(-1)"/>
  </div>
  </fieldset>
  </form>
  </body>
</html>
