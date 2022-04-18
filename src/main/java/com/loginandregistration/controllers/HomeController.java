package com.loginandregistration.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginandregistration.models.User;
import com.loginandregistration.repositories.UserRepository;
import com.loginandregistration.services.UserServices;
import com.loginandregistration.validators.UserValidator;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserServices service;
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired
	private UserValidator validator;
	

	
	@GetMapping("/")
	public String registerForm(@ModelAttribute ("user")User user) {
		return "index.jsp";
	}
	
	@GetMapping("/welcome")
	public String welcome(@ModelAttribute ("user")User user) {
		return "dashboard.jsp";
	}
	
	@PostMapping("/register")
	public String registerValidator(
			@Valid @ModelAttribute("user")User user, 
			BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		this.validator.validate(user, result);
	
		if( result.hasErrors()) return "index.jsp";
		
		this.service.create(user);
		
		redirectAttributes.addFlashAttribute("message" , "Thank you for registering!");
		
		return "redirect:/welcome";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute ("user")User user,BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpSession session, 
			Model model  
			) {
		user = this.service.authenticate(user);
		if ( user!= null) {
			
			session.setAttribute("user", user.getId());
			
			redirectAttributes.addFlashAttribute("message", String.format("Welcome back %s!", user.getEmail()));
			
			return"redirect:/welcome";
		}
		model.addAttribute("message", "Invalid Credentials");
		return "login.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	 
		// Set userId to null and redirect to login/register page
		session.setAttribute("userId", null);
	     
	    return "redirect:/";
	}
	

}
