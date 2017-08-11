$(function(){
	$('#alone1').change(function(){
		var filePath=$(this).val();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
		$('#cont1').append('<i><img src="static/img/del.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+fileName+'</i>');
	});
	$('#alone2').change(function(){
		var filePath=$(this).val();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
		$('#cont2').append('<i><img src="static/img/del.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+fileName+'</i>');
	});
	
	
	//文件删除
	$('#cont1').on('click','i img',function(){
		$(this).parent().remove();
	});
	$('#cont2').on('click','i img',function(){
		$(this).parent().remove();
	});
});