<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>添加商品</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/goodsAdd.css">
  </head>
  <body>
    <div id="send">添加商品信息<span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span></div>
    <form action="${path }/back/goodsManage?op=goodsAdd" method="post" enctype="multipart/form-data">
    <fieldset>
    <legend>添 加 商 品</legend>
    <div><label>商品名称:</label> <input type="text" name="goodsName" value="" /></div>
    <div><label>商品类型:</label> 
    <select name="typeId">
    <c:forEach var="type" items="${typeList }">
    <option value="${type.typeId }">${type.typeName }</option>
    </c:forEach>
    </select>
	</div>
	<div><label>商品价格/折扣:</label><label class="price">价格:</label> <input type="text" name="price" class="price" value=""/>&nbsp;元&nbsp;&nbsp; <label class="price">折扣:</label><input type="text" class="price"  name="discount" value="" />&nbsp;折</div>
    <div><label>上架/推荐/新品:</label>
    <input type="checkbox" name="status" value="0" checked="checked"/>上架
    <input type="checkbox" name=isRecommend value="0"/>推荐
    <input type="checkbox" name="isNew" value="0"/>新品
    </div>
    <div>
    <label>商品图片:</label>
    <input type="file" name="photo" value=""/>
    </div>
    <div>
    <label>商品描述:</label>
    <textarea rows="10%" cols="62%" name="remark"></textarea>
    </div>
    <div class="btn">
    <input type="submit" value="添加">
    <input type="reset" value="重置">
    <input type="button" value="返回" onclick="history.go(-1)"/>
    </div>
    </fieldset>
    </form>
  </body>
</html>
