package ssp.dto.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ssp.dto.CountryDto;

@Component
public class CountryDtoRowMapper implements RowMapper<CountryDto> {

	@Override
	public CountryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		CountryDto countryDto = new CountryDto();
		countryDto.setId(rs.getLong("id"));
		countryDto.setName(rs.getString("name"));
		return countryDto;
	}

}
