package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ssp.dto.RecepiesShortInfo;

@Component
public class RecepieShortInfoRowMapper implements RowMapper<RecepiesShortInfo> {

	@Override
	public RecepiesShortInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecepiesShortInfo info = new RecepiesShortInfo();
		info.setId(rs.getLong("id"));
		info.setName(rs.getString("name"));
		info.setImage(rs.getBytes("image"));
		info.setNumberOfLikes(null);
		return info;
	}

}
