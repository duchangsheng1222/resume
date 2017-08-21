<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>	
<!DOCTYPE html>
<html>
	<head>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/status.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery/jquery-3.0.0.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/resume/interview.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/resume/status.js"></script>
		<meta charset="UTF-8">
		<title>Status</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/js/layui/layui.css"/>
	<script type="text/javascript">
	$(function(){
		//获取上边进度条的高度	
/* 		if(true){
			var nowHeight= parseInt($('.one').height()+$('.two').height()+$('.three').height());
			$('.now').height(nowHeight);
		}
		
 */		
 		interview.baseUrl = "${pageContext.request.contextPath }";
 
		var stepHeightArray = [ parseInt($('.one').height()), parseInt($('.two').height()), parseInt($('.three').height()), parseInt($('.four').height()),
				 parseInt($('.five').height()), parseInt($('.six').height()),
						 parseInt($('.seven').height()), parseInt($('.eight').height()),
								 parseInt($('.nine').height()), parseInt($('.ten').height()), parseInt($('.eleven').height()),
										 parseInt($('.twelve').height()),parseInt($('.thirteen').height()),parseInt($('.fourteen').height())];
		var resumeId = "${resumeId}";
		interview.findByResumeId(resumeId, stepHeightArray);
		//第12步、13步的日历
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			var start = {
				min: laydate.now()
				,max: '2099-06-16 23:59:59'
				,istoday: false
				,choose: function(datas){
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
				}
			};
			var end = {
				 min: laydate.now()
				 ,max: '2099-06-16 23:59:59'
				 ,istoday: false
				 ,choose: function(datas){
				  start.max = datas; //结束日选好后，重置开始日的最大日期
				 }
			};

			
			$('#up12').on('click',function(){
				start.elem = this;
		 		laydate(start);
			})
			$('#up13').on('click',function(){
				start.elem = this;
		 		laydate(start);
			})
		})
		
		//第3步的上传文件
		$('#up1').change(function(){
			var filePath=$(this).val();
	        var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
			$('#up1Label').append("&nbsp;&nbsp;&nbsp;"+fileName);
		})
		
		$('#up2').change(function(){
			var filePath=$(this).val();
	        var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
			$('#up2Label').append("&nbsp;&nbsp;&nbsp;"+fileName);
		})
		
		//第13步上传文件
		$('#up13Input').change(function(){
			console.log(1)
			var filePath=$(this).val();
	        var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
			$('#up13label').append("&nbsp;&nbsp;&nbsp;"+fileName);
		});
		
		$('#uploadVideoBtn').click(function(){
			$("#threeForm").submit();
		});
		
		$('#uploadCerBtn').click(function(){
			$("#threeForm").submit();
		});
		
		
	});
	
	function uploadVideo(){
		
	}
	
	function toInfoPage(resumeId){
		window.location.href = "${pageContext.request.contextPath }/info/page/add?resumeId="+resumeId;
	}
	
	function toUploadPage(resumeId){
		window.location.href = "${pageContext.request.contextPath }/upload/"+resumeId+"/doc";
		
	}

	</script>
	</head>
	<body>
		<div class="box">
			<!--导航条-->
			<div class="nav">
				<a href="/index.jsp" class="homeA">Home</a><a href="javascript;">-</a><a href="login.html" class="loginA">Status</a>
			</div>
			<!--右上角 信封-->
			<div class="messRight">
				<div class="up"></div>
				<p class="down">Message</p>
			</div>
			
			
			<!--以中间棍为基础-->
			<div class="lineCenter">
				<!--最上边的进度条-->
				<div class="now"></div>
				<!--第1步-->
				<div class="step one">
					<div class="dot"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">01<i></i></span>
						<em>
							<i>Applications submitted</i>
							<i class="i2">Waiting for screening</i>
							<input type="button" name="" onclick="toInfoPage(${resumeId});" id="" value="Update Personal Information" />
							<input type="button" id="resumeFile" onclick="toUploadPage(${resumeId});" value="Update Resume and Photo"/>
						</em>
					</div>
				</div>
				
				<!--第2步-->
				<div class="step two">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">02<i></i></span><em>Resume in screening process</em>
					</div>
				</div>
				
				<!--第3步-->
				<div class="step three">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">03<i></i></span>
						<form id="threeForm" action="${pageContext.request.contextPath }/upload/video" method="post"  enctype="multipart/form-data">
						<em>
							<i>Submit introduction video</i>
								<i class="i2"><i class="i21">Submit introduction video </i>
								<input type="hidden" name="resumeId" value="${resumeId }">
								<input type="file" name="video" id="up1" value="upload" /><label id="up1Label" for="up1"></label><span id="uploadVideoBtn">upload</span></i>
								
								<i class="i2"><i class="i22">Submit certificates</i> <input type="file" name="certificates" id="up2" value="upload" /><label  id="up2Label" for="up2"></label><span  id="uploadCerBtn">upload</span></i>
						</em>
						</form>
					</div>
				</div>
				
				<!--第4步-->
				<div class="step four">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">04<i></i></span><em>Video received</em>
					</div>
				</div>
				
				<!--第5步-->
				<div class="step five">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">05<i></i></span><em>Waiting for 1st interview invitation</em>
					</div>
				</div>
				
				<!--第6步-->
				<div class="step six">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">06<i></i></span><em>Waiting for 1st interview result</em>
					</div>
				</div>
				
				<!--第7步-->
				<div class="step seven">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">07<i></i></span><em>Waiting for 2nd interview invitation</em>
					</div>
				</div>
				
				<!--第8步-->
				<div class="step eight">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">08<i></i></span><em>Waiting for 2nd interview result</em>
					</div>
				</div>
				
				<!--第9步-->
				<div class="step nine">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">09<i></i></span>
						<em>
							<i>Waiting for the job offer</i>
							<i class="i2">Last expected date of arrival to China 20xx/month/date</i>
							<input type="button" name="" id="" value="Accept the job offer" /><a href="#">Decline</a>
						</em>
					</div>
				</div>
				
				<!--第10步-->
				<div class="step ten">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">10<i></i></span>
						<em>
							<i>Offer accepted</i>
							<i>Count down 68 days</i>
							<i class="i2">Preparing visa material</i>
						</em>
					</div>
				</div>
				
				<!--第11步-->
				<div class="step eleven">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">11<i></i></span>
						<em>
							<i>Visa materia submitted</i>
							<i class="i2">Visa is being processed</i>
						</em>
					</div>
				</div>
				
				<!--第12步-->
				<div class="step twelve">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">12<i></i></span>
						<em>
							<i>Visa succeed</i>
							<i class="i2"><i class="i21">Visa received date:</i><input type="text" name="" id="up12" value="" /><span>Received visa</span></i>
						</em>
					</div>
				</div>
				
				<!--第13步-->
				<div class="step thirteen">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">13<i></i></span>
						<em>
							<i>Determine China arrival date</i>
							<i class="i2"><i class="i22">Fight ticket image upload</i> <input type="file" name="" id="up13Input" value="upload" /><label id="up13label" for="up13Input"></label><span>upload</span></i>
							<i class="i2 i3"><i class="i21">Fight landing place and date</i><input type="text" name="" id="up13" value="" /><span>Submit</span></i>
						</em>
					</div>
				</div>
				
				<!--第14步-->
				<div class="step fourteen">
					<div class="dot"></div>
					<div class="ban"></div>
					<div class="icon iconBan"></div>
					<div class="rightDetail">
						<div class="gou"></div><span class="num">14<i></i></span><em>Completion of settle down, congratulations!</em>
					</div>
				</div>
				
				<!--底部白条-->
				<div class="step last"></div>
			</div>
		</div>
	</body>
	
	</html>
	