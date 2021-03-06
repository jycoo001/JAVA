<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>网站后台管理模版</title>
<link rel="stylesheet" type="text/css"
	href="static/admin/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="static/admin/css/admin.css" />
</head>
<body>
	<div class="layui-tab page-content-wrap">
		<ul class="layui-tab-title">
			<li class="layui-this">修改资料</li>
			<li>修改密码</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form class="layui-form" action="background/admin/update"
					method="post" style="width: 90%; padding-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label">id：</label>
						<div class="layui-input-block">
							<input type="text" name="id" disabled="disabled" required
								lay-verify="required" placeholder="请输入ID" autocomplete="off"
								class="layui-input" value="${admin.id}">
						</div>
						<input type="hidden" name="id" required lay-verify="required"
							value="${admin.id}">
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-block">
							<input type="text" name="name" required lay-verify="required"
								placeholder="请输入标题" autocomplete="off" class="layui-input"
								value="${admin.name}">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">电话：</label>
						<div class="layui-input-block">
							<input type="text" name="phone" required lay-verify="required"
								placeholder="请输入电话" maxlength="11" autocomplete="off"
								class="layui-input" value="${admin.phone}">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit
								lay-filter="adminInfo">立即提交</button>
						</div>
					</div>
				</form>
			</div>
			<div class="layui-tab-item">
				<form class="layui-form" action="background/admin/update-password"
					method="post" v style="width: 90%; padding-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" disabled="disabled"
								autocomplete="off" class="layui-input layui-disabled"
								value="${admin.name}">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">旧密码：</label>
						<div class="layui-input-block">
							<input type="password" name="password1" required
								lay-verify="required" placeholder="请输入密码" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">新密码：</label>
						<div class="layui-input-block">
							<input type="password" name="password2" required
								lay-verify="required" placeholder="请输入密码" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">重复密码：</label>
						<div class="layui-input-block">
							<input type="password" name="password3" required
								lay-verify="required" placeholder="请输入密码" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit
								lay-filter="adminPassword">立即提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="static/admin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script>
		//Demo
		layui.use([ 'form', 'element' ], function() {
			var form = layui.form();
			var element = layui.element();
			form.render();
			//监听信息提交
			form.on('submit(adminInfo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				$(".layui-form:nth-child(1)").submit();
				return false;
			});
			//监听密码提交
			form.on('submit(adminPassword)', function(data) {
				layer.msg(JSON.stringify(data.field));
				$(".layui-form:nth-child(2)").submit();
				return false;
			});
		});
	</script>
</body>
</html>