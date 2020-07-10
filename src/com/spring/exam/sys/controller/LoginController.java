package com.spring.exam.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping(value="/login")
	public String loginPage(@RequestParam(required = false, name = "error", defaultValue = "false") String error,
							Model model) {
		
		String errorMessage = null;
        if(error.equals("true")) {
            errorMessage = "Username or Password is incorrect !!";
        }
		
        model.addAttribute("errorMessage", errorMessage);
		return "login";
	}
}
