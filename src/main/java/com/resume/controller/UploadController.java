package com.resume.controller;

import java.io.IOException;

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
		try {
			String iconPath = generateResumeDoc(request);
			if (iconPath != null) {
				com.resume.dto.ResumeFile fileDto = new com.resume.dto.ResumeFile();
				fileDto.setResumeId(resumeId);
				fileDto.setFileType(iconPath.substring(iconPath.lastIndexOf(".")+1));
				fileDto.setFileAddress(iconPath);
				fileDto.setType(FileType.RESUME_DOC.getCode());
				fileDto.setUserId(user.getId());
				resumeFileService.saveResumeFile(fileDto);
			} else {
				return resp.fail("please choose resmue file");
			}

		} catch (IOException e) {
			log.error("简历上传失败", e);
			return resp.fail("简历上传失败");
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
		try {
			String iconPath = generateResumeVideo(request);
			if (iconPath != null) {
				com.resume.dto.ResumeFile fileDto = new com.resume.dto.ResumeFile();
				fileDto.setResumeId(resumeId);
				fileDto.setFileType(iconPath.substring(iconPath.lastIndexOf(".")+1));
				fileDto.setFileAddress(iconPath);
				fileDto.setType(FileType.INTRODUCTION_VIDEO.getCode());
				fileDto.setUserId(user.getId());
				resumeFileService.saveResumeFile(fileDto);
			} else {
				return resp.fail("please choose resmue file");
			}

		} catch (IOException e) {
			log.error("简历上传失败", e);
			return resp.fail("简历上传失败");
		}

		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	private String generateResumeDoc(HttpServletRequest request)
			throws IOException {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("icon");
			if (filedata != null) {

				String fileName = filedata.getOriginalFilename();
				BaseFile resumeDoc = new ResumeFile(fileName);
				String filePath = resumeDoc.saveFile(filedata
						.getInputStream());

				return filePath;
			}
		}
		return null;
	}
	
	private String generateResumeVideo(HttpServletRequest request)
			throws IOException {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("icon");
			if (filedata != null) {

				String fileName = filedata.getOriginalFilename();
				BaseFile resumeVideo = new IntroductionVideoFile(fileName);
				String filePath = resumeVideo.saveFile(filedata
						.getInputStream());

				return filePath;
			}
		}
		return null;
	}
}
