$(function(){
	C_table.init();
	C_table.hasJoined();
})

var C_table = {
	url: publicDom.config.url,
	init:function(){},
	showConfirmModal:showConfirmModal,
	hasJoined:function(){},
	apply:function(){},
	readyApply:function(){},
}

C_table.init = function () {
	
}

C_table.readyApply = function(){
	var chapter = $('#apply_input').val();
	var itemURL = C_table.url + url.chapter_item + '?item=' + chapter;
	publicDom.getData('get', itemURL, null, function(data){
		if(data.code == 0) {
			if(data.value == null) {
				M_table.showConfirmModal('错误','success','该分会不存在!!!');
			} else {
				C_table.apply();
			}
		} else {
			M_table.showConfirmModal('错误','success','操作失败!获取分会信息失败!');
		}
	})
}

C_table.apply = function (){
	var newURL = C_table.url + url.applyChapterAlumnus;
	var param = {
		chapter:$('#apply_input').val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL, jsonStr,function(data){
		if (data.code == 0) {
			M_table.showConfirmModal('成功','success','提交申请成功,等待管理员审核!');
		} else if (data.code == 601) {
			M_table.showConfirmModal('错误','success','已有待审核的加入校友分会申请!');
		} else {
			M_table.showConfirmModal('错误','success','操作失败!提交申请失败!');
		}

	});
}

C_table.hasJoined = function(){
	var newURL = C_table.url + url.chapter_hasJoined;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code == 0){
			if (data.value == null) {
				// 未加入校友分会
				$('.input-group').append(
					'<input id="apply_input" type="text" class="form-control" placeholder="输入分会名称....">\
	                   <span class="input-group-btn">\
	                     <button id="apply" class="btn btn-primary" type="button">申请加入分会</button>\
	                   </span>\
	                   <a href="chapter_addition.html"><button class="btn btn-primary col-md-offset-10" type="button">创建分会</button></a>');
				$('#apply').click(function(){
					C_table.readyApply();
				});
			}
		}else{
			console.error("验证校友是否已加入分会失败!");
		}		
	})
}


var bindEvent = function () {


}
