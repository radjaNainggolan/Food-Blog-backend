package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ssp.dto.AppUserShortInfo;

public class AppUserShortInfoRowMapper implements RowMapper<AppUserShortInfo> {

	@Override
	public AppUserShortInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppUserShortInfo appUserShortInfo = new AppUserShortInfo();
		appUserShortInfo.setId(rs.getLong("id"));
		appUserShortInfo.setUserName(rs.getString("user_name"));
		appUserShortInfo.setImage(rs.getBytes("image"));
		return appUserShortInfo;
	}

}
