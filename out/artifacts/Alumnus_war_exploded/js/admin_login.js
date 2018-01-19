$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	//getList:function(curr){},
	showConfirmModal:showConfirmModal,
	login:function(){},
}

M_table.init = function () {

}

M_table.login = function() {
	var newURL = M_table.url + url.admin_login;
	var account = $("#account").val();
	var password = $("#password").val();
	if(account == '' || password == ''){
		M_table.showConfirmModal('错误','success','用户名和密码不能为空');
		return;
	}
	var param = {
		account:account,
		password:password
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr, function(data){
		if(data.code == 0){
			sessionStorage.setItem("admin",account);
			location.href="admin_alumnus.html";
		} else {
			M_table.showConfirmModal("失败", "", data.value);
		}
	})
}


var bindEvent = function () {
	$('.login').click(function(){
		M_table.login();
	});
}
