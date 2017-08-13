package com.prounited.billingapp.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Category;

@Service("categoryService")
public interface CategoryService {

	String addCategory(String categoryJsonString);
	String editCategory(String categoryJsonString);
	String deleteCategory(String categoryJsonString);
	String getAllCategories(String sortMap, String filterMap, String query, int page, int limit);
	public List<Category> getAllCategories();
}
