<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" href="${path}/front/css/bottom.css">
<div class="body">
	<div id="bottom_div">
		<div>
			<span class="title">我的订单</span>
			<ul>
				<li><a href="javascript:;">如何下订单</a></li>
				<li><a href="javascript:;">跟踪最近的订单</a></li>
				<li><a href="javascript:;">查看或修改订单</a></li>
			</ul>
		</div>
		<div>
			<span class="title">如何付款</span>
			<ul>
				<li><a href="javascript:;">付款方式</a></li>
				<li><a href="javascript:;">查看我的礼品卡余额</a></li>
				<li><a href="javascript:;">查看我的电子帐户</a></li>
			</ul>
		</div>
		<div>
			<span class="title">配送信息</span>
			<ul>
				<li><a href="javascript:;">配送费收取标准</a></li>
				<li><a href="javascript:;">国内订单配送时间和配送范围</a></li>
				<li><a href="javascript:;">海外订单配送时间和配送范围</a></li>
			</ul>
		</div>
		<div>
			<span class="title">需要帮助</span>
			<ul>
				<li><a href="javascript:;">汇款单招领</a></li>
				<li><a href="javascript:;">忘记了密码</a></li>
				<li><a href="javascript:;">参考帮助中心</a></li>
			</ul>
		</div>
	</div>
	<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
	<div id="bottom">Copyright&copy; (冰河时代 - <fmt:formatDate value="${date}" pattern="yyyy" />) 千里之行 All Rights Reserved</div>
</div>