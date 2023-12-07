package com.org.ecommerce.service;

import java.util.List;

import com.org.ecommerce.modal.Category;

public interface CategoryService  {

	public Category getCategoryById(long id);
		
	public int updateCategory(Category category);
		
	public void deleteCategory(long id);

	public List<Category> getAllCategories();
	
	public String getCategoriesDropDown(long id);

	public Category createCategory(Category category);

}
