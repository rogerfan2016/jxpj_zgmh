/*
 *	编辑器中使用的全局变量
 *	作者：Penghui.Qu
*/

	//编辑器路径
var editorPath = _path+'/js/editor',
	//样式文件路径
	//处理文件上传的访问路径
	uploadJson = _path+'/editor/kindEditor_fileUpload.html',
	//浏览服务器上文件的访问路径
	//fileManagerJson = editorPath+'/files.jsp';
	fileManagerJson = _path+'/editor/kindEditor_fileManage.html';

//默认视图
var defaultOption = {
	name:'default',
	resizeType : 0,//0 : 编辑器大小固定 1 : 可以调整编辑器高度 2 ： 可以调整编辑器宽度、高度
	//themeType : 'default',//样式风格
	themesPath:_path+"/js/editor/themes/",
	pluginsPath:_path+"/js/editor/plugins/",
	uploadJson : uploadJson,
	fileManagerJson : fileManagerJson,
	allowFileManager : true,//是否允许选择服务器上文件
	items : [
	    //'code', 程序代码     
	    //'multiimage',批量上传图片     
		'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
		'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
		'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
		'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
		'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
		'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
		'anchor', 'link', 'unlink', '|', 'about'
	]
};

//简单视图
var simpleOption = {
	name:'simple',
	resizeType : 0,
	themeType : 'simple',//样式风格
	//cssPath : cssPath,
	themesPath:_path+"/js/editor/themes/",
	pluginsPath:_path+"/js/editor/plugins/",
	uploadJson : uploadJson,
	fileManagerJson : fileManagerJson,
	allowPreviewEmoticons : false,//是否允许浏览远程服务器文件
	allowImageUpload : true,//是否允许上传图片
	items : [
		'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		'insertunorderedlist', '|', 'emoticons', 'image', 'link', 'about'
	]
};