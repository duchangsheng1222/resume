package com.resume.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.resume.dto.ResumeInfo;
import com.resume.response.BaseResponse;
import com.resume.response.ResumeResponse;
import com.resume.service.ResumeService;
import com.resume.spring.security.SecurityContextUtil;
import com.resume.spring.security.User;

@Controller
@RequestMapping("/resume")
public class ResumeController extends AbstractController{
	
	@Autowired
	private ResumeService resumeService;
	
	@RequestMapping("/page/add")
	public String showResumeInfo(){
		return "/resume/add";
	}
	
	@RequestMapping("/page/upload")
	public ModelAndView showResumeUpload(Long resumeId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/resume/upload");
		mav.addObject("resumeId", resumeId);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/{resumeId}/info",method=RequestMethod.GET)
	public BaseResponse getResumeInfo(@PathVariable("resumeId")Long resumeId){
		log.info("@ resume/info resumeId:{}",new Object[]{resumeId});
		ResumeResponse resp = new ResumeResponse();
		if(null == resumeId){
			return resp.fail("resume id cannot be null");
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			return resp.fail("resume is not exists");
		}
		resp.setResumeInfo(resumeInfo);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/list/{page}/{size}",method=RequestMethod.GET)
	public BaseResponse getResumeInfoList(@PathVariable("page")Integer page,@PathVariable("size")Integer size){
		log.info("@ resume/list page:{},size:{}",new Object[]{page,size});
		BaseResponse resp = new BaseResponse();
		if(null == page || null == size){
			return resp.fail("page and size cannot be null");
		}
		List<ResumeInfo> resumes = resumeService.getResumes(page, size);
		resp.setData(resumes);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public BaseResponse saveResumeInfo(ResumeInfo resumeInfo){
		log.info("@ resume/save resumeInfo:{}",new Object[]{resumeInfo});
		BaseResponse resp = new BaseResponse();
		User user = (User)SecurityContextUtil.getUserDetails();
		resumeInfo.setCreatorId(user.getId());
		resumeInfo.setUpdaterId(user.getId());
		resumeInfo.setCreateTime(new Date());
		resumeService.saveResumeInfo(resumeInfo);
		
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public BaseResponse updateResumeInfo(ResumeInfo resumeInfo){
		log.info("@ resume/update resumeInfo:{}",new Object[]{resumeInfo});
		BaseResponse resp = new BaseResponse();
		User user = (User)SecurityContextUtil.getUserDetails();
		resumeInfo.setUpdaterId(user.getId());
		resumeService.updateResumeInfo(resumeInfo);
		
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
		
	}
	

}
