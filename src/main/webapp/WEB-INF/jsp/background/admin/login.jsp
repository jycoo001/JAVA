<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css"
	href="static/after-login/css/index.css" />
<script type="text/javascript" src="static/forward/js/jquery-3.4.1.js"></script>
</head>
<body>

	<img class="bgone" src="static/after-login/img/1.jpg" />
	<img class="pic" src="static/after-login/img/a.png" />

	<div class="table">
		<div class="wel">商城系统后台登录</div>
		<div class="wel1">SHANG CHENG XI TONG HUO TAI DENG LU</div>
		<div class="user">
			<div id="yonghu" style="">
				<img src="static/after-login/img/yhm.png" />
			</div>
			<input type="text" name="name" placeholder="用户名" value="${admin.name}" />
		</div>
		<div class="password">
			<div id="yonghu">
				<img src="static/after-login/img/mm.png" />
			</div>
			<input type="password" name="密码" placeholder="密码" value="${admin.password}" />
		</div>
		<div class="code">
			<input type="text" name="code" required lay-verify="required"
				placeholder="验证码" autocomplete="off" class="layui-input"> <img
				class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();"
				src="code" />
		</div>
		<input class="btn butt" type="button" name="登录" value="登录" />
	</div>

	<div class="formhidd">
		<form action="background/admin/login" method="post">
			<input type="text" name="name" /> <input type="password"
				name="password" /> <input type="text" name="code" />
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			$(".butt").click(function() {
				$(".formhidd input[name=name]").val($("input[name=name]").val());
				$(".formhidd input[name=password]").val($("input[name=密码]").val());
				$(".formhidd input[name=code]").val($("input[name=code]").val());
				$(".formhidd form").submit();
			});
		});
	</script>
</body>
</html>
