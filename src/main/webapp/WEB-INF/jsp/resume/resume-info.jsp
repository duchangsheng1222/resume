<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
	<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<title>Personal information</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery/jquery-3.0.0.js"></script>
	<meta charset="UTF-8">


	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/js/layui/layui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/personalInformation.css"/>
	</head>
	<body>
		<div class="box">
			<!--导航条-->
			<div class="nav">
				<a href="/index.jsp" class="homeA">Home</a><a href="javascript;">-</a><a href="#" class="loginA">Personal information</a>
			</div>
			<p class="tit">Personal information</p>
			<!--表单详情-->
			<div class="formBox">
				<form id="infoForm" >
					<!--==第一行==-->
					<div class="box1">
						<!--姓名-->
						<div class="name">
							<p>Name</p>
							<input type="text" name="name" id="name" value="" />
						</div>
						<!--位置-->
						<div class="position">
							<p>Position applied for</p>
							<input type="text" name="position" id="position" value="" />
						</div>
					</div>
					
					<!--==第二行==-->
					<div class="box2">
						<!--生日-->
						<div class="date">
							<p>Date of Birth</p>
							<input type="text" name="birthDate" id="birth" value="" />
						</div>
						<!--电话-->
						<div class="contact">
							<p>Contact number (Skype is preferred)</p>
							<input type="tel" name="phone" id="tel" value="" />
						</div>
					</div>
					
					<!--==第3行==-->
					<div class="box3">
						<!--性别-->
						<div class="sex">
							<p>Gender</p>
							<input type="radio" name="gender" id="sexman" value="man" />
							<label class="mel active" for="sexman">Male</label>
							<input type="radio" name="gender" id="sexwoman" value="woman" />
							<label class="fmel" for="sexwoman">Female</label>
							<input type="radio" name="gender" id="sexoths" value="woman" />
							<label class="otherMan" for="sexoths">Others</label>
						</div>
						<!--国籍-->
						<div class="citizen">
							<p>Citizenship</p>
							<input type="text" name="citizenship" id="" value="" />
						</div>
					</div>
					
					<!--==第4行==-->
					<div class="box4">
						<!--教育背景-->
						<div class="edu">
							<p>Education background (degree)</p>
							<input type="text" name="education" id="" value="" />
						</div>
						<!--主修-->
						<div class="major">
							<p>Major</p>
							<input type="text" name="major" id="major" value="" />
						</div>
					</div>
					
					<!--==第5行==-->
					<div class="box5">
						<!--xxxx-->
						<div class="obtain">
							<p>Country in which education has been obtained</p>
							<input type="text" name="country" id="obtain" value="" />
						</div>
						<!--学位-->
						<div class="degree">
							<p>Degree / Certifications</p>
							<input type="text" name="certification" id="degree" value="" />
						</div>
					</div>
					
					<!--==第6行==-->
					<div class="box6">
						<!--专业技能-->
						<div class="specical">
							<p>Specialized skills (if possible)</p>
							<div class="addBox">
								<input class="specialInput" type="text" name="specialized" id="" value="" />
								<span></span>
							</div>
						</div>
						<!--工作经验-->
						<div class="years">
							<i class="xia"></i>
							<p>Length of working experience</p>
							<input class="yearsNumber" type="text" name="experienceLength" id="" value="" readonly="readonly"/>
							<span class="numberSpan">
								<em>0</em>
								<em>1</em>
								<em>2</em>
								<em>3</em>
								<em>4</em>
								<em>5</em>
								<em>6</em>
								<em>7</em>
								<em>8</em>
								<em>9</em>
								<em>10</em>
								<em>10+</em>
							</span>
						</div>
					</div>
					<div class="moreSpecical">
						<input type="text" name="" id="" value="" />
						<input type="text" name="" id="" value="" />
					</div>
					<!--==第7行==-->
					<div class="box7">
						<!--工资-->
						<div class="money">
							<p>Expected monthly salary in U.S dollar</p>
							<input type="text" name="" id="" value="" />
						</div>
						<!--期望在中国的工作地点-->
						<div class="china">
							<p>Expected working location in China</p>
							<input type="text" name="" id="" value="" />
						</div>
					</div>
					
					
					<!-- 第8行  年龄段-->
					<div class="age">
						<p>Expected teaching age group of students.(Can be multiple choice)</p>
						
						<input type="radio" name="age" value="0-7" />
						<label for="age1"><em></em></label><span>0-7</span>
						
						<input type="radio" name="age" value="7-12" />
						<label for="age2"><em></em></label><span>7-12</span>
						
						<input type="radio" name="age" value="12-18" />
						<label for="age3"><em></em></label><span>12-18</span>
						
						<input type="radio" name="age" value="adults" />
						<label for="age4"><em></em></label><span>adults</span>
					</div>
					
					<!--第9行-->
					<!--建议-->
					<p class="others">Other positions of consideration</p>
					<textarea class="messageIt" name="otherPositions" rows="" cols=""></textarea>
					<div class="btb">
						<div class="Next">Save&Next</div>
						<div class="save">Save</div>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script src="${pageContext.request.contextPath }/static/js/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/resume/personalInformation.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/resume/resume-info.js"></script>
	
	<script type="text/javascript">
		resumeInfo.baseUrl = "${pageContext.request.contextPath }";
		
		$(".save").on("click",function(){
			resumeInfo.save(0);
		});
		
		$(".Next").on("click",function(){
			//resumeInfo.save(1);
			window.location.href = "${pageContext.request.contextPath }/upload/doc";
		});
		
		
	</script>
</html>

