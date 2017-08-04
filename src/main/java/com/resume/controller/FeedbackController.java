package com.resume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resume.dto.Feedback;
import com.resume.response.BaseResponse;
import com.resume.response.ResponseModel;
import com.resume.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends AbstractController{

	@Autowired
	private FeedbackService feedbackService;
	
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseModel addFeedback(Feedback feedback){
		log.info("@ /feedback/add feedback:{}",new Object[]{feedback});
		BaseResponse resp = new BaseResponse();
		feedbackService.saveFeedback(feedback);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public ResponseModel list(Integer page,Integer size){
		log.info("@ /feedback/list page:{},size:{}",new Object[]{page,size});
		BaseResponse resp = new BaseResponse();
		List<Feedback> feedbacks = feedbackService.getFeedback(page, size);
		resp.setData(feedbacks);
		return resp;
		
	}
}
