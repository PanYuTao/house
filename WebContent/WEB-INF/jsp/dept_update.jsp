<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Demo?type=deptUpdate" method="post">
		<table>
		  <tr>
		    <td><input type="hidden" name="pid" value="${dept.pid }" /></td>
		    <td><input type="text" name="name" value="${dept.pname }" /></td>
		    <td><input type="text" name="premark" value="${dept.premark }" /></td>
		    <td><input type="submit" /></td>
		  </tr>
		</table>
	</form>
</body>
</html>