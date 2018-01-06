$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	addItem:function(){},
}

M_table.init = function () {
	
}


M_table.addItem = function(){
	var newURL = M_table.url + url.admin_alumnus_addition;
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

	publicDom.getDatal('post',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','新增校友信息成功！');
			$('.confirm').click(function() {
				location.reload();
			});
		}else{
			M_table.showConfirmModal('错误','success','新增校友信息失败！')
		}
	})
}



var bindEvent = function () {
	$('#add_alumnus').click(function(){
		M_table.addItem();
	});
}
