<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="background/goods/type" method="post">
		<input type="hidden" name="id" value="${id}">
		<div>
			<select class="select1">
				<option value="">请选择种类</option>
				<c:forEach items="${type}" var="t">
					<option value="${t.id}">${t.name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<select class="select2">
				<option value="">请选择种类</option>
			</select>
		</div>
		<div>
			<select name="typeId" class="select3">
				<option value="">请选择种类</option>
			</select>
		</div>
		<div>
			<input type="submit" value="提交">
			<input type="reset" value="重置">
		</div>
	</form>
	<script type="text/javascript"
		src="static/public/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".select1").change(function() {
				var value =  $(this).val();
				<c:forEach items="${type}" var="t">
					if(${t.id}==value) {
						<c:forEach items="${t.twoTypes}" var="t1">
							$(".select2").append("<option value='${t1.id}'>${t1.name}</option>");
						</c:forEach>
					}
				</c:forEach>
			});
			$(".select2").change(function() {
				var value1 = $(".select1").val();
				var value =  $(this).val();
				<c:forEach items="${type}" var="t">
					if(${t.id}==value1) {
						<c:forEach items="${t.twoTypes}" var="t1">
							if(${t1.id}==value) {
								<c:forEach items="${t1.threeTypes}" var="t2">
									$(".select3").append("<option value='${t2.id}'>${t2.name}</option>");
								</c:forEach>
							}
						</c:forEach>
					}
				</c:forEach>
			});
		});
	</script>
</body>
</html>