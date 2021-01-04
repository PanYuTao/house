<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Demo?type=jsUpdate" method="post">
		<table>
		  <tr>
		    <td><input type="hidden" name="jid" value="${js.jid }" /></td>
		    <td><input type="text" name="name" value="${js.jname }" /></td>
		    <td><input type="submit" /></td>
		  </tr>
		</table>
	</form>
</body>
</html>