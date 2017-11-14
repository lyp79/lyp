<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>修改商品</title>
	<link rel="stylesheet" type="text/css" href="${path }/css/goodsAdd.css">
  </head>
  <body>
    <div id="send">修改商品信息<span id="msg" class="dd msgfont">${msg }<c:remove var="msg" /></span></div>
    <form action="${path }/back/goodsManage?op=goodsUpdate" method="post" enctype="multipart/form-data">
    <fieldset>
    <legend>添 加 商 品</legend>
    <div><label>商品编号:</label> <input type="text" readonly="readonly" name="goodsId" class="ronly" value="${goods.goodsId }"/></div>
    <div><label>商品名称:</label> <input type="text" name="goodsName" value="${goods.goodsName }"/></div>
    <div><label>商品分类:</label> 
    <select name="typeId">
    <c:forEach var="type" items="${typeList }">
    <option value="${type.typeId }" ${goods.goodsType.typeId==type.typeId?'selected="selected"':'' }>${type.typeName }</option>
    </c:forEach>
    </select>
	</div>
	<div>
	<label>商品价格/折扣:</label>
	<label class="price">价格:</label> <input type="text" class="price" name="price" value="${goods.price }"/>&nbsp;元&nbsp;&nbsp;
	<label class="price">折扣:</label><input type="text" class="price"  name="discount" value="${goods.discount }"/>&nbsp;折</div>
    <div><label>上架/推荐/新品:</label>
    <input type="checkbox" name="status" value="${goods.status }" ${goods.status==0?'checked="checked"':""} />上架
    <input type="checkbox" name=isRecommend value="${goods.isRecommend }" ${goods.isRecommend==0?'checked="checked"':""} />推荐
    <input type="checkbox" name="isNew" value="${goods.isNew }" ${goods.isNew==0?'checked="checked"':""} />新品
    </div>
    <div>
    <label>商品图片:</label>
    <input type="file" name="photo" />
    <img src="${path }/product/${goods.photo }" width="40px;" />
    <input type="hidden" name="photo1" value="${goods.photo }"/>
    </div>
    <div>
    <label>商品描述:</label>
    <textarea rows="6" cols="50" name="remark" >${goods.remark }</textarea>
    </div>
    <div class="btn">
    <input type="submit" value="修改">
    <input type="reset" value="重置">
    <input type="button" value="返回" onclick="history.go(-1)"/>
    </div>
    </fieldset>
    </form>
  </body>
</html>
