<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改头像</title>
<link rel="stylesheet" type="text/css"
	href="static/public/css/button.css" />
<script type="text/javascript" src="static/forward/js/jquery-3.4.1.js"></script>

<script type="text/javascript">
	$(function() {
		$(".nono.button").click(function() {
			location.href = "forward/user/my";
		});
	});
</script>
</head>
<body>
	<fieldset>
		<legend>用户修改头像</legend>
		<form action="/forward/user/updateImage" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>上传头像：</td>
					<td><input type="file" name="picturex" autocomplete="off" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit" class="large blue button">提交</button></td>
					<td><button class="nono large green button" type="button">取消</button></td>
				</tr>
			</table>
		</form>

	</fieldset>

</body>
</html>