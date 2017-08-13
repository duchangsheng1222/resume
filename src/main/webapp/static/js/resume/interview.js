

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
						var resumeDown = "<a href='#'>download</a>";
						var videoDown = "<a href='#'>download</a>";
						var message = "<a href='#'>message</a>";
						datas[i] = new Array(interview.resumeId,checkNull(interview.resumeInfo.name),
								checkNull(interview.resumeInfo.age),
								checkNull(interview.resumeInfo.gender),
								checkNull(interview.resumeInfo.phone),
								checkNull(interview.resumeInfo.email),
								checkNull(interview.resumeInfo.citizenship),
								checkNull(interview.resumeInfo.cducation),
								checkNull(interview.resumeInfo.country),
								
								resumeDown,videoDown,message,
//								"<input type='checkbox' name='backword' value='"+interview.resumeId+"'/>",
//								"<input type='checkbox' name='forword' value='"+interview.resumeId+"'/>",
								"<a class='more' href='javascript:void(0);' onclick='backward("+interview.resumeId+","+interview.step+")' >backward</a>",
								"<a class='more' href='javascript:void(0);' onclick='forward("+interview.resumeId+","+interview.step+")' >forward</a>",
								"<a class='more' href='javascript:void(0);' onclick='showMoreInfo("+interview.resumeId+")' >more</a>");
						
					}
					fillTable(datas);
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
	forward : function(resumeId,step){
		$.ajax({
			url:interview.baseUrl + "/interview/"+ resumeId +"/"+step+"/update",
			type:"PUT",
			dataType:"json",
			data:{},
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

function backward(resumeId,step){
	interview.forward(resumeId, step-1);
}

function forward(resumeId,step){
	interview.forward(resumeId, step+1);
	
}

function checkNull(val){
	if(null == val){
		return "";
	}
	return val;
}

function showMoreInfo(resumeId){
	//TODO show info
	
}