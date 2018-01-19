$(function(){
	M_table.init();
	M_table.getList();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	//getList:function(curr){},
	createList:function(JsonData){},
	showConfirmModal:showConfirmModal,
	getList:function(id){},
	approveApply:function(id){},
	disapproveApply:function(){},
}

M_table.init = function () {

}

M_table.getList = function (curr) {
	var newURL = M_table.url + url.admin_alumnus_apply;
	var param = {
		key:  {},
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
			M_table.showConfirmModal('错误','success','获取校友申请列表失败！');
		}		
	})
}

M_table.createList = function (list) {
	$('.alumnus_table table tbody').empty();
	for( var i = 0 ; i < list.length ; i ++ ){
		var tableItem = '<tr data-id="'+list[i].id+'">\
							<td>'+list[i].name+'</td>\
				 			<td>'+list[i].idCard+'</td>\
				 			<td>'+list[i].studentno+'</td>\
				 			<td>'+list[i].college+'</td>\
				 			<td>'+list[i].major+'</td>\
				 			<td>'+list[i].enrolDate+'</td>\
				 			<td>'+list[i].graduationDate+'</td>\
				 			<td style="text-align: right;">\
					 			<button type="button" class="btn btn-info btn-xs btn-approve" style="margin: 0 0 0 5px;">通过</button>\
					 			<button type="button" class="btn btn-danger btn-xs btn-disapprove" style="margin: 0 0 0 5px;">否决</button>\
				 			</td>\
				 		</tr>'
		$('.alumnus_table table tbody').append(tableItem);
	}
	$('.btn-approve').click(function(){
		//申请通过
		var id = $(this).parent().parent().data('id');
		M_table.showConfirmModal("警告","delete","是否确认通过申请?");
		$('.deleteConfirm').click(function(){
			M_table.approveApply(id);
		});
	});

	$('.btn-disapprove').click(function(){
		//申请不通过
		M_table.showConfirmModal("警告","delete","是否确认否决申请?");
		var id = $(this).parent().parent().data('id');
		$('.deleteConfirm').click(function(){
			M_table.disapproveApply(id);
		});
	});

}

M_table.approveApply = function(id) {
	var newURL = M_table.url + url.apply_approve;
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
	var newURL = M_table.url + url.apply_disapprove;
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

	$('#search').click(function(){
		M_table.getList();
	});

	// $('#search').click(function() {
	// 	M_table.getList();
	// });

	// $('#filter').on('keyup', function() {
	// 	  M_table.getList();
	// });

	
}
