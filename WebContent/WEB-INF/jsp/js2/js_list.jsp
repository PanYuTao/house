<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>	
<head>		
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />		
<title>无标题文档</title>		
<link rel="stylesheet" type="text/css" href="css/style.css"></link>		
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"></link>
<script src="js/jquery-1.8.3.js"></script>
<script src="layer/layer.js"></script>
<script src="js/myjs/myjs.js"></script>	
</head>	
	<body>		
		<!-- 位置信息 -->	
		<div class="place">			
			<span>位置：</span>			
			<ul class="placeul">				
				<li><a href="main/right.html">首页</a></li>				
				<li><a href="myjs/all.html">角色管理</a></li>				
				<li><a href="myjs/all.html">角色内容</a></li>			
			</ul>		
		</div>		
		<!-- 内容表格信息 -->
		<div class="rightinfo">			
			<div class="tools">	
				<ul class="toolbar">					
					<li class="addPart">						
						<span><img src="images/t01.png" /> </span>添加					
					</li>
				</ul>							
			</div>			
			<table class="tablelist">
				<thead>
					<tr align='center'>
						<th width='10%' align='center'>编号</th>
						<th width='25%' align='center'>角色名称</th>
						<th width='15%' align='center'>修改</th>
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
			<div class="pagin">				
			<div class="message"></div>			
			</div>		
		</div>	
<script type="text/javascript">
function toDel(url) {
	if (window.confirm("你确定删除吗？")) {
		window.location.href=url;
	}
}
</script>
	</body>
</html>
