$(function(){
	M_table.init();
	bindEvent();
})

var M_table = {
	url: publicDom.config.url,
	init:function(){},
	getChapterApply:function(){},
	getChapterAlumnusApply:function(){},
	getClassApply:function(){},
	getClazzAlumnusApply:function(){},
	getStatus:function(status){},
	showConfirmModal:showConfirmModal,
}

M_table.init = function () {
	M_table.getChapterAlumnusApply();
	M_table.getClazzAlumnusApply();
	M_table.getChapterApply();
	M_table.getClazzApply();

}

M_table.getChapterApply = function () {
	var newURL = M_table.url + url.mychapter_apply;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code == 0){
			if (data.value != null) {
				// M_table.createItemDiv("加入分会申请", data.value.chapter, data.value.content, data.value.status);
				var tableItem = '<div class="col-md-8">\
		  			<div class="content-box-large">\
		  				<div class="panel-heading">\
							<div class="panel-title">创建分会申请</div>\
							<div class="panel-options">\
								<a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>\
								<a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>\
							</div>\
						</div>\
		  				<div class="panel-body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分会名称 : '+ data.value.name +'\
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态 : ' + M_table.getStatus(data.value.status) +' \
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间 : ' + data.value.date +' \
		  				</div>\
		  			</div>\
		  		</div>';
				$('.apply_table').append(tableItem);
			}
		}else{
			console.info("获取创建分会申请状态失败!");
		}		
	});
}

M_table.getChapterAlumnusApply = function () {
	var newURL = M_table.url + url.mychapter_alumnus_apply;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code == 0){
			if (data.value != null) {
				// M_table.createItemDiv("加入分会申请", data.value.chapter, data.value.content, data.value.status);
				var tableItem = '<div class="col-md-8">\
		  			<div class="content-box-large">\
		  				<div class="panel-heading">\
							<div class="panel-title">加入分会申请</div>\
							<div class="panel-options">\
								<a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>\
								<a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>\
							</div>\
						</div>\
		  				<div class="panel-body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分会名称 : '+ data.value.chapter +'\
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态 : ' + M_table.getStatus(data.value.status) +' \
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间 : ' + data.value.date +' \
		  				</div>\
		  			</div>\
		  		</div>';
				$('.apply_table').append(tableItem);
			}
		}else{
			console.info("获取加入分会申请状态失败!");
		}		
	});
}

M_table.getClazzApply = function () {
	var newURL = M_table.url + url.myclazz_apply;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code == 0){
			if (data.value != null) {
				// M_table.createItemDiv("加入分会申请", data.value.chapter, data.value.content, data.value.status);
				var tableItem = '<div class="col-md-8">\
		  			<div class="content-box-large">\
		  				<div class="panel-heading">\
							<div class="panel-title">创建班级申请</div>\
							<div class="panel-options">\
								<a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>\
								<a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>\
							</div>\
						</div>\
		  				<div class="panel-body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级名称 : '+ data.value.name +'\
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态 : ' + M_table.getStatus(data.value.status) +' \
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间 : ' + data.value.date +' \
		  				</div>\
		  			</div>\
		  		</div>';
				$('.apply_table').append(tableItem);
			}
		}else{
			console.info("获取创建班级申请状态失败!");
		}		
	});
}

M_table.getClazzAlumnusApply = function () {
	var newURL = M_table.url + url.myclazz_alumnus_apply;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code == 0){
			if (data.value != null) {
				// M_table.createItemDiv("加入分会申请", data.value.chapter, data.value.content, data.value.status);
				var tableItem = '<div class="col-md-8">\
		  			<div class="content-box-large">\
		  				<div class="panel-heading">\
							<div class="panel-title">加入班级申请</div>\
							<div class="panel-options">\
								<a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>\
								<a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>\
							</div>\
						</div>\
		  				<div class="panel-body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级名称 : '+ data.value.clazz +'\
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态 : ' + M_table.getStatus(data.value.status) +' \
		  					<br><br>\
		  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间 : ' + data.value.date +' \
		  				</div>\
		  			</div>\
		  		</div>';
				$('.apply_table').append(tableItem);
			}
		}else{
			console.info("获取加入班级申请状态失败!");
		}		
	});
}

M_table.getStatus = function(status) {
	if (status == 0) {
		return "待审核";
	} else if (status == 1) {
		return "审核通过";
	} else if (status == 2) {
		return "审核不通过";
	} else {
		return "";
	}
}


var bindEvent = function () {
	
}
