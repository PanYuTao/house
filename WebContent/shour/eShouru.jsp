<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 引入 ECharts 文件 -->
<script src="../js/echarts.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:700px; " ></div>
    
    <script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	
	var smoneys=[];	//收入金额
	var erealname=[]; //经办人
	<c:forEach items="${list}" var="a">
		smoneys.push("${a.smoney}");
		erealname.push("${a.erealname}");
   	</c:forEach>
	var myChart = echarts.init(document.getElementById('main'));
	
	// 指定图表的配置项和数据
    var option = {
        title: {
            text: '现金收入流水账内容分析图'
        },
        // 高亮样式
        emphasis: {
            itemStyle: {
                // 高亮时点的颜色
                color: '#0099CC'
            },
            label: {
                show: true,
                // 高亮时标签的文字
                formatter: erealname
            }
        },
        toolbox: {  //很关键的一个功能，自由切换柱状图曲线图的工具盒子
            show : true,  //让它显示
            feature : {  
                mark : {show: true},  
                magicType : {show: true, type: ['line', 'bar']},  //类型让他两种都存在
                restore : {show: true},  
                saveAsImage : {show: true}  
            }  
        },
        grid: { //表格调整
		    left: '3%',
		    right: '4%',
		    bottom: '3%',
		    containLabel: true
		},
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['收入金额']
        },
        xAxis: {
            data: erealname
        },
        yAxis: {
        	type: 'value'
        },
        series: [{
            name: '收入金额',
            type: 'bar',
            barWidth : 100,	//柱子宽度
            data: smoneys,	//数据
            itemStyle: {
            	color:'#FF6666',	//柱子颜色
				barBorderRadius:[18,18,0,0],	//柱角圆化
                /* normal: {
                    label: {			//数据在柱子上方显示
                        show: true,		//开启显示
                        position: 'top',	//在上方显示
                        textStyle: {	    //数值样式
                            color: 'black',
                            fontSize: 16
                        }
                    }
                } */
            }
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>


</html>