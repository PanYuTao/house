$(function(){
	init();
	//数据验证
	//checkItem();
	//提交表单位
	commitItem();
});
/****************获得焦点********************/
function init() {
	$("#htname").focus();
};
/******************失去焦点事件****************************/
function checkItem()
{
	$("#htname").focusout(function(){
		var htname=$("#htname").val();
		if(sname.length==0)
			{
			   layer.tips('类型名称不能为空！','#sname',{tips:[2,'red']});
			}
		else
			{
			   $.ajax({
				   url:'sort_getAllName.action',
				   dataType:'json',
				   type:'post',
				   data:{htname:htname},
				   async : true,
				   success:function(mydata)
				   {
					   if(mydata==0)
						   {
						      $("#htname").addClass("newsuccess");
					          $("#htname").removeClass("newerror");
						   }
					   else
						   {
						       layer.tips('对不起类型已存在！','#htname',{tips:[2,'red']});
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
	var htname = $("#htname").val();
	var htremark = $("#htremark").val();

	
	$.ajaxFileUpload({
	    url:'http://localhost:8080/house2023/HtController?type=add',
	    secureuri:false,//一般设置为false
	    fileElementId:'doc',//上传对象 
	    data:{
		  "ht.htname":hname,
		  "ht.htremark":htremark
		 }, //上传控件以外的控件对应的参数
	    dataType: 'json', 
	    success:function(mydata,status)
	    	 {

	    	     parent.layer.msg('增加成功!!',{icon:1});
		         var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	             parent.layer.close(index);
	    	 }
	    	  ,
             error: function (data, status, e)//服务器响应失败处理函数
              {

	    	      parent.layer.msg('增加失败!!',{icon:1});
		    	  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	              parent.layer.close(index);
                                 
               }
	    });
	
	
	});
}