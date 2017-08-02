<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="static/js/jquery/jquery-3.0.0.js"></script>
	<script src="static/js/jquery-cookie/jquery.cookie-1.3.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/resume/user.js"></script>
	<title>sign in</title>
	<script>
	
	
	function fun_submit(){
		var pwd = $("#pwd").val() ;
		var repwd = $("#repwd").val() ;
		if(pwd != repwd){
			return;
		}
		
		document.f.submit();
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
<body onload='document.f.email.focus();'>
	<form action="public/user/register" name="f" method="post">
		<input type="email" name="email" placeholder="输入邮箱" />
		<input type="password" name="password" id="pwd"/>
		<input type="password" name="repassword" id="repwd"/>
		<button onclick="fun_submit();"></button>
	</form>
</body>
</html>
