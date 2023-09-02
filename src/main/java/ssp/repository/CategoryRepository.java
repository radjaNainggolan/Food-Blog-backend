package ssp.repository;

import java.util.List;

import ssp.dto.CategoryDto;
import ssp.dto.RecepiesShortInfo;
import ssp.model.Category;

public interface CategoryRepository {

	public List<RecepiesShortInfo> getRecepiesOfCategory();
	
	public List<CategoryDto> getAllCategories();
	
	public CategoryDto getCategoryById(Long categoryId);
	
	public int updateCategoryById(Long categoryId, Category category);
	
	public int deleteCategoryById(Long categoryId);
	
	
}
