package com.team.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.team.entity.Product;
import com.team.entity.Types;
import com.team.persistence.ProductDAO;
import com.team.persistence.TypesDao;
import com.team.service.ProductService;
import com.team.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {
	
	@InjectMocks
	ProductService prodService;
	
	@Mock
	ProductDAO productDao;
	@Mock
	TypesDao typesDao;
	
	private AutoCloseable close;
	
	List<Product> stock;
	List<Types> types;
	
	@BeforeEach
	void setUp() {
		prodService = new ProductServiceImpl();
		
		close = MockitoAnnotations.openMocks(this);
		
		stock=new ArrayList<Product>();
		stock.add(new Product("aaa", 10, new Types("book", 12)));
		stock.add(new Product("bbb", 4, new Types("cd", 10)));
		stock.add(new Product("ccc", 8, new Types("cosmetics", 12)));
		
		types=new ArrayList<Types>();
		types.add( new Types("book", 12));
		types.add( new Types("cd", 10));
		types.add( new Types("cosmetics", 12));
		
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		prodService = null;
		close.close();
	}
	
	@Test
	void testGetProdByIdPositive() {
		when(productDao.findById("aaa")).thenReturn(Optional.of(stock.get(0)));
		assertEquals(stock.get(0), prodService.getProductById("aaa"));
	}
	
	@Test
	void testGetProdByIdNegative() {
		when(productDao.findById("ooo")).thenReturn(Optional.empty());
		assertEquals(null, prodService.getProductById("ooo"));
	}
	
	
	@Test
	void testSaveRight() {
		when(productDao.save(new Product("ddd", 10, new Types("book", 12)))).thenReturn(null);
		assertEquals(true, prodService.saveProduct(new Product("ddd", 10, new Types("book", 12))));
	}
	
	@Test
	void testSaveDuplicate() {
		when(productDao.save(new Product("aaa", 10, new Types("book", 12)))).thenThrow(new RuntimeException());
		assertEquals(false, prodService.saveProduct(new Product("aaa", 10, new Types("book", 12))));
	}
	
	@Test
	void testSaveNull() {
		when(productDao.save(null)).thenThrow(new RuntimeException());
		assertEquals(false, prodService.saveProduct(null));
	}
	
	@Test
	void testGetTypeByIdPositive() {
		when(typesDao.findById("book")).thenReturn(Optional.of(types.get(0)));
		assertEquals(types.get(0), prodService.getType("book"));
	}
	
	@Test
	void testGetTypeByIdNegative() {
		when(typesDao.findById("ooo")).thenReturn(Optional.empty());
		assertEquals(null, prodService.getType("ooo"));
	}
	
	@Test
	void testTSaveRight() {
		when(typesDao.save(new Types("tobacco", 10))).thenReturn(null);
		assertEquals(true, prodService.saveType(new Types("tobacco", 10)));
	}
	
	@Test
	void testTSaveDuplicate() {
		when(typesDao.save(new Types("tobacco", 10))).thenThrow(new RuntimeException());
		assertEquals(false, prodService.saveType(new Types("tobacco", 10)));
	}
	
}
