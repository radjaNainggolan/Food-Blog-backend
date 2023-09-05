package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ssp.dto.CategoryDto;

@Component
public class CategoryDtoRowMapper implements RowMapper<CategoryDto> {

	@Override
	public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryDto category = new CategoryDto(rs.getLong("id"), rs.getString("name"),null);
		return category;
	}

}
