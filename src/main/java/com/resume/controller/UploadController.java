package com.resume.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.resume.response.BaseResponse;
import com.resume.response.ResponseModel;
import com.resume.spring.security.SecurityContextUtil;
import com.resume.spring.security.User;

@Controller
@RequestMapping("/upload")
public class UploadController extends AbstractController{

	@ResponseBody
	@RequestMapping("/resume")
	public ResponseModel uploadIcon(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = (String) request.getAttribute("userId");

		log.info("@ upload/resume ");
		BaseResponse resp = new BaseResponse();
		//获取当前登录用户
		User user = (User)SecurityContextUtil.getUserDetails();
		try {
			String iconPath = generateTeacherMedia(request);
			if (iconPath != null) {
				// 更新教师信息头像字段
//				teacherInfoService.updateTeacherIcon(userId, iconPath);
//				resp.setImgPath(iconPath);
			} else {
				return resp.fail("头像上传失败");
			}

		} catch (IOException e) {
			log.error("头像上传失败", e);
			return resp.fail("头像上传失败");
		}

		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}

	/**
	 * 生成学生头像图片
	 * 
	 * @param request
	 * @param userId
	 * @param schoolId
	 * @param studentNumber
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	private String generateTeacherMedia(HttpServletRequest request)
			throws IOException {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile filedata = ((MultipartHttpServletRequest) request)
					.getFile("icon");
			if (filedata != null) {

				String fileName = filedata.getOriginalFilename();
//				BaseVpsFile teacherIcon = new TeacherIconFile(fileName);
//				String filePath = teacherIcon.saveFile(filedata
//						.getInputStream());

				return "";
			}
		}
		return null;
	}
}
