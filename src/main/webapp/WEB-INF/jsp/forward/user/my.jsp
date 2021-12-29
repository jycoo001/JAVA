<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的信息</title>
<link href="static/forward/css/my.css" rel="stylesheet">
</head>

<body>

	<!-- 我的个人信息 -->
	<!-- 管理员和个人 -->
	<!-- 用户名，密码，电话，身份证，qq，mail -->
	<div>
		<div class="mytit">用户信息</div>
		<div id="list" class="wrap">
			<img class="myspicture" alt="picture" src="${user.picture}">
			<span>${user.userId}</span>
			<a href="forward/user/address">我的收货地址</a>
			<a href="forward/user/allOrder">全部订单</a>
			<a href="forward/cart/index">待付款</a>
			<a href="forward/order/no">待发货</a>
			<a href="forward/order/yes">已发货</a>
		</div>

		<div class="mybtn">
			<div class="centerfooter-btn">
				<a href="index.html">返回首页</a>
			</div>
		</div>
	</div>
	<script src="static/forward/js/my.js"></script>
</body>

</html>