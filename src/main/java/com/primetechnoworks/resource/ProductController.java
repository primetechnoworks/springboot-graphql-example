package com.primetechnoworks.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.primetechnoworks.entity.Product;
import com.primetechnoworks.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@QueryMapping
	public List<Product> getProducts() {
		return service.getProducts();
	}
	
	@QueryMapping
	public List<Product> getProductsByCategory(@Argument String category) {
		return service.getProductsByCategory(category);
	}
	
	@MutationMapping
	public Product updateStock(@Argument int id, @Argument int stock) {
		return service.updateStock(id, stock);
	}
	
	@MutationMapping
	public Product updateStockQuantity(@Argument int id, @Argument int quantity) {
		return service.updateStockQuantity(id, quantity);
	}
}
