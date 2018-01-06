var publicDom = {

	//配置项
	config:{
		rootUrl: "http://127.0.0.1:8080",
		url: "http://127.0.0.1:8080" // 请求接口的url
	},
	getData: function(type, url, param, f, isAsync, err) {
		var ajaxParam  = {
			type: type,
			url: url,
			data: param,		
			async: isAsync !== false,
			contentType: "application/json",  
		    processData: false,  
			success: function(data) {		
				var jsonData;
				// console.info($.parseJSON(data));
				// try{
				// 	jsonData = $.parseJSON(data);
				// }catch(e) { // 返回值不是json格式
				// 	console.log(11111);
				// 	if(typeof f !== 'function') { // 如果没有回调函数,抛出异常。
				// 		throw new Error('请求数据之后，没有回调函数!');
				// 	}
				// 	f(data);
				// 	if (data.code == -1) {
				// 		window.location.href = rootUrl + "/login.html";
				// 	}
				// 	return;
				// }
				if(data.code===1){
					//超时或者存在
					window.location.href = rootUrl + "/login.html";
				} else {
					if(typeof f !== 'function') { // 如果没有回调函数,抛出异常。

						throw new Error('请求数据之后，没有回调函数!');
					}
					f(data);
				}
			},
			error: function(xhr,textStatus) {
				console.log(5);
			}
		};
		$.ajax(ajaxParam);
	},
};

var url = {
	admin_password_update:"/admin/password/update",

	alumnus_item : "/alumnus/item",

	admin_alumnus_list : "/admin/alumnus/list/valid",
	admin_alumnus_addition : "/admin/alumnus/addition",
	alumnus_deleteion : "/admin/alumnus/deletion",
	admin_alumnus_update : "/admin/alumnus/update",

	admin_spalumnus_list : "/admin/alumnus/sp/list",
	admin_spalumnus_addition : "/admin/alumnus/sp/addition",
	spalumnus_deleteion : "/admin/alumnus/sp/deletion",
	admin_spalumnus_update : "/admin/alumnus/sp/update",

	admin_alumnus_apply : "/admin/alumnus/list/invalid",
	apply_approve:"/admin/alumnus/update/valid",
	apply_disapprove:"/admin/alumnus/update/invalid",

	chapter_list : "/chapter/list",
	chapter_item : "/chapter/item",

	chapter_apply_list:"/admin/chapter/apply/list",
	chapter_apply_approve:"/admin/chapter/apply/approve",
	chapter_apply_disapprove:"/admin/chapter/apply/disapprove",

	chapter_member:"/chapter/alumnus/member",

	clazz_list : "/clazz/list",
	clazz_item : "/clazz/item",

	clazz_apply_list:"/admin/clazz/apply/list",
	clazz_apply_approve:"/admin/clazz/apply/approve",
	clazz_apply_disapprove:"/admin/clazz/apply/disapprove",

	clazz_member:"/clazz/alumnus/member",
}

var showConfirmModal = function(info,status,msgBody){
	var gobalModal = "";
	$("#confirm").remove();
	var font_icon = '';
	if(info == '成功'){
		font_icon = 'glyphicon glyphicon-ok';
	}else if(info == '警告'){
		font_icon = 'glyphicon glyphicon-warning-sign';
	}
	else{
		font_icon = 'glyphicon glyphicon-remove';
	}


	if(status!="success"&&status!="delete"){
		gobalModal = '<button type="button" id="confirmBtn" class="btn btn-default comFirmModal hide" data-toggle = "modal" data-target = "#confirm">确定</button>'+
			'<div class = "modal fade" id = "confirm" tabindex = "-1" role = "dialog" aria-labelledby = "myModalLabel" aria-hidden = "true">'+
				'<div class = "modal-dialog">'+
			      '<div class = "modal-content">'+
			         '<div class = "modal-header">'+
			            '<button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">&times;</button>'+  
			            '<h4 class = "modal-title text-danger" id = "myModalLabel"><span class="'+ font_icon +'"></span> '+info+'</h4>'+
			         '</div>'+
			         '<div class = "modal-body">'+msgBody+'</div>'+
			         '<div class = "modal-footer">'+
			            '<button type = "button" class = "btn btn-danger confirm" data-dismiss = "modal">确定</button>'+
			         '</div>'+
			      '</div>'+
			   '</div>'+
			'</div>';
	}
	else if(status=="delete"){
		gobalModal = '<button type="button" id="confirmBtn" class="btn btn-default comFirmModal hide" data-toggle = "modal" data-target = "#confirm">确定</button>'+
					'<div class = "modal fade" id = "confirm" tabindex = "-1" role = "dialog" aria-labelledby = "myModalLabel" aria-hidden = "true">'+
						'<div class = "modal-dialog">'+
					      '<div class = "modal-content">'+
					         '<div class = "modal-header">'+
					            '<button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">&times;</button>'+  
					            '<h4 class = "modal-title text-primary" id = "myModalLabel"><span class="'+ font_icon +'"></span> '+info+'</h4>'+
					         '</div>'+
					         '<div class = "modal-body">'+msgBody+'</div>'+
					         '<div class = "modal-footer">'+
					            '<button type = "button" class = "btn btn-primary deleteConfirm" data-dismiss = "modal">确定</button>'+
					            '<button type = "button" class = "btn btn-default confirm" data-dismiss = "modal">取消</button>'+
					         '</div>'+
					      '</div>'+
					   '</div>'+
					'</div>';
	}
	else{
		gobalModal = '<button type="button" id="confirmBtn" class="btn btn-default comFirmModal hide" data-toggle = "modal" data-target = "#confirm">确定</button>'+
			'<div class = "modal fade" id = "confirm" tabindex = "-1" role = "dialog" aria-labelledby = "myModalLabel" aria-hidden = "true">'+
				'<div class = "modal-dialog">'+
			      '<div class = "modal-content">'+
			         '<div class = "modal-header">'+
			            '<button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">&times;</button>'+  
			            '<h4 class = "modal-title text-primary" id = "myModalLabel"><span class="'+ font_icon +'"></span> '+info+'</h4>'+
			         '</div>'+
			         '<div class = "modal-body">'+msgBody+'</div>'+
			         '<div class = "modal-footer">'+
			            '<button type = "button" class = "btn btn-primary confirm" data-dismiss = "modal">确定</button>'+
			         '</div>'+
			      '</div>'+
			   '</div>'+
			'</div>';
	}
	$("body").append(gobalModal);
	$("#confirmBtn").trigger('click');
}