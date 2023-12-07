package com.org.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ecommerce.modal.Category;
import com.org.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService  {
	
	@Autowired
    CategoryRepository categoryRepository;
	
		@Override
		public Category getCategoryById(long id) {
			return categoryRepository.getCategoryById(id);
		}
		
		@Override
		public void deleteCategory(long id) {
			categoryRepository.deleteCategory(id);
			}

		@Override
		public List<Category> getAllCategories() {
			return categoryRepository.findAll();
			}
			
		@Override
		public String getCategoriesDropDown(long id) {
			StringBuilder sb = new StringBuilder("");
			List<Category> list = categoryRepository.findAll();
			for(Category cat: list) {
				if (cat.getID() == id)
					sb.append("<option value=" + String.valueOf(cat.getID()) + " selected>" + cat.getName() + "</option>");
				else
					sb.append("<option value=" + String.valueOf(cat.getID()) + ">" + cat.getName() + "</option>");
					
			}
			return sb.toString();
			}

		@Override
		public int updateCategory(Category category) {
			return categoryRepository.updCategory(category.getID(), category.getName());
		}

		@Override
		public Category createCategory(Category category) {
			return categoryRepository.save(category);
		}

}
