package ssp.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssp.controller.CategoryController;
import ssp.model.Category;
import ssp.service.CategoryService;

@RestController
@RequestMapping("/api")
public class StandardCategoryController implements CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories/{categoryId}/recepies")
	@Override
	public ResponseEntity<?> getRecepiesOfCategory(@PathVariable("categoryId") Long categoryId) {
		return this.categoryService.getRecepiesOfCategory(categoryId);
	}

	@GetMapping("/categories")
	@Override
	public ResponseEntity<?> getAllCategories() {
		return this.categoryService.getAllCategories();
	}

	@GetMapping("/categories/{categoryId}")
	@Override
	public ResponseEntity<?> getCategoryById(@PathVariable("categoryId") Long categoryId) {
		return this.categoryService.getCategoryById(categoryId);
	}

	@PatchMapping("/categories/{categoryId}")
	@Override
	public ResponseEntity<?> updateCategoryById(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
		return this.categoryService.updateCategoryById(categoryId, category);
	}

	@DeleteMapping("/categories/{categoryId}")
	@Override
	public ResponseEntity<?> deleteCategoryById(@PathVariable("categoryId") Long categoryId) {
		return this.categoryService.deleteCategoryById(categoryId);
	}

	@PostMapping("/categories/category")
	@Override
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
		return this.categoryService.createCategory(category);
	}

}
