<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>短信和二维码注册页面</title>
<meta
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"
	name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<link href="static/forward-login/css/style.css" rel="stylesheet"
	type="text/css">
<link href="static/forward-login/css/login.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="static/forward/js/jquery-3.4.1.js"></script>
</head>


<div class="aui-register-popup">
	<div class="aui-register-box">
		<div class="aui-register-logo">购物登录</div>
		<div class="aui-register-form" id="verifyCheck">
			<div class="register-wrap" id="register-wrap">
				<div class="register" id="register">
					<div class="register-top" id="reg-top">
						<h2 class="normal" id="normal">普通登录</h2>
						<h2 class="nopassword" id="nopw">手机无密码登录</h2>
						<a id="qrcode" href="#"> <span class="aui-tag-size">扫码登录</span>
						</a>
					</div>
					<!--账户密码登录-->
					<div class="register-con" id="rc">
						<div class="aui-register-form-item">
							<input type="text" name="username" maxlength="20"
								placeholder="账户名" class="txt03 f-r3 required" tabindex="3"
								data-valid="isNonEmpty||between:3-20||isUname"
								data-error="><i class='icon-tips'></i>您还没有输入账户名||<i class='icon-tips'></i>用户名长度3-20位||<i class='icon-tips'></i>只能输入字母、数字、且以中文或字母开头"
								id="adminNo"> <label class="focus valid"></label>
						</div>
						<div class="aui-register-form-item">
							<input type="password" name="password" placeholder="密码"
								id="password" maxlength="20" class="txt04 f-r3 required"
								tabindex="4" style="ime-mode: disabled;" onpaste="return  false"
								autocomplete="off"
								data-valid="isNonEmpty||between:6-20||level:2"
								data-error="><i class='icon-tips'></i>密码太短，最少6位||<i class='icon-tips'></i>密码长度6-20位||<i class='icon-tips'></i>密码太简单，有被盗风险，建议字母+数字的组合">
							<label class="focus valid"></label>

						</div>
						<div class="aui-register-form-item">
							<input type="text" name="code" maxlength="10" placeholder="验证码"
								class="txt05 f-r3 required" tabindex="3"
								data-valid="isNonEmpty||between:1-10"
								data-error="><i class='icon-tips'></i>您还没有输入验证码||<i class='icon-tips'></i>用户名长度1-10位"
								id="adminNo"><img id="imageCode" alt="code" src="code">
							<label class="focus valid"></label>
						</div>
						<div class="aui-register-form-item">
							<p class="aui-for-pwd">
								<a class="" href="#">忘记密码</a>
							</p>
							<input id="aui-btn-reg" class="aui-btn-reg" placeholder=""
								readonly="readonly" value="登录">
						</div>
						<div class="aui-protocol">
							登录即同意 <a href="#">《飞天素材网使用协议》</a>& <a href="#">《隐私权条款》</a>
						</div>
						<div class="aui-thirds">
							<a href="#"> <i class="aui-qq-img"></i> <i>QQ登录</i>
							</a> <a href="#"> <i class="aui-wx-img"></i> <i>微信登录</i>
							</a> <a href="#"> <i class="aui-wb-img"></i> <i>微博登录</i>
							</a>
							<div class="clear"></div>
						</div>
					</div>

					<!--手机动态码登录-->
					<div class="login-con" id="lc">
						<div class="aui-register-form-item">
							<input type="text" name="phone" placeholder="手机号码"
								class="txt01 f-r3 required" keycodes="tel" tabindex="1"
								data-valid="isNonEmpty||isPhone"
								data-error="><i class='icon-tips'></i>请输入手机号||<i class='icon-tips'></i>手机号码格式不正确"
								maxlength="11" id="phone"> <label class="focus valid"><div
									class="msg" style="display: none">
									<i class='icon-tips'></i>您还未输入手机号
								</div></label> <span class="aui-get-code btn btn-gray f-r3 f-ml5 f-size13"
								id="time_box" disabled="" style="display: none;"></span> <span
								class="aui-get-code btn btn-gray f-r3 f-ml5 f-size13"
								id="verifyYz">获取动态码</span>
						</div>
						<div class="aui-register-form-item">
							<input type="text" placeholder="动态码" maxlength="6" id="verifyNo"
								class="txt02 f-r3 f-fl required" tabindex="2"
								data-valid="isNonEmpty||isInt"
								data-error="><i class='icon-tips'></i>请填写正确的手机动态码||<i class='icon-tips'></i>请填写6位手机动态码">
							<label class="focus valid"></label>
						</div>
						<div class="aui-register-form-item">
							<input id="aui-btn-reg1" class="aui-btn-reg" placeholder=""
								readonly="readonly" value="登录">
						</div>
						<div class="aui-protocol">
							登录即同意 <a href="#">《飞天素材网使用协议》</a>& <a href="#">《隐私权条款》</a>
						</div>
						<div class="aui-thirds">
							<a href="#"> <i class="aui-qq-img"></i> <i>QQ登录</i>
							</a> <a href="#"> <i class="aui-wx-img"></i> <i>微信登录</i>
							</a> <a href="#"> <i class="aui-wb-img"></i> <i>微博登录</i>
							</a>
							<div class="clear"></div>
						</div>
					</div>


					<!-- 扫码登录 -->
					<div class="saoma" id="sm">
						<div class="screen-tu" id="screen">
							<span class="aui-tag-size">密码登录</span>
						</div>
						<div class="aui-text-item">
							<h1>1秒即登录，方便又安全</h1>
						</div>
						<div class="qr-code">
							<span class="tips_img"></span> <img src="static/picture/code.png"
								alt="">
						</div>
						<div class="aui-tab-footer">
							<p>
								扫码登录，同步账户信息 | <a href="#">下载APP</a>
							</p>
						</div>

					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>

<div style="display: none">
	<form class="formhidd" action="forward/user/login" method="post">
		<input type="text" name="userId" /> <input type="password"
			name="password" /> <input type="text" name="code" />
	</form>
</div>
<script type="text/javascript" src="static/forward-login/js/login.js"></script>
<script type="text/javascript">
	$(function() {
		var detail = "${detail}";
		if (detail) {
			alert(detail);
			if(detail=="登陆成功") {
				window.localStorage.setItem("username","${user.userId}");
				window.location="/";
			}
		}
		$("#imageCode").click(function() {
			$("#imageCode").attr("src", "code?" + Math.random());
		});

		$("#aui-btn-reg").click(function() {
			if (!verifyCheck._click())
				return;
			$(".formhidd>input[name=userId]").val($("input.txt03").val());
			$(".formhidd>input[name=password]").val($("input.txt04").val());
			$(".formhidd>input[name=code]").val($("input.txt05").val());
			$(".formhidd").submit();
		});
		$("#aui-btn-reg1").click(function() {
			if (!verifyCheck._click())
				return;
			//alert('恭喜你！登录成功')

		});
	});
</script>

</html>
