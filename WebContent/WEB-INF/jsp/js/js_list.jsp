<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>岗位显示</h3>
<a href="JsServlet?type=toAdd">岗位添加</a>
<table>
	<thead>
		<tr>
			<td>角色编号</td>
			<td>角色名称</td>
			<td>操作</td>
		</tr>
	</thead>
	<c:forEach items="${jsList }" var="js">
		<tr>
			<td>${js.jid }</td>
			<td>${js.jname }</td>
			<td>
				<a href="JsServlet?type=toUpdate&id=${js.jid }">修改</a>
				<a href="javascript:toDel('JsServlet?type=delete&id=${js.jid }')">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript">
function toDel(url) {
	if (window.confirm("你确定删除吗？")) {
		window.location.href=url;
	}
}
</script>
</body>
</html>