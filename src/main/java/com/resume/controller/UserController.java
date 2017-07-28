package com.resume.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resume.common.MailSender;
import com.resume.service.UserService;
import com.resume.service.exception.UserException;
import com.resume.service.response.BaseResponse;
import com.resume.service.response.ResponseModel;
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

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(String email,String password){
		log.info("@ register email:{},password:{}",new Object[]{email,password});
		String encodePassword = passwordEncoder.encodePassword(password);
		try {
			userService.register(email, encodePassword);
		} catch (UserException e) {
			log.error(e.getMessage());
			return "redirect:/register.jsp";
		}
		return "redirect:/login.jsp";
		
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
			return new BaseResponse<String>().fail("发送验证码失败");
		}
		
		return new BaseResponse<String>().success(BaseResponse.SUCCESS_MESSAGE);
	}
	
	@ResponseBody
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public ResponseModel resetPassword(HttpServletRequest request,String email,String password,String verifyCode){
		log.info("@ resetPassword email:{},password:{},verifyCode:{}",new Object[]{email,password,verifyCode});
		try {
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("emailVerifyCode");
			if(!verifyCode.equalsIgnoreCase(code)){
				
			}
			String encodePassword = passwordEncoder.encodePassword(password);
			userService.resetPassword(email, encodePassword);
		} catch (Exception e) {
			log.error("发送验证码失败 "+e.getMessage(),e);
			return new BaseResponse<String>().fail("发送验证码失败");
		}
		
		return new BaseResponse<String>().success(BaseResponse.SUCCESS_MESSAGE);
	}
}
