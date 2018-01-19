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
	showConfirmModal:showConfirmModal,
}

M_table.init = function () {
	
}


M_table.getList = function (curr) {
	var newURL = M_table.url + url.chapter_list;
	var param = {
		// key:  {
		// 	name: $("#search_input").val() || '',
		// },
		key:{},
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
			M_table.showConfirmModal('错误','success','获取校友分会列表失败！');
		}		
	})
}

M_table.createList = function (list) {
	$(".chapter_table").empty();
	for( var i = 0 ; i < list.length ; i ++ ){
		var tableItem = '<div class="col-md-3" style="float: left; padding: 0 10px;">\
		  			<div class="content-box-large">\
		  				<div class="panel-heading">\
							<div class="panel-title"><a class="chapter_detail" href="#">'+ list[i].name +'</a></div>\
							<div class="panel-options">\
							</div>\
						</div>\
		  				<div class="panel-body" style="height: 150px;overflow:hidden;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ list[i].content +'\
		  				</div>\
		  			</div>\
		  		</div>';
		$('.chapter_table').append(tableItem);
	}
	$('.chapter_detail').click(function(){
		localStorage.setItem('chapter_name', $(this).text());
		location.href="admin_chapter_detail.html";
	});
}


var bindEvent = function () {

}
