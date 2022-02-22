<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>商品详情页面</title>
<link rel="stylesheet" type="text/css"
	href="static/forward-detail/css/detail.css" />
<script src="static/forward-detail/js/jquery-3.6.0.min.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="static/forward-detail/swiper/swiper-bundle.min.css" />
<script src="static/forward-detail/swiper/swiper-bundle.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="static/forward-detail/js/detail.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	var path = "${path}/";
</script>
</head>
<body>
	<div class="hidden"></div>
	<!-- 导航条 -->
	<div class="navigation">
		<div class="container">
			<div>
				<ul>
					<li data-url="goods/detail?id=${goods.id}">${goods.name}</li>
				</ul>
			</div>
			<div>
				<ul>
					<li data-url="#">概述页</li>
					<li data-url="#">参数页</li>
					<li data-url="#">F码通道</li>
					<li data-url="#">咨询客服</li>
					<li data-url="#">用户评价</li>
				</ul>
			</div>

		</div>
	</div>
	<!-- 图片 -->
	<div class="bodyer">
		<div class="swiper">
			<div class="swiper-wrapper">
				<!-- 内容块一 :大图-->
				<c:forEach items="${goods.pictures}" var="p">
					<div class="swiper-slide shuffling">
						<div class="swiper-slide shuffling">
							<div>
								<img src="${p.picture}" />
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- 如果需要分页器 -->
			<div
				class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets swiper-pagination-horizontal">
				<c:forEach items="${goods.pictures}" varStatus="i">
					<span
						class="swiper-pagination-bullet swiper-pagination-bullet-active"
						tabindex="${i.index}">${i.count}</span>
				</c:forEach>
			</div>
			<!-- 如果需要导航按钮 -->
			<div class="swiper-button-prev"></div>
			<div class="swiper-button-next"></div>
		</div>

		<div class="img">${goods.children.goodsIntroduce}</div>
	</div>
</body>
</html>
