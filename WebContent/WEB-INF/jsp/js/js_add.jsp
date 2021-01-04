<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>岗位添加</h3>
<form action="JsServlet" method="post">
	<input type="hidden" name="type" value="add">
	<table>
		<tr>
			<td>角色名称</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
</html>