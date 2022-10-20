package com.team.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.team.entity.DisplayItem;
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
		
		Collection<DisplayItem> display = prodList.stream()
				.map(item -> {
					double price = item.getProduct().getProductPrice();
					double tax = item.getProduct().getProductType().getTax();
					int qty = item.getQuantity();
					double totalPrice = (price*qty)+(tax/100)*qty*price;
					return new DisplayItem(item.getProduct(), qty, item.getProduct().getProductType().getType(), price, tax, totalPrice);
				}).toList();
		double wholePrice = display.stream()
			.map(DisplayItem::getWholePrice)
			.reduce((a, b) -> a + b).orElse(null);
				
		view.addObject("total", wholePrice);
		view.addObject("products", display);
		return view;
	}
	
	
	

}
