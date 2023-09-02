package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ssp.dto.ImageDto;

@Component
public class ImageDtoRowMapper implements RowMapper<ImageDto> {

	@Override
	public ImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ImageDto(rs.getLong("app_user_id"), rs.getBytes("image"));
	}

}
