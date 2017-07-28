package com.resume.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resume.common.Constant;
import com.resume.util.VerifyCodeUtils;

@Controller
@RequestMapping("/public/verify")
public class VerifyController {

	@RequestMapping("/code")
	public void generateVerifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 //鐢熸垚闅忔満瀛椾覆  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //瀛樺叆浼氳瘽session  
        HttpSession session = request.getSession(true);  
        session.setAttribute(Constant.SESSION_GENERATED_VERIFY_KEY, verifyCode.toLowerCase());  
        //鐢熸垚鍥剧墖  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  
	}
}
