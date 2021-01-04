$(function(){
	init();
	//数据验证
	//checkItem();
	//提交表单位
	//下拉框联动事件
	//myaddSe();
	commitItem();
});
/****************获得焦点同时得到两个下拉框的内容********************/
function init() {
	$("#myzj").focus();
	//得到下拉框的值 
	$.ajax({
		url:$webName+'/CusController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{
		   $.each(mydata,function(index,xx){
			   $("#cid").append("<option value="+xx.cid+">"+xx.cname+"</option>");
		   });
		}
	});
	/*$.ajax({
		url:$webName+'/EmpController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{
		   $.each(mydata,function(index,xx){
			   $("#eid").append("<option value="+xx.eid+">"+xx.erealname+"</option>");
		   });
		}
	});*/
	
	$.ajax({
		url:$webName+'/HouseController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{

		   $.each(mydata,function(index,xx){
			   $("#hid").append("<option value="+xx.hid+">"+xx.haddress+"</option>");
		   });
		}
	});
	$.ajax({
		url:$webName+'/DjController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{

		   $.each(mydata,function(index,xx){
			   $("#mid").append("<option value="+xx.mid+">"+xx.mdate+"</option>");
		   });
		}
	});
};

/****************************************************************************/

/******************失去焦点事件****************************/
function checkItem()
{
	$("#pname").focusout(function(){
		var pname=$("#pname").val();
		if(pname.length==0)
			{
			   layer.tips('部门名称不能为空！','#pname',{tips:[2,'red']});
			}
		else
			{
			   $.ajax({
				   url:'part_getAllName.action',
				   dataType:'json',
				   type:'post',
				   data:{pname:pname},
				   async : true,
				   success:function(mydata)
				   {
					   if(mydata==0)
						   {
						      $("#pname").addClass("newsuccess");
					          $("#pname").removeClass("newerror");
						   }
					   else
						   {
						       layer.tips('对不起部门已存在！','#pname',{tips:[2,'red']});
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
	var hid=$("#hid").val();
	var mid = $("#mid").val();
	var mbegintime = $("#mbegintime").val();
	//var eid = $("#eid").val();
	var myzj = $("#myzj").val();
	var cid = $("#cid").val();
		
	
		var mypart = "house.hid=" + hid  + "&dj.mid=" + mid + "&mbegintime=" + mbegintime+ "&cus.cid=" + cid+ "&myzj=" + myzj+ "";
		var i = layer.load(0);
		$.post($webName+'/SfController?type=add',mypart,function(mydata){
			layer.close(i);
			if(mydata=="1")
			{
				parent.layer.msg('增加成功！', {icon : 6,time : 3000});
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
				parent.layer.close(index);
			}
			else
			{
				parent.layer.msg('增加失败', 2, 9);
			}
		},'json');
		
		
		
		
	});
}