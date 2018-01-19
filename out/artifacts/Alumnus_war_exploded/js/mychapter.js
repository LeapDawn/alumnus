$(function(){
	M_table.init();
	bindEvent();
	M_table.getItem();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	getItem:function(){},
	getList:function(curr){},
	createList:function(list){},
	isAdmin:function(){},
	admin:false,
	getAppointList:function(curr){},
	createAppointList:function(list){},
	appoint:function(id){},
	getApplyList:function(curr){},
	createApplyList:function(list){},
	approve:function(){},
	disapprove:function(){},
	leave:function(){},
}

M_table.init = function () {
	
}

M_table.getList = function (curr) {
	var newURL = M_table.url + url.chapter_member;
	var param = {
		key:  {
			"chapter":M_table.id,
		},
		page: curr || 1,
		rows: 10
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(JsonData){
		if(JsonData.code == 0){
			M_table.createList(JsonData.value.data);
			laypage({
				cont : 'alumnus_page',
				pages :JsonData.value.pages,
				curr : JsonData.value.currentPage || 1,
				skin:'#337ab7',
				skip:false,
				jump : function(obj,first){
					if(!first){
						M_table.getList(obj.curr);	
					}
				}
			})
		}else{
			M_table.showConfirmModal('错误','success','获取分会成员列表失败！');
		}		
	})
}

M_table.createList = function (list) {
	$('.alumnus_table table tbody').empty();
	for( var i = 0 ; i < list.length ; i ++ ){
		var tableItem = '<tr data-id="'+list[i].id+'">\
							<td><a class="detail" href="#">'+list[i].name+'</a></td>\
				 			<td>'+list[i].studentno+'</td>\
				 			<td>'+list[i].college+'</td>\
				 			<td>'+list[i].major+'</td>\
				 			<td>'+list[i].enrolDate+'</td>\
				 			<td>'+list[i].graduationDate+'</td>\
				 		  </tr>'
		$('.alumnus_table table tbody').append(tableItem);
	}
	$('.detail').click(function(){
		//详情页
		var id = $(this).parent().parent().data('id');
		localStorage.setItem('alumnus_info_id', id);
		location.href="alumnus_detail.html";
	});
}

M_table.getApplyList = function (curr) {
	var newURL = M_table.url + url.chapter_alumnus_list;
	var param = {
		key:  {
			"chapter":M_table.id,
		},
		page: curr || 1,
		rows: 10
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(JsonData){
		if(JsonData.code == 0){
			M_table.createApplyList(JsonData.value.data);
			laypage({
				cont : 'alumnus_page',
				pages :JsonData.value.pages,
				curr : JsonData.value.currentPage || 1,
				skin:'#337ab7',
				skip:false,
				jump : function(obj,first){
					if(!first){
						M_table.getAppointList(obj.curr);	
					}
				}
			})
		}else{
			M_table.showConfirmModal('错误','success','获取分会成员列表失败！');
		}		
	})
}

M_table.createApplyList = function (list) {
	$('.alumnus_table table tbody').empty();
	for( var i = 0 ; i < list.length ; i ++ ){
		var tableItem = '<tr data-id="'+list[i].id+'">\
							<td><a class="detail" href="#">'+list[i].name+'</a></td>\
				 			<td>'+list[i].studentno+'</td>\
				 			<td>'+list[i].college+'</td>\
				 			<td>'+list[i].major+'</td>\
				 			<td>'+list[i].enrolDate+'</td>\
				 			<td>'+list[i].graduationDate+'</td>\
				 			<td><button type="button" class="btn btn-info btn-xs approve pull-right">通过</button></td>\
				 			<td><button type="button" class="btn btn-danger btn-xs disapprove pull-right">否决</button></td>\
				 		</tr>';
		$('.alumnus_table table tbody').append(tableItem);
	}
	$('.detail').click(function(){
		//详情页
		var id = $(this).parent().parent().data('id');
		localStorage.setItem('alumnus_info_id', id);
		location.href="alumnus_detail.html";
	});
	$('.approve').click(function(){
		//通过申请
		M_table.approve($(this).parent().parent().data("id"));
	});
	$('.disapprove').click(function(){
		//否决申请
		M_table.disapprove($(this).parent().parent().data("id"));
	});
}


M_table.getAppointList = function (curr) {
	var newURL = M_table.url + url.chapter_member;
	var param = {
		key:  {
			"chapter":M_table.id,
			"admin":false
		},
		page: curr || 1,
		rows: 10
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(JsonData){
		if(JsonData.code == 0){
			M_table.createAppointList(JsonData.value.data);
			laypage({
				cont : 'alumnus_page',
				pages :JsonData.value.pages,
				curr : JsonData.value.currentPage || 1,
				skin:'#337ab7',
				skip:false,
				jump : function(obj,first){
					if(!first){
						M_table.getAppointList(obj.curr);	
					}
				}
			})
		}else{
			M_table.showConfirmModal('错误','success','获取分会成员列表失败！');
		}		
	})
}

M_table.createAppointList = function (list) {
	$('.alumnus_table table tbody').empty();
	for( var i = 0 ; i < list.length ; i ++ ){
		var tableItem = '<tr data-id="'+list[i].id+'">\
							<td><a class="detail" href="#">'+list[i].name+'</a></td>\
				 			<td>'+list[i].studentno+'</td>\
				 			<td>'+list[i].college+'</td>\
				 			<td>'+list[i].major+'</td>\
				 			<td>'+list[i].enrolDate+'</td>\
				 			<td>'+list[i].graduationDate+'</td>\
				 			<td><button type="button" class="btn btn-info btn-xs appoint">任命</button></td>\
				 		</tr>';
		$('.alumnus_table table tbody').append(tableItem);
	}
	$('.detail').click(function(){
		//详情页
		var id = $(this).parent().parent().data('id');
		localStorage.setItem('alumnus_info_id', id);
		location.href="alumnus_detail.html";
	});
	$('.appoint').click(function(){
		//任命管理员
		M_table.appoint($(this).parent().parent().data("id"));
	});
}

M_table.approve = function(id) {
	var newURL = M_table.url + url.chapter_alumnus_approve;
	var param = {
		id:id
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(data){
		if (data.code == 0) {
			M_table.showConfirmModal('成功','success','已确认通过申请!');
			M_table.getApplyList(1);
		} else if (data.code == 602) {
			M_table.showConfirmModal('错误','success','只能由分会管理员进行操作!');
		} else {
			M_table.showConfirmModal('错误','success','操作失败');
		}
	});
}

M_table.disapprove = function(id) {
	var newURL = M_table.url + url.chapter_alumnus_disapprove;
	var param = {
		id:id
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(data){
		if (data.code == 0) {
			M_table.showConfirmModal('成功','success','已确认否决申请!');
			M_table.getApplyList(1);
		} else if (data.code == 602) {
			M_table.showConfirmModal('错误','success','只能由分会管理员进行操作!');
		} else {
			M_table.showConfirmModal('错误','success','操作失败');
		}
	});
}

M_table.appoint = function(id) {
	var newURL = M_table.url + url.chapter_appoint;
	var param = {
		chapter:$('.chapter_detail').text(),
		alumnus:id
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL, jsonStr,function(data){
		if (data.code == 0) {
			M_table.showConfirmModal('成功','success','任命新的管理员成功!');
			M_table.getAppointList(1);
		} else if (data.code == 403) {
			M_table.showConfirmModal('错误','success','只能该分会管理员能任命新的管理员!');
		} else {
			M_table.showConfirmModal('错误','success','操作失败');
		}
	});
}

// 判断是否管理员
M_table.isAdmin = function() {
	var newURL = M_table.url + url.isChapterAdmin + '?chapter='+M_table.id;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code == 0) {		
			M_table.admin = data.value;
			if(data.value){
				var admin_button = '<br/><br/><br/><br/>\
			  						<button id="chapter_update" type="button" class="btn btn-info pull-right">编辑</button>';
			  	$("#chapter_content").append(admin_button);
			  	$('#chapter_update').click(function(){
			  		// 编辑分会内容
					localStorage.setItem('chapter_update_name', $('.chapter_detail').text());
					location.href="chapter_update.html";
			  	});

			  	var operation = '<li><a id="appoint_list" href="#">任命管理员</a></li>\
									<li><a id="chapter_alumnus_apply" href="#">查看申请</a></li>\
									<li><a id="leave" href="#">退出分会</a></li>';
			  	var cancel_button = '<button id="cancel" type="button" class="btn btn-default btn-xs pull-right" style="display:none">返回</button>';
			  	$("#operation").empty().append(operation);
			  	$("#alumnus_heading").append(cancel_button);
			  	$('#appoint_list').click(function(){
			  		// 任命管理员
			  		$('.btn-group').attr("style","display:none");
			  		$('#cancel').removeAttr("style");
			  		M_table.getAppointList(1);
			  	});

		  		$('#chapter_alumnus_apply').click(function(){
		  		// 查看申请
			  		$('.btn-group').attr("style","display:none");
			  		$('#cancel').removeAttr("style");
			  		M_table.getApplyList(1);
			  	});

			  	$("#leave").click(function(){
					M_table.leave();
				});

			  	$('#cancel').click(function(){
			  		// 返回
			  		$('#cancel').attr("style","display:none");
			  		$('.btn-group').removeAttr("style");
			  		M_table.getList(1);
			  	});
			}
			M_table.getList(1);
		} else {
			console.error('判断是否管理员失败!');
		}
	});
	return M_table.admin;
}


M_table.getItem = function(id) {
	var newURL = M_table.url + url.mychapter;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code==0){
			var value = data.value;
			M_table.id = value.name;
			$(".chapter_detail").text(value.name);
			$("#chapter_content").text('');
			$("#chapter_content").append(value.content);
			M_table.isAdmin();
		} else if (data.code == 402){
			// 402==未加入校友分会
			location.href="chapter.html";
		} else{
			M_table.showConfirmModal('错误','success','获取校友分会信息失败！');
		}

	})
}

M_table.leave = function() {
	var chapter = $('.chapter_detail').text();
	var newURL = M_table.url + url.chapter_alumnus_leave + '?chapter=' + chapter;
	M_table.showConfirmModal("警告","delete","是否确认退出分会?");
	$('.deleteConfirm').click(function(){
		publicDom.getData('delete',newURL, null, function(data){
			if(data.code==0){
				M_table.showConfirmModal('成功','success','已经退出分会！');
				location.href = "chapter.html";
			} else {
				M_table.showConfirmModal('错误','success','操作失败！')
			}
		});
	});
}


var bindEvent = function () {
	$("#leave").click(function(){
		M_table.leave();
	});
}
