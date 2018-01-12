$(function(){
	Logout.init();
	LogoutbindEvent();
})

var Logout = {
	url: publicDom.config.url,
	init:function(){},
	showConfirmModal:showConfirmModal,
	logout:function(){},
}

Logout.init = function () {
	if (sessionStorage.getItem("admin") == null){
		location.href = "admin_login.html";
	}
}

Logout.logout = function() {
	var newURL = Logout.url + url.admin_logout;
	publicDom.getData('get',newURL, null, function(data){
		if(data.code == 0){
			sessionStorage.removeItem("admin");
			location.href="admin_login.html";
		} else {
			log.error("退出登陆失败!");
			location.href="admin_login.html";
		}
	})
}


var LogoutbindEvent = function () {
	$('#logout').click(function(){
		Logout.logout();
	});
}
