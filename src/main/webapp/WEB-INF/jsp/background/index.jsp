<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html>
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
	<div class="main-layout" id='main-layout'>
		<!--侧边栏-->
		<div class="main-layout-side">
			<div class="m-logo"></div>
			<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				<li class="layui-nav-item layui-nav-itemed"><a
					href="javascript:;"><i class="iconfont">&#xe607;</i>用户管理</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" data-url="background/admin/user"
								data-id='1' data-text="前台用户管理"><span class="l-line"></span>前台用户管理</a>
						</dd>
						<dd>
							<a href="javascript:;" data-url="background/admin/admin"
								data-id='2' data-text="后台管理员"><span class="l-line"></span>后台管理员菜单</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item layui-nav-itemed"><a
					href="javascript:;"><i class="iconfont">&#xe607;</i>菜单管理</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" data-url="background/shuffling" data-id='3'
								data-text="轮播图管理"><span class="l-line"></span>轮播图菜单</a>
						</dd>
						<dd>
							<a href="javascript:;" data-url="background/type/list?parentId=0"
								data-id='4' data-text="商品分类管理"><span class="l-line"></span>商品分类</a>
						</dd>
						<dd>
							<a href="javascript:;" data-url="background/logo" data-id='5'
								data-text="商城LOGO管理"><span class="l-line"></span>LOGO菜单</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="iconfont">&#xe608;</i>内容管理</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" data-url="/background/order/order-list"
								data-id='6' data-text="订单管理"><span class="l-line"></span>订单管理</a>
						</dd>
						<dd>
							<a href="javascript:;" data-url="/background/goods"
								data-id='7' data-text="商品管理"><span class="l-line"></span>商品管理</a>
						</dd>
						<dd>
							<a href="javascript:;" data-url="/background/back"
								data-id='8' data-text="退货管理"><span class="l-line"></span>退货管理</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="iconfont">&#xe604;</i>推荐位管理</a></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="iconfont">&#xe60c;</i>友情链接</a></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="iconfont">&#xe60a;</i>RBAC</a></li>
				<li class="layui-nav-item"><a href="javascript:;"
					data-url="email.html" data-id='4' data-text="邮件系统"><i
						class="iconfont">&#xe603;</i>邮件系统</a></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="iconfont">&#xe60d;</i>生成静态</a></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="iconfont">&#xe600;</i>备份管理</a></li>
				<li class="layui-nav-item"><a href="javascript:;"
					data-url="background/admin/my" data-id='5' data-text="个人信息"><i
						class="iconfont">&#xe606;</i>个人信息</a></li>
				<li class="layui-nav-item"><a href="javascript:;"
					data-url="system.html" data-id='6' data-text="系统设置"><i
						class="iconfont">&#xe60b;</i>系统设置</a></li>
			</ul>
		</div>
		<!--右侧内容-->
		<div class="main-layout-container">
			<!--头部-->
			<div class="main-layout-header">
				<div class="menu-btn" id="hideBtn">
					<a href="javascript:;"> <span class="iconfont">&#xe60e;</span>
					</a>
				</div>
				<ul class="layui-nav" lay-filter="rightNav">
					<li class="layui-nav-item"><a href="javascript:;"
						data-url="email.html" data-id='4' data-text="邮件系统"><i
							class="iconfont">&#xe603;</i></a></li>
					<li class="layui-nav-item"><a href="javascript:;"
						data-url="admin-info.html" data-id='5' data-text="个人信息">超级管理员</a>
					</li>
					<li class="layui-nav-item"><a href="javascript:;"
						data-url="background/admin/logout" data-id='6' data-text="退出">退出</a></li>
				</ul>
			</div>
			<!--主体内容-->
			<div class="main-layout-body">
				<!--tab 切换-->
				<div class="layui-tab layui-tab-brief main-layout-tab"
					lay-filter="tab" lay-allowClose="true">
					<ul class="layui-tab-title">
						<li class="layui-this welcome">后台主页</li>
					</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show"
							style="background: #f5f5f5;">
							<!--1-->
							<iframe src="background/welcome" width="100%" height="100%"
								name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
							<!--1end-->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--遮罩-->
		<div class="main-mask"></div>
	</div>
	<script type="text/javascript">
		var scope = {
			link : 'background/welcome'
		}
	</script>
	<script src="static/admin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="static/admin/js/common.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="static/admin/js/main.js" type="text/javascript"
		charset="utf-8"></script>

</body>
</html>
