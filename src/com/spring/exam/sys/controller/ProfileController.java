package com.spring.exam.sys.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	private static final String uploadLocation = System.getProperty("user.dir") + "\\WebContent\\WEB-INF\\views\\img\\avata\\";
	
	@GetMapping(value="/profile")
	public String profile(
			@SessionAttribute("loginUser") User user,
			@RequestParam(name = "user", required = false, defaultValue = "") String username,
			@RequestParam(name = "updated", required = false, defaultValue = "") String update,
			@RequestParam(name = "avata", required = false, defaultValue = "") String avata,
			Model model) {
		
		if(!username.equals("")) {
			UserInfo userInfo = userService.selectUserByName(user.getUsername());
			model.addAttribute("profile", userInfo);
		}
		
		if(!update.equals("")) {
			model.addAttribute("successMessage", "Saved successfully");
		}
		
		return "profile";
	}
	
	@PostMapping(value="/save-avata")
	public RedirectView updateAvata(
					@RequestParam(value = "username", required = false) String username,
					@RequestParam(value = "file") MultipartFile[] file,
					RedirectAttributes redirectAttributes,
					Model model) {
		
		Path fileNameAndPath = Paths.get(uploadLocation, file[0].getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file[0].getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String avata = file[0].getOriginalFilename();
		redirectAttributes.addAttribute("avata", avata);
		redirectAttributes.addAttribute("user", username);
		
		UserInfo user = userService.selectUserByName(username);
		
		File f = new File(uploadLocation  + user.getAvata()); 
		if(f.exists()) {
			f.delete();
		}
		
		user.setAvata(avata);
		userService.updateUser(user);
		
		return new RedirectView("profile");
	}
	
	@PostMapping(value="/save-profile")
	public String updateProfile(@ModelAttribute UserInfo user) {
		userService.updateUser(user);
		return "redirect:/profile?updated=success&user=" + user.getUsername();
	}
}
