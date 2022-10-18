package com.team.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team.entity.Product;
import com.team.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@ModelAttribute("stkTypes")
	List<String> getDepartment() {
		return productService.getAllProducts().stream()
		.map(Product::getProductType)
		.distinct()
		.collect(Collectors.toList());
	}

	@ModelAttribute("prdNames")
	List<String> getProducts() {
		return productService.getAllNames().stream()
		.filter(product->product.getProductNames()!=null)
		.map(Product::getProductNames)
		.distinct()
		.collect(Collectors.toList());
	}
	
	@ModelAttribute("prdIds")
	List<Integer> getProductIds() {
		return productService.getAllProducts().stream()
		.map(Product::getprdId)
		.collect(Collectors.toList());
	}
	
	
	@RequestMapping("/storeMenu")
	public ModelAndView getMenuPageController() {
		return new ModelAndView("StoreMenu");
	}

	@RequestMapping("/saveProductPage")
	public ModelAndView getSaveProductPage() {
		return new ModelAndView("InputEmployeeData", "command", new Product());
	}

	@RequestMapping("/saveProduct")
	public ModelAndView saveProductController(@ModelAttribute("command") Product product) {

		ModelAndView modelAndView = new ModelAndView();

		String message = null;
		if (productService.saveProduct(product)) {
			message = "Product has been added";
		} else
			message = "Product has not been added";

		modelAndView.addObject("message", message);

		modelAndView.setViewName("Output");

		return modelAndView;
	}

	@RequestMapping("/searchByIdPage")
	public ModelAndView searchProductByIdPageController() {
		return new ModelAndView("InputEmployeeIdForSearch","prd",new Product());
	}

	@RequestMapping("/searchStock")
	public ModelAndView searchProductController(@ModelAttribute("prd") Product group4) {
		ModelAndView modelAndView = new ModelAndView();
		Product product = productService.searchProductById(group4.getProductId());

		if (product != null) {
			modelAndView.addObject("product", product);
			modelAndView.setViewName("ShowProduct");
		} else {
			modelAndView.addObject("message", "Product has the type: " + group4.getProductType() + " doesn't exist!");
			modelAndView.setViewName("Output");
		}
		return modelAndView;
	}

	@RequestMapping("/showAllProducts")
	public ModelAndView showAllProductController() {
		ModelAndView modelAndView = new ModelAndView();
		Collection<Product> prdList = productService.getAllProducts();
		modelAndView.addObject("product", prdList);
		modelAndView.setViewName("ShowAllProducts");

		return modelAndView;
	}
}
