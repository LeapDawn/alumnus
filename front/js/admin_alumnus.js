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
	deleteItem:function(){},
	getList:function(){},
}

M_table.init = function () {

}

M_table.getList = function (curr) {
	var newURL = M_table.url + url.admin_alumnus_list;
	var param = {
		key:  {
			name: $("#search_input").val() || '',
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
			M_table.showConfirmModal('错误','success','获取校友信息列表失败！');
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
					 			<button type="button" class="btn btn-info btn-xs btn-detail" style="margin: 0 0 0 5px;">详情</button>\
					 			<button type="button" class="btn btn-danger btn-xs btn-delete" style="margin: 0 0 0 5px;">删除</button>\
				 			</td>\
				 		</tr>'
		$('.alumnus_table table tbody').append(tableItem);
	}
	$('.btn-detail').click(function(){
		//详情页
		var id = $(this).parent().parent().data('id');
		localStorage.setItem('alumnus_info_id', id);
		location.href="admin_alumnus_detail.html";
	});

	$('.btn-delete').click(function(){
		//删除
		M_table.showConfirmModal("警告","delete","是否确认删除?");
		var del_id = $(this).parent().parent().data('id');
		$('.deleteConfirm').click(function(){
			M_table.deleteItem(del_id);
		});
	});

}

M_table.deleteItem = function(id) {
	var newURL = M_table.url + url.alumnus_deleteion + "?id=" + id;
	publicDom.getData('delete',newURL, null, function(JsonData){
		if(JsonData.code == 0){
			M_table.showConfirmModal("成功", "success", "成功删除校友信息!");
			location.reload();
		} else {
			M_table.showConfirmModal("失败", "", "删除失败!该校友信息不允许被删除!");
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
