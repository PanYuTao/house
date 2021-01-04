<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>出租类别修改</h3>
<form action="SortServlet" method="post">
	<input type="hidden" name="type" value="update">
	<table>
		<tr>
			<td>编号</td>
			<td><input type="text" name="id" value="${sort.sid }" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>出租类别名称</td>
			<td><input type="text" name="name" value="${sort.sname }" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
</html>