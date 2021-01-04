<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>部门添加</h3>
<form action="DeptServlet" method="post">
	<input type="hidden" name="type" value="add">
	<table>
		<tr>
			<td>名称</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td>
				<textarea rows="3" cols="20" name="remark"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
</html>