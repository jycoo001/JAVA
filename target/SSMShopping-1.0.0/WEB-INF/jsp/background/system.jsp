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
<link rel="stylesheet" type="text/css"
	href="static/admin/css/admin.css" />
</head>

<body>
	<div class="layui-tab page-content-wrap">
		<ul class="layui-tab-title">
			<li class="layui-this">站点配置</li>
			<li>SEO配置</li>
			<li>邮箱配置</li>
			<li>评论设置</li>
		</ul>
		<div class="layui-tab-content">
			<!--站点配置-->
			<div class="layui-tab-item layui-show">
				<form class="layui-form" style="width: 90%; padding-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label">网站名称：</label>
						<div class="layui-input-block">
							<input type="text" name="webname" autocomplete="off"
								class="layui-input" value="mfan科技有限公司">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">网站域名：</label>
						<div class="layui-input-block">
							<input type="text" name="domain" autocomplete="off"
								class="layui-input" value="www.mfancms.com">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">站长邮箱：</label>
						<div class="layui-input-block">
							<input type="text" name="email" required lay-verify="required"
								placeholder="请输入联系邮箱" autocomplete="off" class="layui-input"
								value="5303588521@qq.com">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">是否缓存：</label>
						<div class="layui-input-block">
							<input type="checkbox" checked="" name="cache" lay-skin="switch"
								lay-filter="switchTest" lay-text="ON|OFF">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">静态首页：</label>
						<div class="layui-input-block">
							<input type="radio" name="static" value="1" title="生成" checked="">
							<input type="radio" name="static" value="0" title="不生成">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">备案信息：</label>
						<div class="layui-input-block">
							<input type="text" name="icp" required lay-verify="required"
								placeholder="请输入备案信息" autocomplete="off" class="layui-input"
								value="黔ICP备16007047">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">版权信息：</label>
						<div class="layui-input-block">
							<input type="text" name="email" required lay-verify="required"
								placeholder="请输入版权信息" autocomplete="off" class="layui-input"
								value="遵义依来德防水保温公司 copyRight © 2016">
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">统计代码：</label>
						<div class="layui-input-block">
							<textarea name="desc" placeholder="请输入代码" class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit
								lay-filter="siteInfo">立即提交</button>
						</div>
					</div>
				</form>
			</div>
			<!--SEO配置-->
			<div class="layui-tab-item">
				<form class="layui-form" style="width: 90%; padding-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label">SEO标题：</label>
						<div class="layui-input-block">
							<input type="text" name="title" placeholder="请输入seo标题"
								autocomplete="off" class="layui-input" value="">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">关键字：</label>
						<div class="layui-input-block">
							<input type="text" name="keyword" placeholder="请输入seo关键字"
								autocomplete="off" class="layui-input" value="">
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">描述：</label>
						<div class="layui-input-block">
							<textarea name="desc" placeholder="请输入seo描述"
								class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit
								lay-filter="seoInfo">立即提交</button>
						</div>
					</div>
				</form>
			</div>
			<!--邮箱配置-->
			<div class="layui-tab-item">
				<form class="layui-form" style="width: 90%; padding-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label">邮箱模式：</label>
						<div class="layui-input-block">
							<input type="radio" name="static" value="1" title="SMTP函数发送"
								checked="">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">服务器：</label>
						<div class="layui-input-block">
							<input type="text" name="smtp" placeholder="请输入邮件服务器"
								autocomplete="off" class="layui-input" value="smtp.163.com">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">端口：</label>
						<div class="layui-input-block">
							<input type="text" name="port" placeholder="请输入邮件发送端口"
								autocomplete="off" class="layui-input" value="25">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">发件人：</label>
						<div class="layui-input-block">
							<input type="text" name="email" placeholder="请输入发件人地址"
								autocomplete="off" class="layui-input" value="admin@abc3210.com">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">名称：</label>
						<div class="layui-input-block">
							<input type="text" name="name" placeholder="请输入发件人名称"
								autocomplete="off" class="layui-input" value="mfan管理员">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-block">
							<input type="text" name="username" placeholder="请输入用户名"
								autocomplete="off" class="layui-input" value="">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-block">
							<input type="text" name="password" placeholder="请输入密码"
								autocomplete="off" class="layui-input" value="">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit
								lay-filter="emailInfo">立即提交</button>
						</div>
					</div>
				</form>
			</div>
			<!--评论设置-->
			<div class="layui-tab-item">
				<form class="layui-form" style="width: 90%; padding-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label">评论审核：</label>
						<div class="layui-input-block">
							<input type="checkbox" name="audit" lay-skin="primary" title="开启"
								checked="">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">评论间隔：</label>
						<div class="layui-input-inline">
							<input type="text" name="time" placeholder="请输入评论时间间隔"
								autocomplete="off" class="layui-input" value="60">
						</div>
						<div class="layui-form-mid layui-word-aux">秒</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit
								lay-filter="commentInfo">立即提交</button>
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
			form.on('submit(siteInfo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});
			//监听seo提交
			form.on('submit(seoInfo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});
			//监听seo提交
			form.on('submit(emailInfo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});
			//监听评论提交
			form.on('submit(commentInfo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});
		});
	</script>
</body>

</html>