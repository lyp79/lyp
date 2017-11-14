<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
  <head>
    <title>商品类型修改</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/typeAdd.css" >
  </head>
  
  <body>
    <div id="send">修改商品类型</div>
  <form action="${path }/back/typeManage?op=typeUpdate" method="post">
  <fieldset>
  <legend>修 改 商 品 类 型</legend><span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span>
  <div class="d"><label>商品类型编号:</label> <input type="text" class="input1 inputh" readonly="readonly" name="typeId" value="${type.typeId }" /></div>
  <div class="d"><label>商品类型名称:</label> <input type="text" class="input1" name="typeName" value="${type.typeName }" /></div>
  <div class="d" id="btn">
  <input class="btn" type="submit" value="修改"/>
  <input class="btn" type="reset" value="重置"/>
  <input class="btn" type="button" value="返回" onclick="history.go(-1)"/>
  </div>
  </fieldset>
  </form>
  </body>
</html>
