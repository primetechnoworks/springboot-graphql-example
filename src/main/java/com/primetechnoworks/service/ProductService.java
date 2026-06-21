package com.primetechnoworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primetechnoworks.entity.Product;
import com.primetechnoworks.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public List<Product> getProducts() {
		return repository.findAll();
	}
	
	public List<Product> getProductsByCategory(String category) {
		return repository.findByCategory(category);
	}

	public Product updateStock(Integer id, Integer stock) {
		if (stock < 0) {
			throw new IllegalArgumentException("stock must not be negative");
		}

		Product existingProduct = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
		existingProduct.setStock(stock);
		return repository.save(existingProduct);
	}

	public Product updateStockQuantity(Integer id, Integer quantity) {
		Product existingProduct = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		Integer currentStock = existingProduct.getStock();
		if (currentStock == null) {
			currentStock = 0;
		}
		Integer newStock = currentStock + quantity;
		if (newStock < 0) {
			throw new IllegalArgumentException("resulting stock must not be negative");
		}
		existingProduct.setStock(newStock);
		return repository.save(existingProduct);
	}

}
