package com.org.ecommerce.service;

import java.util.List;

import com.org.ecommerce.modal.Product;

public interface ProductService {

		public Product getProductById(Long id);
		
		
		public void updateProduct(Product product);
		

		public void deleteProduct(Long id);

		public List<Product> getAllProducts();

		public Product createProduct(Product product);
	 		
}
