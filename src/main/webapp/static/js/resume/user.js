

	var user = {
			baseUrl : "",
			URL : {
				register : "public/user/register",
				sendVerifyCode : "public/user/sendVerifyCode",
				resetPwd : "public/user/resetPassword",
				list:"public/user/list",
				login : "login.jsp",
				checkVerifyCode : "public/user/checkVerifyCode"
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
							  var parames = new Array();
					          parames.push({ name: "username", value: data.username});
					          parames.push({ name: "password", value: data.password});
					          parames.push({ name: "skip", value: data.skip});

							Post(user.baseUrl + "/login", parames );
							window.location.href = user.baseUrl + "/interview/page";
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
			
			checkVerifyCode : function(email,verifyCode,emailCode){
				$.ajax({
					url: user.baseUrl+user.URL.checkVerifyCode,
					type:"post",
					dataType:"json",
					data:{email:email,verifyCode:verifyCode,emailVerifyCode:emailCode},
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
	
	  function ShowReport_Click() {

          var parames = new Array();
          parames.push({ name: "param1", value: "param1"});
          parames.push({ name: "param2", value: "param2"});

          Post("SupplierReportPreview.aspx", parames);

          return false;
      }

      /*
      *功能： 模拟form表单的提交
      *参数： URL 跳转地址 PARAMTERS 参数
      *返回值：
      *创建时间：20160713
      *创建人： 
      */
      function Post(URL, PARAMTERS) {
          //创建form表单
          var temp_form = document.createElement("form");
          temp_form.action = URL;
          //如需打开新窗口，form的target属性要设置为'_blank'
          temp_form.target = "_self";
          temp_form.method = "post";
          temp_form.style.display = "none";
          //添加参数
          for (var item in PARAMTERS) {
              var opt = document.createElement("textarea");
              opt.name = PARAMTERS[item].name;
              opt.value = PARAMTERS[item].value;
              temp_form.appendChild(opt);
          }
          document.body.appendChild(temp_form);
          //提交数据
          temp_form.submit();
      }
