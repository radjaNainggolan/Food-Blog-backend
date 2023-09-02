package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ssp.dto.AppUserDto;

@Component
public class AppUserDtoRowMapper implements RowMapper<AppUserDto> {

	@Override
	public AppUserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AppUserDto appUserDto = new AppUserDto();
		appUserDto.setId(rs.getLong("id"));
		appUserDto.setFirstName(rs.getString("first_name"));
		appUserDto.setLastName(rs.getString("last_name"));
		appUserDto.setEmail(rs.getString("email"));
		appUserDto.setPassword(rs.getString("password"));
		appUserDto.setUserName(rs.getString("user_name"));
		appUserDto.setImage(null);
		appUserDto.setRecepies(null);
		appUserDto.setLikedRecepies(null);
		
		
		return appUserDto;
	}

}
