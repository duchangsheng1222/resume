

var resumeInfo = {
	baseUrl : "",	
	URL : {
		save : "/info/save",
		update : "/info/update",
		
	},
	save : function(type){
		$.ajax({
			URL:resumeInfo.baseUrl + resumeInfo.URL.save,
			type:"post",
			dataType:"json",
			data:$("infoForm").serialize(),
			success : function(data){
				if(data.status == 1){
					if(0 == type){
						alert("successed");
					}else{
						window.location.href="";
					}
				}else{
					alert("error");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	},
	
	update : function(){
		$.ajax({
			URL:resumeInfo.baseUrl + resumeInfo.URL.update,
			type:"post",
			dataType:"json",
			data:{},
			success : function(data){
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	}
	
};