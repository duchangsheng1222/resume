<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="static/js/jquery/jquery-3.0.0.js"></script>
	<script src="static/js/jquery-cookie/jquery.cookie-1.3.1.js" type="text/javascript"></script>
	<title>sign in</title>
	<script>
	
	// 刷新图片  
    function changeImg() {  
        var imgSrc = $("#imgObj");  
        var src = imgSrc.attr("src");  
        imgSrc.attr("src", changeUrl(src));  
    }  
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
    function changeUrl(url) {  
        var timestamp = (new Date()).valueOf();  
      	var url = "${pageContext.request.contextPath}/public/verify/code?t="+timestamp;
        return url;  
    } 
	
	$(document).ready(function(){
		if ($.cookie('rememberPwd') == 'true') {
			$("#chkRememberPwd").prop('checked', true);
			if ($.cookie('username') != 'undefined') {
				$("#username").val($.cookie('username'));	
			}
			if ($.cookie('password') != 'undefined') {
				$("#password").val($.cookie('password'));
			}
		}
	});
	
	if(self!=top){
		window.location='login.jsp';
	}
	
	function fun_submit(){
		if(document.getElementById("chkRememberPwd").checked){

			$.cookie('rememberPwd', 'true', { expires: 7 });
			$.cookie('username', $("#username").val(), { expires: 7 });
			$.cookie('password', $("#password").val(), { expires: 7 });			
		} else {
			$.removeCookie('rememberPwd');
			$.removeCookie('username');
			$.removeCookie('password');
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
<body onload='document.f.username.focus();'>
<div class="logindiv">
	<div class="logincont">
		<form id="login_id" name='f' action='login' method='POST'>
		
		<table border="0" cellpadding="0" cellspacing="0" class="logintable">
			<tr>
				<td class="loginttd_l">用户名</td>
				<td class="loginttd_r"><input id="username" name="username" class="loginttd_r_input" type="text" value="<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}" escapeXml="false"/></c:if>"/></td>
			</tr>
			<tr>
				<td class="loginttd_l">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				<td class="loginttd_r"><input id="password" class="loginttd_r_input" type="password"  name="password"/></td>
			</tr>
			<tr>
				<td class="loginttd_l"></td>
				<td class="loginttd_r"><input type='checkbox' id="chkRememberPwd"/><font style="color: #8ca3b2;">记住用户名和密码</font></td>
			</tr>
			<tr>
				<td class="loginttd_l">验证码</td>
				<td class="loginttd_r"><input id="verifyCode" name="verifyCode" class="loginttd_r_input" type="text" />
									    <img id="imgObj" alt="验证码" src="<%= request.getContextPath()%>/public/verify/code" onclick="changeImg()"/>  
      								  <a href="#" onclick="changeImg()">换一张</a>  </td>
			</tr>
			<tr>
				<td class="loginttd_l"></td>
				<td class="loginttd_r"><a href="javascript:void(0);" onclick="fun_submit();return false;"><img src="<%= request.getContextPath()%>/static/images/login_img/denglu.gif" width="73" height="28" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
									   <a href="javascript:void(0);" onclick="fun_reset();return false;"><img src="<%= request.getContextPath()%>/static/images/login_img/chongzhi.gif" width="73" height="28" /></a>
									    </td>
			</tr>
			<tr>
				<td colspan="2" style="height: 30px;" >	<div class="logincont_center">
					<c:if test="${not empty param.login_error}">
				      <font color="red">  ${sessionScope }<br/><br/></font>
				    </c:if>
			</div></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:left; color:#6e6e6e;" ></td>
			</tr>
		</table>
		</form>
    </div>
</div>
</body>
</html>
