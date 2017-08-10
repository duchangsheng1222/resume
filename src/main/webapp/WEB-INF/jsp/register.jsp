<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/signUp.css"/>
	<link href="${pageContext.request.contextPath }/static/js/table/tableViewer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/table/tableViewer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/resume/user.js"></script>
	<title>sign in</title>
	<script>
	
	
	function fun_submit(){
		
		var email = $("#email").val() ;
		var pwd = $("#password").val() ;
		var repwd = $("#passwordAgain").val() ;
		if(pwd != repwd){
			return;
		}
		
		user.register(email, pwd);
	}
	function fun_reset(){
		document.f.reset();
	}
	
	document.onkeypress=function(e){
		var code;
		if(!e){
			var e=window.event;
		}
		if(e.keyCode){
			code=e.keyCode;
		}
		else if(e.which){
			code=e.which;
		}
		if(code==13){
			fun_submit();
		}
	}
	
	</script>
		
	</head>
	<body>
		<div class="box">
			<!--导航条-->
			<div class="nav">
				<a href="index.jsp" class="homeA">Home</a><a href="javascript;">-</a><a href="login.jsp" class="loginA">Sign up</a>
			</div>
			
			<!--登录表单-->
			<div class="loginBox">
				<p class="title">Sign up</p>
				<form >
					<input type="email" name="" id="email" value="" placeholder="Email address" required="required"/>
					<input type="password" name="" id="password" value="" placeholder="Enter your Password" required="required"/>
					<input type="password" name="" id="passwordAgain" value="" placeholder="Enter your Password again" required="required"/>
					<input type="button" name="" onclick="fun_submit();" id="sub" value="OK" />
				</form>
			</div>
			
		</div>
	</body>
</html>
