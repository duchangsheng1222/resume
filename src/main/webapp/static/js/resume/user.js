

	var user = {
			baseUrl : "",
			URL : {
				register : "public/user/register",
				sendVerifyCode : "public/user/sendVerifyCode",
				resetPwd : "public/user/resetPassword",
				list:"public/user/list",
				login : "login.jsp",
			},
			list : function(fillTable){
				$.ajax({
					url: user.baseUrl+user.URL.list,
					type:"post",
					dataType:"json",
					success : function(data){
						if(data.status == 1){
							var datas = new Array();
							for (var i = 0; i < data.data.length; i++) {
								var user = data.data[i];
								datas[i] = new Array(user.id,user.nickname,user.email,user.password,user.locked);
								
							}
							fillTable(datas);
						}else{
							alert(data.message);
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
				    }
				});
			},
			register : function(email,pwd){
				$.ajax({
					url: user.baseUrl+user.URL.register,
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
					url: user.baseUrl+user.URL.sendVerifyCode,
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
					url: user.baseUrl+user.URL.resetPwd,
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
