package com.org.ecommerce.service;

import java.util.List;

import com.org.ecommerce.modal.Product;

public interface ProductService {

		public Product getProductById(long id);
		
		
		public int updateProduct(Product product);
		

		public int deleteProduct(long id);

		public List<Product> getAllProducts();
	 		
}
