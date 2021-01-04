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
<h3>出租类别显示</h3>
<a href="SortServlet?type=toAdd">出租类别添加</a>
<table>
	<thead>
		<tr>
			<td>出租类别编号</td>
			<td>出租类别名称</td>
			<td>操作</td>
		</tr>
	</thead>
	<c:forEach items="${sortList }" var="sort">
		<tr>
			<td>${sort.sid }</td>
			<td>${sort.sname }</td>
			<td>
				<a href="SortServlet?type=toUpdate&id=${sort.sid }">修改</a>
				<a href="javascript:toDel('SortServlet?type=delete&id=${sort.sid }')">删除</a>			
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