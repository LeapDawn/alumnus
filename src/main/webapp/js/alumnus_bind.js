$(function(){
	M_table.init();
	bindEvent();
	M_table.getMyInfo();
})

var M_table = {
	url: publicDom.config.url,
	id: '',
	init:function(){},
	showConfirmModal:showConfirmModal,
	bindItem:function(){},
	addItem:function(){},
	getMyInfo:function(){},
	updateItem:function(){}
}

M_table.init = function () {
	
}

M_table.getMyInfo = function() {
	var newURL = M_table.url + url.myinfo;
	publicDom.getData('get',newURL, null,function(data){
		if(data.code==0){
			if (data.value != null) {
				var value = data.value;
				$("#name").val(value.name),
				$("#studentno").val(value.studentno),
				$("#nation").val(value.nation),
				$("#tel").val(value.tel),
				$("#college").val(value.college),
				$("#major").val(value.major),
				$("#idCard").val(value.idCard),
				$("#enrolDate").val(value.enrolDate),
				$("#graduationDate").val(value.graduationDate),
				$("#birthday").val(value.birthday),
				$("#nativePlace").val(value.nativePlace),
				$("#politicalStatus").val(value.politicalStatus);
				$('#bind_alumnus').attr('style', 'display:none');
				$('#add_alumnus').removeAttr("style");
				$('#detail').removeAttr("style");

				$('#add_alumnus').click(function(){
					M_table.updateItem();
				});

				if (data.value.invalid == 1) {
					M_table.showConfirmModal('成功','success','您已提交过校友信息,等待管理人员审核中！');
				} else if(data.value.invalid == 2) {
					M_table.showConfirmModal('成功','success','您提交的校友信息审核不通过,请修改信息后重新提交审核!');
				} else if (data.value.invalid == 0) {
					location.href="alumnus.html";
				} else {
					console.warn('strange invalid');
				}
				
			} else {
				$('#add_alumnus').click(function(){
					M_table.addItem();
				});
				M_table.showConfirmModal('成功','success','请输入信息绑定校友信息！')
			}
			
		} else if(data.code==305){
				$('#add_alumnus').click(function(){
					M_table.addItem();
				});
				M_table.showConfirmModal('成功','success','请输入信息绑定校友信息！')
		} else{
			M_table.showConfirmModal('错误','success','获取个人信息失败！')
		}
	})
}


M_table.bindItem = function(){
	var newURL = M_table.url + url.alumnus_bind;
	var name = $("#name").val();
	var idCard = $("#idCard").val();
	if (name == '' || idCard == ''){
		M_table.showConfirmModal('错误','success','姓名和身份证信息不能为空！');
		return;
	}
	var param = {
		name:name,
		idCard:idCard
	}
	console.info(param);
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','绑定校友信息成功！');
			$('.confirm').click(function(){
				location.href="alumnus.html";
			})
		}else if (data.code==303) {
			M_table.showConfirmModal('错误','success','校友信息库中不存在您的信息，请输入详细数据并提交与管理员审核!');
			$('#bind_alumnus').attr('style', 'display:none');
			$('#add_alumnus').removeAttr("style");
			$('#detail').removeAttr("style");
		}else{
			M_table.showConfirmModal('错误','success','绑定校友信息失败！');
		}
	})
}

M_table.addItem = function(){
	var newURL = M_table.url + url.alumnus_addition;
	var name = $("#name").val();
	var idCard = $("#idCard").val();
	if (name == '' || idCard == ''){
		M_table.showConfirmModal('错误','success','姓名和身份证信息不能为空！');
		return;
	}
	var param = {
		name:$("#name").val(),
		idCard:$("#idCard").val(),
		studentno:$("#studentno").val(),
		nation:$("#nation").val(),
		tel:$("#tel").val(),
		college:$("#college").val(),
		major:$("#major").val(),	
		enrolDate:$("#enrolDate").val(),
		graduationDate:$("#graduationDate").val(),
		birthday:$("#birthday").val(),
		nativePlace:$("#nativePlace").val(),
		politicalStatus:$("#politicalStatus").val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('post',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','绑定校友信息成功,请等待管理员审核!');
			$('#add_alumnus').click(function(){
				M_table.updateItem();
			});
		}else if (data.code==901) {
			M_table.showConfirmModal('错误','success','您已经绑定过校友信息了！');
			$('#add_alumnus').click(function(){
				M_table.updateItem();
			});
		}else{
			M_table.showConfirmModal('错误','success','绑定校友信息失败！');
		}
	})
}


M_table.updateItem = function(){
	var newURL = M_table.url + url.myinfo_update;
	var param = {
		name:$("#name").val(),
		idCard:$("#idCard").val(),
		studentno:$("#studentno").val(),
		nation:$("#nation").val(),
		tel:$("#tel").val(),
		college:$("#college").val(),
		major:$("#major").val(),	
		enrolDate:$("#enrolDate").val(),
		graduationDate:$("#graduationDate").val(),
		birthday:$("#birthday").val(),
		nativePlace:$("#nativePlace").val(),
		politicalStatus:$("#politicalStatus").val()
	}
	var jsonStr = JSON.stringify(param);
	publicDom.getData('put',newURL,jsonStr,function(data){
		if(data.code==0){
			M_table.showConfirmModal('成功','success','修改信息成功,等待管理人员审核!');
		} else{
			M_table.showConfirmModal('错误','success','操作失败!');
		}
	})
}



var bindEvent = function () {
	$('#bind_alumnus').click(function(){
		M_table.bindItem();
	});
	// $('#add_alumnus').click(function(){
	// 	M_table.addItem();
	// });
	
	// $('#more').click(function(){
	// 	$('#detail').removeAttr("style");
	// 	$('#more').attr('style', 'display:none');
	// 	$('#less').removeAttr("style");
	// });
	// $('#less').click(function(){
	// 	$('#detail').attr('style', 'display:none');
	// 	$('#less').attr('style', 'display:none');
	// 	$('#more').removeAttr("style");
	// });
}
