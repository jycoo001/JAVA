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
	<div class="wrap-container clearfix">
		<div class="column-content-detail">
			<form class="layui-form" action="background/order/order-list">
				<div class="layui-form-item">
					<div class="layui-inline tool-btn">
						<button class="layui-btn layui-btn-small layui-btn-normal addBtn"
							data-url="article-add.html">
							<i class="layui-icon">&#xe654;</i>
						</button>
						<button class="layui-btn layui-btn-small layui-btn-danger delBtn"
							data-url="article-add.html">
							<i class="layui-icon">&#xe640;</i>
						</button>
						<button
							class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs"
							data-url="article-add.html">
							<i class="iconfont">&#xe656;</i>
						</button>
					</div>
					<div class="layui-inline">
						<input type="text" name="userId" placeholder="请输入用户名"
							autocomplete="off" class="layui-input">
					</div>
					<div class="layui-inline">
						<select name="status" lay-filter="status">
							<option value="">请选择一个状态</option>
							<option value="未发货">未发货</option>
							<option value="已发货">已发货</option>
						</select>
					</div>
					<button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
				</div>
			</form>
			<div class="layui-form" id="table-list">
				<table class="layui-table" lay-even lay-skin="nob">
					<colgroup>
						<col width="50">
						<col class="hidden-xs" width="50">
						<col class="hidden-xs" width="100">
						<col>
						<col class="hidden-xs" width="150">
						<col class="hidden-xs" width="150">
						<col width="80">
						<col width="150">
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" name="" lay-skin="primary"
								lay-filter="allChoose"></th>
							<th class="hidden-xs">订单号</th>
							<th class="hidden-xs">排序</th>
							<th>名称</th>
							<c:forEach items="${order[0].details}" var="o">
								<th>商品名</th>
								<th>数量</th>
							</c:forEach>
							<th class="hidden-xs">创建时间</th>
							<th class="hidden-xs">修改时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${order}" var="o">
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary"
									data-id="1"></td>
								<td class="hidden-xs">${o.orderId}</td>
								<td class="hidden-xs"><input type="text" name="title"
									autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>${o.userId}</td>
								<c:forEach items="${o.details}" var="d">
									<td>${d.goods.name}</td>
									<td>${d.count}</td>
								</c:forEach>
								<td class="hidden-xs">${o.localCreatTime}</td>
								<td class="hidden-xs">${o.localUpdateTime}</td>
								<td><button
										class="layui-btn layui-btn-mini layui-btn-normal">${o.status}</button></td>
								<td>
									<div class="layui-inline">
										<button
											class="layui-btn layui-btn-small layui-btn-normal go-btn"
											data-id="${o.id}" data-url="background/order/order-detail">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button
											class="layui-btn layui-btn-small layui-btn-danger del-btn"
											data-id="1" data-url="article-detail.html">
											<i class="layui-icon">&#xe640;</i>
										</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page-wrap">

					<ul class="pagination">
						<c:if test="${page.pageNum!=1}">
							<li><a
								href="/background/order-list?pageNumber=${pageNumber-1}&pageSize=${page.pageSize}">«</a></li>
						</c:if>
						<c:if test="${page.pageNum==1 }">
							<li class="disabled"><span>«</span></li>
						</c:if>
						<c:forEach begin="${page.navigateFirstPage}"
							end="${page.navigateLastPage}" var="p">
							<c:choose>
								<c:when test="${p==pageNumber}">
									<li class="active"><span>${p}</span></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="/background/order/order-list?pageNumber=${p}&pageSize=${page.pageSize}">${p}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${page.pageNum!=page.navigateLastPage}">
							<li><a
								href="/background/order-list?pageNumber=${pageNumber+1}&pageSize=${page.pageSize}">»</a></li>
						</c:if>
						<c:if test="${page.pageNum==page.navigateLastPage}">
							<li class="disabled"><span>»</span></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script src="static/admin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="static/admin/js/common.js" type="text/javascript"
		charset="utf-8"></script>
</body>

</html>