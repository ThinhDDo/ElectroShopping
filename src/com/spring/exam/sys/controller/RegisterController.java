package com.spring.exam.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		try {
			userService.insertUser(user);	
		} catch(Exception e) {
			return "redirect:/login?register=false";
		}
		
		return "redirect:/login?register=true";
	}
}
