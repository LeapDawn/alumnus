$(function(){
	Logout.init();
	bindEvent();
})

var Logout = {
	url: publicDom.config.url,
	init:function(){},
	showConfirmModal:showConfirmModal,
	logout:function(){},
}

Logout.init = function () {
	if (sessionStorage.getItem("account") == null){
		location.href = "login.html";
	}
}

Logout.logout = function() {
	var newURL = Logout.url + url.logout;
	publicDom.getData('get',newURL, null, function(data){
		if(data.code == 0){
			sessionStorage.removeItem("account");
			location.href="login.html";
		} else {
			log.error("退出登陆失败!");
			location.href="login.html";
		}
	})
}


var bindEvent = function () {
	$('#logout').click(function(){
		Logout.logout();
	});
}
