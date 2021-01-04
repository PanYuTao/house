var eid=0;
var sid=0;
$(function(){
 
  init();
  //数据验证
 // checkItem();
  //提交表单位
   commitItem();
});
function init() {
	var sid=$.getParameter("sid"); 
	$.ajax({
		url:$webName+'/ShourController?type=toUpdate',
		data:{sid:sid},
		dataType:'json',
		type:'post',
		success:function(mydata)
		{
			$("#smoney").val(mydata.smoney);
			$("#sremark").val(mydata.sremark);
			$("#sid").val(mydata.sid);
			eid=mydata.eid;
			sid=mydata.sid;
			
			var t=mydata.stm;
			if(t=="客户房租")
				{
				   $('#stm option:eq(0)').attr('selected',true);
				}
			else if(t=="客户物损赔偿")
				{
				   $('#stm option:eq(1)').attr('selected',true);
				}
			else
				{
				   $('#stm option:eq(2)').attr('selected',true);
				}
			
			//得到下拉框
			$.ajax( {
				url : $webName+'/EmpController?type=listAll',
				dataType : 'json',
				type : 'post',
				data : '',
				async : true,
				success : function(mydata) {
					$.each(mydata, function(index, xx) {
						if (eid == xx.eid) {
							$("#eid").append(
									"<option value=" + xx.eid
											+ " selected='selected'>"
											+ xx.ename + "</option>");
						} else {
							$("#eid").append(
									"<option value=" + xx.eid + ">" + xx.ename
											+ "</option>");
						}
					});
				}
			});
		}
	});
	
};
/*****************************************/
/******************失去焦点事件****************************/
function checkItem()
{
	$("#sname").focusout(function(){
		var sname=$("#sname").val();
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
				   data:{sname:sname},
				   async : true,
				   success:function(mydata)
				   {
					   if(mydata==0)
						   {
						      $("#sname").addClass("newsuccess");
					          $("#sname").removeClass("newerror");
						   }
					   else
						   {
						       layer.tips('对不起类型已存在！','#sname',{tips:[2,'red']});
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
	var smoney = $("#smoney").val();
	var stm = $("#stm").val();
	var sid=$("#sid").val();
	var eid=$("#eid").val();
	var sremark = $("#sremark").val();
	
	var myage=/^[0-9]*$/;   //正则表达式
	var m=myage.test(smoney);
	
	
	if(smoney.length==0)
		{
		   layer.tips('收入金额不能为空！','#smoney',{tips:[2,'red']});
		   $("#smoney").focus();
		   return false;
		}
	else if(!m)
		{
		  layer.tips('收入金额只能为正数！','#smoney',{tips:[2,'red']});
		  $("#smoney").focus();
		  return false;
		}
	else if(sremark.length==0)
		{
		   layer.tips('收入备注不能为空！','#sremark',{tips:[2,'red']});
		  $("#sremark").focus();
		  return false;
		}
	else
		{
		   var mypart = "sr.smoney=" + smoney +  "&sr.stm="+stm+"&sr.sremark="+sremark+"&sr.sid="+sid+"&sr.eid="+eid;
		   var i = layer.load(0);
		   $.post($webName+'/ShourController?type=update',mypart,function(mydata){
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