package com.team.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.service.ShoppingBasketService;

@Controller
public class ShoppingBasketController {
	
	@Autowired
	ShoppingBasketService basketService;
	
	@RequestMapping("/seeCart")
	public ModelAndView getCartPage(@SessionAttribute("user") User user) {
		ModelAndView view = new ModelAndView("ShoppingCart");
		Collection<ShoppingBasketItem> prodList = basketService.getShoppingBasket(user);
		view.addObject("products", prodList);
		return view;
	}
	
	
	

}
