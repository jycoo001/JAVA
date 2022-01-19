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
	<form action="background/type/update" method="post">
		<input type="hidden" name="id" value="${id}">
		<div>
			<select name="oneId" class="select1">
				<option value="">请选择种类</option>
				<c:forEach items="${type}" var="t">
					<option value="${t.typeId}">${t.name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<select name="twoId" class="select2">
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
					if(${t.typeId}==value) {
						$(".select2").empty();
						$(".select3").empty();
						$(".select2").append("<option value=''>请选择种类</option>");
						$(".select3").append("<option value=''>请选择种类</option>");
						<c:forEach items="${t.childrens}" var="t1">
							$(".select2").append("<option value='${t1.typeId}'>${t1.name}</option>");
						</c:forEach>
					}
				</c:forEach>
				
			});
			$(".select2").change(function() {
				var value1 = $(".select1").val();
				var value =  $(this).val();
				<c:forEach items="${type}" var="t">
					if(${t.typeId}==value1) {
						<c:forEach items="${t.childrens}" var="t1">
							if(${t1.typeId}==value) {
								$(".select3").empty();
								$(".select3").append("<option value=''>请选择种类</option>");
								<c:forEach items="${t1.childrens}" var="t2">
									$(".select3").append("<option value='${t2.typeId}'>${t2.name}</option>");
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