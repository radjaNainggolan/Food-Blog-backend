package ssp.service;

import org.springframework.http.ResponseEntity;

import ssp.model.Category;

public interface CategoryService {

	
	public ResponseEntity<?> getRecepiesOfCategory(Long categoryId);
	
	public ResponseEntity<?> getAllCategories();
	
	public ResponseEntity<?> getCategoryById(Long categoryId);
	
	public ResponseEntity<?> updateCategoryById(Long categoryId, Category category);
	
	public ResponseEntity<?> deleteCategoryById(Long categoryId);
	
	public ResponseEntity<?> createCategory(Category category);
}
