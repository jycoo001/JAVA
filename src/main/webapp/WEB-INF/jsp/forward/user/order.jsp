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
<link href="static/public/css/button.css" rel="stylesheet">
<link href="static/forward/css/order.css" rel="stylesheet">
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
				</a> <a href="/forward/user/my">${user.userId}(${user.id})</a>
				<button type="button" class="btn-edit">修改用户</button>
			</div>
			<div class="address">
				<table>
					<thead>
						<th>订单号</th>
						<th>实付款</th>
						<th>地址</th>
						<th>物流状态</th>
						<th colspan="2">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${order}" var="o">
							<tr>
								<td>${o.id}</td>
								<td>${o.allPrice}</td>
								<td>${user.myAddress.address.parent.parent.parent.name}
									${user.myAddress.address.parent.parent.name}
									${user.myAddress.address.parent.name }
									${user.myAddress.address.name }</td>
								<td><c:choose>
										<c:when test="${o.status=='未发货'}">
										${o.status}
									</c:when>
										<c:otherwise>
										${o.userStatus}
									</c:otherwise>
									</c:choose></td>
								<td><c:if test="${o.userStatus!='已收货' && o.status=='已发货'}">
										<a href="forward/order/orderUpdate?id=${o.id}"><button
												class="button">确认收货</button></a>
									</c:if></td>
								<td><c:if
										test="${o.userStatus!='已收货' && o.userStatus!='已退货'}">
										<a href="forward/user/goods-back?id=${o.id}"><button
												class="button">我要退货</button></a>
									</c:if> <c:if test="${o.userStatus=='已收货'}">
										<a href="javascript:void(0);" class="goodsDesc"><button
												class="button">去评价</button></a>
									</c:if> <c:if test="${o.userStatus=='已退货'}">
										<span>已退货</span>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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

	<div class="orderDesc hidden">
		<form action="forward/order/desc" method="post">
			<!-- 加载编辑器的容器 -->
			<input type="hidden" name="id" value="" />
			<script id="editor" name="desc" type="text/plain"></script>
			<button type="submit" class="ueditor">立即提交</button>
			<button type="button" class="fail">取消</button>
		</form>
	</div>
	<script type="text/javascript" src="static/forward/js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="static/public/layer/layer.js"></script>
	<script type="text/javascript" src="static/forward/js/order.js"></script>
	<!-- UEditor配置 -->
	<script type="text/javascript" src="static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="static/ueditor/ueditor.all.min.js"></script>
	<script>
		//注意：某些情况下，可能会出现stu.description中的双引号没有转义，导致出现截断问题
		var content = `${desc}`;

		//在script标记上设置name属性，则文本编辑区在失去焦点时，会自动创建一个name属性值与其相同的文本域表单，用于提交
		var ue = UE.getEditor('editor', {
			//防止UEditor无限制自动长高
			autoHeightEnabled : false,
			//此属性用于指定编辑区的高度，其效果等同于设置ue.setHeight(320)。值必须为数字，单位px，字符串无效。
			initialFrameHeight : 300,
			initialFrameWeight : 300,
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
		if (detail) {
			layer.alert(detail);
		}
	</script>
</body>

</html>