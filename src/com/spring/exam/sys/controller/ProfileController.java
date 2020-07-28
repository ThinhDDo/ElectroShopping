package com.spring.exam.sys.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@SessionAttributes(names = {"user"})
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping(value="/profile")
	public String profile(
			@RequestParam(name = "updated", required = false, defaultValue = "") String update,
			@RequestParam(name = "avata", required = false, defaultValue = "") String avata,
			Model model) {
		
		if(!update.equals("")) {
			model.addAttribute("successMessage", "Saved successfully");
		}
		
		return "profile";
	}
	
	@PostMapping(value="/save-avata")
	public String updateAvata(
					@SessionAttribute(value = "user", required = false) UserInfo user,
					@RequestParam(value = "avata") MultipartFile file,
					RedirectAttributes redirectAttributes,
					Model model) {
		
		System.out.println("Original name: " + file.getOriginalFilename()); // *.png
		System.out.println("Name: " + file.getName()); // name from input
		System.out.println("Size: " + file.getSize()); // Size of byte
		System.out.println("Content Type: " + file.getContentType()); // image/jpeg
		String localPath = System.getProperty("user.dir") + "\\WEB-INF\\views\\img\\avata";
		System.out.println("Local path: " + localPath);
		
		// image path
		File serverFile = new File(servletContext.getRealPath("/WEB-INF/views/img/avata"), file.getOriginalFilename());
		File localFile = new File(System.getProperty("user.dir") + "\\WebContent\\WEB-INF\\views\\img\\avata", file.getOriginalFilename());
		
		// Transfer the input image to server path
		try {
			file.transferTo(serverFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Transfer the input image to server path
		try {
			file.transferTo(localFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		user.setAvata(file.getOriginalFilename());
		userService.updateUser(user);
		model.addAttribute("user", user);
		
		return "redirect:/profile?updated=true";
	}
	
	/**
	 * Save user infomation
	 * @param user
	 * @return
	 */
	@PostMapping(value="/save-profile")
	public String updateProfile(@SessionAttribute(name="user",required = true) UserInfo user,
								@ModelAttribute UserInfo newProfile, 
								Model model) {
		
		try {
			byte[] bytes_fullname = newProfile.getFullname().getBytes("ISO-8859-1");
			String bfullname = new String(bytes_fullname, "UTF-8");
			user.setFullname(bfullname);
			
			byte[] bytes_address = newProfile.getAddress().getBytes("ISO-8859-1");
			String baddress = new String(bytes_address, "UTF-8");
			user.setAddress(baddress);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userService.updateUser(user);
		model.addAttribute("user", user);
		return "redirect:/profile?updated=true";
	}
	
	@PostMapping(value="save-password")
	public String savePassword(@RequestParam(name="oldPassword", defaultValue="", required=true) String oldPassword,
							   @RequestParam(name="newPassword", defaultValue="", required=true) String newPassword,
							   Model model,
							   Authentication auth) {
		
		if(auth.isAuthenticated()) {
			User loginUser = (User) auth.getPrincipal();
			UserInfo userInfo = userService.selectUserByName(loginUser.getUsername());
			if(passwordEncoder.matches(oldPassword, userInfo.getPassword())) {
				
				// Validation
				if(newPassword.length() > 8 && newPassword.matches("(.*)[!@#$%^&*](.*)")) {
					userInfo.setPassword(newPassword);
					userService.updatePassword(userInfo);
				} else {
					model.addAttribute("passwordError", "Đổi password thất bại");
				}
			} else {
				model.addAttribute("passwordSuccess", "Đổi password thành công");
			}
		}
		
		System.out.println("old: " + oldPassword);
		System.out.println("new: " + newPassword);
		return "redirect:/";
	}
}
