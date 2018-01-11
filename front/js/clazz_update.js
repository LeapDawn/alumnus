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
	var name = localStorage.getItem('clazz_update_name');
	M_table.getItem(name);
}

M_table.getItem = function(name) {
	var newURL = M_table.url + url.clazz_item + '?item=' + name;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code==0){
			var value = data.value;
			$("#name").val(value.name);
			$("#content").val(value.content);
		} else{
			M_table.showConfirmModal('错误','success','获取校友班级信息失败！');
		}

	})
}

M_table.updateItem = function(){
	var newURL = M_table.url + url.clazz_update;
	var param = {
		name:$("#name").val(),
		content:$("#content").val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','修改成功！');
		} else if (data.code==701) {
			M_table.showConfirmModal('错误','success','只有班级管理员才能修改!')
		} else{
			M_table.showConfirmModal('错误','success','操作失败！')
		}
	})
}



var bindEvent = function () {
	$('#clazz_update').click(function(){
		M_table.updateItem();
	});
}
