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
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>商城用户自助修改</title>
<link href="static/forward/css/public.css" rel="stylesheet">
<link href="static/forward/css/sigin.css" rel="stylesheet">
<script src="static/forward/js/jquery-3.4.1.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$(".submit").click(function() {
			$(".form-form>form").submit();
		});
	});
</script>
<body>
	<div class="form-wrap"></div>
	<div class="form">

		<!-- 顶部 -->
		<div class="form-top">
			<a href="index.html">返回首页</a>
		</div>
		<!-- 表单logo -->
		<div class="form-logo"></div>
		<!-- 表单内容 -->
		<div class="form-form">
			<h3>修改用户</h3>
			<form action="/forward/user/update" method="post">
				<input type="hidden" name="id" value="${user.id}">
				<div class="form-input">
					<input type="text" name="userId" placeholder="请输入用户名" id="username"
						value="${user.userId}"> <label class="hint"></label>
				</div>
				<div class="form-input">
					<input type="password" name="password" placeholder="请输入密码" id="psd"
						value=""> <label class="hint"></label class="hint">
				</div>
				<div class="form-input">
					<input type="password" name="" placeholder="请确认密码" id="repsd"
						value=""> <label class="hint"></label class="hint">
				</div>
				<div class="form-input">
					<input type="tel" name="phone" placeholder="请输入手机号" id="tel"
						value="${user.phone}"> <label class="hint"></label class="hint">
				</div>
				<div class="form-input">
					<input type="text" name="cardId" placeholder="请输入身份证号" id="id"
						value="${user.cardId}"> <label class="hint"></label class="hint">
				</div>
				<div class="form-input">
					<input type="text" name="qq" placeholder="请输入QQ号" id="qq"
						value="${user.qq }"> <label class="hint"></label class="hint">
				</div>
				<div class="form-input">
					<input type="email" name="email" placeholder="请输入邮箱" id="mail"
						value="${user.email }"> <label class="hint"></label class="hint">
				</div>
				<div class="submit">修改</div>
			</form>
		</div>
	</div>
</body>
</html>