$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	addItem:function(){},
	readyAddItem:function(){},
}

M_table.init = function () {
	
}

M_table.readyAddItem = function(){
	var clazz = $("#name").val();
	var newURL = M_table.url + url.clazz_item + '?item=' + clazz;
	publicDom.getData('get', newURL, null, function(data){
		if(data.code == 0) {
			if(data.value == null) {
				M_table.addItem();
			} else {
				M_table.showConfirmModal('错误','success','该班级已经存在!');
			}
		} else {
			M_table.showConfirmModal('错误','success','操作失败!验证班级是否存在失败!');
		}
	})

}

M_table.addItem = function(){
	var newURL = M_table.url + url.applyClazz;
	var param = {
		name:$("#name").val(),
		content:$("#content").val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','提交创建申请成功,等待管理人员审核！');
			$('.confirm').click(function() {
				location.reload();
			});
		} else if (data.code==501) {
			M_table.showConfirmModal('错误','success','已有待审核的创建校友班级申请!')
		} else if (data.code==509) {
			M_table.showConfirmModal('错误','success','已经有其他人申请同名班级!')
		} else{
			M_table.showConfirmModal('错误','success','提交申请失败！')
		}
	})
}



var bindEvent = function () {
	$('#add_clazz').click(function(){
		M_table.readyAddItem();
	});
}
