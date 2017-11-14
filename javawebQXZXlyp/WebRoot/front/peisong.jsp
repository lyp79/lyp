<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
<title>填写配送信息-千里之行-在线销售旅游产品</title>
<link rel="stylesheet" href="${path }/front/css/common.css" type="text/css" />
<link rel="stylesheet" href="${path }/front/css/peisong.css" type="text/css" />
</head>
<body>
<%@ include file="top.jsp" %>
<div id="middle_div">
	<div class="sitemap">
		您现在所在的位置：<a target="_top" href="${path }/front/index.jsp">网站首页</a>&nbsp;&gt;&nbsp;填写配送信息
	</div>
	<div class="peisong">填写配送信息</div>
	<form action="${path }/front/payManage" method="post">
		<input type="hidden" name="op" value="detail" />
		<input type="hidden" name="email" value="${customerLogin.email }" />
		<table>
			<tr>
				<td>收货人姓名:</td>
				<td>
					<input class="input_box" name="name" type="text" value="${customerLogin.custDetail.name }" />
					<span class="tooltip">请填写真实的姓名。</span>
				</td>
			</tr>
			<tr>
				<td>固定电话:</td>
				<td>
					<input class="input_box"  type="text" name="telPhone" value="${customerLogin.custDetail.telPhone }"/>
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</td>
			</tr>
			<tr>
				<td>移动电话:</td>
				<td>
					<input class="input_box"  type="text" name="movePhone" value="${customerLogin.custDetail.movePhone }" />
					<span class="tooltip">固定电话与移动电话至少选填一项。</span>
				</td>
			</tr>
			<tr>
				<td>收货地址:</td>
				<td>
					<input class="input_box_long" type="text" name="address" value="${customerLogin.custDetail.address }" />
					<span class="tooltip">请详细填写真实地址。</span>
				</td>
			</tr>
		</table>
		<div style="height:40px;line-height:40px;padding-left:110px;">
			<input type="submit" class="btn61_21" value="提交" />&nbsp;&nbsp;
			<input type="button" class="btn61_21" value="重置" />
		</div>
	</form>
	</div>
</html>
