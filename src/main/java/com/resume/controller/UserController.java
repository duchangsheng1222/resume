package com.resume.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.resume.common.MailSender;
import com.resume.dto.UserInfo;
import com.resume.enums.BaseRoleType;
import com.resume.response.BaseResponse;
import com.resume.response.LoginResponse;
import com.resume.response.ResponseModel;
import com.resume.service.UserService;
import com.resume.service.exception.UserException;
import com.resume.spring.security.StandardPasswordEncoder;
import com.resume.util.VerifyCodeUtils;

@Controller
@RequestMapping("/public/user")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private StandardPasswordEncoder passwordEncoder;
	
	@Resource
	private MailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@Value("${verifyMailTitle}")
	private String verifyMailTitle;
	
	@RequestMapping(value="/reg/page",method=RequestMethod.GET)
	public String showRegisterPage(){
		return "/register";
	}
	
	@RequestMapping(value="/forgot/page",method=RequestMethod.GET)
	public String showForgotPage(){
		return "/forgot";
	}

	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseModel register(String email,String password,String role,RedirectAttributes attr){
		log.info("@ register email:{},password:{}",new Object[]{email,password});
		LoginResponse resp = new LoginResponse();
		String encodePassword = passwordEncoder.encodePassword(password);
		try {
			if(StringUtils.isEmpty(role)){
				role = BaseRoleType.MEMEBER.getCode();
			}
			userService.register(email, encodePassword,role);
		} catch (UserException e) {
			log.error(e.getMessage());
			return resp.fail(e.getMessage());
		}
		resp.setUsername(email);
		resp.setPassword(password);
		resp.setSkip("1");
		return resp.success(BaseResponse.SUCCESS_MESSAGE);
		
	}
	
	/**
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendVerifyCode",method=RequestMethod.POST)
	public ResponseModel sendVerifyCode(HttpServletRequest request,String email){
		log.info("@ sendVerifyCode email:{}",new Object[]{email});
		
		try {
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			
			HttpSession session = request.getSession();
			session.setAttribute("emailVerifyCode", verifyCode);
			
			mailSender.sendMail(email, verifyMailTitle, verifyCode);
		} catch (Exception e) {
			log.error("发送验证码失败 "+e.getMessage(),e);
			return new BaseResponse().fail("Failed to send verification code");
		}
		
		return new BaseResponse().success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/checkVerifyCode",method=RequestMethod.POST)
	public ResponseModel checkVerifyCode(HttpServletRequest request,String email,String emailVerifyCode,String verifyCode){
		log.info("@ resetPassword email:{},emailVerifyCode:{},verifyCode:{}",new Object[]{email,emailVerifyCode,verifyCode});
		try {
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("emailVerifyCode");
			if(!emailVerifyCode.equalsIgnoreCase(code)){
				return new BaseResponse().fail("verification code error");
			}
			String sessionVerifyCode = (String)session.getAttribute("verifyCode");
			if(!verifyCode.equals(sessionVerifyCode)){
				return new BaseResponse().fail("verification code error");
			}
		} catch (Exception e) {
			log.error("发送验证码失败 "+e.getMessage(),e);
			return new BaseResponse().fail("Failed to send verification code");
		}
		
		return new BaseResponse().success(BaseResponse.SUCCESS_MESSAGE);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public ResponseModel resetPassword(HttpServletRequest request,String email,String password,String verifyCode){
		log.info("@ resetPassword email:{},password:{},verifyCode:{}",new Object[]{email,password,verifyCode});
		try {
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("emailVerifyCode");
			if(!verifyCode.equalsIgnoreCase(code)){
				return new BaseResponse().fail("verification code error");
			}
			String encodePassword = passwordEncoder.encodePassword(password);
			userService.resetPassword(email, encodePassword);
		} catch (Exception e) {
			log.error("发送验证码失败 "+e.getMessage(),e);
			return new BaseResponse().fail("Failed to send verification code");
		}
		
		return new BaseResponse().success(BaseResponse.SUCCESS_MESSAGE);
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
}
