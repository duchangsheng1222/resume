package com.resume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resume.dto.UserInfo;
import com.resume.enums.BaseRoleType;
import com.resume.response.BaseResponse;
import com.resume.response.ResponseModel;
import com.resume.service.UserService;
import com.resume.spring.security.SecurityContextUtil;
import com.resume.spring.security.User;

@Controller
@RequestMapping("user")
public class AdminUserController extends AbstractController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/page")
	public String showListUser(){
		User user = (User)SecurityContextUtil.getUserDetails();
		if(BaseRoleType.EMPLOYEE.getCode().equals(user.getRole()) ||
				BaseRoleType.ADMIN.getCode().equals(user.getRole())){
			
			return "/manage/userList";
		}
		
		return "redirect:/interview/page";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ResponseModel listUsers(){
		log.info("@ listUsers");
		BaseResponse resp = new BaseResponse();
		List<UserInfo> listUsers = userService.listUsers();
		resp.setData(listUsers);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/set/emp",method=RequestMethod.POST)
	public ResponseModel setEmp( String email){
		BaseResponse resp = new BaseResponse();
		userService.setEmployee(email);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/set/{userId}/{locked}/status",method=RequestMethod.PUT)
	public ResponseModel setStatus(@PathVariable("userId") Long userId,@PathVariable("locked") String locked){
		BaseResponse resp = new BaseResponse();
		userService.lockUser(userId, locked);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{userId}",method=RequestMethod.DELETE)
	public ResponseModel delete(@PathVariable("userId") Long userId){
		BaseResponse resp = new BaseResponse();
		userService.deleteUser(userId);
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
	}

}
