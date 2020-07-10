package com.spring.exam.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/profile")
	public String profile(
			@RequestParam(name = "user", required = false, defaultValue = "") String username,
			@RequestParam(name = "updated", required = false, defaultValue = "") String update,
			Model model) {
		
		if(!username.equals("")) {
			UserInfo userInfo = userService.selectUserByName(username);
			model.addAttribute("profile", userInfo);
		}
		
		if(!update.equals("")) {
			model.addAttribute("successMessage", "Saved successfully");
		}
		
		return "profile";
	}
	
	@PostMapping(value="/save-profile")
	public String updateProfile(@ModelAttribute UserInfo user) {
		userService.updateUser(user);
		return "redirect:/profile?updated=success&user=" + user.getUsername();
	}
}
