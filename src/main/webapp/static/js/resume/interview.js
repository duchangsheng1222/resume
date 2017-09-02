

var interview = {
	baseUrl : "",
	currentStep : 1,
	URL : {
		save : "",
		update : "",
		list : "/interview/flow/list",
		
	},
	save : function(){
		$.ajax({
			url:interview.URL.save,
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
	
	update : function(){
		$.ajax({
			url:interview.URL.update,
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
	
	findByResumeId : function(resumeId,stepHeightArray){
		$.ajax({
			url:interview.baseUrl + "/interview/"+ resumeId +"/status",
			type:"get",
			dataType:"json",
			data:{},
			success : function(data){
				if(data.status == 1){
					var flow = data.interviewFlow;
					var step = flow.step;
					var nowHeight= stepHeightArray[0];
					for (var i = 0; i < step && step < stepHeightArray.length; i++) {
						var height = stepHeightArray[i+1];
						nowHeight += height;
					}
					$('.now').height(nowHeight);
					
					
				}else{
					alert(data.message);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	},
	list : function(fillTable){
		$.ajax({
			url: interview.baseUrl+interview.URL.list,
			type:"post",
			dataType:"json",
			data:{step:interview.currentStep},
			success : function(data){
				if(data.status == 1){
					var datas = new Array();
					for (var i = 0; i < data.data.length; i++) {
						var interview = data.data[i];
						list.header=new Array("Name","<span >Age</span>","Gender","Skype","Email address",
								"Citizenship","Education","Education country",
								"Resume","Video","Message","Backward","Forward","");
						
						var resumeDown = "<a href='javascript:void(0);' data-reveal-id='myDownload' data-animation='none'>download new</a>";
						if(null != interview.resumeInfo.resumeFiles){
							for (var j = 0; j < interview.resumeInfo.resumeFiles.length; j++) {
								var file = interview.resumeInfo.resumeFiles[j];
								$("#myDownload").append("<p><a href='#'>"+file.fileName+"</a></p>");
							}
							if(interview.resumeInfo.resumeFiles.length > 1){
								resumeDown = "<a href='javascript:void(0);' data-reveal-id='myDownload'>download new</a>";
							}else{
								resumeDown = "<a href='javascript:void(0);'  data-reveal-id='myDownload'>download</a>";
							}
						}
						
						var videoDown = "-";
						if(null != interview.video){
							if("0" == interview.video.downloaded){
								
								videoDown = "<a href='javascript:void(0);' style='color:blue;' onclick='download(\'video_"+interview.resumeId+"\',\'"+
										interview.video.fileAddress+"\');' id='video_"+interview.resumeId+"'>download new</a>";
							}else{
								videoDown = "<a href='javascript:void(0);' onclick='download(\'video_"+interview.resumeId+"\',\'"+interview.video.fileAddress+"\');' id='video_"+interview.resumeId+"'>download</a>";
							}
							
						}
						
						
						
						var message = "<a href='#'>message</a>";
						datas[i] = new Array(interview.resumeId,checkNull(interview.resumeInfo.name),
								checkNull(interview.resumeInfo.age),
								checkNull(interview.resumeInfo.gender),
								checkNull(interview.resumeInfo.phone),
								checkNull(interview.resumeInfo.userInfo.email),
								checkNull(interview.resumeInfo.citizenship),
								checkNull(interview.resumeInfo.cducation),
								checkNull(interview.resumeInfo.country),
								
								resumeDown,videoDown,message,
//								"<input type='checkbox' name='backword' value='"+interview.resumeId+"'/>",
//								"<input type='checkbox' name='forword' value='"+interview.resumeId+"'/>",
								"<a class='more' href='javascript:void(0);' onclick='backward("+interview.resumeId+","+interview.step+")' >backward</a>",
								"<a class='more' href='javascript:void(0);' onclick='forward("+interview.resumeId+","+interview.step+")' >forward</a>",
								"<a class='more' href='javascript:void(0);' onclick='showMoreInfo("+interview.resumeId+","+interview.step+")' >more</a>");
						
					}
					fillTable(datas);
					
					$('a[data-reveal-id]').on('click', function(e) {
						e.preventDefault();
						var modalLocation = $(this).attr('data-reveal-id');
						$('#'+modalLocation).reveal($(this).data());
					});

				}else{
					alert(data.message);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	},
	
	updateStatus : function(resumeId,step){
		$.ajax({
			url:interview.baseUrl + "/interview/"+ resumeId +"/"+step+"/update",
			type:"PUT",
			dataType:"json",
			data:{},
			success : function(data){
				if(data.status == 1){
					var flow = data.interviewFlow;
					var step = flow.step;
					var nowHeight= 0;
					for (var i = 0; i < step; i++) {
						var height = stepHeightArray[i];
						nowHeight += height;
					}
					$('.now').height(nowHeight);
				}else{
					alert(data.message);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	},
	forward : function(resumeId,step,decline){
		var data = {};
		if(decline){
			data.prototype.decline = decline;
		}
		$.ajax({
			url:interview.baseUrl + "/interview/"+ resumeId +"/"+step+"/update",
			type:"PUT",
			dataType:"json",
			data:data,
			success : function(data){
				if(data.status == 1){
					interview.list(fillTable);
				}else{
					alert(data.message);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
		    }
		});
	}
	
	
	
};

function download(elementId,fileAddress){
	$("#"+elementId).html("download");
	$("#"+elementId).css("color","black");
//	window.open(interview.baseUrl );
}

function backward(resumeId,step){
	interview.forward(resumeId, step-1);
}

function forward(resumeId,step){
	interview.forward(resumeId, step+1);
	
}

function accepted(decline,resumeId){
	forward()
}

function checkNull(val){
	if(null == val){
		return "";
	}
	return val;
}

function showMoreInfo(resumeId,step){
	//TODO show info
	
	$.ajax({
		url:interview.baseUrl + "/info/"+resumeId +"/info",
		type:"get",
		dataType:"json",
		data:{},
		success : function(data){
			if(data.status == 1){
				var resume = data.resumeInfo;
				$("#d_name").html(checkNull(resume.name));
				$("#d_position").html(checkNull(resume.position));
				$("#d_birthDate").html(checkNull(resume.birthDate));
				$("#d_phone").html(checkNull(resume.phone));
				$("#d_citizenship").html(checkNull(resume.citizenship));
				$("#d_education").html(checkNull(resume.education));
				$("#d_major").html(checkNull(resume.major));
				$("#d_county").html(checkNull(resume.country));
				$("#d_certification").html(checkNull(resume.certification));
				$("#d_specialized").html(checkNull(resume.specialized));
				$("#d_experienceLength").html(checkNull(resume.experienceLength));
				$("#d_salary").html(checkNull(resume.salary));
				$("#d_location").html(checkNull(resume.location));
				$("#d_otherPositions").html(checkNull(resume.otherPositions));
				$("#d_age").html(checkNull(resume.age));
				if("m" == checkNull(resume.gender)){
					$("#d_gender").html("Male");
				}else if("f" == checkNull(resume.gender)){
					$("#d_gender").html("Fmale");
				}if("" == checkNull(resume.gender)){
					$("#d_gender").html("Other");
				} 
				
				$("#pre").on('click',function(){
					backward(resumeId, step);
				});
				$("#clo").on('click',function(){
					forward(resumeId,step);
				});
			}else{
				alert(data.message);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(XMLHttpRequest.status+"-"+XMLHttpRequest.readyState + "-" + textStatus);
	    }
	});
	
	$('#myInfo').reveal();
}


function proccessStatus(step){
	switch (step) {
	case 1:
		
		;
	case 2:
		
		;
	case 3:
		
		;
	case 4:
		
		;
	case 5:
		
		;
	case 6:
		
		;
	case 7:
		
		;
	case 8:
		
		;
	case 9:
		
		;
	case 10:
		
		;
	case 11:
		
		;
	case 12:
		
		;
	case 13:
		
		;
	case 14:
		
		;

	default:
		break;
	}
}






