package com.org.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ecommerce.modal.Product;
import com.org.ecommerce.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

		@Override	
		public Product getProductById(long id) {
			return productRepository.getProductById(id);
		}
		
		@Override
		@Transactional
		public int updateProduct(Product product){
			return productRepository.updateProduct(
				product.getID(),
				product.getName(),
				product.getPrice(),
				product.getDateAdded(),
				product.getCategoryId()
			);
		}
		
		@Override
		public int deleteProduct(long id){
			return productRepository.deleteProduct(id);
		}

		@Override
		public List<Product> getAllProducts(){
			return productRepository.findAll();
		}

		@Override
		public Product createProduct(Product product){
			return productRepository.save(product);
		}

	 		
}
