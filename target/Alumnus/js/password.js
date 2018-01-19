$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	//getList:function(curr){},
	showConfirmModal:showConfirmModal,
	updatePassword:function(){},
}

M_table.init = function () {

}

M_table.updatePassword = function() {
	var old_password = $("#old_password").val();
	var new_password = $("#new_password").val();
	var new_password2 = $("#new_password2").val();

	if (new_password2 != new_password) {
		M_table.showConfirmModal('警告','success','两次密码输入不一致!');
	} else if(old_password == '' || new_password == '' || new_password2 == ''){
		M_table.showConfirmModal('错误','success','密码输入不能为空!');
	} else {
		var newURL = M_table.url + url.password_update;
        var param = {
            accounts:old_password,
            password:new_password
        }
        var jsonStr = JSON.stringify(param);
		publicDom.getData('put',newURL, jsonStr,function(JsonData){
			if (JsonData.code == 0) {
				M_table.showConfirmModal('成功','success','密码修改成功!');
			} else if (JsonData.code == 304) {
				M_table.showConfirmModal('错误','success','原密码错误!');
			} else {
				M_table.showConfirmModal('错误','success','操作失败!');
			}
		});
	}
}



var bindEvent = function () {

	$('#update_password').click(function(){
		M_table.updatePassword();
	});	
}
