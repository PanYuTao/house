$(function(){
	//dom加载完毕之后自动执行
	init();
	commitItem();
});

//从服务器提取数据,绑定到update.html页面
function init(){
	var jid = $.getParameter("jid");
	$.ajax({
		url:$webName+"/JsController?type=toUpdate",
		data:{"jid":jid},
		dataType: "json",
		type:"post",
		success:function(madata){
			$("#jid").val(madata.jid);//val读取/设置表单元素的值
			$("#jname").val(madata.jname);
			
		},
		error:function(){
			console.log("服务器响应失败,url错误,服务器报错!");
		}
	});
}
//提取update.html页面中的数据
function commintItem(){
	$("#btn").bind("click",function(){
		var jid = $("#jid").val();
		var jname = $("#jname").val();
		var data={"jid":jid,"jname":jname};
		var i = layer.load(0);
		$.post("../JsController?type=update",data,function(mydata){
			layer.close(i);
			if(mydata==1)
			 {
			   parent.layer.msg('修改成功！', {icon : 6,time : 3000});
			   var index = parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
			   parent.layer.close(index);
			 }
			else
			 {
			     parent.layer.msg('修改失败', 2, 9);
			 }
		},"json");
	});
}