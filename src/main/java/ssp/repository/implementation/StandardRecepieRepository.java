package ssp.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssp.dto.AppUserShortInfo;
import ssp.dto.CommentDto;
import ssp.dto.ImageDto;
import ssp.dto.RecepieDto;
import ssp.dto.row_mapper.CommentDtoRowMapper;
import ssp.dto.row_mapper.ImageDtoRowMapper;
import ssp.model.Recepie;
import ssp.repository.RecepieRepository;

@Repository
public class StandardRecepieRepository implements RecepieRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ImageDtoRowMapper imageDtoRowMapper;
	
	@Autowired
	CommentDtoRowMapper commentDtoRowMapper;
	
	private final String createRecepie = 
			"""
			INSERT INTO recepie(
			ingredients, name, prep_time, text, app_user_id, category_id, country_id)
			VALUES (?, ?, ?, ?, ?, ?, ?);	
			""";
	
	private final String getRecepiesImagesById = 
			"""
			SELECT ri.id, ri.image
			FROM
				recepie as r
				inner join
				recepie_image as ri
				on ri.recepie_id = r.id
			WHERE r.id = ?	
			""";
	
	private final String getRecepiesComments = 
			"""
			SELECT au.user_name, aui.image, rc.text
			FROM
				recepie as r
				inner join
				recepie_comment as rc
				on rc.recepie_id = r.id
				inner join 
				app_user as au
				on au.id = rc.app_user_id
				inner join 
				app_user_image as aui
				on aui.app_user_id = au.id
			WHERE r.id = ?	
			""";
	
	private final String deleteRecpie =
			"""
			DELETE FROM recepie WHERE id = ?	
			""";
	
	private final String updateRecepie =
			"""
			UPDATE recepie SET ingredients = ?, name = ?, prep_time = ?, text = ?, app_user_id = ?, category_id = ?, country_id = ?	
			""";
	
	@Override
	public Recepie createRecepie(Recepie recepie) {
		
		int res = this.jdbcTemplate.update(createRecepie,
				recepie.getIngredients(),
				recepie.getName(),
				recepie.getPrepTime(),
				recepie.getText(),
				recepie.getAppUserId(),
				recepie.getCategoryId(),
				recepie.getCountryId());
		
		long id = this.jdbcTemplate.queryForObject("SELECT id FROM recepie WHERE name = ?", 
				Long.class, 
				recepie.getName());
		
		recepie.setId(id);
		
		return recepie;
	}

	@Override
	public List<ImageDto> getRecepieImagesById(Long recepieId) {
		List<ImageDto> images = this.jdbcTemplate.query(getRecepiesImagesById, imageDtoRowMapper, recepieId);
		return images;
	}

	@Override
	public List<CommentDto> getRecepieCommentsById(Long recepieId) {
		List<CommentDto> comments = this.jdbcTemplate.query(getRecepiesComments, commentDtoRowMapper, recepieId);
		return comments;
	}

	

	@Override
	public List<RecepieDto> getAllRecepies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecepieDto getRecepieById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRecepieById(Long id, Recepie recepie) {
		int res = this.jdbcTemplate.update(updateRecepie, 
				recepie.getIngredients(), 
				recepie.getName(),
				recepie.getPrepTime(),
				recepie.getText(),
				recepie.getAppUserId(),
				recepie.getCategoryId(),
				recepie.getCountryId());
		return res;
	}

	@Override
	public int deleteRecepieById(Long recepieId) {
		int res = this.jdbcTemplate.update(deleteRecpie, recepieId);
		return res;
	}

	@Override
	public AppUserShortInfo getAppUserShortInfo(Long recepieId) {
		// TODO Auto-generated method stub
		return null;
	}

}
