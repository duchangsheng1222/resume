package com.resume.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.resume.dto.ResumeFile;
import com.resume.dto.ResumeInfo;
import com.resume.enums.FileType;
import com.resume.files.BaseFile;
import com.resume.files.CertificationFile;
import com.resume.files.FlightFile;
import com.resume.files.IntroductionVideoFile;
import com.resume.files.PhotoFile;
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
	
	@RequestMapping("/{resumeId}/doc")
	public String showUploadPage(Model model,@PathVariable("resumeId")Long resumeId){
		
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
	@RequestMapping(value="/files/list",method=RequestMethod.POST)
	public ResponseModel getFiles(Long resumeId){
		log.info("@ upload/files/list resumeId:{}",new Object[]{resumeId});
		BaseResponse resp = new BaseResponse();
		
		List<ResumeFile> docs = resumeFileService.getResumeFileByResumeIdAndType(resumeId, FileType.RESUME_DOC.getCode());
		List<ResumeFile> photos = resumeFileService.getResumeFileByResumeIdAndType(resumeId, FileType.PHOTO.getCode());
		
		docs.addAll(photos);
		resp.setData(docs);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}

	
	@RequestMapping(value="/resume",method=RequestMethod.POST)
	public String uploadResume(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/resume resumeId:{}",new Object[]{resumeId});
		if(null == resumeId){
			model.addAttribute("error", "resumeId is not be null");
			return "redirect:/upload/"+resumeId+"/doc";
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			model.addAttribute("error", "resume not exists");
			return "redirect:/upload/"+resumeId+"/doc";
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			model.addAttribute("error", "this is not your resume");
			return "redirect:/upload/"+resumeId+"/doc";
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("resumeFile");
			try {
				String filePath = generateResumeFile(filedata,FileType.RESUME_DOC);
				String fileName = iso2Utf8(filedata.getOriginalFilename());
				if (filePath != null) {
					saveFile(resumeId,fileName, user, filePath,FileType.RESUME_DOC);
				} else {
					model.addAttribute("error", "please choose resmue file");
					return "redirect:/upload/"+resumeId+"/doc";
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				model.addAttribute("error", "Resume upload failure");
				return "redirect:/upload/"+resumeId+"/doc";
			}
		}else{
			log.error("请求中不包含文件");
			model.addAttribute("error", "please choose resmue file");
			return "redirect:/upload/"+resumeId+"/doc";
		}	

		return "redirect:/upload/"+resumeId+"/doc";
	}
	
	@RequestMapping(value="/photo",method=RequestMethod.POST)
	public String uploadVideo(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/photo resumeId:{}",new Object[]{resumeId});
		if(null == resumeId){
			model.addAttribute("error", "resumeId is not be null");
			return "redirect:/upload/"+resumeId+"/doc";
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			model.addAttribute("error", "resume not exists");
			return "redirect:/upload/"+resumeId+"/doc";
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			model.addAttribute("error", "this is not your resume");
			return "redirect:/upload/"+resumeId+"/doc";
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("photo");
			try {
				String filePath = generateResumeFile(filedata,FileType.PHOTO);
				String fileName = iso2Utf8(filedata.getOriginalFilename());
				if (filePath != null) {
					saveFile(resumeId,fileName, user, filePath,FileType.PHOTO);
				} else {
					model.addAttribute("error", "please choose photo file");
					return "redirect:/upload/"+resumeId+"/doc";
				}
				
			} catch (IOException e) {
				log.error("简历上传失败", e);
				model.addAttribute("error", "photo upload failure");
				return "redirect:/upload/"+resumeId+"/doc";
			}
		}else{
			log.error("请求中不包含文件");
			model.addAttribute("error", "please choose photo file");
			return "redirect:/upload/"+resumeId+"/doc";
		}

		return "redirect:/upload/"+resumeId+"/doc";
	}
	
	@RequestMapping(value="/video",method=RequestMethod.POST)
	public String uploadPhoto(Model model, HttpServletRequest request,
			HttpServletResponse response,Long resumeId) {
		log.info("@ upload/video resumeId:{}",new Object[]{resumeId});
		if(null == resumeId){
			model.addAttribute("error","resumeId is not be null");
			model.addAttribute("resumeId", resumeId);
			return "redirect:/interview/page";
		}
		ResumeInfo resumeInfo = resumeService.getResumeById(resumeId);
		if(null == resumeInfo){
			model.addAttribute("error","resume not exists");
			model.addAttribute("resumeId", resumeId);
			return "redirect:/interview/page";
		}
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		if(user.getId() != resumeInfo.getCreatorId()){
			
			model.addAttribute("error","this is not your resume");
			model.addAttribute("resumeId", resumeId);
			return "redirect:/interview/page";
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("video");
			MultipartFile certificationFiledata = ((MultipartHttpServletRequest) request)
					.getFile("certification");
			if(null != filedata){
				try {
					String filePath = generateResumeFile(filedata,FileType.INTRODUCTION_VIDEO);
					String fileName = iso2Utf8(filedata.getOriginalFilename());
					if (filePath != null) {
						saveFile(resumeId,fileName, user, filePath,FileType.INTRODUCTION_VIDEO);
					} else {
						model.addAttribute("error","please choose video file");
						model.addAttribute("resumeId", resumeId);
						return "redirect:/interview/page";
					}
					
				} catch (IOException e) {
					log.error("简历上传失败", e);
					model.addAttribute("error","video upload failure");
					model.addAttribute("resumeId", resumeId);
					return "redirect:/interview/page";
				}
			}else if(null != certificationFiledata){
				try {
					String filePath = generateResumeFile(filedata,FileType.CERTIFICATION);
					String fileName = iso2Utf8(certificationFiledata.getOriginalFilename());
					if (filePath != null) {
						saveFile(resumeId,fileName, user, filePath,FileType.CERTIFICATION);
					} else {
						model.addAttribute("error","please choose certification file");
						model.addAttribute("resumeId", resumeId);
						return "redirect:/interview/page";
					}
					
				} catch (IOException e) {
					log.error("简历上传失败", e);
					model.addAttribute("error","certification upload failure");
					model.addAttribute("resumeId", resumeId);
					return "redirect:/interview/page";
				}
			}else{
				log.error("请求中不包含文件");
				model.addAttribute("error","please choose a file");
				model.addAttribute("resumeId", resumeId);
				return "redirect:/interview/page";
			}
		}else{
			log.error("请求中不包含文件");
			model.addAttribute("error","please choose a file");
			model.addAttribute("resumeId", resumeId);
			return "redirect:/interview/page";
		}
		
		return "redirect:/interview/page";
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
				String fileName = iso2Utf8(filedata.getOriginalFilename());
				if (filePath != null) {
					saveFile(resumeId,fileName, user, filePath,FileType.CERTIFICATION);
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
				String fileName = iso2Utf8(filedata.getOriginalFilename());
				if (filePath != null) {
					saveFile(resumeId,fileName, user, filePath,FileType.FLIGHT_TICKET);
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

	private void saveFile(Long resumeId, String fileName,User user, String filePath,FileType fileType) {
		com.resume.dto.ResumeFile fileDto = new com.resume.dto.ResumeFile();
		fileDto.setResumeId(resumeId);
		fileDto.setFileType(filePath.substring(filePath.lastIndexOf(".")+1));
		fileDto.setFileAddress(filePath);
		fileDto.setType(fileType.getCode());
		fileDto.setUserId(user.getId());
		fileDto.setFileName(fileName);
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
				resumeFile = new com.resume.files.ResumeFile(fileName);
				
			}else if(FileType.PHOTO.equals(fileType)){
				resumeFile = new PhotoFile(fileName);
				
			}else if(FileType.CERTIFICATION.equals(fileType)){
				resumeFile = new CertificationFile(fileName);
				
			}else if(FileType.FLIGHT_TICKET.equals(fileType)){
				resumeFile = new FlightFile(fileName);
				
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
