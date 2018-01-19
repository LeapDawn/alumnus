$(function(){
	M_table.init();
	M_table.getList();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	getList:function(curr){},
	createList:function(JsonData){},
	approveApply:function(id){},
	disapproveApply:function(id){},
	showConfirmModal:showConfirmModal,
}

M_table.init = function () {
	
}


M_table.getList = function (curr) {
	var newURL = M_table.url + url.chapter_apply_list;
	var param = {
		key:0,
		page: curr || 1,
		rows: 8
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(JsonData){
		if(JsonData.code == 0){
			M_table.createList(JsonData.value.data);
			laypage({
				cont : 'chapter_page',
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
			M_table.showConfirmModal('错误','success','获取校友分会申请列表失败！');
		}		
	})
}
M_table.createList = function (list) {
	$(".chapter_table").empty();
	for( var i = 0 ; i < list.length ; i ++ ){
		var tableItem = '<div class="col-md-3" style="float: left; padding: 0 10px;">\
		  			<div class="content-box-large" data-id="'+ list[i].id + '">\
		  				<div class="panel-heading">\
							<div class="panel-title">'+ list[i].name +'</div>\
						</div>\
		  				<div class="panel-body" style="height: 150px;overflow:hidden;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ list[i].content +'\
		  				</div>\
						<button type="button" class="btn btn-primary btn-xs col-sm-offset-4 btn-approve">通过</button>\
 						<button type="button" class="btn btn-danger  btn-xs pull-right btn-disapprove">否决</button>\
		  			</div>\
		  		</div>';
		$('.chapter_table').append(tableItem);
	}
	$('.btn-approve').click(function(){
		//申请通过
		var id = $(this).parent().data("id");
		M_table.showConfirmModal("警告","delete","是否确认通过申请?");
		$('.deleteConfirm').click(function(){
			M_table.approveApply(id);
		});
	});

	$('.btn-disapprove').click(function(){
		//申请不通过
		var id = $(this).parent().data("id");
		M_table.showConfirmModal("警告","delete","是否确认否决申请?");	
		$('.deleteConfirm').click(function(){
			M_table.disapproveApply(id);
		});
	});
}

M_table.approveApply = function(id) {
	var newURL = M_table.url + url.chapter_apply_approve;
	var param = {
		id:id
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL, jsonStr, function(JsonData){
		if(JsonData.code == 0){
			M_table.showConfirmModal("成功", "success", "已确认通过校友申请!");
			location.reload();
		} else {
			M_table.showConfirmModal("失败", "", "操作失败!");
		}
	})
}

M_table.disapproveApply = function(id) {
	var newURL = M_table.url + url.chapter_apply_disapprove;
	var param = {
		id:id
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL, jsonStr, function(JsonData){
		if(JsonData.code == 0){
			M_table.showConfirmModal("成功", "success", "已确认否决校友申请!");
			location.reload();
		} else {
			M_table.showConfirmModal("失败", "", "操作失败!");
		}
	})
}


var bindEvent = function () {
	
}
