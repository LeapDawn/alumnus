$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	getItem:function(name){},
	updateItem:function(){},
}

M_table.init = function () {
	var name = localStorage.getItem('chapter_update_name');
	M_table.getItem(name);
}

M_table.getItem = function(name) {
	var newURL = M_table.url + url.chapter_item + '?item=' + name;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code==0){
			var value = data.value;
			$("#name").val(value.name);
			$("#content").val(value.content);
		} else{
			M_table.showConfirmModal('错误','success','获取校友分会信息失败！');
		}

	})
}

M_table.updateItem = function(){
	var newURL = M_table.url + url.chapter_update;
	var param = {
		name:$("#name").val(),
		content:$("#content").val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','修改成功！');
		} else if (data.code==701) {
			M_table.showConfirmModal('错误','success','只有分会管理员才能修改!')
		} else{
			M_table.showConfirmModal('错误','success','操作失败！')
		}
	})
}



var bindEvent = function () {
	$('#chapter_update').click(function(){
		M_table.updateItem();
	});
}
