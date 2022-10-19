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
import com.team.entity.User;
import com.team.service.OrderService;
import com.team.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;

	@ModelAttribute("prdTypes")
	List<String> getProductTypes() {
		return productService.getAllProducts().stream()
		.map(Product::getProductType)
		.distinct()
		.collect(Collectors.toList());
	}

	@ModelAttribute("prdNames")
	List<String> getProducts() {
		return productService.getAllProducts().stream()
		.map(Product::getProductName)
		.distinct()
		.collect(Collectors.toList());
	}
	
	@ModelAttribute("prdIds")
	List<Integer> getProductIds() {
		return productService.getAllProducts().stream()
		.map(Product::getProductId)
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
		return new ModelAndView("SaveProduct", "command", new Product());
	}

	@RequestMapping("/saveProduct")
	public ModelAndView saveProductController(@ModelAttribute("command") Product product) {

		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		
		if (productService.insertNewProduct(product)) {
			message = "Product has been added";
		} else
			message = "Product has not been added";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("SaveProduct");
		return modelAndView;
	}

	@RequestMapping("/deleteByIdPage")
	public ModelAndView editProductByIdPage() {
		return new ModelAndView("DeleteProduct", "prd", new Product());
	}

	@RequestMapping("/deleteProduct")
	public ModelAndView searchProductController(@ModelAttribute("prd") Product product) {
		ModelAndView modelAndView = new ModelAndView();

		if (productService.deleteProductById(product.getProductId())) {
			modelAndView.addObject("message", "Product deleted");
		} else {
			modelAndView.addObject("message", "Product with id " + product.getProductId() +" does not exist");	
		}
		
		modelAndView.setViewName("DeleteProduct");
		return modelAndView;
	}

	@RequestMapping("/showAllProducts")
	public ModelAndView showAllProductController() {
		ModelAndView modelAndView = new ModelAndView();
		Collection<Product> prdList = productService.getAllProducts();
		modelAndView.addObject("products", prdList);
		modelAndView.setViewName("ShowAllProducts");

		return modelAndView;
	}
	
	@RequestMapping("/shopProducts")
	public ModelAndView getShopUserPageController() {
		ModelAndView modelAndView = new ModelAndView("Shop");
		Collection<Product> prdList = productService.getAllProducts();
		modelAndView.addObject("products", prdList);
		return modelAndView;
	}
	
	@RequestMapping("/addProductToCart")
	public ModelAndView addProductToCartController(HttpServletRequest request, @SessionAttribute("user") User user) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("desiredQty"));
		Product addProd = productService.getProductById(id);
		orderService.addProductToOrder(user, addProd);
		
		ModelAndView modelAndView = new ModelAndView("Shop");
		Collection<Product> prdList = productService.getAllProducts();
		modelAndView.addObject("products", prdList);
		modelAndView.addObject("message", "Product chosen " + addProd.getProductName() + " qty " + quantity);
		return modelAndView;
	}
}
