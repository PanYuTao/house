<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Demo?type=sortUpdate" method="post">
		<table>
		  <tr>
		    <td><input type="hidden" name="sid" value="${sort.sid }" /></td>
		    <td><input type="text" name="name" value="${sort.sname }" /></td>
		    <td><input type="submit" /></td>
		  </tr>
		</table>
	</form>
</body>
</html>