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
	var chapter = $("#name").val();
	var newURL = M_table.url + url.chapter_item + '?item=' + chapter;
	publicDom.getData('get', newURL, null, function(data){
		if(data.code == 0) {
			if(data.value == null) {
				M_table.addItem();
			} else {
				M_table.showConfirmModal('错误','success','该分会已经存在!');
			}
		} else {
			M_table.showConfirmModal('错误','success','操作失败!验证分会是否存在失败!');
		}
	})

}

M_table.addItem = function(){
	var newURL = M_table.url + url.applyChapter;
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
			M_table.showConfirmModal('错误','success','已有待审核的创建校友分会申请!')
		} else if (data.code==509) {
			M_table.showConfirmModal('错误','success','已经有其他人申请同名分会!')
		} else{
			M_table.showConfirmModal('错误','success','提交申请失败！')
		}
	})
}



var bindEvent = function () {
	$('#add_chapter').click(function(){
		M_table.readyAddItem();
	});
}
