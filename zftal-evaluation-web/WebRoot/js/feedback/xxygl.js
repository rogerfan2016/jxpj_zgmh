

function bdan(){
	var btn_dr=jQuery("#btn_dr");//导入
	var btn_xg=jQuery("#btn_xg");//修改
	var btn_sc=jQuery("#btn_sc");//删除
	var btn_mb=jQuery("#btn_mb");//模板下载
	var btn_tj=jQuery("#btn_tj");//新增
	
	if(btn_dr != null){
		btn_dr.click(function () {
			var url=_path+'/feedback/staff_uploadTemplate.html';
			showWindow('信息员导入',url,480,150);
		});
	}
	if(btn_xg != null){
		btn_xg.click(function () {
			var items = $('#.con_overlfow input[name = "id"]:checkbox:checked');
			if(items.length == 0){
				alert("请选择列表数据");
				return ;
			}
			if(items.length > 1){
				alert("请选择一项数据!");
				return ;
			}
			var url=_path+'/feedback/staff_staffEdit.html?staffIds='+items.get(0).value;
			showWindow('信息员修改',url,480,250);
		});
	}
	if(btn_mb != null){
		btn_mb.click(function () {
			var url=_path+'/feedback/staff_downloadTemplate.html';
			window.location.href = url;
		});
	}
	if(btn_sc != null){
		btn_sc.click(function(){
			var ids =""; 
			var items = $('#.con_overlfow input[name = "id"]:checkbox:checked');
			for (var i = 0; i < items.length; i++) {
				ids = (ids + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
			}
			if(ids == ""){
				alert("请选择列表数据");
				return;
			}
			$.ajax({type:"post",
				url:_path+"/feedback/staff_delete.html",
				data:{staffIds:ids},
				success:function(data){
					if(data){
						window.location.href = window.location.href;
					}else{
						alert("系统异常,删除失败!");
					}						
				},
				datatType:"json",
				global:false
			});
		});
		
	}
	if(btn_tj != null){
		btn_tj.click(function () {
			var url=_path+'/feedback/staff_staffAddView.html';
			showWindow('信息员添加',url,480,250);
		});
	}
	
	$('#selectAll').click(function(){
        //判断apple是否被选中
        var selectAllChecked=$('#selectAll').is(':checked');
        var items=$('.con_overlfow input[name="id"]');
        selectAllChecked?items.attr('checked',true):items.attr('checked',false);
    });
}