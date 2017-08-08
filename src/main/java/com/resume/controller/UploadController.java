package com.resume.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.resume.dto.ResumeInfo;
import com.resume.enums.FileType;
import com.resume.files.BaseFile;
import com.resume.files.IntroductionVideoFile;
import com.resume.files.ResumeFile;
import com.resume.response.BaseResponse;
import com.resume.response.ResponseModel;
import com.resume.service.ResumeFileService;
import com.resume.service.ResumeService;
import com.resume.spring.security.SecurityContextUtil;
import com.resume.spring.security.User;

@Controller
@RequestMapping("/upload")
public class UploadController extends AbstractController{
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private ResumeFileService resumeFileService;
	
	@RequestMapping("/doc")
	public String showUploadPage(Model model,Long resumeId){
		
		model.addAttribute("resumeId", resumeId);
		return "/resume/upload_doc";
	}
	
//	@RequestMapping("/Video")
//	public String showUploadVideoPage(Model model,Long resumeId){
//		
//		model.addAttribute("resumeId", resumeId);
//		return "/resume/upload_doc";
//	}

	@ResponseBody
	@RequestMapping("/resume")
	public ResponseModel uploadResume(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/resume resumeId:{}",new Object[]{resumeId});
		BaseResponse resp = new BaseResponse();
		if(null == resumeId){
			return resp.fail("resumeId is not be null");
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			return resp.fail("resume not exists");
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			return resp.fail("this is not your resume");
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("resumeFile");
			try {
				String filePath = generateResumeFile(filedata,FileType.RESUME_DOC);
				if (filePath != null) {
					saveFile(resumeId, user, filePath,FileType.RESUME_DOC);
				} else {
					return resp.fail("please choose resmue file");
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				return resp.fail("Resume upload failure");
			}
		}else{
			log.error("请求中不包含文件");
			return resp.fail("please choose resmue file");
		}	

		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping("/video")
	public ResponseModel uploadVideo(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/video resumeId:{}",new Object[]{resumeId});
		BaseResponse resp = new BaseResponse();
		if(null == resumeId){
			return resp.fail("resumeId is not be null");
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			return resp.fail("resume not exists");
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			return resp.fail("this is not your resume");
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("video");
			try {
				String filePath = generateResumeFile(filedata,FileType.INTRODUCTION_VIDEO);
				if (filePath != null) {
					saveFile(resumeId, user, filePath,FileType.INTRODUCTION_VIDEO);
				} else {
					return resp.fail("please choose video file");
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				return resp.fail("video upload failure");
			}
		}else{
			log.error("请求中不包含文件");
			return resp.fail("please choose video file");
		}

		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping("/photo")
	public ResponseModel uploadPhoto(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/photo resumeId:{}",new Object[]{resumeId});
		BaseResponse resp = new BaseResponse();
		if(null == resumeId){
			return resp.fail("resumeId is not be null");
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			return resp.fail("resume not exists");
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			return resp.fail("this is not your resume");
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("photo");
			try {
				String filePath = generateResumeFile(filedata,FileType.PHOTO);
				if (filePath != null) {
					saveFile(resumeId, user, filePath,FileType.PHOTO);
				} else {
					return resp.fail("please choose photo file");
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				return resp.fail("photo upload failure");
			}
		}else{
			log.error("请求中不包含文件");
			return resp.fail("please choose photo file");
		}
		
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping("/certification")
	public ResponseModel uploadCertification(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/certification resumeId:{}",new Object[]{resumeId});
		BaseResponse resp = new BaseResponse();
		if(null == resumeId){
			return resp.fail("resumeId is not be null");
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			return resp.fail("resume not exists");
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			return resp.fail("this is not your resume");
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("certification");
			try {
				String filePath = generateResumeFile(filedata,FileType.CERTIFICATION);
				if (filePath != null) {
					saveFile(resumeId, user, filePath,FileType.CERTIFICATION);
				} else {
					return resp.fail("please choose certification file");
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				return resp.fail("certification upload failure");
			}
		}else{
			log.error("请求中不包含文件");
			return resp.fail("please choose certification file");
		}
		
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping("/ticket")
	public ResponseModel uploadFlightTicket(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/certification resumeId:{}",new Object[]{resumeId});
		BaseResponse resp = new BaseResponse();
		if(null == resumeId){
			return resp.fail("resumeId is not be null");
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			return resp.fail("resume not exists");
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			return resp.fail("this is not your resume");
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("flightTicket");
			try {
				String filePath = generateResumeFile(filedata,FileType.FLIGHT_TICKET);
				if (filePath != null) {
					saveFile(resumeId, user, filePath,FileType.FLIGHT_TICKET);
				} else {
					return resp.fail("please choose flight ticket file");
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				return resp.fail("flight ticket upload failure");
			}
		}else{
			log.error("请求中不包含文件");
			return resp.fail("please choose flight ticket file");
		}
		
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}

	private void saveFile(Long resumeId, User user, String filePath,FileType fileType) {
		com.resume.dto.ResumeFile fileDto = new com.resume.dto.ResumeFile();
		fileDto.setResumeId(resumeId);
		fileDto.setFileType(filePath.substring(filePath.lastIndexOf(".")+1));
		fileDto.setFileAddress(filePath);
		fileDto.setType(fileType.getCode());
		fileDto.setUserId(user.getId());
		resumeFileService.saveResumeFile(fileDto);
	}
	
	private String generateResumeFile(MultipartFile filedata,FileType fileType)
			throws IOException {
		if (filedata != null) {
			String fileName = iso2Utf8(filedata.getOriginalFilename());
			BaseFile resumeFile = null;
			if(FileType.INTRODUCTION_VIDEO.equals(fileType)){
				resumeFile = new IntroductionVideoFile(fileName);
			}else if(FileType.RESUME_DOC.equals(fileType)){
				resumeFile = new IntroductionVideoFile(fileName);
				
			}else if(FileType.PHOTO.equals(fileType)){
				resumeFile = new IntroductionVideoFile(fileName);
				
			}else if(FileType.CERTIFICATION.equals(fileType)){
				resumeFile = new IntroductionVideoFile(fileName);
				
			}else if(FileType.FLIGHT_TICKET.equals(fileType)){
				resumeFile = new IntroductionVideoFile(fileName);
				
			}
			if(null != resumeFile){
				String filePath = resumeFile.saveFile(filedata
						.getInputStream());
				
				return filePath;
			}
		}
		return null;
	}
	
	public String iso2Utf8(String str){
		String utf8Str;
		try {
			utf8Str = new String(str.getBytes("ISO8859-1"),"UTF8");
		} catch (UnsupportedEncodingException e) {
			log.error("upload file error",e);
			return str;
		}
		return utf8Str;
	}
}
