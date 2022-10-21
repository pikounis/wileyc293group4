package com.team.controller;

import java.util.ArrayList;
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

import com.team.entity.DisplayItem;
import com.team.entity.DisplayOrder;
import com.team.entity.OrderItem;
import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.StockItem;
import com.team.entity.Types;
import com.team.entity.User;
import com.team.exceptions.NegativeInputException;
import com.team.exceptions.OutOfStockException;
import com.team.service.OrderService;
import com.team.service.ProductService;
import com.team.service.ShoppingBasketService;
import com.team.service.StockItemService;
import com.team.service.UserService;

@Controller
public class StockController {

	@Autowired
	private ProductService productService;
	@Autowired
	private StockItemService stockService;
	@Autowired
	private ShoppingBasketService basketService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@ModelAttribute("prdTypes")
	List<String> getProductTypes() {
		return productService.getAllTypes().stream()
		.map(Types::getType)
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
		stock.setProductName(stock.getProduct().getProductName());
		System.out.println(stock.getProduct().toString());
		if (productService.saveProduct(stock.getProduct()) && stockService.insertNewStockItem(stock)) 
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

		if (stockService.deleteStockById(stock.getProduct().getProductName())) 
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
		
		ModelAndView modelAndView = new ModelAndView("Shop");
		String id = request.getParameter("id");
		int quantity = Integer.parseInt(request.getParameter("desiredQty"));
		Product addProd = productService.getProductById(id);
		ShoppingBasketItem sbItem = new ShoppingBasketItem(user, addProd, quantity);
		try {
			basketService.addProductToBasket(sbItem);
			modelAndView.addObject("message", "Product chosen " + addProd.getProductName() + " qty " + quantity);
		} catch (NegativeInputException e) {
			modelAndView.addObject("message", "Product quantity must be greater than 0");
		} catch (OutOfStockException e) {
			modelAndView.addObject("message", "Product quantity out of stock");
		}
		
		
		Collection<StockItem> prdList = stockService.getAllStockItems();
		modelAndView.addObject("products", prdList);
		
		return modelAndView;
	}
	
	@RequestMapping("/saveTypePage")
	public ModelAndView getNewTypePage() {
		ModelAndView modelAndView = new ModelAndView("AddType");
		modelAndView.addObject("command", new Types());
		return modelAndView;
	}
	
	@RequestMapping("/saveType")
	public ModelAndView saveNewType(@ModelAttribute("command") Types type) {
		ModelAndView modelAndView = new ModelAndView("AddType");
		if(productService.saveType(type))
			modelAndView.addObject("message", "Type added!");
		else
			modelAndView.addObject("message", "Type not added");
		modelAndView.addObject("command", new Types());
		return modelAndView;
	}
	
	@RequestMapping("/updateTypePage")
	public ModelAndView getChangeTypePage() {
		ModelAndView modelAndView = new ModelAndView("ChangeTypeTaxes");
		modelAndView.addObject("command", new Types());
		return modelAndView;
	}
	
	@RequestMapping("/updateType")
	public ModelAndView saveChangeType(@ModelAttribute("command") Types type) {
		ModelAndView modelAndView = new ModelAndView("ChangeTypeTaxes");
		if(productService.saveType(type))
			modelAndView.addObject("message", "Type edited!");
		else
			modelAndView.addObject("message", "Type not edited");
		modelAndView.addObject("command", new Types());
		return modelAndView;
	}
	
	@RequestMapping("/checkout")
	public ModelAndView getCheckoutController(@SessionAttribute("user") User user){
		ModelAndView view = new ModelAndView();
		Collection<ShoppingBasketItem> listItems = basketService.getShoppingBasket(user);
		try {
			if(orderService.purchaseBasket(user, listItems)) {
				userService.updateUserLastOrder(user);
				basketService.emptyBasket(user);

				Collection<DisplayItem> display = listItems.stream()
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
				view.setViewName("checkout");
			} else {
				view.addObject("message", "there was a problem with the order");
				view.setViewName("ShoppingCart");
			}
		} catch (OutOfStockException e) {
			e.printStackTrace();
			view.addObject("message", "there was a problem with the order");
			view.setViewName("ShoppingCart");
		}
		return view;
	}
	
	@RequestMapping("/removeProdFromCart")
	public ModelAndView deleteFromCartController(@SessionAttribute("user") User user, HttpServletRequest request){
		ModelAndView view = new ModelAndView("ShoppingCart");
		
		String name = request.getParameter("id");
		Product prod = productService.getProductById(name);
		if (prod!=null && basketService.removeProductfromBasket(user, prod)) {
			view.addObject("message", "Product removed!");
		} else {
			view.addObject("message", "There was a problem removing the product");
		}
		
		Collection<ShoppingBasketItem> prodList = basketService.getShoppingBasket(user);
		
		if(!prodList.isEmpty()) {
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
		}
		return view;
		
	}	
	
	@RequestMapping("/seeCart")
	public ModelAndView getCartPage(@SessionAttribute("user") User user) {
		ModelAndView view = new ModelAndView("ShoppingCart");
		Collection<ShoppingBasketItem> prodList = basketService.getShoppingBasket(user);
		
		if(!prodList.isEmpty()) {
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
		}
		return view;
	}
	
	@RequestMapping("/orderHistory")
	public ModelAndView orderHistory(@SessionAttribute("user") User user) {
		ModelAndView view = new  ModelAndView("orderHistory");
		int lastOrder = user.getOrderNumber();
		Collection<DisplayOrder> coll = new ArrayList<DisplayOrder>();
		
		for (int i=0; i<lastOrder; i++) {
			Collection<OrderItem> prodList = orderService.getOrderNFromIt(user, i);
			
			if(!prodList.isEmpty()) {
				DisplayOrder dord = new DisplayOrder();
				dord.setProdList(prodList.stream()
						.map(item -> {
							double price = item.getProduct().getProductPrice();
							double tax = item.getProduct().getProductType().getTax();
							int qty = item.getQuantity();
							double totalPrice = (price*qty)+(tax/100)*qty*price;
							return new DisplayItem(item.getProduct(), qty, item.getProduct().getProductType().getType(), price, tax, totalPrice);
						}).toList());
				
				double wholePrice = dord.getProdList().stream()
					.map(DisplayItem::getWholePrice)
					.reduce((a, b) -> a + b).orElse(null);
				dord.setTotal(wholePrice);
				coll.add(dord);
			}
			
		}
		if (!coll.isEmpty()) {
			view.addObject("ordlist", coll);
		}
		return view;
	}
	
	@RequestMapping("/emptyCart")
	public ModelAndView emptyBasket(@SessionAttribute("user") User user) {
		ModelAndView view = new ModelAndView("ShoppingCart");
		if(basketService.emptyBasket(user)) {
			view.addObject("message", "All products removed!");
		} else {
			view.addObject("message", "There was a problem removing the products");
		}
		
		Collection<ShoppingBasketItem> prodList = basketService.getShoppingBasket(user);
		
		if(!prodList.isEmpty()) {
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
		}
		return view;
		
	}
}
