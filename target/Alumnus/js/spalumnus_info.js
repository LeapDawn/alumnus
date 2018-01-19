$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	getItem:function(){},
	updateItem:function(){},
}

M_table.init = function () {
	// 获取列表页传递过来的ID
	M_table.id = localStorage.getItem('spalumnus_info_id');
	// localStorage.removeItem('alumnus_info_id');
	if (M_table.id != null && M_table.id != '') {
		M_table.getItem(M_table.id);
	}
}


M_table.getItem = function(id) {
	var newURL = M_table.url + url.alumnus_item + "?id=" + M_table.id;
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
			M_table.showConfirmModal('错误','success','获取特殊校友详细信息失败！')
		}

	})

}

M_table.updateItem = function(id){
	var newURL = M_table.url + url.admin_spalumnus_update;
	var param = {
		id:M_table.id,
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
			M_table.showConfirmModal('成功','success','修改特殊校友信息成功！');
			$('.confirm').click(function() {
				location.reload();
			});
		} else {
			M_table.showConfirmModal('错误','success','修改特殊校友信息失败！')
		}
	});
}


var bindEvent = function () {

	$('#save_alumnus').click(function(){
		M_table.updateItem();
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
