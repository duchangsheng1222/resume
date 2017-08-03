

var user = {
	URL : {
		register : "public/user/register",
		sendVerifyCode : "public/user/sendVerifyCode",
		resetPwd : "public/user/resetPassword",
		login : "login.jsp",
	},
	register : function(email,pwd){
		$.ajax({
			URL:user.URL.register,
			type:"post",
			dataType:"json",
			data:{email:email,password:pwd},
			success : function(data){
				if(data.status == 1){
					window.location.href = user.URL.login;
				}else{
					alert(data.message);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	},
	
	sendVerifyCode : function(){
		$.ajax({
			URL:user.URL.sendVerifyCode,
			type:"post",
			dataType:"json",
			data:{},
			success : function(data){
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	},
	
	resetPwd : function(){
		$.ajax({
			URL:user.URL.resetPwd,
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