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
	<form class="layui-form column-content-detail"
		enctype="multipart/form-data" method="post">
		<div class="layui-tab">
			<ul class="layui-tab-title">
				<li class="layui-this">添加LOGO图片</li>
				<li>更多</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div class="layui-form-item">
						<label class="layui-form-label">状态：</label>
						<div class="layui-input-block">
							<select name="status" lay-verify="required">
								<option value="">请选择状态</option>
								<option value="1">正常</option>
								<option value="0">冻结</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">图片上传：</label>
						<div class="layui-input-block">
							<input type="file" name="picturex" autocomplete="off" />
						</div>
					</div>
				</div>
				<div class="layui-tab-item">no have</div>
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
	<script>
		layui
				.use(
						[ 'form', 'jquery', 'laydate', 'layer', 'laypage',
								'dialog', 'common', 'tool', 'element',
								'upload', 'layedit' ],
						function() {
							//start
							//提交按钮
							$("button.layui-btn-normal").click(function() {
								$("form.layui-form").submit();
							})
							//重置按钮
							$(".layui-btn-primary").click(function() {
								$("form.layui-form")[0].reset();
							})
							//end
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
								url : '',
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