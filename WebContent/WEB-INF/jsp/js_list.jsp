<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function deptDel(url) {
		if (window.confirm("确定删除吗？")) {
			window.location.href = url
		}
	}
	function jsDel(url) {
		if (window.confirm("确定删除吗？")) {
			window.location.href = url
		}
	}
	function sortDel(url) {
		if (window.confirm("确定删除吗？")) {
			window.location.href = url
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="css/style.css"></link>
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"></link>
<script src="js/jquery-1.8.3.js"></script>
<script src="layer/layer.js"></script>
<script src="js/part/mydepty.js"></script>
<SCRIPT type="text/javascript">
	//////////////权限控制！！！//////////////		//var $userLevel=parseInt("");//获取登陆者的权限等级		var $webName="/pro";		//$(function(){		//	var tools=$(".tools");		//	if($userLevel!=-1){		//		tools.hide();		//	}		//});
</SCRIPT>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="../main/right.html">首页</a></li>
			<li><a href="../part/all.html">部门管理</a></li>
			<li><a href="../part/all.html">基本内容</a></li>
		</ul>
	</div>
	<!-- 内容表格信息 -->
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="addPart"><span><img src="images/t01.png" />
				</span>添加</li>
			</ul>
			<ul class="toolbar">
				<li class="delPart"><span><img src="images/t01.png" />
				</span>已经撤销部门</li>
			</ul>
			<ul class="toolbar">
				<li class="backPart" style="display: none;"><span><img
						src="../images/t01.png" /> </span>查询部门</li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr align='center'>
					<th width='30%' align='center'>编号</th>
					<th width='50%' align='center'>部门名称</th>
					<th width='20%'>操作</th>
				</tr>
			</thead>
			<c:forEach items="${jslist }" var="js">
				<tr>
					<td>${js.jid }</td>
					<td>${js.jname }</td>
					<td><a href="Demo?type=jsSelectByid&jid=${js.jid }">修改</a>
					<a href="javaScript:jsDel('Demo?type=jsDel&jid=${js.jid }')">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="pagin">
			<div class="message"></div>
		</div>
	</div>
</body>
</html>