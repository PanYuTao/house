$(function(){
 
  init();
  //数据验证
  checkItem();
  //提交表单位
   commitItem();
});
function init() {
	var jid=$.getParameter("jid"); 
	$.ajax({
		url:'http://localhost:8080/house2023/JsController?type=toUpdate',
		data:{jid:jid},
		dataType:'json',
		type:'post',
		success:function(mydata)
		{
			$("#jname").val(mydata.jname);
			
			$("#jid").val(mydata.jid);
		}
	});
	
};
/*****************************************/
/******************失去焦点事件****************************/
function checkItem()
{
	$("#jname").focusout(function(){
		var jname=$("#jname").val();
		if(jname.length==0)
			{
			   layer.tips('角色名称不能为空！','#jname',{tips:[2,'red']});
			}
		else
			{
			   $.ajax({
				   url:'js_getAllName.action',
				   dataType:'json',
				   type:'post',
				   data:{jname:jname},
				   async : true,
				   success:function(mydata)
				   {
					   if(mydata==0)
						   {
						      $("#jname").addClass("newsuccess");
					          $("#jname").removeClass("newerror");
						   }
					   else
						   {
						       layer.tips('对不起角色已存在！','#jname',{tips:[2,'red']});
						   }
					   $("#botao").val(mydata);
				   }
			   });
			}
	});
}



/******************************提交表单********************************/
function commitItem()
{
	$(".btn").bind("click",function(){	
	var jname = $("#jname").val();
	
	var jid=$("#jid").val();
	if(jname.length==0)
		{
		   layer.tips('角色名称不能为空！','#jname',{tips:[2,'red']});
		   $("#jname").focus();
		   return false;
		}
	else if($("#botao").val()>0)
		{
		  layer.tips('对不起角色已存在！','#jname',{tips:[2,'red']});
		  $("#jname").focus();
		  return false;
		}
	else
		{
		   var mypart = "js.jname=" + jname + "&js.jid="+jid;
		   var i = layer.load(0);
		   $.post('http://localhost:8080/house2023/JsController?type=update',mypart,function(mydata){
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
		   },'json');
		}
	});
}
$.extend({
	getParameter: function (name) {
		var results = new RegExp('[\\?&]'+ name + '=([^&#]*)' ).exec(window.location.href);
		if (!results) { return null; }
		return unescape(results[1]) || null;
	}
});