package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ssp.dto.CommentDto;

public class CommentDtoRowMapper implements RowMapper<CommentDto>{

	@Override
	public CommentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CommentDto commentDto = new CommentDto();
		commentDto.setAppUserUserName(rs.getString("user_name"));
		commentDto.setText(rs.getString("text"));
		commentDto.setImage(rs.getBytes("image"));
		
		return commentDto;
	}

}
