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
<style type="text/css">
</style>
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
						<th colspan="3">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${user.address}" var="a">
							<tr>
								<td>${a.address.parent.parent.parent.name}
									${a.address.parent.parent.name} ${a.address.parent.name}
									${a.address.name}</td>
								<input type="hidden" class="inpaddre" name="addressId"
									value="${a.id}" />
								<td><c:choose>
										<c:when test="${a.defaults=='1'}">
											<span>默认地址</span>
										</c:when>
										<c:otherwise>
											<button class="btn-edit" my="${a.id}">设置为默认地址</button>
										</c:otherwise>
									</c:choose></td>
								<td>
									<button class="address-edit" my="${a.id}">修改地址</button>
								</td>
								<td>
									<button class="address-delete" my="${a.id}">删除地址</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<button class="address-add">新增地址</button>
				<div id="modal" class="c2 hide">
					<div class="" style="cursor: pointer;" onclick="Hide();">x</div>
					<div class="kong"></div>
					<input type="hidden" name="address" value="" />
					<p>
						省：<select name="oneId"></select>
					</p>
					<p>
						市：<select name="twoId"></select>
					</p>
					<p>
						区/县：<select name="threeId"></select>
					</p>
					<p>
						镇：<select name="addressId"></select>
					</p>
					<p>
						<input class="addYes" type="button" value="确定"> <input
							type="button" value="取消" onclick="Hide();">
					</p>
				</div>
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