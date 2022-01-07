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
	<form class="layui-form column-content-detail">
		<div class="layui-tab">
			<ul class="layui-tab-title">
				<li class="layui-this">添加用户</li>
				<li>更多</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-block">
							<input type="text" name="userId" value="${user.userId}" required
								lay-verify="required" placeholder="请输入用户名" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-block">
							<input type="password" name="password" required
								lay-verify="required" placeholder="请输入密码" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<select name="userStatus" lay-verify="required">
								<option value="">请选择状态</option>
								<option value="1">正常</option>
								<option value="0">冻结</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">头像上传：</label>
						<div class="layui-input-block">
							<input type="file" name="pic" class="layui-upload-file">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">电话：</label>
						<div class="layui-input-block">
							<input type="text" name="phone" required lay-verify="required"
								value="${user.phone}" maxlength="11" placeholder="请输入电话号码"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">QQ：</label>
						<div class="layui-input-block">
							<input type="text" name="qq" required lay-verify="required"
								value="${user.qq}" maxlength="10" placeholder="请输入QQ"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">e-mail：</label>
						<div class="layui-input-block">
							<input type="email" name="email" required lay-verify="required"
								value="${user.email}" placeholder="请输入邮箱" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">身份证号：</label>
						<div class="layui-input-block">
							<input type="text" name="cardId" required lay-verify="required"
								value="${user.cardId}" placeholder="请输入身份证号" autocomplete="off"
								class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
					<div class="layui-form-item">
						<label class="layui-form-label">金额：</label>
						<div class="layui-input-block">
							<input type="text" name="userMoney" placeholder="请输入金额"
								value="${userStatus}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="padding-left: 10px;">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-normal" lay-submit
					lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script src="static/admin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="static/admin/js/common.js" type="text/javascript"
		charset="utf-8"></script>
	<script>
		layui
				.use(
						[ 'form', 'jquery', 'laydate', 'layer', 'laypage',
								'dialog', 'common', 'tool', 'element',
								'upload', 'layedit' ],
						function() {
							var form = layui.form(), layer = layui.layer, $ = layui.jquery, laypage = layui.laypage, laydate = layui.laydate, layedit = layui.layedit, common = layui.common, tool = layui.tool, element = layui
									.element(), dialog = layui.dialog;

							//获取当前iframe的name值
							var iframeObj = $(window.frameElement).attr('name');
							//创建一个编辑器
							var editIndex = layedit.build('LAY_demo_editor', {
								tool : [ 'strong' //加粗
								, 'italic' //斜体
								, 'underline' //下划线
								, 'del' //删除线
								, '|' //分割线
								, 'left' //左对齐
								, 'center' //居中对齐
								, 'right' //右对齐
								, 'link' //超链接
								, 'unlink' //清除链接
								, 'image' //插入图片
								],
								height : 100
							})
							//全选
							form.on('checkbox(allChoose)', function(data) {
								var child = $(data.elem).parents('table').find(
										'tbody input[type="checkbox"]');
								child.each(function(index, item) {
									item.checked = data.elem.checked;
								});
								form.render('checkbox');
							});
							form.render();

							layui.upload({
								url : '上传接口url',
								success : function(res) {
									console.log(res); //上传成功返回值，必须为json格式
								}
							});
						});
		var detail = "${detail}";
		if (detail == '添加成功') {
			layer.cloaseAll();
		}
	</script>
</body>
</html>