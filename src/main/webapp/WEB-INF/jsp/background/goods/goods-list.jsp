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
			<form class="layui-form" action="background/goods" method="post">
				<div class="layui-form-item">
					<div class="layui-inline tool-btn">
						<button class="layui-btn layui-btn-small layui-btn-normal addBtn"
							data-url="background/goods/goods-add">
							<i class="layui-icon">&#xe654;</i>
						</button>
						<button class="layui-btn layui-btn-small layui-btn-danger delBtn"
							data-url="background/goods/goods-delete-many">
							<i class="layui-icon">&#xe640;</i>
						</button>
						<button
							class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs"
							data-url="article-add.html">
							<i class="iconfont">&#xe656;</i>
						</button>
					</div>
					<div class="layui-inline">
						<input type="text" name="name" placeholder="请输入商品名"
							value="${goods.name}" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-inline">
						<input type="text" name="price" placeholder="请输入价格"
							value="${goods.price}" autocomplete="off" class="layui-input">
					</div>
					<input type="hidden" name="pageNumber" value="" /> <input
						type="hidden" name="pageSize" value="" />

					<button class="layui-btn layui-btn-normal findBtn"
						lay-submit="search">搜索</button>
					<button class="layui-btn layui-btn-normal excelNow"
						data-url="background/goods/excel">导出本页</button>
					<button class="layui-btn layui-btn-normal excelAll"
						data-url="background/goods/excel">导出全部</button>
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
							<th class="hidden-xs">商品编号</th>
							<th class="hidden-xs">排序</th>
							<th>商品名</th>
							<th>价格</th>
							<th>门店价</th>
							<th>剩余数量</th>
							<th>单位</th>
							<th>热度</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${list}" var="l">
							<tr>
								<td><input type="checkbox" name="" class="myChecked"
									lay-skin="primary" data-id="${l.id}"></td>
								<td>${l.goodsId }</td>
								<td class="hidden-xs"><input type="text" name="title"
									autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td class="hidden-xs">${l.name}</td>
								<td>${l.price}</td>
								<td>${l.shopPrice}</td>
								<td>${l.inventory}</td>
								<td>${l.goodsUnit}</td>
								<td><button
										class="layui-btn layui-btn-mini layui-btn-normal">
										${l.hot}</button></td>
								<td>${l.type.parent.parent.name}${l.type.parent.name}
									${l.type.name}</td>
								<td>
									<div class="layui-inline">
										<button
											class="layui-btn layui-btn-small layui-btn-normal go-btn"
											data-id="${l.id}" data-url="background/goods/goods-detail">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button
											class="layui-btn layui-btn-small layui-btn-danger del-btn"
											data-id="${l.id}" data-url="background/goods/goods-delete">
											<i class="layui-icon">&#xe640;</i>
										</button>
										<button
											class="layui-btn layui-btn-small layui-btn-normal addBtn1"
											data-id="${l.id}" data-url="background/goods/type">
											<i class="layui-icon">种类</i>
										</button>
										<button
											class="layui-btn layui-btn-small layui-btn-normal go-btn"
											data-id="${l.id}" data-url="background/goods/picture">
											<i class="layui-icon">图片</i>
										</button>
										<button
											class="layui-btn layui-btn-small layui-btn-normal go-btn"
											data-id="${l.id}" data-url="background/goods/detail">
											<i class="layui-icon">详情</i>
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
							<li><a class="last" href="javascript:void(0);"
								data-pageNumber="${pageNumber-1}">«</a></li>
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
									<li><a href="javascript:void(0);" class="pageNumber"
										data-pageNumber="${p}">${p}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${page.pageNum!=page.navigateLastPage}">
							<li><a class="next" data-pageNumber="${pageNumber+1}"
								href="javascript:void(0);">»</a></li>
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