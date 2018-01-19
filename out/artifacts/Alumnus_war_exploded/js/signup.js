$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	showConfirmModal:showConfirmModal,
	signup:function(){},
}

M_table.init = function () {

}

M_table.signup = function() {
	var newURL = M_table.url + url.signup;
	var account = $("#account").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	if(account == '' || password == '' || password2 == ''){
		M_table.showConfirmModal('错误','success','用户名和密码不能为空!');
	} else if (password != password2) {
		M_table.showConfirmModal('警告','success','两次密码输入不一致!');
		$("#password").val('');
		$("#password2").val('');
	} else {
		var param = {
			accounts:account,
			password:password
		}
		var jsonStr = JSON.stringify(param);
		publicDom.getData('post',newURL, jsonStr, function(data){
			if(data.code == 0){
				sessionStorage.setItem("account",account);
				location.href="alumnus_bind.html";
			} else if(data.code == 11){
				M_table.showConfirmModal("错误", "", "注册失败!用户名已经存在!");
			} else {
				M_table.showConfirmModal("错误", "", data.value);
			}
		});
	}
}


var bindEvent = function () {
	$('.signup').click(function(){
		M_table.signup();
	});
}
