package com.spring.exam.sys.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value="/register-form")
	public String registerForm(Model model) {
		
		UserInfo newUser = new UserInfo();
		newUser.setRole_id(1);
		model.addAttribute("newUser", newUser);
		return "register";
	}
	
	@PostMapping(value="/register")
	public String register(@ModelAttribute UserInfo user) {
		
		
		// Fullname
		byte[] fullname_bytes;
		try {
			fullname_bytes = user.getFullname().getBytes("ISO-8859-1");
			String bfullname = new String(fullname_bytes, "UTF-8");
			user.setFullname(bfullname);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// set avata
		user.setAvata("user_male.png");
		
		try {
			userService.insertUser(user);
		} catch(Exception e) {
			return "redirect:/login?register=false";
		}
		
		return "redirect:/login?register=true";
	}
}
