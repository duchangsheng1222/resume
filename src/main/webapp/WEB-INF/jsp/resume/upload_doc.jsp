<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/static/js/table/tableViewer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/table/tableViewer.js"></script>
	<title>upload</title>
	<script>
	
	
	function fillTable(datas){
		list.callback(datas);
	}
	
	
	
	function fun_submit(){
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
<body>
	<form  name="f" action="upload/resume" method="POST" enctype="multipart/form-data">
		<input type="file" name="resumeFile" />
		<input type="hidden" name="resumeId" value="${resumeId }" />
		<button onclick="fun_submit();"></button>
	</form>
</body>
</html>
