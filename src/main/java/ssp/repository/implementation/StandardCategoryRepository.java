package ssp.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssp.dto.CategoryDto;
import ssp.dto.RecepiesShortInfo;
import ssp.dto.row_mapper.CategoryDtoRowMapper;
import ssp.dto.row_mapper.RecepieShortInfoRowMapper;
import ssp.model.Category;
import ssp.repository.AppUserRepository;
import ssp.repository.CategoryRepository;

@Repository
public class StandardCategoryRepository implements CategoryRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	RecepieShortInfoRowMapper shortInfoRowMapper;
	
	@Autowired
	CategoryDtoRowMapper categoryDtoRowMapper;
	
	private final String getRecepiesOfCategory = """
			SELECT DISTINCT r.id, r.name, ri.image
			FROM
				recepie as r
				inner join
				recepie_image as ri
				on ri.recepie_id = r.id
				inner join 
				category as c
				on c.id = r.category_id
			WHERE c.id = ?
			""";
	
	private final String getAllCatogories = """
			SELECT * FROM category
			""";
	
	private final String getCategoryById = """
			
			SELECT * FROM category WHERE id = ?
			
			""";
	
	private final String updateCategory = """
			UPDATE category set name = ? WHERE id = ?
			""";
	
	private final String deleteCategoryById = """
			
			DELETE FROM category WHERE id = ?
			""";
	
	private final String numberOfCateogiresById = """
			SELECT COUNT(*) FROM category WHERE id = ?
			""";
	
	private final String createCategory = """
			INSERT INTO category(name) VALUES (?)
			""";
	
	@Override
	public List<RecepiesShortInfo> getRecepiesOfCategory(Long categoryId) {
		
		List<RecepiesShortInfo> recepiesShortInfos = this.jdbcTemplate.query(getRecepiesOfCategory, shortInfoRowMapper, categoryId);
		
		for(RecepiesShortInfo info : recepiesShortInfos) {
			int numOfLikes = this.appUserRepository.numberOfLikesOfRecepieById(info.getId());
			info.setNumberOfLikes(numOfLikes);
		}
		
		return recepiesShortInfos;
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
	
		List<CategoryDto>  list = this.jdbcTemplate.query(getAllCatogories, categoryDtoRowMapper);
		
		for(CategoryDto dto : list) {
			List<RecepiesShortInfo> shortInfos = this.getRecepiesOfCategory(dto.getId());
			for(RecepiesShortInfo info : shortInfos) {
				int numOfLikes = this.appUserRepository.numberOfLikesOfRecepieById(info.getId());
				info.setNumberOfLikes(numOfLikes);
			}
			dto.setRecepies(shortInfos);
		}
		
		return list;
		
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		
		CategoryDto categoryDto = this.jdbcTemplate.queryForObject(getCategoryById, categoryDtoRowMapper, categoryId);
		List<RecepiesShortInfo>  recepiesShortInfos = this.getRecepiesOfCategory(categoryId);
		for(RecepiesShortInfo info : recepiesShortInfos) {
			int numOfLikes = this.appUserRepository.numberOfLikesOfRecepieById(info.getId());
			info.setNumberOfLikes(numOfLikes);
		}
		categoryDto.setRecepies(recepiesShortInfos);
		
		return categoryDto;
		
		
	}

	@Override
	public int updateCategoryById(Long categoryId, Category category) {
		
		int res = this.jdbcTemplate.update(updateCategory, category.getName(), categoryId);
		return res;
		
		
	}

	@Override
	public int deleteCategoryById(Long categoryId) {
		int res = this.jdbcTemplate.update(deleteCategoryById, categoryId);
		return res;
	}

	@Override
	public int numberOfCategoriesById(Long categoryId) {
		int res = this.jdbcTemplate.queryForObject(numberOfCateogiresById, Integer.class, categoryId);
		return res;
	}

	@Override
	public Category createCategory(Category category) {
		int res = this.jdbcTemplate.update(createCategory, category.getName());
		Long res1 = this.jdbcTemplate.queryForObject("SELECT id FROM category where name = ?", Long.class ,category.getName());
		category.setId(res1);
		return category;
		
	}

}
