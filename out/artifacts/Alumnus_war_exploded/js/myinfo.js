$(function(){
	M_table.init();
	M_table.getMyInfo();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	showConfirmModal:showConfirmModal,
	getMyInfo:function(){},
	updateMyInfo:function(){},
}

M_table.init = function () {

}


M_table.getMyInfo = function() {
	var newURL = M_table.url + url.myinfo;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code==0){
			var value = data.value;
			$("#name").val(value.name),
			$("#studentno").val(value.studentno),
			$("#nation").val(value.nation),
			$("#tel").val(value.tel),
			$("#college").val(value.college),
			$("#major").val(value.major),
			$("#idCard").val(value.idCard),
			$("#enrolDate").val(value.enrolDate),
			$("#graduationDate").val(value.graduationDate),
			$("#birthday").val(value.birthday),
			$("#nativePlace").val(value.nativePlace),
			$("#politicalStatus").val(value.politicalStatus);
		}else{
			M_table.showConfirmModal('错误','success','获取个人信息失败！')
		}
	})
}

M_table.updateMyInfo = function(){
	var newURL = M_table.url + url.myinfo_update;
	var param = {
		name:$("#name").val(),
		studentno:$("#studentno").val(),
		nation:$("#nation").val(),
		tel:$("#tel").val(),
		college:$("#college").val(),
		major:$("#major").val(),
		idCard:$("#idCard").val(),
		enrolDate:$("#enrolDate").val(),
		graduationDate:$("#graduationDate").val(),
		birthday:$("#birthday").val(),
		nativePlace:$("#nativePlace").val(),
		politicalStatus:$("#politicalStatus").val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL, jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','修改校友信息成功！');
			$('.confirm').click(function() {
				location.reload();
			});
		} else {
			M_table.showConfirmModal('错误','success','修改校友信息失败！')
		}
	});
}


var bindEvent = function () {

	$('#save_alumnus').click(function(){
		M_table.updateMyInfo();
	});

	$('#cancel').click(function(){
		location.reload();
	});

	$('#update_alumnus').click(function(){
		$("#name").removeAttr("disabled"),
		$("#studentno").removeAttr("disabled"),
		$("#nation").removeAttr("disabled"),
		$("#tel").removeAttr("disabled"),
		$("#college").removeAttr("disabled"),
		$("#major").removeAttr("disabled"),
		$("#idCard").removeAttr("disabled"),
		$("#enrolDate").removeAttr("disabled"),
		$("#graduationDate").removeAttr("disabled"),
		$("#birthday").removeAttr("disabled"),
		$("#nativePlace").removeAttr("disabled"),
		$("#politicalStatus").removeAttr("disabled")

		$('#save_alumnus').removeAttr("style");
		$('#cancel').removeAttr("style");
		$("#update_alumnus").attr("style","display:none");
	});
	
}
