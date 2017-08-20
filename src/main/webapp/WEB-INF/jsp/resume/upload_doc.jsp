<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/common/head.jsp"%>
		<meta charset="UTF-8">
		<title>Upload</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/upload.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery/jquery-3.0.0.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/resume/upload.js"></script>
		<script type="text/javascript">
		$(function(){
			var error = "${error}";
			if(error && "${error}" != error && "" != error){
				alert(error);
			}
			
			$('#alone1').change(function(){
				var filePath=$(this).val();
				if("" == filePath){
					return ;
				}
		        var arr=filePath.split('\\');
		        var fileName=arr[arr.length-1];
				$('#cont1').append('<i><img src="static/img/del.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+fileName+'</i>');
			});
			$('#alone2').change(function(){
				var filePath=$(this).val();
				if("" == filePath){
					return ;
				}
		        var arr=filePath.split('\\');
		        var fileName=arr[arr.length-1];
				$('#cont2').append('<i><img src="static/img/del.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+fileName+'</i>');
			});
			
			$("#upload1").on("click",function(){
				$("#form1").submit();
			});
			
			$("#upload2").on("click",function(){
				$("#form2").submit();
			});
			
			//文件删除
			$('#cont1').on('click','i img',function(){
				$(this).parent().remove();
			});
			$('#cont2').on('click','i img',function(){
				$(this).parent().remove();
			});
			$('#finish').on('click',function(){
				window.location.href = "${pageContext.request.contextPath }/interview/page";
			});
			
			$.ajax({
				url : "",
				type : "POST",
				dataType : "json",
				data:{email:email,password:pwd},
				success : function(data){
					if(data.status == 1){
						  var parames = new Array();
				          parames.push({ name: "username", value: data.username});
				          parames.push({ name: "password", value: data.password});
				          parames.push({ name: "skip", value: data.skip});

						Post(user.baseUrl + "/login", parames );
						//window.location.href = user.baseUrl + "/interview/page";
					}else{
						$("#errorSpan").html(data.message);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
			    }
			});
		});
		</script>
	</head>
	<body>
		<div class="box">
			<!--导航条-->
			<div class="nav">
				<a href="/index.jsp" class="homeA">Home</a><a href="javascript;">-</a><a href="#" class="loginA">Upload</a>
			</div>
			<!--表单详情-->
			<div class="formBox">
				<p class="tit">Upload</p>
				<form id="form1" action="${pageContext.request.contextPath }/upload/resume" method="post" enctype="multipart/form-data">
					<input type="hidden" name="resumeId" id="fileResumeId" value="${resumeId}"/>
					<!--Resume-->
					<div class="alone">
						<em>Resume</em>
						<div class="center">
							<span id="cont1"></span>
							<input type="file" name="resumeFile" id="alone1" required="required"/>
							<label for="alone1" class="add" id="add1"></label>
						</div>
						<span id="upload1">Upload</span>
					</div>
					<p class="filecl">&nbsp;</p>
				</form>
				<form id="form2" action="${pageContext.request.contextPath }/upload/video" method="post"  enctype="multipart/form-data">	
					<input type="hidden" name="resumeId" id="videoResumeId" value="${resumeId}"/>
					<!--Photo-->
					<div class="alone">
						<em id="em2">Photo</em>
						<div class="center">
							<span id="cont2"></span>
							<input type="file" name="videoFile" id="alone2" value=""  required="required"/>
							<label for="alone2" class="add" id="add2"></label>
						</div>
						<span id="upload2">Upload</span>
					</div>
					<p class="filecl"><span>xxxx.png</span><span>xxxx.png</span><span>xxxx.png</span></p>
					<!--finish-->
					<input type="button" id="finish" name="" value="Finish" />
					
				</form>
			</div>
		</div>
	</body>
</html>
