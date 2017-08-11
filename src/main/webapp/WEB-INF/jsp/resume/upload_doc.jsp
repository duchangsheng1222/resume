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
				<form action="#" method="post">
					<!--Resume-->
					<div class="alone">
						<em>Resume</em>
						<div class="center">
							<span id="cont1"></span>
							<input type="file" name="" id="alone1" value="" />
							<label for="alone1" class="add" id="add1"></label>
						</div>
						<span id="upload1">Upload</span>
					</div>
					<!--Photo-->
					<div class="alone">
						<em id="em2">Photo</em>
						<div class="center">
							<span id="cont2"></span>
							<input type="file" name="" id="alone2" value="" />
							<label for="alone2" class="add" id="add2"></label>
						</div>
						<span id="upload2">Upload</span>
					</div>
					<!--finish-->
					<input type="submit" id="finish" name="" value="Finish" />
					
				</form>
			</div>
		</div>
	</body>
</html>
