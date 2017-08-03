package com.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resume.dto.InterviewFlow;
import com.resume.response.BaseResponse;
import com.resume.response.InterviewResponse;
import com.resume.response.ResponseModel;
import com.resume.service.InterviewFlowService;
import com.resume.spring.security.SecurityContextUtil;
import com.resume.spring.security.User;

@Controller
@RequestMapping("/interview")
public class InterviewFlowController extends AbstractController{
	
	@Autowired
	private InterviewFlowService interviewFlowService;

	@RequestMapping("/page")
	public String showInterviewPage(Model model,Long resumeId){
		log.info("@ interview/page resumeId:{}",new Object[]{resumeId});
		model.addAttribute("resumeId", resumeId);
		return "/interview/page";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/{resumeId}/{step}/update",method=RequestMethod.PUT)
	public ResponseModel updateInterview(Integer step,Long id){
		log.info("@ interview/page id:{}",new Object[]{id});
		BaseResponse resp = new BaseResponse();
		if(null == step || null == id){
			return resp.fail("Step and id cannot be null");
		}
		InterviewFlow flow = interviewFlowService.findById(id);
		if(flow.getStep() >= step){
			return resp.fail("You have completed this step");
		}
		
		User user = (User)SecurityContextUtil.getUserDetails();
		InterviewFlow interviewFlow = new InterviewFlow();
		interviewFlow.setUpdaterId(user.getId());
		interviewFlow.setStep(step);
		interviewFlow.setId(id);
		interviewFlowService.updateFlowStatus(interviewFlow);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/{resumeId}/status",method=RequestMethod.GET)
	public ResponseModel interviewStatus(Long resumeId){
		log.info("@ interview/status resumeId:{}",new Object[]{resumeId});
		InterviewResponse resp = new InterviewResponse();
		if(null == resumeId){
			return resp.fail("resumeId cannot be null");
		}
		InterviewFlow flow = interviewFlowService.findByResumeId(resumeId);
		resp.setInterviewFlow(flow);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	
	
}