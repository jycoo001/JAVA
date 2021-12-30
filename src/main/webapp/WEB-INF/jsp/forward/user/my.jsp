<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的信息</title>
<link href="static/forward/css/my.css" rel="stylesheet">
<script type="text/javascript">
	var detail = "${detail}";
</script>
</head>

<body>

	<div>
		<div class="mytit">用户信息</div>
		<div id="list" class="wrap">
			<div class="user">
				<a href="forward/user/updateImage"> <img
					src="${user.userPicture}"
					style="width: 60px; height: 60px; border-radius: 20px; margin-right: 100px;"
					alt="" />
				</a> <a href="forward/user/my">${user.userId}(${user.id})</a>
				<button type="button" class="btn-edit">修改用户</button>
			</div>
			<div class="address">
				<span>收货地址：</span>
				<table>
					<thead>
						<th>地址</th>
						<th>操作</th>
					</thead>
					<tbody>
						<c:forEach items="${user.address}" var="a">
							<tr>
								<td>${a.address.parent.parent.name}${a.address.parent.name}
									${a.address.name}</td>
								<td><c:choose>
										<c:when test="${a.defaults=='1'}">
											<span>默认地址</span>
										</c:when>
										<c:otherwise>
											<button class="btn-edit" my="${a.id}">设置为默认地址</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<button class="address-add">新增地址</button>
			</div>
			<div class="order">
				<a href="forward/order/all">所有订单</a>
			</div>
			<div class="noorder">
				<a href="forward/order/noorder">未发货订单</a>
			</div>
			<div class="orderno">
				<a href="forward/order/orderno">未收货订单</a>
			</div>
			<div class="detail">
				<a href="forward/order/orderyes">未评价订单</a>
			</div>
		</div>

		<div class="mybtn">
			<div class="centerfooter-btn">
				<a href="">返回首页</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="static/forward/js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="static/public/layer/layer.js"></script>
	<script src="static/forward/js/my.js"></script>
</body>

</html>