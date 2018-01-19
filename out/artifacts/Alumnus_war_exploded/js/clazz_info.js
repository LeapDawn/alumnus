$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	getItem:function(){},
	getList:function(){},
	createList:function(JsonData){},
}

M_table.init = function () {
	// 获取列表页传递过来的ID
	M_table.id = localStorage.getItem('clazz_name');
	if (M_table.id != null && M_table.id != '') {
		M_table.getItem(M_table.id);
		M_table.getList(1);
	}
}

M_table.getList = function (curr) {
	var newURL = M_table.url + url.clazz_member;
	var param = {
		key:  {
			"clazz":M_table.id
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
			M_table.showConfirmModal('错误','success','获取班级成员列表失败！');
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


M_table.getItem = function(id) {
	var newURL = M_table.url + url.clazz_item + "?item=" + M_table.id;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code==0){
			var value = data.value;
			$(".clazz_detail").text(value.name),
			$("#clazz_content").text(value.content)
		}else{
			M_table.showConfirmModal('错误','success','获取校友班级信息失败！')
		}
	})

}


var bindEvent = function () {

	
}
