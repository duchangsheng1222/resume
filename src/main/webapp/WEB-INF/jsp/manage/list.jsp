<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/manage.css"/>
	<link href="${pageContext.request.contextPath }/static/js/table/tableViewer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/table/tableViewer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/resume/interview.js"></script>
	<title>sign in</title>
	<script>
var list=new QueryList("line_list","checkbox");
	
	$(function(){
		interview.baseUrl = "${pageContext.request.contextPath }/";
		list.header=new Array("Name","<span id='thAge' class='thorder'>Age</span>",
				"<span id='thgender' class='thorder'>Gender</span>","Skype","Email address",
				"<span id='thcitizenship' class='thorder'>Citizenship</span>",
				"<span id='theducation' class='thorder'>Education</span>","Education country",
				"Resume","Video","Message","Backward","Forward","");
		
		list.fill=true;
	    list.getData = function (){
	    	interview.list(fillTable);
		
		}; 
		list.createTable();
		list.getData();
		
		$(".left").on('click','li',function(){
			$(".left li").removeClass("sel");
			$(this).addClass("sel");
			interview.currentStep = $(this).attr("value");
			interview.list(fillTable);
		});
		
		
	});
	
	function fillTable(datas){
		list.callback(datas);
		$(".thorder").addClass("ordercol");
	}
		
	
	</script>
		
	</head>
	<body>
		<div class="box">
			<!--导航条-->
			<div class="nav">
				<a href="index.jsp" class="homeA">Home</a><a href="javascript;">-</a><a href="#" class="loginA">resume list</a>
			</div>
			<p class="tit"></p>
			<c:if test="${not empty adminUrl }">
				<p class="adminUrl"><a href="${pageContext.request.contextPath }${adminUrl }">admin</a></p>
			</c:if>
			<div class="clr">
		<div class="left">
			<ul>
				<li class="sel" value="1">1.Applications submitted<li>
				<li value="2">2. Resume screened<li>
				<li value="3">3. Submit introduction video and certifications<li>
				<li value="4">4. Video & certificates submitted<li>
				<li value="5">5. Wait for 1st interview<li>
				<li value="6">6. 1st interview passed, result emailed.<li>
				<li value="7">7. Wait for 2nd interview<li>
				<li value="8">8. 2nd interview passed, result emailed.<li>
				<li value="9">9. Wait for job offer<li>
				<li value="10">10. Offer accepted<li>
				<li value="11">11. Visa materia submitted<li>
				<li value="12">12. Visa succeed<li>
				<li value="13">13. Determine China arrival date<li>
				<li value="14">14. Completion of settle down<li>
			</ul>
		</div>
		<div class="right" id="line_list"></div>
		</div>
		</div>
	</body>
</html>

