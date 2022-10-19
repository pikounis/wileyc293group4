package com.team.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.StockItem;
import com.team.entity.User;
import com.team.service.OrderService;
import com.team.service.ProductService;
import com.team.service.ShoppingBasketService;
import com.team.service.StockItemService;

@Controller
public class StockController {

	@Autowired
	private ProductService productService;
	@Autowired
	private StockItemService stockService;
	@Autowired
	private ShoppingBasketService basketService;

	@ModelAttribute("prdTypes")
	List<String> getProductTypes() {
		return stockService.getAllStockItems().stream()
		.map(StockItem::getProduct)
		.map(Product::getProductType)
		.distinct()
		.collect(Collectors.toList());
	}

	@ModelAttribute("prdNames")
	List<String> getProducts() {
		return stockService.getAllStockItems().stream()
		.map(StockItem::getProduct)
		.map(Product::getProductName)
		.distinct()
		.collect(Collectors.toList());
	}
	
	
	@RequestMapping("/storeMenu")
	public ModelAndView getMenuAdminPageController() {
		return new ModelAndView("Menu");
	}
	
	@RequestMapping("/shopMenu")
	public ModelAndView getMenuUserPageController() {
		return new ModelAndView("ShopMenu");
	}

	@RequestMapping("/saveProductPage")
	public ModelAndView getSaveProductPage() {
		return new ModelAndView("SaveProduct", "command", new StockItem());
	}

	@RequestMapping("/saveProduct")
	public ModelAndView saveProductController(@ModelAttribute("command") StockItem stock) {

		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		
		if (productService.insertNewProduct(stock.getProduct()) && stockService.insertNewStockItem(stock)) 
				message = "Product has been added";
		else
			message = "Product has not been added";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("SaveProduct");
		return modelAndView;
	}

	@RequestMapping("/deleteByIdPage")
	public ModelAndView editProductByIdPage() {
		return new ModelAndView("DeleteProduct", "prd", new StockItem());
	}

	@RequestMapping("/deleteProduct")
	public ModelAndView searchProductController(@ModelAttribute("prd") StockItem stock) {
		ModelAndView modelAndView = new ModelAndView();

		if (stockService.deleteStockById(stock.getProduct()) && productService.deleteProductById(stock.getProduct().getProductName())) 
			modelAndView.addObject("message", "Product deleted");
		else
			modelAndView.addObject("message", "Product with id " + stock.getProduct().getProductName() +" does not exist");	
		
		modelAndView.setViewName("DeleteProduct");
		return modelAndView;
	}

	@RequestMapping("/showAllProducts")
	public ModelAndView showAllProductController() {
		ModelAndView modelAndView = new ModelAndView();
		Collection<StockItem> prdList = stockService.getAllStockItems();
		modelAndView.addObject("products", prdList);
		modelAndView.setViewName("ShowAllProducts");

		return modelAndView;
	}
	
	@RequestMapping("/shopProducts")
	public ModelAndView getShopUserPageController() {
		ModelAndView modelAndView = new ModelAndView("Shop");
		Collection<StockItem> prdList = stockService.getAllStockItems();
		modelAndView.addObject("products", prdList);
		return modelAndView;
	}
	
	@RequestMapping("/addProductToCart")
	public ModelAndView addProductToCartController(HttpServletRequest request, @SessionAttribute("user") User user) {
		
		String id = request.getParameter("id");
		int quantity = Integer.parseInt(request.getParameter("desiredQty"));
		Product addProd = productService.getProductById(id);
		ShoppingBasketItem sbItem = new ShoppingBasketItem(user, addProd.getProductName(), addProd, quantity);
		System.out.println(sbItem.toString());
		basketService.addProductToBasket(sbItem);
		
		ModelAndView modelAndView = new ModelAndView("Shop");
		Collection<StockItem> prdList = stockService.getAllStockItems();
		modelAndView.addObject("products", prdList);
		modelAndView.addObject("message", "Product chosen " + addProd.getProductName() + " qty " + quantity);
		return modelAndView;
	}
}
