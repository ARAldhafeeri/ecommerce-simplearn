package com.org.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.ecommerce.modal.Product;
import com.org.ecommerce.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

		@Override	
		public Product getProductById(Long id) {
			return productRepository.getProductById(id);
		}
		
		@Override
		@Transactional
		public void updateProduct(Product product){
			productRepository.updateProduct(
				product.getID(),
				product.getName(),
				product.getPrice(),
				product.getDateAdded(),
				product.getCategoryId()
			);

		}
		
		@Override
		public void deleteProduct(Long id){
			productRepository.deleteProduct(id);
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
