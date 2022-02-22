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
	<div class="page-content-wrap">
		<form class="layui-form" action="background/goods/edit-detail">
			<div class="layui-tab" style="margin: 0;">
				<ul class="layui-tab-title">
					<li><a href="background/goods">商品列表</a></li>
					<li class="layui-this">商品详细</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item"></div>
					<div class="layui-tab-item layui-show">
						<div class="layui-form-item">
							<label class="layui-form-label">备注：</label>
							<div class="layui-input-block">
								<!-- 加载编辑器的容器 -->
								<script id="editor" name="goodsIntroduce" type="text/plain"></script>
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
	</div>
	<script type="text/javascript">
			var SCOPE = {
				static: '/static',
				index: '/admin/category/index.html',
				add: 'add.html',
				save: '/admin/category/save.html',
				edit: 'add.html',
				updateEdit: '/admin/category/updateedit.html',
				status: '/admin/category/updatestatus.html',
				del: '/admin/category/del.html',
				delAll: '/admin/category/deleteall.html',
				listOrderAll: '/admin/category/listorderall.html'
			}
		</script>
	<script src="static/admin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="static/admin/js/common.js" type="text/javascript"
		charset="utf-8"></script>
	<!-- UEditor配置 -->
	<script type="text/javascript" src="static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="static/ueditor/ueditor.all.min.js"></script>
	<script>
	
	//注意：某些情况下，可能会出现stu.description中的双引号没有转义，导致出现截断问题
	var content = `${goodsDetail.goodsIntroduce}`;

	//在script标记上设置name属性，则文本编辑区在失去焦点时，会自动创建一个name属性值与其相同的文本域表单，用于提交
	var ue = UE.getEditor('editor', {
		//防止UEditor无限制自动长高
		autoHeightEnabled : false,
		//此属性用于指定编辑区的高度，其效果等同于设置ue.setHeight(320)。值必须为数字，单位px，字符串无效。
		initialFrameHeight : 320,
		//统一后台提交地址（例如：获取配置，上传图片等）。覆盖ueditor.config.js中的serverUrl默认属性值
		//ueditor不同的操作都会提交到此地址，并携带一个名称为action的查询参数
		serverUrl : "/ueditor"
	});

	//实例就绪事件
	ue.ready(function() {
		//设置UEditor文本编辑区的高度，其效果等同于设置initialFrameHeight属性
		//this.setHeight(320);
		this.setContent(content);
		//this.setDisabled(true);
	});
			var detail = "${detail}";
			if(detail) {
				alert(detail);
			}
	</script>
</body>

</html>