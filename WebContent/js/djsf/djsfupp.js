var cid=0;
var hid=0;
var mid=0;
var aid=0;

$(function(){
  init();
  //数据验证
  //checkItem();
  //提交表单位
   //myaddSe();
   commitItem();
});


function init() {
   $("#myzj").focus();
   yid=$.getParameter("yid");
	//--------------------------------------------------
	$.ajax({
		url:$webName+'/SfController?type=toUpdate',
		dataType:'json',
		type:'post',
		data:{yid:yid},
		async : true,
		success:function(mydata)
		{
              $("#eid").val(mydata.eid);
			   $("#cid").val(mydata.cid);
			   $("#hid").val(mydata.hid);
			   $("#mid").val(mydata.mid);
			   $("#mbegintime").val(mydata.mbegintime);
			   $("#myzj").val(mydata.myzj);
			   $("#yid").val(mydata.yid);
			   cid=mydata.cid;
			   hid=mydata.hid;
			   eid=mydata.eid;
			   mid=mydata.mid;
			   yid=mydata.yid
			   
		
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
			   if(cid == xx.cid){
				   $("#cid").append(
							"<option value=" + xx.cid
									+ " selected='selected'>"
									+ xx.cname + "</option>");
			   }else{
				   $("#cid").append("<option value="+xx.cid+">"+xx.cname+"</option>");
			   }
			  
		   });
		}
	});
	$.ajax({
		url:$webName+'/EmpController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{
		   $.each(mydata,function(index,xx){
			   if(eid == xx.eid){
				   $("#eid").append(
							"<option value=" + xx.eid
									+ " selected='selected'>"
									+ xx.erealname + "</option>");
			   }else{
				   $("#eid").append("<option value="+xx.eid+">"+xx.erealname+"</option>");
			   }
		   });
		}
	});
	
	$.ajax({
		url:$webName+'/HouseController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{

		   $.each(mydata,function(index,xx){
			   if(hid == xx.hid){
				   $("#hid").append(
							"<option value=" + xx.hid
									+ " selected='selected'>"
									+ xx.haddress + "</option>");
			   }else{
				   $("#hid").append("<option value="+xx.hid+">"
						   +xx.haddress+"</option>");
			   }
		   });
		}
	});
	$.ajax({
		url:'http://localhost:8080/house2023/DjController?type=listAll',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{

		   $.each(mydata,function(index,xx){
			   if(mid == xx.mid){
				   $("#mid").append(
							"<option value=" + xx.mid
									+ " selected='selected'>"
									+ xx.mdate + "</option>");
			   }else{
				   $("#mid").append("<option value="+xx.mid+">"+xx.mdate+"</option>");
			   }
			   
		   });
		}
	});
	
		}
	});
	
}
/******************失去焦点事件****************************/

$.extend({
	getParameter: function (name){
		var results = new RegExp('[\\?&]'+name+'=([^&#]*)').exec(window.location.href);
		if(!results){return null;}
		return unescape(results[1])|| null;
	}
})

/******************************提交表单********************************/
function commitItem()
{
	$(".btn").bind("click",function(){
	var myzj = $("#myzj").val();
	var mid=$("#mid").val();
	var mbegintime=$("#mbegintime").val();
	var eid=$("#eid").val();
	var cid=$("#cid").val();
	var yid=$("#yid").val();
	var hid=$("#hid").val();
	
	var myage=/^[0-9]*$/;   //正则表达式
	var n=myage.test(myzj);

	if(myzj.length==0)
		{
		   layer.tips('预收租金不能为空！','#myzj',{tips:[2,'red']});
		   $("#myzj").focus();
		   return false;
		}
	else if(!n)
		{
		   layer.tips('预收租金只能为正数！','#myzj',{tips:[2,'red']});
		   $("#myzj").focus();
		   return false;
		}
	else if(mbegintime.length==0)
		{
		   layer.tips('时间不能为空！','#time',{tips:[2,'red']});
		   $("#mbegintime").focus();
		   return false;
		}
	else
		{
		   var mypart = "sf.myzj=" + myzj + "&sf.mbegintime="+mbegintime+"&sf.mid="+mid+"&emp.eid="+eid+"&house.hid="+hid+"&sf.yid="+yid+"&cus.cid="+cid;
		   var i = layer.load(0);
		   $.post($webName+'/SfController?type=update',mypart,function(mydata){
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
