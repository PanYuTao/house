<!-- page指令  contentType指定当前页类型及字符编码,pageEncoding指定字符编码 -->
<%@page import="com.donkee.house.entity.Dept"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 使用taglib指令导入JSTL标签   prefix属性值任意 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jsp脚本 -->
<%
//读取数据
List<Dept> rdList = (List<Dept>) request.getAttribute("deptList");//getAttribute读取数据,若没有读取到返回null
out.print("共"+rdList.size()+"条记录");
%>
<h3>部门显示</h3>
<a href="DeptServlet?type=toAdd">部门添加</a>
<table>
	<thead>
		<tr>
			<td>编号</td>
			<td>名称</td>
			<td>状态</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
	</thead>
	<!-- forEach遍历标签,items指定要遍历的集合
		EL表达式,${var},按request,session,application的顺序读取作用域var的值,直到找到为止,如果没有找到,返回空(不是null);需要类型转换
		forEach遍历items集合,并赋值给var指定的变量
	 -->
	<c:forEach items="${deptList }" var="dept">
		<!-- ${dept.pid }:调用dept变量中的getPid()方法 -->
		<tr>
			<td>${dept.pid }</td>
			<td>${dept.pname }</td>
			<td>${dept.pflag }</td>
			<td>${dept.premark }</td>
			<td>
				<a href="DeptServlet?type=toUpdate&id=${dept.pid }">修改</a>
				<a href="javascript:toDel('DeptServlet?type=delete&id=${dept.pid }')">删除</a>
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