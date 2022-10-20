package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.team.entity.User;
import com.team.service.UserService;

@Controller
@SessionAttributes({"user"})
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public ModelAndView getLoginPage() {
		return new ModelAndView("Login","command",new User());
	}
	
	@RequestMapping("/loginUser")
	public ModelAndView loginCheck(@ModelAttribute("command") User user) {
		ModelAndView modelAndView=new ModelAndView();
		User loginUser = userService.loginUser(user);
		
		if(loginUser == null) {
			modelAndView.addObject("message", "Username or password error");
			modelAndView.setViewName("Login");
		}
		else if (loginUser.isAdmin()) {
			modelAndView.addObject("user", loginUser);
			modelAndView.setViewName("Menu");
		}
		else {
			modelAndView.addObject("user", loginUser);
			modelAndView.setViewName("ShopMenu");
		}
		return modelAndView;
	}
	
	@RequestMapping("/LogoutOfSession")
	public ModelAndView getLogoutPage(@ModelAttribute("command") User user) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("Login");
		modelAndView.addObject("message", "Logout Successful!");
		return modelAndView;
	}
	
	@RequestMapping("/register")
	public ModelAndView getRegisterPage() {
		return new ModelAndView("Register","command",new User());
	}
	
	@RequestMapping("/registerUser")
	public ModelAndView registerCheck(@ModelAttribute("command") User user) {
		ModelAndView modelAndView=new ModelAndView();
		user.setAdmin(false);
		boolean success = userService.registerUser(user);
		
		if(success) {
			modelAndView.addObject("message", "Registration successful!");
			modelAndView.setViewName("Login");
		}
		else {
			modelAndView.addObject("message", "Registration not successful. Try a different username.");
			modelAndView.setViewName("Register");
		}
		return modelAndView;
	}
	
	@RequestMapping("/setAdmin")
	public ModelAndView makeAdmin(@ModelAttribute("user") User user) {
		userService.setAdmin(user, true);
		return new ModelAndView("ShopMenu");
	}
	
	@RequestMapping("/checkout")
	public ModelAndView getMenuUserPageController() {
		return new ModelAndView("checkout");
	}
	
	
	
	
}
