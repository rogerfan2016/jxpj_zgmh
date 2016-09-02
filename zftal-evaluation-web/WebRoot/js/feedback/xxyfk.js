
//信息员课程反馈
function feedback(kcid,globalid){
	var url=_path+'/feedback/info_addFeedbackIndex.html?kcid='+kcid+"&globalid="+globalid;
	showWindow('信息反馈',url,600,250);
}