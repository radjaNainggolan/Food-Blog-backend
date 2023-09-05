package ssp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ssp.dto.CategoryDto;
import ssp.dto.RecepiesShortInfo;
import ssp.model.Category;
import ssp.repository.CategoryRepository;
import ssp.service.CategoryService;

@Service
public class StandardCategoryService implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Override
	public ResponseEntity<?> getRecepiesOfCategory(Long categoryId) {
		
		int res = this.categoryRepository.numberOfCategoriesById(categoryId);
		
		if(res != 0) {
			List<RecepiesShortInfo> infos = this.categoryRepository.getRecepiesOfCategory(categoryId);
			return new ResponseEntity<List<RecepiesShortInfo>>(infos, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Categroy with given id does not exists",HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<?> getAllCategories() {
		List<CategoryDto> list = this.categoryRepository.getAllCategories();
	
		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.OK);
	}
	
	

	@Override
	public ResponseEntity<?> getCategoryById(Long categoryId) {
		int res = this.categoryRepository.numberOfCategoriesById(categoryId);
		
		if(res != 0) {
			CategoryDto categoryDto = this.categoryRepository.getCategoryById(categoryId);
			return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Category with given id does not exists", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> updateCategoryById(Long categoryId, Category category) {
		int res = this.categoryRepository.numberOfCategoriesById(categoryId);
		
		if(res != 0) {
			int res1 = this.categoryRepository.updateCategoryById(categoryId, category);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Category with given id does not exists", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> deleteCategoryById(Long categoryId) {
		int res = this.categoryRepository.numberOfCategoriesById(categoryId);
		
		if(res != 0) {
			int res1 = this.categoryRepository.deleteCategoryById(categoryId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Category with given id does not exists", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> createCategory(Category category) {
		Category res = this.categoryRepository.createCategory(category);
		return new ResponseEntity<Category>(res, HttpStatus.CREATED);
	}

}
