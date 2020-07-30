package com.spring.exam.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/login")
	public String loginPage(@RequestParam(required = false, name = "error", defaultValue = "false") String error,
							@RequestParam(required = false, name = "register", defaultValue = "") String register,
							Model model) {
		
		String errorMessage = null;
		String successMessage = null;
        if(error.equals("true")) {
            errorMessage = "Username hoặc password của bạn không đúng!";
        }
        if(register.equals("true")) {
        	successMessage = "Đăng kí thành công";
        } else if(register.equals("false")) {
        	errorMessage = "Đăng kí thất bại"; 
        } else {
        	;
        }
		
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);
		return "login";
	}
}
