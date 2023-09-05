package ssp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ssp.model.Category;

public interface CategoryController {

	public ResponseEntity<?> getRecepiesOfCategory(@PathVariable("categoryId") Long categoryId);
	
	public ResponseEntity<?> getAllCategories();
	
	public ResponseEntity<?> getCategoryById(@PathVariable("categorId") Long categoryId);
	
	public ResponseEntity<?> updateCategoryById(@PathVariable("categorId") Long categoryId, @RequestBody  Category category);
	
	public ResponseEntity<?> deleteCategoryById(@PathVariable("categorId") Long categoryId);
	
	public ResponseEntity<?> createCategory(@RequestBody Category category);

}
