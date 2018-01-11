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
	getList:function(){},
}

M_table.init = function () {

}

M_table.getList = function (curr) {
	var newURL = M_table.url + url.alumnus_list;
	console.log(newURL);
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
							<td><a class="alumnus_detail" href="#">'+list[i].name+'</a></td>\
				 			<td>'+list[i].studentno+'</td>\
				 			<td>'+list[i].college+'</td>\
				 			<td>'+list[i].major+'</td>\
				 			<td>'+list[i].enrolDate+'</td>\
				 			<td>'+list[i].graduationDate+'</td>\
				 		</tr>'
		$('.alumnus_table table tbody').append(tableItem);
	}
	$('.alumnus_detail').click(function(){
		//详情页
		var id = $(this).parent().data('id');
		localStorage.setItem('alumnus_id', id);
		location.href="alumnus_detail.html";
	});
}

var bindEvent = function () {

	$('#search').click(function(){
		M_table.getList();
	});
}
