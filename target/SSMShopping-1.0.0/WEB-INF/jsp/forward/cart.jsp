<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="refresh" content="2"> -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>购物车</title>
<link href="static/forward/css/cart.css" rel="stylesheet">

</head>

<body>
	<!-- 头部 -->
	<div class="header"></div>
	<div class="wrap">
		<ul class="top">
			<li><input type="checkbox" class="checall">全选</li>
			<li>单价</li>
			<li>数量</li>
			<li>小计(元)</li>
			<li>编辑</li>
		</ul>
		<ul class="main">

		</ul>

		<div class="footer">
			<div class="footer-check">
				<div>
					<input type="checkbox" class="checall">全选
				</div>
				<div class="delall">删除选中的商品</div>
				<div>
					共<b class="goods">0</b>件商品
				</div>
				<div>
					已选择<b class="goodsnum">0</b>件
				</div>
			</div>
			<div class="footer-count">
				<div>
					合计(不含运费):
					<div class="price">￥0.00</div>
				</div>
				<div>去结算</div>
			</div>
		</div>
	</div>
	<script src="static/forward/js/jquery-3.4.1.js"></script>
	<script src="static/layer.js"></script>
	<script src="static/forward/js/cart.js"></script>
</body>

</html>